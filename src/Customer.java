import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private LocalDate startDate;

	public Customer(int id, String firstName, String lastName, String address, String phone, LocalDate startDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.startDate = startDate;
	}
	public Customer(int customerId, String customerFirstName, String customerLastName) {
		this.id = customerId;
		this.firstName = customerFirstName;
		this.lastName = customerLastName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	private List<Sale> sales;

	public Customer() {
		this.sales = new ArrayList<>();
	}



	public Customer(String name) {
		this.firstName = name;
	}
	public void addSale(Sale sale) {
		sales.add(sale);
	}	

}

