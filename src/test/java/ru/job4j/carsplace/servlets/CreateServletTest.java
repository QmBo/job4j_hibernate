package ru.job4j.carsplace.servlets;

import org.apache.logging.log4j.LogManager;
import org.junit.Before;
import org.junit.Test;


import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.carsplace.logic.DBStore;
import ru.job4j.carsplace.logic.MemoryStore;
import ru.job4j.carsplace.models.Maker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.*;
/**
 * CreateServletTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.hibernate.*"})
@PrepareForTest({ImageUploader.class, LogManager.class, DBStore.class, ServletOutputStream.class})
public class CreateServletTest {
    private static final String MAKER = "maker";
    private static final String GEN = "gen";
    private static final String MODEL = "model";
    private static final String BODY = "body";
    private static final String ENGINE = "engine";
    private static final String LOGIN = "login";
    private static final String DOORS = "doors";
    private static final String DRIVE = "drive";
    private static final String GEAR_BOX = "gearBox";
    private static final String NAME = "name";
    private static final String ODD = "odd";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String NUMBERS = "numbersOfPhoto";
    private static final String YEAR = "year";
    private static final String LOAD = "load";

    @Before
    public void setUp() {
        mockStatic(LogManager.class);
        mockStatic(ImageUploader.class);
        mockStatic(DBStore.class);
        mockStatic(ServletOutputStream.class);
    }

    @Test
    public void whenAddAddThenAddInStore() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        MemoryStore store = MemoryStore.getInstance();
        when(DBStore.getInstance()).thenReturn(store);
        when(request.getAttribute(NAME)).thenReturn("ADD NAME");
        when(request.getAttribute(MAKER)).thenReturn("1");
        when(request.getAttribute(MODEL)).thenReturn("3");
        when(request.getAttribute(GEN)).thenReturn("7");
        when(request.getAttribute(BODY)).thenReturn("26");
        when(request.getAttribute(DOORS)).thenReturn("12");
        when(request.getAttribute(ENGINE)).thenReturn("70");
        when(request.getAttribute(DRIVE)).thenReturn("1");
        when(request.getAttribute(DESCRIPTION)).thenReturn("ADD DESCRIPTION");
        when(request.getAttribute(PRICE)).thenReturn("1000");
        when(request.getAttribute(ODD)).thenReturn("112");
        when(request.getAttribute(LOGIN)).thenReturn("1");
        when(request.getAttribute(NUMBERS)).thenReturn(0);
        when(request.getAttribute(GEAR_BOX)).thenReturn("1");
        when(request.getAttribute(YEAR)).thenReturn("2020");
        when(ImageUploader.upload(any(HttpServlet.class), any(HttpServletRequest.class))).thenReturn(null);
        new CreateServlet().doPost(request, response);
        long count = store.allAdd().stream()
                .filter(add -> (add.getName().equals("ADD NAME") && add.getDescription().equals("ADD DESCRIPTION")))
                .count();
        assertThat(count, is(1L));
    }

    @Test
    public void whenLoadThenReturnMaker() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        MemoryStore store = MemoryStore.getInstance();
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
        when(DBStore.getInstance()).thenReturn(store);
        when(request.getParameterNames()).thenReturn(new EClass(LOAD));
        when(response.getOutputStream()).thenReturn(stream);
        new CreateServlet().doGet(request, response);
        assertThat(array.toString().contains("[]"), is(true));
        store.addModels(List.of(new Maker().setName("TestName").setId(7)));
        new CreateServlet().doGet(request, response);
        assertThat(array.toString().contains("[{\"id\": 7, \"name\": \"TestName\"}]"), is(true));
    }

    private static class EClass implements Enumeration<String> {
        private final String string;
        private int i = 0;
        public EClass(String string) {
            this.string = string;
        }

        @Override
        public boolean hasMoreElements() {
            return i == 0;
        }

        @Override
        public String nextElement() {
            this.i++;
            return this.string;
        }
    }
}