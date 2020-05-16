package net.parttimepolymath.sandbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * This main class is the entry point which provides the command line interface via commons-cli.
 * Note the use of the project lombok logging annotation.
 *
 * @author Robert Hook
 * @since 2020-05-16
 */
@Slf4j
public class Main {

    /**
     * provide a command line interface.
     * @param args the command line arguments, assumed non null but possibly empty.
     */
    public static void main(final String[] args) {
        log.info("started");

        Options options = options();
        try {
            CommandLine line = new DefaultParser().parse(options, args);

            if (line.hasOption('?')) {
                help(options);
            } else {
                Application instance = Application
                        .builder()
                        .debugMode(line.hasOption('d'))
                        .iterations(iterations(line))
                        .namesFile("names.txt")
                        .build();
                instance.run();
                log.info(instance.getResult().toString());
            }
        } catch (ParseException ex) {
            help(options);
        }
        log.info("ended");
    }

    /**
     * determine the number of iterations specified in the command line.
     * The approach here is not necessarily the best way because a mal-formed option value will be
     * treated as the default.

     * @param line the non-null command line to examine
     * @return the number of iterations specified, or the default if none.
     */
    private static int iterations(final CommandLine line) {
        if (line.hasOption('n') && NumberUtils.isParsable(line.getOptionValue("n"))) {
            return NumberUtils.toInt(StringUtils.strip(line.getOptionValue("n")));
        } else {
            return ApplicationProperties.getIterations();
        }
    }

    /**
     * print the command line help associated with the supplied options.
     * @param options a non-null set of CLI options.
     */
    private static void help(final Options options) {
        HelpFormatter help = new HelpFormatter();
        help.printHelp(ApplicationProperties.getAppName(), options);
    }

    /**
     * construct the set of options for the command line.
     * @return a non-null set of options.
     */
    private static Options options() {
        Options options = new Options();
        options.addOption(Option.builder("d").longOpt("debug").desc("enable debug mode").build());
        options.addOption(Option.builder("n").longOpt("count").desc("number of iterations to run").hasArg().numberOfArgs(1).argName("count").build());
        options.addOption((Option.builder("?").longOpt("help").desc("print this help message").build()));

        return options;
    }
}
