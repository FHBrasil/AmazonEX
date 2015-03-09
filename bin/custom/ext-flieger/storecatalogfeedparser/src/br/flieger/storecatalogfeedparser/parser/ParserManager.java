package br.flieger.storecatalogfeedparser.parser;

import static br.flieger.storecatalogfeedparser.constants.Constants.FEED_INPUT_PREFIX;
import static br.flieger.storecatalogfeedparser.constants.Constants.PROJECT_PROPERTIES;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import br.flieger.storecatalogfeedparser.parser.ParseableEntry.EntryPart;

/**
 * 
 * @author franthescolly
 *
 */
public class ParserManager {

    private final Properties properties;


    public ParserManager() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(PROJECT_PROPERTIES));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public List<ParseableEntry> getParseableEntries(File root) {
        List<ParseableEntry> entries = new ArrayList<ParseableEntry>();
        for (String key : filter(properties.keySet(), FEED_INPUT_PREFIX, EntryPart.KEY)) {
            String input = filter(properties.keySet(), key, EntryPart.VALUE).get(0);
            String output = root.getName() + "/" + getOutputByInput(key);
            boolean isBOMRequired = !key.contains(".nobom");
            entries.add(new ParseableEntry(input, output, isBOMRequired));
        }
        return Collections.unmodifiableList(entries);
    }


    private String getOutputByInput(String input) {
        input = input.replaceAll(".feed.input.", ".feed.output.");
        List<String> filter = filter(properties.keySet(), input, EntryPart.VALUE);
        return filter.isEmpty() ? "" : filter.get(0);
    }


    private List<String> filter(final Collection<?> src, final String prefix,
            final EntryPart entryPart) {
        List<String> result = new ArrayList<String>();
        for (Object element : src) {
            if (element.toString().startsWith(prefix)) {
                switch (entryPart) {
                case KEY:
                    result.add(element.toString());
                    break;
                case VALUE:
                    result.add(properties.getProperty(element.toString()).trim());
                    break;
                }
            }
        }
        return result;
    }
}