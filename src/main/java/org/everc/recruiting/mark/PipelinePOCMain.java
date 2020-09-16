package org.everc.recruiting.mark;

import org.everc.recruiting.mark.entities.TransmitStats;
import org.everc.recruiting.mark.sourcedata.ExternalApisProxy;
import org.everc.recruiting.mark.sourcedata.SourceDataManager;
import java.io.IOException;

public class PipelinePOCMain {

    public static void main(String[] args) {
        try {
            SourceDataManager dataManager = new SourceDataManager(
                    PipelinePOCMain.class.getResourceAsStream("/source_data.csv"),
                    new ExternalApisProxy(ExternalApiCfg.getLocalExternalApiCfg()));

            dataManager.enrichData();
            TransmitStats stats = dataManager.transmitData();
            System.out.println("Pipeline for product transmit finished: " + stats);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }



}
