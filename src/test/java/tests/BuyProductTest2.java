package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class BuyProductTest2 extends BaseTest {

    @Test
    public void buyProductTest() {

        // Login
        LoginPage login =
                new LoginPage(driver);

        login.login(
                config.getProperty("username"),
                config.getProperty("password"));

        // Add Product
        ProductsPage product =
                new ProductsPage(driver);

        product.addProductToCart();

        // Checkout
        CartPage cart =
                new CartPage(driver);

        cart.clickCheckout();

        // Complete Order
        CheckoutPage checkout =
                new CheckoutPage(driver);

        checkout.completeOrder(
                config.getProperty("firstname"),
                config.getProperty("lastname"),
                config.getProperty("zipcode"));

        // Validation
        Assert.assertTrue(
                checkout.isOrderSuccessful(),
                "Order was not successful");
    }
}