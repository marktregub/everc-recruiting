package org.everc.recruiting.mark.sourcedata;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.everc.recruiting.mark.entities.Product;
import org.everc.recruiting.mark.entities.TransmitStats;

public class SourceDataManager {
    private final List<Product> products = new ArrayList<>();
    private final ExternalApisProxy externalApisProxy;

    public SourceDataManager(InputStream csvFileStream, ExternalApisProxy externalApisProxy) throws IOException {
        this.externalApisProxy = externalApisProxy;
        reloadData(csvFileStream);
    }

    public void reloadData(InputStream csvFileStream) throws IOException {
        products.clear();
        CSVParser parser = CSVParser.parse(
                csvFileStream, Charset.forName("UTF-8"), CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for(CSVRecord record : parser) {
            products.add(new Product(record.get(0),record.toMap()));
        }
    }

    public void enrichData()  {
        externalApisProxy.executeEnrichment(products);
    }

    public TransmitStats transmitData() {
        return externalApisProxy.executeTransmit(products);
    }

    public final List<Product> getProducts() { return products; }

}
