package ru.job4j.serialization.json;

import java.util.Objects;

public class Author {
    private String name;
    private int numberOfHands;
    private boolean isHuman;

    public Author(String name, int numberOfHands, boolean isHuman) {
        this.name = name;
        this.numberOfHands = numberOfHands;
        this.isHuman = isHuman;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Author author = (Author) o;
        return numberOfHands == author.numberOfHands && isHuman == author.isHuman && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfHands, isHuman);
    }

    @Override
    public String toString() {
        return "Author{"
                + "name='"
                + name
                + '\''
                + ", numberOfHands="
                + numberOfHands
                + ", isHuman="
                + isHuman
                + '}';
    }
}
