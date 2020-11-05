package be.lair.parser.text;

public abstract class Parser<T> extends Record {
    public abstract Record parse(ParseFunction parseFunction);
}
