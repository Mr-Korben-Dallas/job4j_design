package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Author> writers = new ArrayList<>(List.of(
                new Author("John Doe", 2, true),
                new Author("Lisa Doe", 2, true)
        ));
        Book book = new Book(writers, "Some Book", "USA");
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(book));
        final String bookJson =
                "{"
                        + "\"name\":\"Changed Book Name\","
                        + "\"country\":\"China\","
                        + "\"authors\":"
                        + "[{\"name\":\"Other Firstwriter\", \"numberOfHands\":1, \"isHuman\":true},"
                        + "{\"name\":\"Other Secondwriter\", \"numberOfHands\":2, \"isHuman\":false}]"
                        + "}";
        final Book deserializedBook = gson.fromJson(bookJson, Book.class);
        System.out.println(deserializedBook);
        JAXBContext context = JAXBContext.newInstance(Author.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(writers.get(1), writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Author result = (Author) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
        JSONObject jsonAuthor = new JSONObject("{\"name\":\"Tony Hawk\", \"numberOfHands\":2, \"isHuman\":true}");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", book.getName());
        jsonObject.put("country", book.getCountry());
        jsonObject.put("authors", jsonAuthor);
        System.out.println(jsonObject);
        System.out.println(new JSONObject(book));
    }
}
