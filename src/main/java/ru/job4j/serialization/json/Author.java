package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int numberOfHands;
    @XmlAttribute
    private boolean isHuman;

    public Author() {
    }

    public Author(String name, int numberOfHands, boolean isHuman) {
        this.name = name;
        this.numberOfHands = numberOfHands;
        this.isHuman = isHuman;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfHands() {
        return numberOfHands;
    }

    public boolean isHuman() {
        return isHuman;
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
