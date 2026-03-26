package Junk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import Model.Product.Bill;
import Model.Product.Item;
import Model.Product.Supplier;
import Model.Users.Administrator;
import Model.Users.Cashier;
import Model.Users.Manager;
import Model.Users.Sector;

public class Test {
    
	

	
	@SuppressWarnings("deprecation")
    public static void main(String[] args) {
    	
    	Manager manager1 = new Manager(
                "Alice Johnson", 
                new Date(85, 3, 23),  // Date constructor (year - 1900, month - 1-based)
                "555-1234", 
                "alice.johnson@example.com", 
                75000.00, 
                "alicej", 
                "password123"
            );

            // Manager 2
            Manager manager2 = new Manager(
                "Bob Smith", 
                new Date(78, 8, 12),  // Date constructor (year - 1900, month - 1-based)
                "555-5678", 
                "bob.smith@example.com", 
                82000.00, 
                "bobsmith", 
                "securepass456"
            );

            // Manager 3
            Manager manager3 = new Manager(
                "Clara Wilson", 
                new Date(90, 1, 28),  // Date constructor (year - 1900, month - 1-based)
                "555-8765", 
                "clara.wilson@example.com", 
                71000.00, 
                "claraw", 
                "password789"
            );

            // Manager 4
            Manager manager4 = new Manager(
                "David Lee", 
                new Date(82, 10, 10),  // Date constructor (year - 1900, month - 1-based)
                "555-3456", 
                "david.lee@example.com", 
                88000.00, 
                "davidlee", 
                "passcode321"
            );

            // Manager 5
            Manager manager5 = new Manager(
                "Eva Martinez", 
                new Date(87, 6, 4),  // Date constructor (year - 1900, month - 1-based)
                "555-9876", 
                "eva.martinez@example.com", 
                95000.00, 
                "evam", 
                "evaPass234"
            );
            
            Sector sector1 = new Sector("Mobile Devices", manager1); // Alice manages Mobile Devices
            Sector sector2 = new Sector("Laptops", manager2);       // Bob manages Laptops
            Sector sector3 = new Sector("Home Appliances", manager3); // Clara manages Home Appliances
            Sector sector4 = new Sector("Televisions", manager4);    // David manages Televisions
            Sector sector5 = new Sector("Gaming Consoles", manager5); // Eva manages Gaming Consoles
            
            // Mobile Devices Sector
            Supplier supplier1 = new Supplier("TechSupplies Inc.", "contact@techsupplies.com", "555-1111");
            Supplier supplier2 = new Supplier("MobileMakers", "service@mobilemakers.com", "555-6666");

            // Laptops Sector
            Supplier supplier3 = new Supplier("GadgetWorld Ltd.", "info@gadgetworld.com", "555-2222");
            Supplier supplier4 = new Supplier("LaptopSupplies Ltd.", "contact@laptopsupplies.com", "555-7777");

            // Home Appliances Sector
            Supplier supplier5 = new Supplier("HomeTech Suppliers", "support@hometechsuppliers.com", "555-3333");
            Supplier supplier6 = new Supplier("ApplianceMasters", "support@appliancemasters.com", "555-8888");

            // Televisions Sector
            Supplier supplier7 = new Supplier("ElectroWorld Inc.", "contact@electroworld.com", "555-5555");
            Supplier supplier8 = new Supplier("HomeElectronics Inc.", "info@homeelectronics.com", "555-9999");

            // Gaming Consoles Sector
            Supplier supplier9 = new Supplier("GamingGear Co.", "sales@gaminggear.com", "555-4444");
            Supplier supplier10 = new Supplier("ConsoleSupplies Co.", "sales@consolesupplies.com", "555-0000");
            
            //Mobile Devices Sector
            Item item1 = new Item("Smartphone", "Mobile Phone", supplier1, 300.00, 500.00, 50, sector1);
            item1.setOrderAmount(2);
            item1.setStockLevel(100);
            Item item2 = new Item("Smartwatch", "Wearable", supplier1, 150.00, 250.00, 30, sector1);
            item2.setOrderAmount(2);
            item2.setStockLevel(100);
            Item item3 = new Item("Headphones", "Audio", supplier1, 50.00, 100.00, 100, sector1);
            item3.setOrderAmount(2);
            item3.setStockLevel(100);
            Item item4 = new Item("Tablet", "Mobile Device", supplier1, 200.00, 350.00, 40, sector1);
            item4.setOrderAmount(2);
            item4.setStockLevel(100);
            Item item5 = new Item("Power Bank", "Accessory", supplier1, 20.00, 40.00, 200, sector1);
            item5.setOrderAmount(2);
            item5.setStockLevel(100);
            
            Item item6 = new Item("Smartphone", "Mobile Phone", supplier2, 300.00, 500.00, 60, sector1);
            item6.setOrderAmount(2);
            item6.setStockLevel(100);
            Item item7 = new Item("Smartwatch", "Wearable", supplier2, 160.00, 270.00, 40, sector1);
            item7.setOrderAmount(2);
            item7.setStockLevel(100);
            Item item8 = new Item("Headphones", "Audio", supplier2, 55.00, 110.00, 120, sector1);
            item8.setOrderAmount(2);
            item8.setStockLevel(100);
            Item item9 = new Item("Tablet", "Mobile Device", supplier2, 210.00, 360.00, 50, sector1);
            item9.setOrderAmount(2);
            item9.setStockLevel(100);
            Item item10 = new Item("Power Bank", "Accessory", supplier2, 25.00, 45.00, 250, sector1);
            item10.setOrderAmount(2);
            item10.setStockLevel(100);
            
            // Laptops Sector
            Item item11 = new Item("Gaming Laptop", "Laptop", supplier3, 800.00, 1200.00, 20, sector2);
            item11.setOrderAmount(2);
            item11.setStockLevel(100);
            Item item12 = new Item("Ultrabook", "Laptop", supplier3, 600.00, 950.00, 15, sector2);
            item12.setOrderAmount(2);
            item12.setStockLevel(100);
            Item item13 = new Item("Laptop Charger", "Accessory", supplier3, 25.00, 50.00, 100, sector2);
            item13.setOrderAmount(2);
            item13.setStockLevel(100);
            Item item14 = new Item("Laptop Sleeve", "Accessory", supplier3, 15.00, 30.00, 150, sector2);
            item14.setOrderAmount(2);
            item14.setStockLevel(100);
            Item item15 = new Item("Laptop Stand", "Accessory", supplier3, 35.00, 70.00, 80, sector2);
            item15.setOrderAmount(2);
            item15.setStockLevel(100);
            
            Item item16 = new Item("Gaming Laptop", "Laptop", supplier4, 850.00, 1300.00, 18, sector2);
            item16.setOrderAmount(2);
            item16.setStockLevel(100);
            Item item17 = new Item("Ultrabook", "Laptop", supplier4, 650.00, 1000.00, 20, sector2);
            item17.setOrderAmount(2);
            item17.setStockLevel(100);
            Item item18 = new Item("Laptop Charger", "Accessory", supplier4, 30.00, 55.00, 120, sector2);
            item18.setOrderAmount(2);
            item18.setStockLevel(100);
            Item item19 = new Item("Laptop Sleeve", "Accessory", supplier4, 20.00, 40.00, 130, sector2);
            item19.setOrderAmount(2);
            item19.setStockLevel(100);
            Item item20 = new Item("Laptop Stand", "Accessory", supplier4, 40.00, 75.00, 90, sector2);
            item20.setOrderAmount(2);
            item20.setStockLevel(100);
            
            // Home Appliances Sector
            Item item21 = new Item("Refrigerator", "Home Appliance", supplier5, 300.00, 600.00, 25, sector3);
            item21.setOrderAmount(2);
            item21.setStockLevel(100);
            Item item22 = new Item("Washing Machine", "Home Appliance", supplier5, 200.00, 450.00, 18, sector3);
            item22.setOrderAmount(2);
            item22.setStockLevel(100);
            Item item23 = new Item("Microwave Oven", "Home Appliance", supplier5, 80.00, 150.00, 50, sector3);
            item23.setOrderAmount(2);
            item23.setStockLevel(100);
            Item item24 = new Item("Blender", "Kitchen Appliance", supplier5, 40.00, 70.00, 75, sector3);
            item24.setOrderAmount(2);
            item24.setStockLevel(100);
            Item item25 = new Item("Air Conditioner", "Home Appliance", supplier5, 400.00, 700.00, 12, sector3);
            item25.setOrderAmount(2);
            item25.setStockLevel(100);
            
            Item item26 = new Item("Refrigerator", "Home Appliance", supplier6, 320.00, 650.00, 30, sector3);
            item26.setOrderAmount(2);
            item26.setStockLevel(100);
            Item item27 = new Item("Washing Machine", "Home Appliance", supplier6, 210.00, 470.00, 22, sector3);
            item27.setOrderAmount(2);
            item27.setStockLevel(100);
            Item item28 = new Item("Microwave Oven", "Home Appliance", supplier6, 85.00, 160.00, 55, sector3);
            item28.setOrderAmount(2);
            item28.setStockLevel(100);
            Item item29 = new Item("Blender", "Kitchen Appliance", supplier6, 45.00, 75.00, 80, sector3);
            item29.setOrderAmount(2);
            item29.setStockLevel(100);
            Item item30 = new Item("Air Conditioner", "Home Appliance", supplier6, 420.00, 720.00, 15, sector3);
            item30.setOrderAmount(2);
            item30.setStockLevel(100);
            
            // Televisions Sector
            Item item31 = new Item("4K LED TV", "Television", supplier7, 250.00, 600.00, 30, sector4);
            item31.setOrderAmount(2);
            item31.setStockLevel(100);
            Item item32 = new Item("Smart TV", "Television", supplier7, 500.00, 900.00, 40, sector4);
            item32.setOrderAmount(2);
            item32.setStockLevel(100);
            Item item33 = new Item("Soundbar", "Audio", supplier7, 100.00, 200.00, 60, sector4);
            item33.setOrderAmount(2);
            item33.setStockLevel(100);
            Item item34 = new Item("OLED TV", "Television", supplier7, 600.00, 1200.00, 20, sector4);
            item34.setOrderAmount(2);
            item34.setStockLevel(100);
            Item item35 = new Item("TV Wall Mount", "Accessory", supplier7, 30.00, 50.00, 100, sector4);
            item35.setOrderAmount(2);
            item35.setStockLevel(100);
            
            Item item36 = new Item("4K LED TV", "Television", supplier8, 260.00, 620.00, 35, sector4);
            item36.setOrderAmount(2);
            item36.setStockLevel(100);
            Item item37 = new Item("Smart TV", "Television", supplier8, 520.00, 920.00, 45, sector4);
            item37.setOrderAmount(2);
            item37.setStockLevel(100);
            Item item38 = new Item("Soundbar", "Audio", supplier8, 110.00, 210.00, 70, sector4);
            item38.setOrderAmount(2);
            item38.setStockLevel(100);
            Item item39 = new Item("OLED TV", "Television", supplier8, 650.00, 1250.00, 18, sector4);
            item39.setOrderAmount(2);
            item39.setStockLevel(100);
            Item item40 = new Item("TV Wall Mount", "Accessory", supplier8, 35.00, 55.00, 120, sector4);
            item40.setOrderAmount(2);
            item40.setStockLevel(100);
            
            // Gaming Consoles Sector
            Item item41 = new Item("PlayStation 5", "Gaming Console", supplier9, 400.00, 700.00, 15, sector5);
            item41.setOrderAmount(2);
            item41.setStockLevel(100);
            Item item42 = new Item("Xbox Series X", "Gaming Console", supplier9, 350.00, 650.00, 20, sector5);
            item42.setOrderAmount(2);
            item42.setStockLevel(100);
            Item item43 = new Item("Nintendo Switch", "Gaming Console", supplier9, 200.00, 400.00, 25, sector5);
            item43.setOrderAmount(2);
            item43.setStockLevel(100);
            Item item44 = new Item("Game Controller", "Accessory", supplier9, 30.00, 60.00, 150, sector5);
            item44.setOrderAmount(2);
            item44.setStockLevel(100);
            Item item45 = new Item("Game Subscription", "Service", supplier9, 10.00, 20.00, 500, sector5);
            item45.setOrderAmount(2);
            item45.setStockLevel(100);
            
            Item item46 = new Item("PlayStation 5", "Gaming Console", supplier10, 420.00, 720.00, 12, sector5);
            item46.setOrderAmount(2);
            item46.setStockLevel(100);
            Item item47 = new Item("Xbox Series X", "Gaming Console", supplier10, 370.00, 670.00, 18, sector5);
            item47.setOrderAmount(2);
            item47.setStockLevel(100);
            Item item48 = new Item("Nintendo Switch", "Gaming Console", supplier10, 220.00, 420.00, 22, sector5);
            item48.setOrderAmount(2);
            item48.setStockLevel(100);
            Item item49 = new Item("Game Controller", "Accessory", supplier10, 35.00, 65.00, 160, sector5);
            item49.setOrderAmount(2);
            item49.setStockLevel(100);
            Item item50 = new Item("Game Subscription", "Service", supplier10, 12.00, 22.00, 550, sector5);
            item50.setOrderAmount(2);
            item50.setStockLevel(100);
            
            //carts
            ArrayList<Item> cart1 = new ArrayList<>(Arrays.asList(item1));
            ArrayList<Item> cart2 = new ArrayList<>(Arrays.asList(item2, item5));
            ArrayList<Item> cart3 = new ArrayList<>(Arrays.asList(item3, item6, item7));
            ArrayList<Item> cart4 = new ArrayList<>(Arrays.asList(item4));
            ArrayList<Item> cart5 = new ArrayList<>(Arrays.asList(item5, item8));
            ArrayList<Item> cart6 = new ArrayList<>(Arrays.asList(item6, item4, item3, item1));
            ArrayList<Item> cart7 = new ArrayList<>(Arrays.asList(item9));
            ArrayList<Item> cart8 = new ArrayList<>(Arrays.asList(item10, item15));
            ArrayList<Item> cart9 = new ArrayList<>(Arrays.asList(item11));
            ArrayList<Item> cart10 = new ArrayList<>(Arrays.asList(item12, item16, item14));
            ArrayList<Item> cart11 = new ArrayList<>(Arrays.asList(item13, item19));
            ArrayList<Item> cart12 = new ArrayList<>(Arrays.asList(item17, item18));
            ArrayList<Item> cart13 = new ArrayList<>(Arrays.asList(item20));
            ArrayList<Item> cart14 = new ArrayList<>(Arrays.asList(item21, item22, item23));
            ArrayList<Item> cart15 = new ArrayList<>(Arrays.asList(item24, item25, item26, item27));
            ArrayList<Item> cart16 = new ArrayList<>(Arrays.asList(item28, item29));
            ArrayList<Item> cart17 = new ArrayList<>(Arrays.asList(item30));
            ArrayList<Item> cart18 = new ArrayList<>(Arrays.asList(item31, item32));
            ArrayList<Item> cart19 = new ArrayList<>(Arrays.asList(item33, item35, item36));
            ArrayList<Item> cart20 = new ArrayList<>(Arrays.asList(item37, item38, item39, item40, item41));
            
            
            
            //Cashiers
            // Cashiers for Mobile Devices
            Cashier cashier1 = new Cashier(sector1, "John Doe", new Date(95, 5, 15), "555-1112", "john.doe@example.com", 40000.00, "johnd", "johnPass123");
            Cashier cashier2 = new Cashier(sector1, "Emily White", new Date(96, 7, 20), "555-1113", "emily.white@example.com", 42000.00, "emilyw", "emilyPass456");

            // Cashiers for Laptops
            Cashier cashier3 = new Cashier(sector2, "Michael Brown", new Date(94, 9, 30), "555-2222", "michael.brown@example.com", 45000.00, "mikeb", "mikePass789");
            Cashier cashier4 = new Cashier(sector2, "Sarah Green", new Date(97, 2, 10), "555-2223", "sarah.green@example.com", 47000.00, "sarahg", "sarahPass012");

            // Cashiers for Home Appliances
            Cashier cashier5 = new Cashier(sector3, "Daniel Harris", new Date(91, 1, 25), "555-3333", "daniel.harris@example.com", 43000.00, "danielh", "danielPass345");
            Cashier cashier6 = new Cashier(sector3, "Olivia Adams", new Date(94, 3, 12), "555-3334", "olivia.adams@example.com", 45000.00, "oliviaa", "oliviaPass678");

            // Cashiers for Televisions
            Cashier cashier7 = new Cashier(sector4, "Ethan Clark", new Date(89, 4, 18), "555-4444", "ethan.clark@example.com", 47000.00, "ethanc", "ethanPass901");
            Cashier cashier8 = new Cashier(sector4, "Sophia Lewis", new Date(92, 10, 5), "555-4445", "sophia.lewis@example.com", 49000.00, "sophial", "sophiaPass234");

            // Cashiers for Gaming Consoles
            Cashier cashier9 = new Cashier(sector5, "Lucas Walker", new Date(97, 6, 30), "555-5555", "lucas.walker@example.com", 46000.00, "lucasw", "lucasPass567");
            Cashier cashier10 = new Cashier(sector5, "Ava Martinez", new Date(93, 12, 11), "555-5556", "ava.martinez@example.com", 48000.00, "avam", "avaPass890");

            
         // Creating 20 bills based on the carts and cashiers
            Bill bill1 = new Bill(cart1, cashier1);
            Bill bill2 = new Bill(cart2, cashier2);
            Bill bill3 = new Bill(cart3, cashier3);
            Bill bill4 = new Bill(cart4, cashier4);
            Bill bill5 = new Bill(cart5, cashier5);
            Bill bill6 = new Bill(cart6, cashier6);
            Bill bill7 = new Bill(cart7, cashier7);
            Bill bill8 = new Bill(cart8, cashier8);
            Bill bill9 = new Bill(cart9, cashier9);
            Bill bill10 = new Bill(cart10, cashier10);
            Bill bill11 = new Bill(cart11, cashier1);
            Bill bill12 = new Bill(cart12, cashier2);
            Bill bill13 = new Bill(cart13, cashier3);
            Bill bill14 = new Bill(cart14, cashier4);
            Bill bill15 = new Bill(cart15, cashier5);
            Bill bill16 = new Bill(cart16, cashier6);
            Bill bill17 = new Bill(cart17, cashier7);
            Bill bill18 = new Bill(cart18, cashier8);
            Bill bill19 = new Bill(cart19, cashier9);
            Bill bill20 = new Bill(cart20, cashier10);
            
            bill1.generateBillFile();
            bill2.generateBillFile();
            bill3.generateBillFile();
            bill4.generateBillFile();
            bill5.generateBillFile();
            bill6.generateBillFile();
            bill7.generateBillFile();
            bill8.generateBillFile();
            bill9.generateBillFile();
            bill10.generateBillFile();
            bill11.generateBillFile();
            bill12.generateBillFile();
            bill13.generateBillFile();
            bill14.generateBillFile();
            bill15.generateBillFile();
            bill16.generateBillFile();
            bill17.generateBillFile();
            bill18.generateBillFile();
            bill19.generateBillFile();
            bill20.generateBillFile();
            
            
            
            
            @SuppressWarnings("unused")
			Administrator admin1 = new Administrator(
            	    "George Matthews",  // name
            	    new Date(80, 11, 15), // dateOfBirth (year - 1900, month - 1-based)
            	    "555-1234",  // phoneNumber
            	    "george.matthews@example.com",  // email
            	    100000.00,  // salary
            	    "georgem",  // username
            	    "adminPass123"  // password
            	);

            
    }

}
