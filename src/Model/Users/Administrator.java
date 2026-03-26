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

public class Administrator extends Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Administrator(String name, Date dateOfBirth, String phoneNumber, String email, double salary, String username, String password) {
		super(name, dateOfBirth, phoneNumber, email, salary, AccessLevel.ADMIN, username, password);
		this.storeInAdminList();
	}
	
	public Administrator() {
		
	}
	
	public void storeInAdminList() {	 
		try(FileOutputStream fos = new FileOutputStream("src/DAO/aa_AdminList.dat", true);
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
	
	
	
	
	@SuppressWarnings("unused")
	public void CreateEmployee(Sector sector, String name, Date dateOfBirth, String phoneNumber, String email, double salary, AccessLevel accessLevel, String username, String password ) {
		if(accessLevel == AccessLevel.CASHIER) {
			Cashier cashier = new Cashier(sector, name, dateOfBirth, phoneNumber, email, salary, username, password);
		}
		else if(accessLevel == AccessLevel.MANAGER) {
			Manager manager = new Manager(name, dateOfBirth, phoneNumber, email, salary, username, password);
		}
		else if (accessLevel == AccessLevel.ADMIN) {
			Administrator administrator = new Administrator(name, dateOfBirth, phoneNumber, email, salary, username, password);
		}else {
			
		}
	}
	
	public void deleteEmployee(Employee employee) {
		if(employee.getaccessLevel()==AccessLevel.CASHIER) {
			ArrayList<Cashier> cashiers =this.viewCashiers();
			cashiers.remove((Cashier)employee);
			this.updateCashierList(cashiers);
			
		}
		if(employee.getaccessLevel()==AccessLevel.MANAGER) {
			ArrayList<Manager> managers =this.viewManagers();
			managers.remove((Manager)employee);
			this.updateManagerList(managers);
		}
		if(employee.getaccessLevel()==AccessLevel.ADMIN) {
			ArrayList<Administrator> admins =this.viewAdmins();
			admins.remove((Administrator)employee);
			this.updateAdminList(admins);
		}
	}
	
	public ArrayList<Cashier> viewCashiers(){
		ArrayList<Cashier> cashiers = new ArrayList<Cashier>();

		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_CashierList.dat"))){
			while (true) {
                try {
                    Cashier cashier = (Cashier) in.readObject();
                    cashiers.add(cashier);
                    
                } catch (java.io.EOFException e) {
                    break;  
                }
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return cashiers;
	}
	
	public ArrayList<Manager> viewManagers(){
		ArrayList<Manager> managers = new ArrayList<Manager>();

		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_ManagerList.dat"))){
			while (true) {
                try {
                    Manager manager = (Manager) in.readObject();
                    managers.add(manager);
                    
                } catch (java.io.EOFException e) {
                    break;  
                }
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return managers;
	}

	public ArrayList<Administrator> viewAdmins(){
		ArrayList<Administrator> administrators = new ArrayList<Administrator>();

		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_AdminList.dat"))){
			while (true) {
                try {
                    Administrator admin = (Administrator) in.readObject();
                    administrators.add(admin);
                    
                } catch (java.io.EOFException e) {
                    break;  
                }
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return administrators;
	}
	
	
	
	
	
	public void updateCashierList(ArrayList<Cashier> cashierList) {
        try (FileOutputStream fos = new FileOutputStream("src/DAO/aa_CashierList.dat");
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
            for (Cashier cashier : cashierList) {
                out.writeObject(cashier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void updateManagerList(ArrayList<Manager> managerList) {
        try (FileOutputStream fos = new FileOutputStream("src/DAO/aa_ManagerList.dat");
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
            for (Manager manager : managerList) {
                out.writeObject(manager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void updateAdminList(ArrayList<Administrator> adminList) {
        try (FileOutputStream fos = new FileOutputStream("src/DAO/aa_AdminList.dat");
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
            for (Administrator admin : adminList) {
                out.writeObject(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public double getTotalEarnings() {
		double earnings=0;
		
		ArrayList<Bill> allBills = new ArrayList<Bill>();
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_BillList.dat"))){
			while (true) {
                try {
                    Bill bill = (Bill) in.readObject();
                    allBills.add(bill);
                } catch (java.io.EOFException e) {
                    break;  
                }
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<allBills.size();i++) {
			earnings+=allBills.get(i).getTotalAmount();
		}
		return earnings;
	}
	
	public double getTotalSpendings() {
		double spendings=0;
		
		ArrayList<Item> allItems = new ArrayList<Item>();
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_ItemList.dat"))){
			while (true) {
                try {
                    Item item = (Item) in.readObject();
                    allItems.add(item);
                } catch (java.io.EOFException e) {
                    break;  
                }
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<allItems.size();i++) {
			spendings+=allItems.get(i).getStockCost();
		}
		return spendings;
	}
	
	public double getTotalTurnover() {
		return (this.getTotalEarnings()-this.getTotalSpendings());
	}
}


