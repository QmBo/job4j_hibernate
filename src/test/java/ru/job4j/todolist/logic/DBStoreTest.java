package ru.job4j.todolist.logic;

import org.junit.Test;
import ru.job4j.Store;
import ru.job4j.todolist.models.Item;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class DBStoreTest {

    private static final String TEST = "Test";

    @Test
    public void whenAddItemThenTaskInStore() {
        Store<Item> store = DBStore.getInstance();
        Item item = store.add(new Item().setDescription(TEST));
        assertThat(item.getId(), not(0));
        assertThat(store.findAll().stream().filter(item1 -> item1.getId() == item.getId()).count(), is(1L));
        store.delete(item);
    }

}