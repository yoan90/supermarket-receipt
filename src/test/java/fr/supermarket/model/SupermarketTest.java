package fr.esiea.supermarket.model;
import fr.esiea.supermarket.model.*;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SupermarketTest {

    // Test 2.5 kg de pommes à 1.99€
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


// Test des 10% de remise sur le rix sachant que le prix normal est de 2.49€ par sachet 
@Test
    public void TestTenPercentDiscount() {

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

 // Test 3 pour le prix de 2 brosse à dents
 @Test
    public void testThreeForTwo() {

        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 3.0);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush,0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expectedTotalPrice = (0.99*2);
        double totalPrice = receipt.getTotalPrice();
        Assertions.assertThat(totalPrice).isEqualTo(expectedTotalPrice).as("Test three toothbrush for the price of two");

    }
    // Test 5 dentifrice pour 7.49€, sachant qu le prix à l'unité est de 1.79€
    
@Test 
    public void testFiveForAmount() {

        SupermarketCatalog catalog = new FakeCatalog();
        Product toothpaste = new Product("toothpaste", ProductUnit.Each);
        catalog.addProduct(toothpaste,1.79);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothpaste, 5.0);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, toothpaste,7.49);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expectedTotalPrice = 7.49;
        double totalPrice = receipt.getTotalPrice();
        Assertions.assertThat(totalPrice).isEqualTo(expectedTotalPrice).as("Test five toothpaste discount");

    }

@Test //Test deux boites de tomate cerise pour 0.99€, sachant que le prix normale est de 0.69€ par boite

    public void testTwoForAmount() {

        SupermarketCatalog catalog = new FakeCatalog();

        Product tomatoesBox = new Product("tomatoes Boxes", ProductUnit.Each);
        catalog.addProduct(tomatoesBox,0.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(tomatoesBox, 2.0);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, tomatoesBox,0.99);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expectedTotalPrice = 0.99;
        double totalPrice = receipt.getTotalPrice();
        Assertions.assertThat(totalPrice).isEqualTo(expectedTotalPrice).as("Test two cherry tomatoe boxes discount");

    }
    
 @Test //Test aucune remise proposée
    public void testWithoutDiscountProposed() {

        SupermarketCatalog catalog = new FakeCatalog();

        Product tomatoesBox = new Product("tomatoes Boxes", ProductUnit.Each);
        catalog.addProduct(tomatoesBox, 0.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(tomatoesBox, 9.0);

        Teller teller = new Teller(catalog);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expectedTotalPrice = (0.99*9);
        double totalPrice = receipt.getTotalPrice();
        Assertions.assertThat(totalPrice).isEqualTo(expectedTotalPrice).as("Test Without discount proposed");

    }
    @Test
    public void testEqual() {
        Product apples = new Product("apples", ProductUnit.Kilo);
        Product mango = new Product("mango", ProductUnit.Each);
        
        Product firstapple = new Product("apple 1", ProductUnit.Kilo);
        Product secondapple = new Product("apple 2", ProductUnit.Each);
        Product Thirdapple = new Product("apple 3", ProductUnit.Each);
        Product secondmango = new Product("mango 2", ProductUnit.Each);
        
        
        Assertions.assertThat(apples.equals(null)).isFalse();
         Assertions.assertThat(apples.equals(null)).isFalse();

        Assertions.assertThat(mango.equals(apples)).isFalse();
        Assertions.assertThat(apples.equals(apples)).isTrue();
        Assertions.assertThat(mango.equals(secondmango)).isFalse();
        Assertions.assertThat(mango.equals("mango")).isFalse();
    }
    
    
   @Test
    public void testBasicShoppingCart(){
        Product toothbrush = new Product("toothpaste", ProductUnit.Each);
        ShoppingCart cart = new ShoppingCart();
        
        catalog.addProduct(toothpaste,1.79);
        cart.addItemQuantity(toothpaste, 2);

        Teller teller = new Teller(catalog);
        Receipt receipt = teller.checksOutArticlesFrom(cart)
            
        Assertions.assertThat(cart.productQuantities.values().toString()).isNotNull();
        //Assertions.assertThat(receipt.getTotalPrice()).isEqualTo(3.58).as("total cart price");
    }
    
    
    
    
 }
