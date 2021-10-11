package project.nextleveltechnology.util;


import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XMLParse {

   // <T> T fromFile(String filePath, Class<T> tClass) throws JAXBException, FileNotFoundException;
    <T> T fromString(String data, Class<T> tClass);
    String serialize(Object o);
  //  <T> void writeToFile(String filePath, T entity) throws JAXBException;
}
