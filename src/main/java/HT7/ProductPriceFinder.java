package HT7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class ProductPriceFinder {
    private BinarySearchTree<Product> bst;

    public ProductPriceFinder() {
        bst = new BinarySearchTree<>();
    }

    public void loadCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header
            
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] data = line.split(",", -1); // El -1 asegura que se incluyan campos vacíos
                // Verificamos que la línea tenga suficientes columnas
                if (data.length < 20) {
                    System.out.println("Línea " + lineNumber + " incompleta: " + line);
                    continue;
                }

                // Mapeamos las columnas
                String sku = data[6]; // SKU
                String priceRetailStr = data[9]; // PRICE_RETAIL
                String priceCurrentStr = data[10]; // PRICE_CURRENT
                String productName = data[18]; // PRODUCT_NAME
                String category = data[15]; // DEPARTMENT

                // Validamos que los campos no estén vacíos
                if (sku.isEmpty() || productName.isEmpty() || category.isEmpty()) {
                    System.out.println("Línea " + lineNumber + ": SKU, Product_Name o Category vacío. Saltando línea.");
                    continue;
                }

                // Parseamos los precios con manejo de valores vacíos
                double priceRetail;
                try {
                    priceRetail = priceRetailStr.isEmpty() ? 0.0 : Double.parseDouble(priceRetailStr);
                } catch (NumberFormatException e) {
                    System.out.println("Línea " + lineNumber + ": PRICE_RETAIL no válido: " + priceRetailStr + ". Saltando línea.");
                    continue;
                }

                double priceCurrent;
                try {
                    priceCurrent = priceCurrentStr.isEmpty() ? 0.0 : Double.parseDouble(priceCurrentStr);
                } catch (NumberFormatException e) {
                    System.out.println("Línea " + lineNumber + ": PRICE_CURRENT no válido: " + priceCurrentStr + ". Saltando línea.");
                    continue;
                }

                Product product = new Product(sku, priceRetail, priceCurrent, 
                                          productName, category);
                bst.insert(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProductPriceFinder finder = new ProductPriceFinder();
        String filePath = "C:\\Users\\carlo\\Downloads\\archive (1)\\home appliance skus lowes.csv";
        finder.loadCSV(filePath);
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Buscar por SKU");
            System.out.println("2. Listar precios ascendente");
            System.out.println("3. Listar precios descendente");
            System.out.println("4. Salir");
            
            int option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                    System.out.print("Ingrese SKU: ");
                    String sku = scanner.nextLine();
                    Product p = finder.bst.find(sku);
                    if (p != null) System.out.println(p);
                    else System.out.println("Producto no encontrado");
                    break;
                case 2:
                    finder.bst.inOrderAscending();
                    break;
                case 3:
                    finder.bst.inOrderDescending();
                    break;
                case 4:
                    return;
            }
        }
    }
}