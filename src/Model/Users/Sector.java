package Model.Users;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Model.Product.Item;

public class Sector implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Manager manager;
	
	public Sector(String name, Manager manager) {
		this.name=name;
		this.manager=manager;
		this.storeInSectorList();
	}
	public Sector() {
		
	}
	
	public ArrayList<Item> viewStoredItems(){
		ArrayList<Item> storedItems = new ArrayList<Item>();

		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_ItemList.dat"))){
			while (true) {
                try {
                    Item item = (Item) in.readObject();
                    if(item.getSellingSector().getName().equals(this.getName())) {
                    	storedItems.add(item);
                    }
                } catch (java.io.EOFException e) {
                    break;  
                }
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return storedItems;
	}
	
	public void storeInSectorList() {
		try(FileOutputStream fos = new FileOutputStream("src/DAO/aa_SectorList.dat", true);
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
	
	public ArrayList<Sector> viewSectorList(){
		ArrayList<Sector> sectors = new ArrayList<Sector>();
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/DAO/aa_SectorList.dat"))){
			while (true) {
                try {
                    Sector sector = (Sector) in.readObject();
                    sectors.add(sector);
                    
                } catch (java.io.EOFException e) {
                    break;  
                }
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sectors;
	}
	
	public String getSectorDetails() {
		return name+"\n"+manager.getname();
	}
	
	public String getName() {
		return name;
	}
	public Manager getManager() {
		return manager;
	}

}
