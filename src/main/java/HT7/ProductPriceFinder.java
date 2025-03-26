package HT7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * A class that manages a collection of products loaded from a CSV file and provides
 * functionality to search and list products using a Binary Search Tree.
 */
public class ProductPriceFinder {
    private BinarySearchTree<Product> bst;

    /**
     * Constructs a new ProductPriceFinder with an empty Binary Search Tree.
     */
    public ProductPriceFinder() {
        bst = new BinarySearchTree<>();
    }

    /**
     * Loads product data from a CSV file into the Binary Search Tree.
     * The CSV file is expected to have columns in the following order:
     * CATEGORY, DATE_SCRAPED, SORT_BY, RUN_START_DATE, SUBCATEGORY, SHIPPING_LOCATION,
     * SKU, COUNTRY, BRAND, PRICE_RETAIL, PRICE_CURRENT, SELLER, PRODUCT_URL, CURRENCY,
     * BREADCRUMBS, DEPARTMENT, PROMOTION, BESTSELLER_RANK, PRODUCT_NAME, WEBSITE_URL.
     * Only SKU, PRICE_RETAIL, PRICE_CURRENT, PRODUCT_NAME, and DEPARTMENT are used.
     *
     * @param filename the path to the CSV file
     */
    public void loadCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();
            
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] data = line.split(",", -1);
                if (data.length < 20) {
                    System.out.println("Line " + lineNumber + " incomplete: " + line);
                    continue;
                }

                String sku = data[6];
                String priceRetailStr = data[9];
                String priceCurrentStr = data[10];
                String productName = data[18];
                String category = data[15];

                if (sku.isEmpty() || productName.isEmpty() || category.isEmpty()) {
                    System.out.println("Line " + lineNumber + ": SKU, Product_Name, or Category is empty. Skipping line.");
                    continue;
                }

                double priceRetail;
                try {
                    priceRetail = priceRetailStr.isEmpty() ? 0.0 : Double.parseDouble(priceRetailStr);
                } catch (NumberFormatException e) {
                    System.out.println("Line " + lineNumber + ": Invalid PRICE_RETAIL: " + priceRetailStr + ". Skipping line.");
                    continue;
                }

                double priceCurrent;
                try {
                    priceCurrent = priceCurrentStr.isEmpty() ? 0.0 : Double.parseDouble(priceCurrentStr);
                } catch (NumberFormatException e) {
                    System.out.println("Line " + lineNumber + ": Invalid PRICE_CURRENT: " + priceCurrentStr + ". Skipping line.");
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

    /**
     * Runs the main program, allowing the user to interact with the product data
     * through a command-line interface. Options include searching by SKU, listing
     * products in ascending or descending order, and exiting the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ProductPriceFinder finder = new ProductPriceFinder();
        String filePath = "C:\\Users\\carlo\\Downloads\\archive (1)\\home appliance skus lowes.csv";
        finder.loadCSV(filePath);
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Search by SKU");
            System.out.println("2. List prices in ascending order");
            System.out.println("3. List prices in descending order");
            System.out.println("4. Exit");
            
            int option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                    System.out.print("Enter SKU: ");
                    String sku = scanner.nextLine();
                    Product p = finder.bst.find(sku);
                    if (p != null) System.out.println(p);
                    else System.out.println("Product not found");
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