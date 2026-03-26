package Controller;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Model.Product.Bill;
import Model.Product.Item;
import Model.Users.Cashier;

public class CashierController {

    private Cashier cashier;
    private ArrayList<Item> cart;

    public CashierController(Cashier cashier) {
        this.cashier = cashier;
        this.cart = new ArrayList<>();
    }

    // Add item to the cart
    public void addItemToCart(Item item, int quantity) {
        item.setOrderAmount(quantity);
        cart.add(item);
        // Debugging: Print each item added to the cart
        System.out.println("Added to cart: " + item.getName() + " - " + item.getSellingPrice() + " x " + quantity);
    }

    // Remove item from the cart
    public void removeItemFromCart(Item item) {
        cart.remove(item);
    }

    // Get all the items in the cart
    public ArrayList<Item> getCart() {
        return cart;
    }

    // Calculate the total amount for the items in the cart
    public double calculateTotalAmount() {
        double total = 0.0;
        for (Item item : cart) {
            total += item.getSellingPrice() * item.getOrderAmount();
        }
        return total;
    }

    // Generate the bill
    public void generateBill() {
        // Generate the bill using the current cart items
        Bill bill = new Bill(cart, cashier);
        bill.generateBillFile();  // Call the generateBillFile() method from the Bill class
        
        // Now that the bill has been generated, clear the cart
        cart.clear();
    }

    // Get available items (this can be modified as needed to get data from a database or file)
    public ArrayList<Item> getAvailableItems() {
        ArrayList<Item> itemList = new ArrayList<Item>();
        // You can load the items from a file or database as needed
        // For example, you already have code for loading items from a file (aa_ItemList.dat)
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_ItemList.dat"))) {
            while (true) {
                try {
                    Item x = (Item) in.readObject();
                    itemList.add(x);
                } catch (java.io.EOFException e) {
                    break;  
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }
    
    public void clearCart() {
        cart.clear();  // Clears the cart by removing all items
    }
}
