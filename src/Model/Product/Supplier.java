package Model.Product;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Supplier implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Supplier(String name, String email, String phoneNumber) {
		this.name=name;
		this.email=email;
		this.phoneNumber=phoneNumber;
		this.storeInSupplierList();
	}
	
	public Supplier() {
		
	}
	
	private void storeInSupplierList() {
		try(FileOutputStream fos = new FileOutputStream("src/DAO/aa_SupplierList.dat", true);
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

	private String name;
	private String email;
	private String phoneNumber;
	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	private ArrayList<Item> suppliedItems = new ArrayList<Item>();
	
	public String getSuppliedItems() {
		String list = "";
		for(int i=0; i<suppliedItems.size();i++) {
			list = list+suppliedItems.get(i).getName()+"\n";
		}
		return list;
	}
	
}
