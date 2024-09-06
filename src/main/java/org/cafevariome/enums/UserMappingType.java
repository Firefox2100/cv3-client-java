package org.cafevariome.enums;

public enum UserMappingType implements BaseEnum<String> {
    NO_MAPPING("noMapping"),
    STATIC("static"),
    DYNAMIC("dynamic");

    private final String value;

    UserMappingType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
