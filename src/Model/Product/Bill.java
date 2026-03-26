package Model.Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.Users.Cashier;

public class Bill implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private int billNumber;
	private Date date;
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy   hh:mm:ss");
	private ArrayList<Item> itemList=new ArrayList<Item>();
	private double totalAmount;
	private Cashier cashier;
	
	public Bill(ArrayList<Item> itemList, Cashier cashier) {
		this.itemList=itemList;
		this.cashier=cashier;
	}
	
	public Bill() {
		
	}
	
	//test constructor
	public Bill(ArrayList<Item> itemList) {
		this.itemList=itemList;
	}
	
	public void storeInBillList() {
		try(FileOutputStream fos = new FileOutputStream("src/DAO/aa_BillList.dat", true);
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
	
	
	public void generateBillFile() {
		this.date = new Date();
		try {
			BufferedReader billNrReader = new BufferedReader(new FileReader("src/DAO/aa_BillCnt.txt"));
			this.billNumber = Integer.parseInt(billNrReader.readLine());
			
			String billFileName = "src/DAO/bill_"+String.format("%05d", billNumber)+"_("+dateFormat1.format(date)+").txt";
			this.totalAmount=0;
			
			
			
			BufferedWriter billWriter = new BufferedWriter(new FileWriter(billFileName));
			billWriter.write("----------------------------------------------------------------------");
			billWriter.write(String.format("\n| %-66s |", "Bill nr. "+billNumber));
			billWriter.write(String.format("\n| %-66s |", "Date & Time: "+dateFormat2.format(date)));
			billWriter.write(String.format("\n| %-66s |", "Cashier: "+cashier.getname()));
			billWriter.write(String.format("\n| %-66s |", "Department: "+cashier.getSector().getName()));
			billWriter.write("\n----------------------------------------------------------------------");
			billWriter.write(String.format("\n| %-25s | %-6s | %-13s | %-13s |", "Items", "Amount", "Price", "Total"));
			for(int i=1;i<=itemList.size();i++) {
				billWriter.write(String.format("\n| %-25s | %-6s | %-13s | %-13s |", i+". "+itemList.get(i-1).getName(), itemList.get(i-1).getOrderAmount(), itemList.get(i-1).getSellingPrice()+" $", itemList.get(i-1).getOrderAmount()*(itemList.get(i-1).getSellingPrice())+" $"));
				this.totalAmount+=(itemList.get(i-1).getOrderAmount())*(itemList.get(i-1).getSellingPrice());
			}
			billWriter.write("\n----------------------------------------------------------------------");
			billWriter.write(String.format("\n| %-25s   %-6s   %-13s   %-13s |", "", "", "Total: ", totalAmount+" $"));
			billWriter.write("\n----------------------------------------------------------------------");
			billWriter.close();
			
			BufferedWriter billNrWriter = new BufferedWriter(new FileWriter("src/DAO/aa_BillCnt.txt"));
			billNrWriter.write(""+(billNumber+1));
			billNrWriter.close();
			billNrReader.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}

		this.storeInBillList();
		
	}
	
	public double calculateTotal() {
		double sum=0;
		for(int i=0;i<itemList.size();i++) {
			sum+=itemList.get(i).getSellingPrice();
		}
		return sum;
	}
	
	public int getBillNumber() {
		return billNumber;
	}
	
	public Date getBillDate() {
		return date;
	}
	public Cashier getCashier() {
		return cashier;
	}
	public double getTotalAmount() {
		return totalAmount;
	}

}
