package net.parttimepolymath.sandbox;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Convenience wrappers for dealing with files.
 *
 * @author Robert Hook
 * @since 2020-05-22
 */
public class FileUtils {

    /**
     * does a file specified by a filename - which can be a path to a file - appear to be readable?
     *
     * @param fileName the target file path
     * @return true if the entry appears to point to a readable file, false otherwise.
     */
    public static boolean hasFile(final String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return false;
        }

        return Files.isReadable(Paths.get(fileName));
    }

    /**
     * return a file system file as a list of strings - i.e. assume the file is a "text" file with
     * lines semantics.
     *
     * @param fileName the file to try to read.
     * @return the contents of the file, or an empty list if not found.
     */
    public static List<String> readFileAsList(final String fileName) {
        if (!hasFile(fileName)) {
            return Collections.emptyList();
        }

        try {
            return new BufferedReader(new FileReader(fileName)).lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }
}
