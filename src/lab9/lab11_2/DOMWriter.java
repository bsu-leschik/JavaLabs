package lab9.lab11_2;

import lab9.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class DOMWriter {

    public static void write(Vector<Vector> students, File file) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder =  docBuildFactory.newDocumentBuilder();

        Document document = builder.newDocument();

        Element root = document.createElement("students");

        for (Vector student : students) {
            Element element = document.createElement("student");
            element.setAttribute("identifier", student.get(0).toString());
            element.setAttribute("surname", student.get(1).toString());
            element.setAttribute("courseNumber", student.get(2).toString());
            element.setAttribute("groupNumber", student.get(3).toString());

            root.appendChild(element);
        }
        document.appendChild(root);

        FileOutputStream outputStream = new FileOutputStream(file);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");


        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(outputStream);

        transformer.transform(source, result);
    }
}
