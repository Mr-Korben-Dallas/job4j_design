package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Author> writers = new ArrayList<>(List.of(
                new Author("John Doe"),
                new Author("Lisa Doe")
        ));
        Book book = new Book(writers, "Some Book", "USA");
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(book));
        final String bookJson =
                "{"
                        + "\"name\":\"Changed Book Name\","
                        + "\"country\":\"China\","
                        + "\"authors\":"
                        + "[{\"name\":\"Other Firstwriter\"},{\"name\":\"Other Secondwriter\"}]"
                        + "}";
        final Book deserializedBook = gson.fromJson(bookJson, Book.class);
        System.out.println(deserializedBook);
    }
}
