package ru.job4j.todolist.logic;

import ru.job4j.Store;
import ru.job4j.todolist.models.Item;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

/**
 * MainLogic
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.04.2020
 */
public class MainLogic {
    private static final String DESCRIPTION = "desc";
    private static final MainLogic LOGIC = new MainLogic();
    private static final String ID = "id";
    private static final String DONE = "done";
    private final Store<Item> store = DBStore.getInstance();

    /**
     * Private constructor.
     */
    private MainLogic() {
    }

    /**
     * Instance getter.
     * @return MainLogic instance
     */
    public static MainLogic getInstance() {
        return LOGIC;
    }

    /**
     * Return List of all tasks or {@code null}.
     * @return all tasks or {@code null}
     */
    public List<Item> allItem() {
        return this.store.findAll();
    }

    /**
     * Add new task to store.
     * @param req request
     * @return task with id
     */
    public Item addItem(final HttpServletRequest req) {
        Item item = this.store.add(
                new Item()
                        .setDescription(req.getParameter(DESCRIPTION))
                        .setCreate(new Timestamp(System.currentTimeMillis()))
        );
        return item.getId() == 0 ? null : item;
    }

    /**
     * Chang done field in item.
     * @param req request
     */
    public void doneChang(final HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter(ID));
        Item old = this.store.findById(new Item().setId(id));
        if (old != null) {
            old.setDone(Boolean.parseBoolean(req.getParameter(DONE)));
            this.store.update(old);
        }
    }
}
