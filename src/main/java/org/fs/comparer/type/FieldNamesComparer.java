package org.fs.comparer.type;

import org.fs.comparer.rule.FilteredFields;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FieldNamesComparer<L, R> extends Type<L, R> {
    @Override
    public boolean compare(L left, R right, FilteredFields fields) {
        Map<String, Field> leftFields = Arrays.stream(left.getClass().getDeclaredFields())
                .filter(e -> fields.getLeftFieldsNames().contains(e.getName()))
                .collect(Collectors.toMap(Field::getName, Function.identity()));

        Map<String, Field> rightFields = Arrays.stream(right.getClass().getDeclaredFields())
                .filter(e -> fields.getRightFieldsNames().contains(e.getName()))
                .collect(Collectors.toMap(Field::getName, Function.identity()));

        for(var leftEntity : leftFields.entrySet()) {
            var leftKey = leftEntity.getKey();

            if(!rightFields.containsKey(leftKey))
                continue;

            Field leftField = leftEntity.getValue();
            Field rightField = rightFields.get(leftKey);

            // TODO: только для одного контекста или нет
            leftField.setAccessible(true);
            rightField.setAccessible(true);

            try {
                if(!leftField.get(left).equals(rightField.get(right))) {
                    System.out.printf("Left field: \"%s\" not equals to right \"%s\"", leftField.getName(), rightField.getName());
                    return false;
                }
            } catch (IllegalAccessException e) {
                System.out.printf("Left field: \"%s\" has error with right \"%s\"", leftField.getName(), rightField.getName());
                throw new RuntimeException(e);
            }
        }

        return true;
    }
}
