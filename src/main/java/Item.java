package main.java;

public abstract class Item {
	String name;
	double price;
	int quantity;
	boolean isImported;

	public Item(String name, double price, boolean isImported, int quantity)
	{	
		this.name=name;
		this.price=price;
		this.quantity = quantity;
		this.isImported=isImported;
	}

	abstract public double getSalesTax();
	public double getImportDuty() {
		if(isImported) {	
			return (price*Constants.importedTaxRate)/100;
		}
		return 0.0;
	}
	public double getFinalPrice() {
		return (price + getSalesTax() + getImportDuty());
	}


}
