package org.everc.recruiting.mark.entities;

import org.json.simple.JSONObject;

import java.util.Map;

public class Product {
    private Map<String, String> data;
    private String batchId;
    public Product(String batchId, Map<String, String> data) {
        this.batchId = batchId;
        this.data = data;}

    public JSONObject toJson() { return new JSONObject(data); }

    public void enrich(Map<String, String> enrichmentData) { data.putAll(enrichmentData);}
    public Object getFieldValue(String fieldName) { return data.get(fieldName);}
    public String getBatchId() { return batchId;}

    @Override
    public String toString() {
        return toJson().toString();
    }
}
