package main.tests;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;

import main.resources.JavaClientInstantiationExample;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITests {

    private static JavaClientInstantiationExample ex;

    @BeforeClass
    public static void setUp() throws Exception {
        ex = new JavaClientInstantiationExample();
        ex.init();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        ex.endSession();
    }

    @Test
    public void getProductList() throws InterruptedException,
            ExecutionException {

        int count = ex.getAllProductsAndPrint();
        assertTrue(count > 0);
    }

    @Test
    public void getProductByIdAsync() {

        String result = ex
                .getProductByIdAsync("0a4a2278-c20a-4645-891a-8ff360a5dda8");

        assertTrue(result != null);
    }

}
