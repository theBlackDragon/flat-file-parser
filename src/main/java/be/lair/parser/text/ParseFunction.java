package be.lair.parser.text;

import java.util.Queue;

public interface ParseFunction {
    void parse(Record parent, Queue<String> fileContents, String record);
}
