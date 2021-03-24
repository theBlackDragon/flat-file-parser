package be.lair.parser.text;

import java.io.InputStream;

public class ParserFactory {

    private String recordIdentifier = "\n";

    public <T, R extends Parser<T>> R instance(T input) {
        if(input instanceof InputStream){
            InputStreamParser parser = new InputStreamParser((InputStream)input);
            parser.setRecordIdentifier(recordIdentifier);
            return (R)parser;
        } else {
            throw new RuntimeException("No parser for " + input.getClass());
        }
    }

    public String getRecordIdentifier() {
        return recordIdentifier;
    }

    /**
     * Set the string that identifies individual records
     *
     * This is used to split the file at the record boundaries, as
     * well as to generate output through generate()
     *
     * @param recordIdentifier needs to be a valid Java regex
     */
    public void setRecordIdentifier(String recordIdentifier) {
        this.recordIdentifier = recordIdentifier;
    }
}
