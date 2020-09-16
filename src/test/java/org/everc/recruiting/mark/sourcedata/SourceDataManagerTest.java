package org.everc.recruiting.mark.sourcedata;

import org.everc.recruiting.mark.ExternalApiCfg;
import org.everc.recruiting.mark.entities.Product;
import org.everc.recruiting.mark.entities.TransmitStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SourceDataManagerTest {
    private SourceDataManager sourceDataManager;
    private static final Integer PRODUCTS_NUMBER = 5000;
    private static final Integer BATCHES_NUMBER = 13;

    @BeforeEach
    void init() throws Exception {
        sourceDataManager = new SourceDataManager(
                getClass().getResourceAsStream("/source_data.csv"),
                new ExternalApisProxy(ExternalApiCfg.getLocalExternalApiCfg()));
        Assertions.assertEquals(PRODUCTS_NUMBER, sourceDataManager.getProducts().size());
    }

    @Test
    void testEnrichment()  {
        sourceDataManager.enrichData();
        for(Product product: sourceDataManager.getProducts()) {
            for(String fieldName: Arrays.asList("score", "category", "risk")) {
                Assertions.assertNotNull(product.getFieldValue(fieldName));
            }
        }
    }

    @Test
    void testTransmit() {
        TransmitStats stats = sourceDataManager.transmitData();
        Assertions.assertEquals(PRODUCTS_NUMBER, stats.getTransmitsNumber());
        Assertions.assertEquals(BATCHES_NUMBER, stats.getBatchesNumber());
    }

}