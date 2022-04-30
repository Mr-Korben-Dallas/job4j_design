package ru.job4j.generics;

public class Role extends Base {
    private final String type;

    public Role(String id, String type) {
        super(id);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
