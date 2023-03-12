
public class product {
	private int id;
    private String name;
    private String manufacturer;
    private String style;
    private double purchasePrice;
    private double salePrice;
    private int qtyOnHand;
    private double commissionPercentage;
    
    public product(int id, String name, String manufacturer, String style, double purchasePrice, double salePrice, int qtyOnHand, double commissionPercentage) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.style = style;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.qtyOnHand = qtyOnHand;
        this.commissionPercentage = commissionPercentage;
    }

    public product() {
		// TODO Auto-generated constructor stub
	}

	public product(int productId, String productName) {
        this.id = productId;
        this.name = productName;

	}

	public product(int productId, String productName, double price) {
        this.id = productId;
        this.name = productName;
        this.salePrice = price;

	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }
}

    

