package org.cafevariome.enums;

public enum BeaconAuthMethod implements BaseEnum<String>{
    NO_AUTH("noAuth"),
    BASIC_AUTH("basicAuth"),
    TOKEN_AUTH("tokenAuth");

    private final String value;

    BeaconAuthMethod(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static BeaconAuthMethod fromValue(String value) {
        return BaseEnum.fromValue(BeaconAuthMethod.class, value);
    }
}
