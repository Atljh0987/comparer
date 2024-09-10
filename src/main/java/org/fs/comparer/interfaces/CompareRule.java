package org.fs.comparer.interfaces;

public interface CompareRule extends ComparerExecutor {
    CompareRule ignoreFields(String ...fields);
}
