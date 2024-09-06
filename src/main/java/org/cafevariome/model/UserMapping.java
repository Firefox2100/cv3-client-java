package org.cafevariome.model;

import java.util.Map;

import org.cafevariome.enums.UserMappingType;

public class UserMapping {
    private UserMappingType mappingType;
    private String mappingUser;
    private Map<String, String> mapping;

    public UserMapping () {
        this.mappingType = UserMappingType.NO_MAPPING;
        this.mappingUser = null;
        this.mapping = null;
    }

    public UserMapping(String mappingUser) {
        this.mappingType = UserMappingType.STATIC;
        this.mappingUser = mappingUser;
        this.mapping = null;
    }

    public UserMapping(Map<String, String> mapping) {
        this.mappingType = UserMappingType.DYNAMIC;
        this.mappingUser = null;
        this.mapping = mapping;
    }

    public UserMappingType getMappingType() {
        return mappingType;
    }

    public void setMappingType(UserMappingType mappingType) {
        this.mappingType = mappingType;
    }

    public String getMappingUser() {
        return mappingUser;
    }

    public void setMappingUser(String mappingUser) {
        this.mappingUser = mappingUser;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }
}
