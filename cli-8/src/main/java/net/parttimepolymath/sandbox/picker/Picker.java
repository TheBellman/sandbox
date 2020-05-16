package net.parttimepolymath.sandbox.picker;

import java.util.Optional;

public interface Picker<T> {
    /**
     * based on the content of an implementation, pick and return 1 random item from it.
     * @return a random element from the contained set of T
     */
    T pick();

    /**
     * given a 1-in-n chance, pick an item from the implementation's collection of T
     * @param n the odds
     * @return a random element or nothing
     */
    Optional<T> maybePick(int n);

    /**
     * get the size of the contained data set.
     * @return the size of the contained set
     */
    int size();
}
