package Model.Users;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import Model.Product.Bill;
import Model.Product.Item;

public class Manager extends Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Manager(String name, Date dateOfBirth, String phoneNumber, String email, double salary, String username, String password) {
		super(name,dateOfBirth,phoneNumber,email,salary,AccessLevel.MANAGER,username,password);
		this.storeInManagerListList();
	}
	public Manager() {
		
	} 
	
	public void storeInManagerListList() {
		try(FileOutputStream fos = new FileOutputStream("src/DAO/aa_ManagerList.dat", true);
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
	
	
	public ArrayList<Sector> viewManagedSectors(){
		ArrayList<Sector> allSectors = new ArrayList<Sector>();
		ArrayList<Sector> managedSectors = new ArrayList<Sector>();
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_SectorList.dat"))){
			while (true) {
                try {
                    Sector sector = (Sector) in.readObject();
                    allSectors.add(sector);
                    
                } catch (java.io.EOFException e) {
                    break;  
                }
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<allSectors.size();i++) {
			
			if(allSectors.get(i).getManager().getname().equals(this.getname())) {
				managedSectors.add(allSectors.get(i));
				System.out.println(allSectors.get(i).getName());
			}
		}
		
		return managedSectors;
	}
	
	public ArrayList<Cashier> viewManagedCashiers(){
		ArrayList<Cashier> managedCashiers = new ArrayList<Cashier>();

		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_CashierList.dat"))){
			while (true) {
                try {
                    Cashier cashier = (Cashier) in.readObject();
                    if(cashier.getSector().getManager().getname().equals(this.getname())) {
                    	managedCashiers.add(cashier);
                    }
                } catch (java.io.EOFException e) {
                    break;  
                }
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return managedCashiers;
	}
	
	public String viewSectorStock() {
		
		String toReturn="";
		ArrayList<Sector> sectors = this.viewManagedSectors();
		for(int i=0;i<sectors.size();i++) {
			toReturn = toReturn + "Sector:" + sectors.get(i).getName() + "\n";
			for(int j=0;j<sectors.get(i).viewStoredItems().size();j++) {
				toReturn = toReturn + sectors.get(i).viewStoredItems().get(j).getName()+"    Remaining: "+sectors.get(i).viewStoredItems().get(j).getStockLevel()+"\n";
				System.out.println( sectors.get(i).viewStoredItems().get(j).getName()+"    Remaining: "+sectors.get(i).viewStoredItems().get(j).getStockLevel()+"\n");
			}
			toReturn = toReturn + "\n";
			
		}
		
		return toReturn;
		
	}
	
	public ArrayList<Bill> viewCashierDailyBills(Cashier cashier, Date currentDate){
		return cashier.viewTodayBills(currentDate);
	}
	public ArrayList<Bill> viewCashierAllTimeBills(Cashier cashier){
		return cashier.viewAllPersonalBills();
	}
	public double viewCashierTotalTurnover(Cashier cashier) {
		double total=0;
		for(int i=0;i<cashier.viewAllPersonalBills().size();i++) {
			total+=cashier.viewAllPersonalBills().get(i).getTotalAmount();
		}
		return total;
	}
	
	
	public ArrayList<Bill> viewSectorDailyBills(Sector sector, Date currentDate){
		ArrayList<Bill> sectorDailyBills = new ArrayList<Bill>();
		for(int i=0;i<this.viewManagedCashiers().size();i++) {
			if(this.viewManagedCashiers().get(i).getSector().equals(sector)) {
				for(int j=0;j<this.viewManagedCashiers().get(i).viewTodayBills(currentDate).size();j++) {
					sectorDailyBills.add(this.viewManagedCashiers().get(i).viewTodayBills(currentDate).get(j));
				}
			}
		}
		return sectorDailyBills;
	}
	public ArrayList<Bill> viewSectorAllTimeBills(Sector sector){
		ArrayList<Bill> sectorAllTimeBills = new ArrayList<Bill>();
		for(int i=0;i<this.viewManagedCashiers().size();i++) {
			if(this.viewManagedCashiers().get(i).getSector().equals(sector)) {
				for(int j=0;j<this.viewManagedCashiers().get(i).viewAllPersonalBills().size();j++) {
					sectorAllTimeBills.add(this.viewManagedCashiers().get(i).viewAllPersonalBills().get(j));
				}
			}
		}
		return sectorAllTimeBills;
	}
	public double viewSectorTotalTurnover(Sector sector) {
		double total=0;
		for(int i=0;i<this.viewManagedCashiers().size();i++) {
			if(this.viewManagedCashiers().get(i).getSector().getName().equals(sector.getName())) {
				total+=this.viewCashierTotalTurnover(this.viewManagedCashiers().get(i));
			}
		}
		return total;
	}
	
	public void restock(Item item, int amount) {
		ArrayList<Item> itemList= new ArrayList<Item>();
		//read all items
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_ItemList.dat"))){
			while (true) {
                try {
                    Item x = (Item) in.readObject();
                    itemList.add(x);
                } catch (java.io.EOFException e) {
                    break;  
                }
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//update stock
		for(int i=0;i<itemList.size();i++) {
			if(itemList.get(i).equals(item)) {
				itemList.get(i).updateStockLevel(amount);
			}
		}
		
		//update item list
		try (FileOutputStream fos = new FileOutputStream("src/DAO/aa_ItemList.dat");
	   	         // If the file already exists, we want to skip writing the header
	    			ObjectOutputStream out = new ObjectOutputStream(fos) {
	    	             @Override
	    	             protected void writeStreamHeader() throws IOException {
	    	                 // Only write the header if the file is empty
	    	                 if (fos.getChannel().position() == 0) {
	    	                     super.writeStreamHeader();
	    	                 }
	    	             }
	    	         }) {
	            for (Item y : itemList) {
	                out.writeObject(y);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	    }
	}

}
