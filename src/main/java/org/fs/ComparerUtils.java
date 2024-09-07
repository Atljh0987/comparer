package org.fs;

import org.fs.exceptions.ComparerException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ComparerUtils {
    public static void compare(boolean condition, String message) {
        if(condition) throw new ComparerException(message);
    }

//    public static <L, R> boolean hasTheSameTypes(L left, R right) {
//
//    }

    public static <L, R> boolean compareByEqualFields(L left, R right) {
        Map<String, Field> leftFieldsMap = Arrays.stream(left.getClass().getDeclaredFields())
                .collect(Collectors.toMap(Field::getName, Function.identity()));

        Map<String, Field> rightFieldsMap = Arrays.stream(right.getClass().getDeclaredFields())
                .collect(Collectors.toMap(Field::getName, Function.identity()));

        for(var leftEntity : leftFieldsMap.entrySet()) {

        }

        return true;
    }
}
