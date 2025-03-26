package HT7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class ProductPriceFinderTest {
    private ProductPriceFinder finder;
    private BinarySearchTree<Product> bst;

    @BeforeEach
    void setUp() {
        finder = new ProductPriceFinder();
        bst = new BinarySearchTree<>();
    }

    @Test
    void testInsert() {
        Product p1 = new Product("SKU001", 100.0, 80.0, "Toaster", "Appliances");
        bst.insert(p1);

        Product found = bst.find("SKU001");
        assertNotNull(found, "El producto debería encontrarse después de la inserción");
        assertEquals("SKU001", found.getSku(), "El SKU del producto encontrado debería ser SKU001");
        assertEquals("Toaster", found.getProductName(), "El nombre del producto debería ser Toaster");
    }

    @Test
    void testFind() {
        Product p1 = new Product("SKU001", 100.0, 80.0, "Toaster", "Appliances");
        Product p2 = new Product("SKU002", 150.0, 120.0, "Blender", "Appliances");
        bst.insert(p1);
        bst.insert(p2);

        Product found = bst.find("SKU002");
        assertNotNull(found, "El producto con SKU002 debería encontrarse");
        assertEquals("Blender", found.getProductName(), "El nombre del producto debería ser Blender");

        Product notFound = bst.find("SKU999");
        assertNull(notFound, "No debería encontrarse un producto con SKU999");
    }

    @Test
    void testInOrderAscending() {
        Product p1 = new Product("SKU001", 100.0, 80.0, "Toaster", "Appliances");
        Product p2 = new Product("SKU002", 150.0, 120.0, "Blender", "Appliances");
        Product p3 = new Product("SKU003", 200.0, 180.0, "Microwave", "Appliances");
        bst.insert(p2); // SKU002
        bst.insert(p1); // SKU001
        bst.insert(p3); // SKU003

        // Capturamos la salida de System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        bst.inOrderAscending();

        // Restauramos System.out
        System.setOut(originalOut);

        // La salida esperada debería ser: SKU001, SKU002, SKU003 (en orden ascendente por SKU)
        String expectedOutput = "SKU: SKU001, Product: Toaster, Category: Appliances, Retail: $100.0, Current: $80.0\n" +
                               "SKU: SKU002, Product: Blender, Category: Appliances, Retail: $150.0, Current: $120.0\n" +
                               "SKU: SKU003, Product: Microwave, Category: Appliances, Retail: $200.0, Current: $180.0\n";

        // Normalizamos los saltos de línea en la salida real
        String actualOutput = outContent.toString().replace("\r\n", "\n");

        assertEquals(expectedOutput, actualOutput, "Los productos deberían listarse en orden ascendente por SKU");
    }

    @Test
    void testInOrderDescending() {
        Product p1 = new Product("SKU001", 100.0, 80.0, "Toaster", "Appliances");
        Product p2 = new Product("SKU002", 150.0, 120.0, "Blender", "Appliances");
        Product p3 = new Product("SKU003", 200.0, 180.0, "Microwave", "Appliances");
        bst.insert(p2); // SKU002
        bst.insert(p1); // SKU001
        bst.insert(p3); // SKU003

        // Capturamos la salida de System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        bst.inOrderDescending();

        // Restauramos System.out
        System.setOut(originalOut);

        // La salida esperada debería ser: SKU003, SKU002, SKU001 (en orden descendente por SKU)
        String expectedOutput = "SKU: SKU003, Product: Microwave, Category: Appliances, Retail: $200.0, Current: $180.0\n" +
                               "SKU: SKU002, Product: Blender, Category: Appliances, Retail: $150.0, Current: $120.0\n" +
                               "SKU: SKU001, Product: Toaster, Category: Appliances, Retail: $100.0, Current: $80.0\n";

        // Normalizamos los saltos de línea en la salida real
        String actualOutput = outContent.toString().replace("\r\n", "\n");

        assertEquals(expectedOutput, actualOutput, "Los productos deberían listarse en orden descendente por SKU");
    }
}