package org.fs.comparer.executor;

import org.fs.comparer.exceptions.RuleValidationException;
import org.fs.comparer.interfaces.CompareRule;
import org.fs.comparer.interfaces.ComparerType;
import org.fs.comparer.rule.FilteredFields;
import org.fs.comparer.rule.IgnoreFields;
import org.fs.comparer.rule.Rule;
import org.fs.comparer.rule.RuleFields;
import org.fs.comparer.type.FieldNamesComparer;
import org.fs.comparer.type.Type;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ComparerMain<L, R> implements ComparerType, CompareRule {
    private final L left;
    private final R right;

    private Type<L, R> type = null;
    private final List<Rule> rules = new ArrayList<>();

    public ComparerMain(L left, R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public CompareRule fieldNamesComparer() {
        this.type = new FieldNamesComparer<>();

        return this;
    }

    @Override
    public CompareRule ignoreFields(String... fields) {
        this.rules.add(new IgnoreFields(fields));

        return this;
    }

    @Override
    public boolean execute() {
        return type.compare(left, right, this.executeRules());
    }

    private RuleFields extractFields() {
        var leftFieldsNames = this.extractFieldsNames(left);
        var rightFieldsNames = this.extractFieldsNames(right);

        return new RuleFields(leftFieldsNames, rightFieldsNames);
    }

    private Set<String> extractFieldsNames(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toSet());
    }

    // TODO: empty rules
    // TODO: empty all fields
    private FilteredFields executeRules() {
        RuleFields ruleFields = this.extractFields();

        if(rules.isEmpty()) {
            return new FilteredFields(ruleFields);
        }

        return this.rulesInCycle(ruleFields);
    }

    private FilteredFields rulesInCycle(RuleFields ruleFields) {
        FilteredFields filteredFields = null;

        for(var rule : rules) {
            filteredFields = rule.filter(ruleFields);
        }

        this.validate(filteredFields);

        return filteredFields;
    }

    private void validate(FilteredFields filteredFields) {
        Objects.requireNonNull(filteredFields, "filteredFields");

        if(filteredFields.getLeftFieldsNames()  == null) throw new RuleValidationException("Left fields after rules executed is null");
        if(filteredFields.getRightFieldsNames() == null) throw new RuleValidationException("Right fields after rules executed is null");

        if(filteredFields.getLeftFieldsNames().isEmpty())  throw new RuleValidationException("Left fields after rules executed is null");
        if(filteredFields.getRightFieldsNames().isEmpty()) throw new RuleValidationException("Right fields after rules executed is null");
    }
}
