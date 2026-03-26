package Model.Users;

import java.io.Serializable;
import java.util.Date;

public class Employee  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Date dateOfBirth;
	private String phoneNumber;
	private String email;
	private String department;
	private double salary;
	private AccessLevel accessLevel;
	private String username;
	private String password;
	
	public Employee (String name, Date dateOfBirth, String phoneNumber, String email, double salary, AccessLevel accessLevel, String username, String password) {
		this.name=name;
		this.dateOfBirth=dateOfBirth;
		this.phoneNumber=phoneNumber;
		this.email=email;
		this.salary=salary;
		this.accessLevel=accessLevel;
		this.username=username;
		this.password=password;
	}
	
	public Employee() {
		
	}
	
	public String getDetails() {
		return name+"\n"+  dateOfBirth+"\n" +phoneNumber+"\n"+ email +"\n"+accessLevel;
	}
	public String getname() {
		return name;
	}
	public Date getdateOfBirth() {
		return dateOfBirth;
	}
	public String phoneNumber() {
		return phoneNumber;
	}
	public String getemail() {
		return email;
	}
	public String getDepartment() {
		return department;
	}
	public double getsalary() {
		return salary;
	}
	public AccessLevel getaccessLevel() {
		return accessLevel;
	}
	public String getusername() {
		return username;
	}
	public String getpassword() {
		return password;
	}
	
	public boolean login(String usernameInput, String passwordInput) {
		if(this.getusername().equals(usernameInput) && this.getpassword().equals(passwordInput)) {
			return true;
			
		}
		else 
			return false;
		
	}
	public void setUsername(String username) {
		this.username=username;
	}
	public void setPassword(String password) {
		this.password=password;
	}

	
	
	

}
