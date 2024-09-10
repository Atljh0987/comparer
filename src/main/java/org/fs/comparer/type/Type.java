package org.fs.comparer.type;

import org.fs.comparer.rule.FilteredFields;

public abstract class Type<L, R> {
    public abstract boolean compare(L left, R right, FilteredFields fields);
}
