package org.cafevariome.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public interface BaseEnum<T> {

    @JsonValue
    T getValue();

    @JsonCreator
    static <E extends Enum<E> & BaseEnum<T>, T> E fromValue(Class<E> enumClass, T value) {
        for (E enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.getValue().equals(value)) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
