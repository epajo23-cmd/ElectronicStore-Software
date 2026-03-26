package Model.Users;
//finished

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.Product.Bill;
import Model.Product.Item;

public class Cashier extends Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sector sector;
	
	public Cashier(Sector sector, String name, Date dateOfBirth, String phoneNumber, String email, double salary, String username, String password) {
		super(name, dateOfBirth, phoneNumber, email, salary, AccessLevel.CASHIER, username, password);
		this.sector=sector;
		this.storeInCashierList();
		
	}
	public Cashier() {
		
	}
	
	
	
	public void storeInCashierList() {	 
		try(FileOutputStream fos = new FileOutputStream("src/DAO/aa_CashierList.dat", true);
		         // If the file already exists, we want to skip writing the header
				ObjectOutputStream out = new ObjectOutputStream(fos) {
		             @Override
		             protected void writeStreamHeader() throws IOException {
		                 // Only write the header if the file is empty
		                 if (fos.getChannel().position() == 0) {
		                     super.writeStreamHeader();
		                 }
		             }
		         }){
			
			out.writeObject(this);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private ArrayList<Item> cart = new ArrayList<Item>();
	
	
	public void addToCart(Item toAdd) {
		this.cart.add(toAdd);
	}
	
	public String generateBill(ArrayList<Item> cart) {
		Bill toGenerate = new Bill(cart,this);
		toGenerate.generateBillFile();
		cart.clear();
		return "Bill successfully generated";
	}
	
	public ArrayList<Bill> viewTodayBills(Date currentDate) {
		ArrayList<Bill> todaysBills = new ArrayList<Bill>();
		ArrayList<Bill> allBills = new ArrayList<Bill>();
		
		//load all existing bills into allBills
		allBills=this.viewAllPersonalBills();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		for(int i=0;i<allBills.size();i++) {
			if(dateFormat.format(allBills.get(i).getBillDate()).equals(dateFormat.format(currentDate))) {
				todaysBills.add(allBills.get(i));
			}
		}
		
		return todaysBills;
	}
	
	public ArrayList<Bill> viewAllPersonalBills() {
	    ArrayList<Bill> allBills = new ArrayList<Bill>();
	    ArrayList<Bill> personalBills = new ArrayList<Bill>();

	    // Load all existing bills into allBills
	    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_BillList.dat"))) {
	        while (true) {
	            try {
	                Bill bill = (Bill) in.readObject();
	                allBills.add(bill);
	            } catch (java.io.EOFException e) {
	                System.out.println("End of file reached.");  // Debugging
	                break;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }


	    // Filter bills for the current cashier
	    for (Bill bill : allBills) {
	        if (bill.getCashier().getname().equals(this.getname())) { 
	            personalBills.add(bill);
	        }
	    }

	    System.out.println("Personal Bills: " + personalBills);  // Debugging to see filtered bills
	    return personalBills;
	}
	
	

	
	public ArrayList<Item> getCart(){
		return cart;
	}
	
	public Sector getSector() {
		return sector;
	}

}
