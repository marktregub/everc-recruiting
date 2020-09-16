package org.everc.recruiting.mark.entities;

public class TransmitStats {
    private Integer transmitsNumber=0;
    private Integer batchesNumber=0;

    public void incrementTransmits() {
        transmitsNumber++;
    }

    public void incrementBatchesNumber() {
        batchesNumber++;
    }

    public Integer getTransmitsNumber() {
        return transmitsNumber;
    }

    public Integer getBatchesNumber() {
        return batchesNumber;
    }

    @Override
    public String toString() {
        return "TransmitStats{" +
                "transmitsNumber=" + transmitsNumber +
                ", batchesNumber=" + batchesNumber +
                '}';
    }
}
