package fr.esiea.supermarket.model;
import fr.esiea.supermarket.model.*;
import fr.esiea.supermarket.ReceiptPrinter;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SupermarketTest {

    // Test 2.5 kg de pommes à 1.99€
 @Test
    public void testSomething() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("Toothbrush", ProductUnit.Each);
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
        Assertions.assertThat(receipt.getTotalPrice()).isEqualTo(expectedTotalPrice).as("Special price on Apples and Toothbrush");
    }


// Test des 10% de remise sur le rix sachant que le prix normal est de 2.49€ par sachet 
@Test
    public void TestTenPercentDiscount() {

        SupermarketCatalog catalog = new FakeCatalog();
        Product rice = new Product("Rice",ProductUnit.Each);
        catalog.addProduct(rice, 2.49);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(rice);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, rice,10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expectedTotalPrice =  2.49 - ((2.49*10)/100);
        double totalPrice = receipt.getTotalPrice();
        Assertions.assertThat(totalPrice).isEqualTo(expectedTotalPrice).as("Get ten percent off discount on the rice bag");

    }

 // Test 3 pour le prix de 2 brosse à dents
 @Test
    public void testThreeForTwo() {

        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("Toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 3.0);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush,0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expectedTotalPrice = (0.99*2);
        double totalPrice = receipt.getTotalPrice();
        Assertions.assertThat(totalPrice).isEqualTo(expectedTotalPrice).as("Get three toothbrush for the price of two");

    }
    // Test 5 dentifrices pour 7.49€, sachant qu le prix à l'unité est de 1.79€
    
@Test 
    public void testFiveForAmount() {

        SupermarketCatalog catalog = new FakeCatalog();
        Product toothpaste = new Product("Toothpaste", ProductUnit.Each);
        catalog.addProduct(toothpaste,1.79);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothpaste, 5.0);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, toothpaste,7.49);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expectedTotalPrice = 7.49;
        double totalPrice = receipt.getTotalPrice();
        Assertions.assertThat(totalPrice).isEqualTo(expectedTotalPrice).as("Get five toothpaste for a special price");

    }

@Test //Test deux boites de tomate cerise pour 0.99€, sachant que le prix normale est de 0.69€ par boite

    public void testTwoForAmount() {

        SupermarketCatalog catalog = new FakeCatalog();

        Product tomatoesBox = new Product("Cherry Tomatoes Boxes", ProductUnit.Each);
        catalog.addProduct(tomatoesBox,0.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(tomatoesBox, 2.0);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, tomatoesBox,0.99);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expectedTotalPrice = 0.99;
        double totalPrice = receipt.getTotalPrice();
        Assertions.assertThat(totalPrice).isEqualTo(expectedTotalPrice).as("Get two cherry tomatoe boxes for a special price");

    }
    
 @Test //Test aucune remise proposée
    public void testWithoutDiscountProposed() {

        SupermarketCatalog catalog = new FakeCatalog();

        Product tomatoesBox = new Product("Cherry Tomatoes Boxes", ProductUnit.Each);
        catalog.addProduct(tomatoesBox, 0.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(tomatoesBox, 9.0);

        Teller teller = new Teller(catalog);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double expectedTotalPrice = (0.99*9);
        double totalPrice = receipt.getTotalPrice();
        Assertions.assertThat(totalPrice).isEqualTo(expectedTotalPrice).as("No discount");

    }
    @Test
    public void testEqual() {
        Product apples = new Product("Apples", ProductUnit.Kilo);
        Product mango = new Product("Mango", ProductUnit.Each);
        
        Product firstapple = new Product("Apple 1", ProductUnit.Kilo);
        Product secondapple = new Product("Apple 2", ProductUnit.Each);
        Product Thirdapple = new Product("Apple 3", ProductUnit.Each);
        Product secondmango = new Product("Mango 2", ProductUnit.Each);
        
        
        Assertions.assertThat(apples.equals(null)).isFalse();
        Assertions.assertThat(apples.equals(null)).isFalse();
        Assertions.assertThat(mango.equals(apples)).isFalse();
        Assertions.assertThat(apples.equals(apples)).isTrue();
        Assertions.assertThat(mango.equals(secondmango)).isFalse();
        Assertions.assertThat(mango.equals("Mango")).isFalse();
    }
    
    
   @Test
    public void testBasicShoppingCart(){
        Product toothpaste = new Product("Toothpaste", ProductUnit.Each);
        ShoppingCart cart = new ShoppingCart();
        
        cart.addItemQuantity(toothpaste, 2);
        Assertions.assertThat(cart.productQuantities.values().toString()).isNotNull();
    }
    
  
 //}
    
 /*************************** RECEIPT ITEM **************************/
 
//public class ReceiptItemTest {
    
 @Test
    public void testReceiptPrinterWithoutDiscount(){
        
        SupermarketCatalog catalog = new FakeCatalog();
        ReceiptPrinter printer = new ReceiptPrinter();
        ShoppingCart cart = new ShoppingCart();
        Teller teller = new Teller(catalog);
       

        Product apples = new Product("Apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);
        cart.addItemQuantity(apples,10);

        Product toothbrush = new Product("Toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        cart.addItemQuantity(toothbrush,5);
        
        Receipt receipt = teller.checksOutArticlesFrom(cart);
 

        String string_init = printer.printReceipt(receipt);
        String result = "Apples" + "   " + "19.9" + "\n" + "Toothbrush" + "4.95 = 24.85";
        
        Assertions.assertThat(printer.printReceipt(receipt)).isNotBlank();
        Assertions.assertThat(string_init).isEqualTo(result);
    }
    
    
  @Test
      public void testReceiptItemQuantity() {
        Product toothbrush = new Product("Toothbrush", ProductUnit.Each);
        ReceiptItem item = new ReceiptItem(toothbrush, 2, 3, 6);
        double expectedQuantity = 2;
        Assertions.assertThat(expectedQuantity).isEqualTo(item.getQuantity()).as("Get item quantity");
    }
  

  @Test
    public void testReceiptItemAttribut(){

        Product apples = new Product("Apples", ProductUnit.Kilo);
        Product flan = new Product("Flan", ProductUnit.Each);

        ReceiptItem receiptItemapple = new ReceiptItem(apples,2,3,6);
        ReceiptItem receiptItemflan = new ReceiptItem(flan,1,4,4);

        Assertions.assertThat(receiptItemapple.getProduct()).isEqualTo(apples);
        Assertions.assertThat(receiptItemapple.getQuantity()).isEqualTo(2);
        Assertions.assertThat(receiptItemapple.getPrice()).isEqualTo(3);
        Assertions.assertThat(receiptItemapple.getTotalPrice()).isEqualTo(6);     
        Assertions.assertThat(receiptItemapple.getTotalPrice()).isEqualTo(4);


    }
    
  @Test
    public void testReceiptItemEquals(){
        
        Product fleur = new Product("fleur", ProductUnit.Each);
        Product vin = new Product("vin", ProductUnit.Each);
        Product eau = new Product("eau", ProductUnit.Kilo);
        ReceiptItem receiptItemfleur = new ReceiptItem(fleur,3,3,9);
        ReceiptItem receiptItemvin = new ReceiptItem(vin,10,1,10);
        ReceiptItem receiptItemeau = new ReceiptItem(eau, 20, 1, 40);
       
         Assertions.assertThat(receiptItemfleur).isNotEqualTo(null);
         Assertions.assertThat(receiptItemeau.equals(eau)).isEqualTo(true);
         Assertions.assertThat(receiptItemvin.equals(eau)).isEqualTo(false);
        Assertions.assertThat(receiptItemfleur.equals("fleur")).isEqualTo(false);
    }
    

 @Test
    public void testFinalPriceItem(){
        Product boussole = new Product("Boussole", ProductUnit.Each);
        ReceiptItem item = new ReceiptItem(boussole, 1, 10, 20 );
        double finalprice = 20;
        Assertions.assertThat(finalprice).isEqualTo(item.getTotalPrice()).as("Get final price");
    }

    
 //}

 /************************* RECEIPT PRINTER ************************/
    
 //public class ReceiptPrinterTest {

    @Test
    public void DefaultReceiptPrinterTest() {


        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("Toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("Apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 2);
        cart.addItemQuantity(toothbrush,1);
        cart.addItemQuantity(apples, 2.5);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 1);
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        ReceiptPrinter printerDefault = new ReceiptPrinter();

        String expectedPrintDefault =
        "Toothbrush                          1.98\n"
        +"  0.99 * 2\n"
        +"Toothbrush                          0.99\n"
        +"Apples                              4.98\n"
        +"  1.99 * 2.500\n"
        +"3 for 2(toothbrush)                -0.99\n"
        +"\n"
        +"Total:                              6.96";
        
        Assertions.assertThat(expectedPrintDefault).isEqualTo(printerDefault.printReceipt(receipt)).as("Default Receipt Printer");
        
    }
    
    
    @Test
    public void WithColsReceiptPrinterTest() {

        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("Toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("Apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 2);
        cart.addItemQuantity(toothbrush,1);
        cart.addItemQuantity(apples, 2.5);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 1);
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        ReceiptPrinter printerDefault = new ReceiptPrinter();
        ReceiptPrinter printerWithCols = new ReceiptPrinter(30);

        String expectedPrintWithCol =
        "Toothbrush                1.98\n"
        +"  0.99 * 2\n"
        +"Toothbrush                0.99\n"
        +"Apples                    4.98\n"
        +"  1.99 * 2.500\n"
        +"3 for 2(toothbrush)      -0.99\n"
        +"\n"
        +"Total:                    6.96";

        Assertions.assertThat(expectedPrintWithCol).isEqualTo(printerWithCols.printReceipt(receipt)).as("Receipt printer loaded with 30 columns");

    }
     
     
     
 }
