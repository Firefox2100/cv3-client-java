package org.cafevariome.api;

import org.cafevariome.util.AppConfig;
import org.cafevariome.util.HttpUtil;

public class DataFileAPI extends ClientAPI{
    public DataFileAPI(AppConfig config, HttpUtil httpUtil) {
        super(config, httpUtil);
    }

    public boolean delete(String dataFileID) {
        try {
            return httpUtil.deleteModel(config.getAdminURI() + "/datafiles/" + dataFileID);
        } catch (Exception e) {
            config.logger.error("Error deleting data file: {}", e.getMessage());
            return false;
        }
    }
}
