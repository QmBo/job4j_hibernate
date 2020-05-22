package ru.job4j.todolist.logic;

import ru.job4j.Store;
import ru.job4j.todolist.models.Item;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * MemoryStore
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 16.04.2020
 */
public class MemoryStore implements Store<Item> {
    private final static MemoryStore STORE = new MemoryStore();
    private final List<Item> list = new CopyOnWriteArrayList<>();
    private Integer lastId = 0;

    /**
     * Private constructor.
     */
    private MemoryStore() {
    }

    /**
     * Get instance.
     * @return instance
     */
    public static Store<Item> getInstance() {
        return STORE;
    }

    /**
     * Add new Item and set Item id.
     * @param element Item to add
     * @return Item with id
     */
    @Override
    public Item add(Item element) {
        this.list.add(element);
        return element.setId(++this.lastId);
    }

    /**
     * Update Item.
     * @param element new data to item
     */
    @Override
    public void update(Item element) {
        Item result = this.findById(element);
        if (result != null) {
            this.list.add(result.getId(), element);
        }
    }

    /**
     * Delete Item.
     * @param element item to delete
     */
    @Override
    public void delete(Item element) {
        Item result = this.findById(element);
        if (result != null) {
            this.list.remove(element);
        }
    }

    /**
     * All Item finder order by create time or {@code null} if not items.
     * @return all items from memory or {@code null} if not items
     */
    @Override
    public List<Item> findAll() {
        return this.list;
    }

    /**
     * Find Item by id.
     * @param element item to find
     * @return founded Item on {@code null}
     */
    @Override
    public Item findById(Item element) {
        Item result = null;
        for (Item item : this.list) {
            if (element.getId() == item.getId()) {
                result = item;
                break;
            }
        }
        return result;
    }
}
