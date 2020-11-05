package be.lair.parser.text;

import java.io.InputStream;

public class ParserFactory {

    private ParserFactory() {}

    public static <T, R extends Parser<T>> R instance(T input) {
        if(input instanceof InputStream){
            return (R)new InputStreamParser((InputStream)input);
        } else {
            throw new RuntimeException("No parser for " + input.getClass());
        }
    }
}
