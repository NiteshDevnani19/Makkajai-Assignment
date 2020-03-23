package main.java;

public class NonTaxableGoods extends Item {
	
	public NonTaxableGoods(String name, double price, boolean isImported, int quantity) {
		super(name, price, isImported, quantity);
	}
	
	@Override
	public double getSalesTax() {
		return 0.0;
	}
}

