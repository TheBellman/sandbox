package net.parttimepolymath.sandbox;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.ThreadSafe;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This class represents each 'tick' in a time range, where the frequency of the tick is specified along with the range. The
 * ticks will be greater than equal to the 'from' end of the range, and less than the 'to' end of the range. Note that while the
 * class is constructed with Instant that are not necessarily rounded, the actual range uses truncated versions of the
 * input parameters. For example specifying 2016-02-14T03:17:27Z to 2016-02-14T05:43:17Z with a one hour tick will give the range
 * {2016-02-14T03:00:00Z, 2016-02-14T04:00:00Z, 2016-02-14T05:00:00Z}. Note also that while this class implements Collection, some
 * methods of Collection are illegal because they do not make sense.
 *
 * @since 2016-03-03
 * @author Robert Hook
 */
@ThreadSafe
@ToString
@Getter
@EqualsAndHashCode
public class InstantRange implements Collection<Instant> {

    /**
     * the normalised 'from' end of the range.
     */
    private final Instant fromInstant;
    /**
     * the normalised 'to' end of the range.
     */
    private final Instant toInstant;
    /**
     * the normalised tick size.
     */
    private final ChronoUnit interval;

    /**
     * Construct an instance of a range which will count across the specified unit. Note that if 'from' and 'to' are specified
     * the wrong way around, the range will still work as expected.
     *
     * @param from the 'from' date time, which will be truncated to the specified unit. If null, will be now less one unit.
     * @param to the 'to' date time, which will be truncated to the specified unit. If null, will be now.
     * @param unit the unit used to increment from the 'from' to the 'to'. If null, will default to hour. Not all units
     *            are supported - only those supported for manipulation of Interval, which currently means DAYS or smaller.
     */
    public InstantRange(final Instant from, final Instant to, final ChronoUnit unit) {
        interval = Optional.ofNullable(unit).orElse(ChronoUnit.HOURS);

        Instant trialFrom = Optional.ofNullable(from).orElse(Instant.now().minus(1, interval)).truncatedTo(interval);
        Instant trialTo = Optional.ofNullable(to).orElse(Instant.now()).truncatedTo(interval);

        if (trialFrom.isBefore(trialTo)) {
            fromInstant = trialFrom;
            toInstant = trialTo;
        } else {
            toInstant = trialFrom;
            fromInstant = trialTo;
        }
    }

    /**
     * return the number of ticks the range should have. Note that this is not completely in agreement with isEmpty(), because
     * an empty range (ie where from and to are the same) will always have at least 1 tick, but would be reported as being
     * empty.
     */
    @Override
    public final int size() {
        return (int) (fromInstant.until(toInstant, interval) + 1);
    }

    /**
     * is the range empty? Note that this is not completely in agreement with size(), because an empty range (ie where
     * from and to are the same) will always have at least 1 tick but would be reported as being empty.
     */
    @Override
    public final boolean isEmpty() {
        return !(fromInstant.isBefore(toInstant));
    }

    /**
     * returns true if the object is a non-null Instant that falls inside the range. Note that for
     * correctness of the semantics of 'contains', only Instant objects that fall on one of the expected
     * 'ticks' will be deemed to be contained. For example if the range tick interval is HOURS, then you could expect
     * 2016-02-14T09:00:00Z to be in the range, but 2016-02-14T09:00:01Z not to be in the range.
     *
     * @param o the object to be tested.
     */
    @Override
    public final boolean contains(final Object o) {
        if (!(o instanceof Instant)) {
            return false;
        }

        Instant instant = (Instant) o;
        if (instant.truncatedTo(interval).equals(instant)) {
            return instant.equals(fromInstant) || instant.equals(toInstant)
                    || (instant.isAfter(fromInstant) && instant.isBefore(toInstant));
        } else {
            return false;
        }
    }

    /**
     * return an iterator that can be used to iterate over the range. Note that an 'empty' range will always generate at least
     * one tick, ie the iterator will always emit at least the start of the range in the case where the start and end are the same.
     *
     * @see java.util.Collection#iterator()
     */
    @Override
    public final Iterator<Instant> iterator() {
        return new InstantRangeIterator(fromInstant, toInstant, interval);
    }


    /**
     * expose the range as a stream.
     * @return the stream derived from this collection's iterator.
     */
    public final Stream<Instant> asStream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    /**
     * emit the ticks for this range as an array.
     *
     * @return an array of objects (which will be guaranteed to be Instant), which is guaranteed to be non-null.
     * @see java.util.Collection#toArray()
     */
    @Override
    public final Object[] toArray() {
        return StreamSupport.stream(this.spliterator(), false).toArray(Instant[]::new);
    }

    /**
     * emit the ticks as an array of objects, according to the semantics of Collection. While this will work for certain
     * types of arrays, it's not particularly useful and only preserved for compatibility with Collection.
     *
     * @param <T> the type of object we are building an array of - ideally just an Instant
     * @param a the array to build into.
     * @see java.util.Collection#toArray(Object[])
     */
    @SuppressWarnings("unchecked")
    @Override
    public final <T> T[] toArray(final T[] a) {
        int size = size();
        Object[] elements = toArray();

        if (a.length < size) {
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }

        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /**
     * does the range include all items in a collection? A null collection automatically returns false.
     *
     * @param c the collection to test.
     */
    @Override
    public final boolean containsAll(final Collection<?> c) {
        if (c == null) {
            return false;
        }
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * modifying the range is not supported, and UnsupportedOperationException will be thrown.
     *
     * @param e the instant to add.
     */
    @Override
    public final boolean add(final Instant e) {
        throw new UnsupportedOperationException();
    }

    /**
     * modifying the range is not supported, and UnsupportedOperationException will be thrown.
     *
     * @param o the object to remove.
     */
    @Override
    public final boolean remove(final Object o) {
        throw new UnsupportedOperationException();
    }

    /**
     * modifying the range is not supported, and UnsupportedOperationException will be thrown.
     *
     * @param c the collection to copy in.
     */
    @Override
    public final boolean addAll(final Collection<? extends Instant> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * modifying the range is not supported, and UnsupportedOperationException will be thrown.
     *
     * @param c the items to remove
     */
    @Override
    public final boolean removeAll(final Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * modifying the range is not supported, and UnsupportedOperationException will be thrown.
     *
     * @param c the items to retain
     */
    @Override
    public final boolean retainAll(final Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * modifying the range is not supported, and UnsupportedOperationException will be thrown.
     *
     * @see java.util.Collection#clear()
     */
    @Override
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    /**
     * the actual iterator implementation.
     */
    private static final class InstantRangeIterator implements Iterator<Instant> {

        /**
         * the current value in the iteration.
         */
        private Instant current;
        /**
         * the end of the range.
         */
        private final Instant end;
        /**
         * the tick size.
         */
        private final ChronoUnit interval;

        /**
         * default constructor.
         *
         * @param rangeStart the starting date/time of the range
         * @param rangeEnd the ending date/time (inclusive) of the range
         * @param unit the size of the tick.
         */
        private InstantRangeIterator(final Instant rangeStart, final Instant rangeEnd, final ChronoUnit unit) {
            current = rangeStart;
            end = rangeEnd;
            interval = unit;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Instant next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Instant ret = current;
            current = current.plus(1, interval);
            if (current.compareTo(end) > 0) {
                current = null;
            }
            return ret;
        }

        /**
         * remove an item from the range - this must not be used.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}