package com.example.wt_laba2.bean;

/**

 Class representing a shopping item.
 */
public class ShoppingItem {

    /** The product associated with the shopping item. */
    public Product item;

    /** The quantity of this product in the shopping cart. */
    public int quantity;

    /**

     Constructor for creating a shopping item.
     @param product the product to be associated with the shopping item
     @param quantity the quantity of this product in the shopping cart
     */
    public ShoppingItem(Product product, int quantity) {
        item = (Product) product.Clone(); // Cloning the product to avoid issues with modifying the original object
        this.quantity = quantity;
    }
    /**

     Get the product associated with the shopping item.
     @return the product associated with the shopping item
     */
    public Product getProduct() {
        return item;
    }
    /**

     Get the quantity of this product in the shopping cart.
     @return the quantity of this product in the shopping cart
     */
    public int getQuantity() {
        return quantity;
    }
}