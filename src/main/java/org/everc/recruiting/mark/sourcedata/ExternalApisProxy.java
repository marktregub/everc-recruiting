package org.everc.recruiting.mark.sourcedata;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.everc.recruiting.mark.ExternalApiCfg;

import org.everc.recruiting.mark.entities.Product;
import org.everc.recruiting.mark.entities.TransmitStats;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExternalApisProxy {
    private static final int MAX_THREADS_NUMBER = 1000;
    private final Map<ExternalApiCfg.EXTERNAL_API_TYPE, String> externalApisCfg;
    private OkHttpClient httpClient = new OkHttpClient.Builder().build();
    private ObjectMapper objectMapper = new ObjectMapper();

    public ExternalApisProxy(Map<ExternalApiCfg.EXTERNAL_API_TYPE, String> externalApisCfg) {
        this.externalApisCfg = externalApisCfg;
    }

    public TransmitStats executeTransmit(List<Product> products) {
        Map<String, Integer> batchCounters = new ConcurrentHashMap<>();
        for(Product product: products) {
            Integer countForBatch = batchCounters.get(product.getBatchId());
            batchCounters.put(product.getBatchId(), countForBatch == null ? 1 : countForBatch+1);
        }
        TransmitStats stats = new TransmitStats();

        for(Product product: products) {
            String transmitUrl = externalApisCfg.get(ExternalApiCfg.EXTERNAL_API_TYPE.TRANSMIT);
            String batchUrl = externalApisCfg.get(ExternalApiCfg.EXTERNAL_API_TYPE.BATCH);
            Response response = executePostRequest(transmitUrl, product.toJson());
            System.out.println(response.code() + " -- Product transmitted " + product.toJson());
            stats.incrementTransmits();
            response.close();

            Integer countForBatch = batchCounters.get(product.getBatchId());
            if (countForBatch > 1) {
                batchCounters.put(product.getBatchId(), countForBatch - 1);
            } else {
                response = executePostRequest(batchUrl,
                        new JSONObject(
                                new HashMap<String, String>() {{
                                    put("completedBatch", product.getBatchId());
                                }}));
                response.close();
                System.out.println("Batch completed " + product.getBatchId());
                stats.incrementBatchesNumber();
            }
        }
        return stats;
    }

    public void executeEnrichment(List<Product> products) {
        executeConcurrently(products, product -> {
            String url = externalApisCfg.get(ExternalApiCfg.EXTERNAL_API_TYPE.ENRICHMENT);
            Response response = executePostRequest(url, product.toJson());

            try {
                Map<String, String> responseJson = objectMapper.readValue(response.body().string(), Map.class);
                System.out.println(response + " -- Product after enrichment " + product.toString() );
                product.enrich(responseJson);
            } catch(IOException e) {
                throw new RuntimeException("Failure fetching response " + url, e);
            } finally {
                response.close();
            }
            return null;
        });
    }

    public void executeConcurrently(List<Product> products, Function<Product, Void> singleCall ) {
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(MAX_THREADS_NUMBER, products.size()/10));

        List<CompletableFuture> futures = products.stream()
                .map(product -> CompletableFuture.runAsync(() -> singleCall.apply(product), executor))
                .collect(Collectors.toList());

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Got interrupted during enrichment", e);
        } catch (Exception e) {
            throw new RuntimeException("Got exception during enrichment", e);
        }
    }

    private Response executePostRequest(String url, JSONObject body) {
        MediaType MediaType_JSON = MediaType.parse("application/json; charset=utf-8");

        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType_JSON, body.toJSONString()));
        try {
            return httpClient.newCall(builder.build()).execute();
        } catch(IOException e) {
            throw new RuntimeException("Failure executing POST request to " + url);
        }
    }

}
