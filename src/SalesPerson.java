import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class SalesPerson {
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private Date startDate;
	private Date terminationDate;
	private String manager;


	private List<Sale> sales;

	public SalesPerson() {
		this.sales = new ArrayList<>();
	}

	public SalesPerson(int id, String firstName, String lastName, String address, String phone, Date startDate,
			Date terminationDate, String manager) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.startDate = startDate;
		this.terminationDate = terminationDate;
		this.manager = manager;
		this.sales = new ArrayList<>();
	}

	public SalesPerson(int salespersonId, String salespersonFirstName) {
		this.id = salespersonId;	
		this.firstName = salespersonFirstName;	
	}

	public SalesPerson(int id2, String firstName2, String lastName2) {
		this.id = id2;	
		this.firstName = firstName2;	
		this.lastName =  lastName2;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public void addSale(Sale sale) {
		sales.add(sale);
	}

	public List<Sale> getSales() {
		return sales;
	}
}
