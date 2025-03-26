package HT7;

/**
 * Represents a product with details such as SKU, retail price, current price, name, and category.
 * Implements {@link Comparable} to allow ordering by SKU.
 */
public class Product implements Comparable<Product> {
    private String sku;
    private double priceRetail;
    private double priceCurrent;
    private String productName;
    private String category;

    /**
     * Constructs a new Product with the specified details.
     *
     * @param sku          the unique identifier of the product
     * @param priceRetail  the retail price of the product
     * @param priceCurrent the current price of the product
     * @param productName  the name of the product
     * @param category     the category of the product
     */
    public Product(String sku, double priceRetail, double priceCurrent, 
                   String productName, String category) {
        this.sku = sku;
        this.priceRetail = priceRetail;
        this.priceCurrent = priceCurrent;
        this.productName = productName;
        this.category = category;
    }

    /**
     * Gets the SKU of the product.
     *
     * @return the SKU
     */
    public String getSku() {
        return sku;
    }

    /**
     * Gets the retail price of the product.
     *
     * @return the retail price
     */
    public double getPriceRetail() {
        return priceRetail;
    }

    /**
     * Gets the current price of the product.
     *
     * @return the current price
     */
    public double getPriceCurrent() {
        return priceCurrent;
    }

    /**
     * Gets the name of the product.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Gets the category of the product.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Compares this product with another product based on their SKUs.
     *
     * @param other the product to compare with
     * @return a negative integer, zero, or a positive integer as this SKU
     *         is less than, equal to, or greater than the specified product's SKU
     */
    @Override
    public int compareTo(Product other) {
        return this.sku.compareTo(other.sku);
    }

    /**
     * Returns a string representation of the product.
     *
     * @return a string containing the SKU, product name, category, retail price, and current price
     */
    @Override
    public String toString() {
        return "SKU: " + sku + ", Product: " + productName + 
               ", Category: " + category + ", Retail: $" + priceRetail + 
               ", Current: $" + priceCurrent;
    }
}