package main.java;

import main.resources.JavaClientInstantiationExample;

public class TestClass {

    public static void main(String[] args) {

        try {
            JavaClientInstantiationExample ex = new JavaClientInstantiationExample();
            ex.init();

            System.out.println("The count of Products was: "
                    + ex.getAllProductsAndPrint());

            System.out
                    .println("The Name of the Produkt is: "
                            + ex.getProductByIdAsync("sku_MB_PREMIUM_TECH_T_variant1_1413361099892"));

            ex.endSession();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
