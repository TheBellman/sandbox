package net.parttimepolymath.sandbox.picker;

import org.apache.commons.lang3.tuple.Pair;

/**
 * extension of the string file lister which can split results into pairs.
 * @since 2020-05-16
 * @author Robert Hook
 */
public class StringPairListFilePicker extends StringListFilePicker {
    private final String delimiter;

    /**
     * construct with filename and delimiter
     * @param fileName the non-null name of the resource file to pick from
     * @param delimiter the delimiter used between fields in each line
     */
    public StringPairListFilePicker(final String fileName, final String delimiter) {
        super(fileName);
        this.delimiter = delimiter;
    }

    /**
     * construct with filename, using a comma as the default field seperator
     * @param fileName the non-null name of the resource file to pick from
     */
    public StringPairListFilePicker(final String fileName) {
        super(fileName);
        this.delimiter = ",";
    }

    /**
     * pick a random pair of strings from the collection.
     * @return a non null pair of strings.
     */
    public Pair<String, String> pickPair() {
        String[] record = pick().split(delimiter, 2);
        assert record.length == 2 : "pair must have at least two tokens";
        return Pair.of(record[0], record[1]);
    }
}
