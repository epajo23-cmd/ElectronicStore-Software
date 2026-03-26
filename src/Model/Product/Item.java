package Model.Product;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Model.Users.Sector;

public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String category;
	private Supplier supplier;
	private double purchasePrice;
	private double sellingPrice;
	private int stockLevel;
	private int orderAmount;
	private Sector sellingSector;
	
	public Item(String name, String category, Supplier supplier, double purchasePrice, 
			double sellingPrice, int stockLevel, Sector sellingSector) {
	this.name=name;
	this.category=category;
	this.supplier=supplier;
	this.purchasePrice=purchasePrice;
	this.sellingPrice=sellingPrice;
	this.stockLevel=stockLevel;
	this.sellingSector = sellingSector;
	this.storeInItemList();
	}
	
	public Item() {
		
	}
	
	public void storeInItemList() {
		try(FileOutputStream fos = new FileOutputStream("src/DAO/aa_ItemList.dat", true);
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
	
	
	
	//test constructor
	public Item(String name, int orderAmount, double sellingPrice) {
		this.name=name;
		this.sellingPrice=sellingPrice;
		this.orderAmount=orderAmount;
	}
	
	public Item(String name, double sellingPrice) {
		this.name=name;
		this.sellingPrice=sellingPrice;
	}

	public String getName() {
		return name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	
	public double getPurchasePrice() {
		return purchasePrice;
	}
	
	public double getSellingPrice() {
		return sellingPrice;
	}
	
	public int getStockLevel() {
		return stockLevel;
	}
	
	public void setStockLevel(int stockLevel) {
		 this.stockLevel=stockLevel;
	}
	
	public void updateStockLevel(int quantity) {
		stockLevel+=quantity;
	}
	
	public int getOrderAmount() {
		return orderAmount;
	}
	
	public void setOrderAmount(int orderAmount) {
		this.orderAmount=orderAmount;
	}

	public Sector getSellingSector() {
		return sellingSector;
	}

	public void setSellingSector(Sector sellingSector) {
		this.sellingSector = sellingSector;
	}

	public double getStockCost() {
		return stockLevel*purchasePrice;
	}
}
