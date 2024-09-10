package org.cafevariome.model;

import org.cafevariome.enums.DataFilesType;

public class DataFile extends AppModel{
    private String dataFileID;
    private String filename;
    private DataFilesType fileFormat;
    private boolean isProcessed;
    private String sourceID;

    public DataFile() {}

    public DataFile(String dataFileID,
                    String filename,
                    DataFilesType fileFormat,
                    boolean isProcessed,
                    String sourceID) {
        this.dataFileID = dataFileID;
        this.filename = filename;
        this.fileFormat = fileFormat;
        this.isProcessed = isProcessed;
        this.sourceID = sourceID;
    }

    public String getDataFileID() {
        return dataFileID;
    }

    public void setDataFileID(String dataFileID) {
        this.dataFileID = dataFileID;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public DataFilesType getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(DataFilesType fileFormat) {
        this.fileFormat = fileFormat;
    }

    public boolean getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }
}
