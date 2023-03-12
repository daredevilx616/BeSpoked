import java.time.LocalDate;

public class Sale {
    private int id;
    private product product;
    private Customer customer;
    private LocalDate saleDate;
    private SalesPerson salesperson;
    private double price;
    private static double salespersonCommission;
    
    public Sale(int id, product product, Customer customer, LocalDate saleDate, SalesPerson salesperson, double price, double salespersonCommission) {
        this.id = id;
        this.product = product;
        this.customer = customer;
        this.saleDate = saleDate;
        this.salesperson = salesperson;
        this.price = price;
        this.salespersonCommission = salespersonCommission;
    }
    public Sale(product product, Customer customer, SalesPerson salesperson, double price, LocalDate saleDate, double salespersonCommission) {
        this.product = product;
        this.customer = customer;
        this.salesperson = salesperson;
        this.price = price;
        this.saleDate = saleDate;
        this.salespersonCommission = salespersonCommission;
    }
    public static double getCommissionRate() {
        return salespersonCommission;
    }

    public int getId() {
        return id;
    }

    public product getProduct() {
        return product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public SalesPerson getSalesperson() {
        return salesperson;
    }
    public double getPrice() {
        return price;
    }

    public double getSalespersonCommission() {
        return salespersonCommission;
    }
}
