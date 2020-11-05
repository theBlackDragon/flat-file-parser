//package be.lair.parser.text.document;
//
//import be.lair.parser.text.parser.Record;
//import be.lair.parser.text.parser.RecordParser;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Queue;
//
//public class TestFile extends RecordParser {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    public TestFile(Record parent, Queue<String> fileContents, String line) {
//        super(parent, fileContents, line);
//    }
//
//    @Override
//    public String[] getChildIdentifiers() {
//        return new String[]{"AA", "XX"};
//    }
//
//    @Override
//    public Record parse(String identifier, String line, Queue<String> fileContents) {
//        switch(line.substring(0,2)) {
//            case "AA":
//                logger.trace("Parser has a listener count of {}", this.getSectionAddedListeners().size());
//                addSection(new AaRecord(this, fileContents, line));
//                break;
//            case "XX":
//                addSection(new XxRecord(line));
//                break;
//            default:
//                // nothing to see here, moving along
//        }
//        return this;
//    }
//}
