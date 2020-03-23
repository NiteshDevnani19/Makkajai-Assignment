package main.java;

public class TaxableGoods extends Item {
	public TaxableGoods(String name, double price, boolean isImported, int quantity) {
		super(name, price, isImported, quantity);
	}

	@Override
	public double getSalesTax() {
		return (price*Constants.salesTaxRate)/100;		
	}
}
