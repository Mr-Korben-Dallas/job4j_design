package ru.job4j.serialization.json;

import java.util.List;
import java.util.Objects;

public class Book {
    private List<Author> authors;
    private String name;
    private String country;

    public Book(List<Author> authors, String name, String country) {
        this.authors = authors;
        this.name = name;
        this.country = country;
    }

    public List<Author> getAuthors() {
            return authors;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(authors, book.authors) && Objects.equals(name, book.name) && Objects.equals(country, book.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authors, name, country);
    }

    @Override
    public String toString() {
        return "Book{"
                + "authors="
                + authors
                + ", name='"
                + name
                + '\''
                + ", country="
                + country
                + '}';
    }
}
