package org.fs.comparer.rule;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class IgnoreFields extends Rule {
    private final Set<String> ignoreFields;

    public IgnoreFields(String ...fields) {
        this.ignoreFields = Arrays.stream(fields).collect(Collectors.toSet());
    }

    @Override
    public FilteredFields filter(RuleFields ruleFields) {
        Set<String> leftFields  = ruleFields.leftFieldsNames().stream()
                .filter(e -> !ignoreFields.contains(e))
                .collect(Collectors.toSet());
        Set<String> rightFields = ruleFields.rightFieldsNames().stream()
                .filter(e -> !ignoreFields.contains(e))
                .collect(Collectors.toSet());

        return new FilteredFields(leftFields, rightFields);
    }
}
