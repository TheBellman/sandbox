package net.parttimepolymath.sandbox.picker;

import net.parttimepolymath.sandbox.RandomUtil;

import java.util.*;

/**
 * in testing, it's often useful to be able to add some variation and chaos. This allows random selection
 * from a given list.
 * @param <T> the type of item contained in an instance.
 */
public class ListPicker<T> implements Picker<T> {
    private final List<T> items=new ArrayList<>();

    /**
     * construct with a collection of elements
     * @param elements the non null set of elements to construct with - note that we copy the elements from the input.
     */
    public ListPicker(Collection<T> elements) {
        items.addAll(elements);
    }

    /**
     * construct with a collection of elements
     * @param elements the set of elements to use
     */
    @SafeVarargs
    public ListPicker(T... elements) {
        this(Arrays.asList(elements));
    }

    @Override
    public T pick() {
        return items.get(RandomUtil.randomOffset(items.size()));
    }

    @Override
    public Optional<T> maybePick(final int n) {
        return RandomUtil.oneIn(n) ? Optional.of(pick()) : Optional.empty();
    }

    @Override
    public int size() {
        return items.size();
    }
}
