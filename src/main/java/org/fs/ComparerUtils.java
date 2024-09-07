package org.fs;

import org.fs.exceptions.ComparerException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ComparerUtils {
    public static void compare(boolean condition, String message) {
        if(condition) throw new ComparerException(message);
    }

    public static <L, R> boolean compareByEqualFields(L left, R right) {
        Map<String, Field> leftFieldsMap = Arrays.stream(left.getClass().getDeclaredFields())
                .collect(Collectors.toMap(Field::getName, Function.identity()));

        Map<String, Field> rightFieldsMap = Arrays.stream(right.getClass().getDeclaredFields())
                .collect(Collectors.toMap(Field::getName, Function.identity()));

        for(var leftEntity : leftFieldsMap.entrySet()) {
            var leftKey = leftEntity.getKey();

            if(!rightFieldsMap.containsKey(leftKey))
                continue;

            Field leftF = leftEntity.getValue();
            Field rightF = rightFieldsMap.get(leftKey);

            leftF.setAccessible(true);
            rightF.setAccessible(true);

            try {
                if(!leftF.get(left).equals(rightF.get(right))) {
                    System.out.printf("Left field: \"%s\" not equals to right \"%s\"", leftF.getName(), rightF.getName());
                    return false;
                }
            } catch (IllegalAccessException e) {
                System.out.printf("Left field: \"%s\" has error with right \"%s\"", leftF.getName(), rightF.getName());
                throw new RuntimeException(e);
            }
        }

        return true;
    }

//    public static <L, R> boolean compareLists(Collection<L> leftList, Collection<R> rightList) {
//        if(leftList.size() != rightList.size())
//            return false;
//
//        for (int i = 0; i < leftList.size(); i++) {
//
//        }
//    }
}
