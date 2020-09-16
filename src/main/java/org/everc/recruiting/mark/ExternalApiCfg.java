package org.everc.recruiting.mark;

import java.util.HashMap;
import java.util.Map;

public class ExternalApiCfg {

    public static Map<EXTERNAL_API_TYPE, String> getLocalExternalApiCfg() {
        return  new HashMap<EXTERNAL_API_TYPE, String>() {{
            put(EXTERNAL_API_TYPE.ENRICHMENT, "http://localhost:1080/test_api");
            put(EXTERNAL_API_TYPE.TRANSMIT, "http://localhost:1080/item");
            put(EXTERNAL_API_TYPE.BATCH, "http://localhost:1080/batch");
        }};
    }

    public enum EXTERNAL_API_TYPE {
        ENRICHMENT, TRANSMIT, BATCH;
    }
}
