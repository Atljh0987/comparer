package org.fs.helpers;

import java.lang.reflect.Field;

public class ComparerHelper {
    public static boolean hasOnlyPrimitiveFields(Object object) {
        for (Field declaredField : object.getClass().getDeclaredFields()) {
            try {
                if(!declaredField.get(object).getClass().isPrimitive())
                    return false;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }
}
