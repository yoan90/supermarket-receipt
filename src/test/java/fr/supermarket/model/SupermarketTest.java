package dojo.supermarket.model;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SupermarketTest {

    @Test
    public void testSomething() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        //L'offre sur la brosse à dents n'a aucune influence sur le prix du kilos de pomme
        double expectedTotalPrice = 2.5 * 1.99;
        double totalPrice = receipt.getTotalPrice();
        Assertions.assertThat(receipt.getTotalPrice()).isEqualTo(expectedTotalPrice).as("apple test and toothbrush");
    }
}

// Test des 10% de remise sur le rix sachant que le prix normal est de 2.49€ par sachet 

@Test
    public void testTenPercentDiscount() {

        SupermarketCatalog catalog = new FakeCatalog();
        Product rice = new Product("rice",ProductUnit.Each);
        catalog.addProduct(rice, 2.49);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(rice);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, rice,10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expectedTotalPrice =  2.49 - ((2.49*10)/100);
        double totalPrice = receipt.getTotalPrice();
        Assertions.assertThat(totalPrice).isEqualTo(expectedTotalPrice).as("test ten percent discount on the rice bag");

    }
