package org.cafevariome.enums;

public enum DataFilesType implements BaseEnum<String> {
    CSV("csv"),
    VCF("vcf");

    private final String value;

    DataFilesType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static DataFilesType fromValue(String value) {
        return BaseEnum.fromValue(DataFilesType.class, value);
    }
}
