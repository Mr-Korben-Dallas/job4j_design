package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenTypeIsModerator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Moderator"));
        Role result = store.findById("1");
        assertThat(result.getType(), is("Moderator"));
    }

    @Test
    public void whenAddAndFindThenTypeIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Moderator"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindTypeIsAdministrator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.add(new Role("1", "Moderator"));
        Role result = store.findById("1");
        assertThat(result.getType(), is("Administrator"));
    }

    @Test
    public void whenReplaceThenTypeIsModerator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.replace("1", new Role("1", "Moderator"));
        Role result = store.findById("1");
        assertThat(result.getType(), is("Moderator"));
    }

    @Test
    public void whenNoReplaceTypeThenNoChangeType() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.replace("10", new Role("10", "Moderator"));
        Role result = store.findById("1");
        assertThat(result.getType(), is("Administrator"));
    }

    @Test
    public void whenDeleteTypeThenTypeIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteTypeThenTypeIsModerator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.delete("5");
        Role result = store.findById("1");
        assertThat(result.getType(), is("Administrator"));
    }
}