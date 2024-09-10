package org.fs.comparer;

import org.fs.comparer.executor.ComparerMain;
import org.fs.comparer.interfaces.ComparerType;

/**
 * Comparer main class
 */
public class Comparer {

    /**
     * Method to start comparison
     * @param left object for comparison
     * @param right object for comparison
     * @return comparer type
     */
    public static <L, R> ComparerType compare(L left, R right) {
        return new ComparerMain<>(left, right);
    }
}