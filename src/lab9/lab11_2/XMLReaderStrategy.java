package lab9.lab11_2;

import lab9.Student;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface XMLReaderStrategy {
    ArrayList<Student> parse(File file) throws IllegalArgumentException, IOException, ParserConfigurationException, SAXException;
}
