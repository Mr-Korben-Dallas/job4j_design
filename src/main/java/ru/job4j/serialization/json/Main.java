package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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
    }
}
