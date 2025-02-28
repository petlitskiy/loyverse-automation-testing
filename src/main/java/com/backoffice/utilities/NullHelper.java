package com.backoffice.utilities;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Neha Kharbanda
 */
public class NullHelper {

    public static <T extends Object> boolean isNull(T t) {
        return !Optional.ofNullable(t).isPresent();
    }

    public static <T extends Object> boolean isnotNull(T t) {
        return !Optional.ofNullable(t).isPresent();
    }

    public static <T> T nullSafeGet(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException npe) {
            return null;
        }
    }

}
