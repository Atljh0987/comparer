package org.fs.comparer.rule;

import org.fs.comparer.exceptions.RuleValidationException;

import java.util.Objects;
import java.util.Set;

public class FilteredFields {
    private final Set<String> leftFieldsNames;
    private final Set<String> rightFieldsNames;

    public FilteredFields(Set<String> leftFieldsNames, Set<String> rightFieldsNames) {
        this.leftFieldsNames = leftFieldsNames;
        this.rightFieldsNames = rightFieldsNames;
    }

    public FilteredFields(RuleFields ruleFields) {
        this.validate(ruleFields);

        this.leftFieldsNames = ruleFields.leftFieldsNames();
        this.rightFieldsNames = ruleFields.rightFieldsNames();
    }

    public void validate(RuleFields ruleFields) {
        Objects.requireNonNull(ruleFields, "ruleFields");

        if(ruleFields.leftFieldsNames()  == null) throw new RuleValidationException("Left fields is null");
        if(ruleFields.rightFieldsNames() == null) throw new RuleValidationException("Right fields is null");

        if(ruleFields.leftFieldsNames().isEmpty())  throw new RuleValidationException("Left fields is null");
        if(ruleFields.rightFieldsNames().isEmpty()) throw new RuleValidationException("Right fields is null");
    }

    public Set<String> getLeftFieldsNames() {
        return leftFieldsNames;
    }

    public Set<String> getRightFieldsNames() {
        return rightFieldsNames;
    }
}
