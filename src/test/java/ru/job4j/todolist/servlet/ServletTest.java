package ru.job4j.todolist.servlet;

import org.apache.logging.log4j.LogManager;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.todolist.logic.DBStore;
import ru.job4j.todolist.logic.MemoryStore;
import ru.job4j.todolist.logic.Store;
import ru.job4j.todolist.models.Item;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.powermock.api.mockito.PowerMockito.*;


@PowerMockIgnore({"org.hibernate.*", "com.sun.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({DBStore.class, LogManager.class})
public class ServletTest {
    private static final String DESCRIPTION = "desc";
    private static final String TEST_TASK_DESCRIPTION = "Test Task Description";
    private static final String TEST_DONE_DESCRIPTION = "Test Done Description";
    private static final String ID = "id";
    private static final String DONE = "done";

    @Before
    public void setUp() {
        mockStatic(DBStore.class);
        mockStatic(LogManager.class);
    }

    @Test
    public void whenAddTaskThenTaskInStore() throws IOException {
        Store<Item> store = MemoryStore.getInstance();
        when(DBStore.getInstance()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        ServletOutputStream stream = new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }
            @Override
            public void setWriteListener(WriteListener writeListener) {
            }
            @Override
            public void write(int b) {
                array.write(b);
            }
        };
        when(resp.getOutputStream()).thenReturn(stream);
        when(req.getParameter(DESCRIPTION)).thenReturn(TEST_TASK_DESCRIPTION);
        new AddTaskServlet().doPost(req, resp);
        List<Item> items = store.findAll();
        assertThat(items.stream().filter(i -> TEST_TASK_DESCRIPTION.equals(i.getDescription())).count(), is(1L));
        assertThat(array.toString().contains(TEST_TASK_DESCRIPTION), is(true));
    }

    @Test
    public void whenChangeTaskDoneThenTaskDoneChang() {
        Store<Item> store = MemoryStore.getInstance();
        when(DBStore.getInstance()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        Item item = store.add(new Item().setDescription(TEST_DONE_DESCRIPTION));
        List<Item> items = store.findAll();
        Item result = items.stream().filter(
                i -> TEST_DONE_DESCRIPTION.equals(i.getDescription())
        ).findFirst().orElseThrow();
        assertThat(item.isDone(), is(false));
        when(req.getParameter(ID)).thenReturn(String.valueOf(result.getId()));
        when(req.getParameter(DONE)).thenReturn(String.valueOf(true));
        new ListServlet().doPost(req, resp);
        assertThat(item.isDone(), is(true));
    }
}