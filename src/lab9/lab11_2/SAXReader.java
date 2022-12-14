package lab9.lab11_2;

import lab9.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SAXReader implements XMLReaderStrategy {

    @Override
    public ArrayList<Student> parse(File file) throws IllegalArgumentException, IOException, ParserConfigurationException, SAXException, NullPointerException {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        SAXReaderHandler handler= new SAXReaderHandler();
        parser.parse(file, handler);
        return handler.students;
    }
}

class SAXReaderHandler extends DefaultHandler {

    ArrayList<Student> students;

    @Override
    public void startDocument(){
        students = new ArrayList<>();
    }

    @Override
    public void startElement(String namespaceURL, String localName, String qName, Attributes attributes) throws SAXException, NullPointerException{
        if (!qName.equals("student")){
            return;
        }

        if (attributes.getLength() < 4){
            throw new SAXException("Inappropriate attributes for student tag");
        }

        students.add(new Student(Integer.parseInt(attributes.getValue("identifier")),
                attributes.getValue("surname"),
                Integer.parseInt(attributes.getValue("courseNumber")),
                Integer.parseInt(attributes.getValue("groupNumber"))
        ));
    }

    @Override
    public void endElement(String namespaceURL, String localName, String qName) throws SAXException{

    }
}
