package org.fs.comparer.rule;

import java.util.Set;

public record RuleFields(Set<String> leftFieldsNames, Set<String> rightFieldsNames) {
}
