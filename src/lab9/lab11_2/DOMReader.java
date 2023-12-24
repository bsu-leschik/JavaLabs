package lab9.lab11_2;

import lab9.Student;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DOMReader implements XMLReaderStrategy{

    @Override
    public ArrayList<Student> parse(File file) throws IllegalArgumentException, IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder =  docBuildFactory.newDocumentBuilder();

        return getStudentsFomNodes(builder.parse(file).getElementsByTagName("student"));
    }

    private ArrayList<Student> getStudentsFomNodes(NodeList studentsNodeList) throws NullPointerException{
        ArrayList<Student> studentsList = new ArrayList<>();
        for (int i = 0; i < studentsNodeList.getLength(); i++) {
            NamedNodeMap attributes = studentsNodeList.item(i).getAttributes();

            studentsList.add(new Student(Integer.parseInt(attributes.getNamedItem("identifier").getTextContent()),
                    attributes.getNamedItem("surname").getTextContent(),
                    Integer.parseInt(attributes.getNamedItem("courseNumber").getTextContent()),
                    Integer.parseInt(attributes.getNamedItem("groupNumber").getTextContent())));
        }
        return studentsList;
    }
}
