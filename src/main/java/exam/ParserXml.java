package exam;

import org.xml.sax.SAXException;

import javax.validation.constraints.Null;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.Objects;

public class ParserXml implements Parser {

    @Override
    public Model parse(String filename, @Null String modelName) throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(Model.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        ClassLoader classLoader = getClass().getClassLoader();
        Schema modelSchema = sf.newSchema(Objects.requireNonNull(classLoader.getResource("XmlModel.xsd")));
        unmarshaller.setSchema(modelSchema);
        return (Model) unmarshaller.unmarshal(new File(filename));
    }

}
