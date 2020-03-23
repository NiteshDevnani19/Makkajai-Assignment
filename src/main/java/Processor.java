package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Processor {

	public void process() {
		printOutput(feedInput());
	}

	private List<Item> feedInput() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter number of items: ");
		int numberOfItems=0;
		try {
			numberOfItems = Integer.parseInt(sc.nextLine());
			if(numberOfItems<1) {
				throw new Exception("Number of items cannot be less than one");
			}
		} catch (Exception e) {
			System.out.println("Invalid number of Items");
			System.exit(0);
		}
		List<Item> itemList = new ArrayList<Item>();
		for(int i = 0 ; i < numberOfItems ; i++) {
			System.out.println("Enter Product Name : ");
			String name = sc.nextLine();
			System.out.println("Enter Product Price : ");

			double price;
			try {
				price = Double.parseDouble(sc.nextLine());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				throw new NumberFormatException("Invalid Price");
			}

			System.out.println("Is Product Imported : ");
			String isImportedString = sc.nextLine();
			boolean isImported = "yes".equals(isImportedString) ||
					"y".equals(isImportedString) ||
					"Yes".equals(isImportedString) ||
					"Y".equals(isImportedString) ? true : false;
			System.out.println("Is Product Taxable : ");
			String isTaxableString = sc.nextLine();
			boolean isTaxable = "yes".equals(isTaxableString) ||
					"y".equals(isTaxableString) ||
					"Yes".equals(isTaxableString) ||
					"Y".equals(isTaxableString) ? true : false;
			System.out.println("Enter Quantity : ");
			int quantity=0;
			try {
				quantity = Integer.parseInt(sc.nextLine());
				if(quantity<1)
					throw new Exception("invalid quantity");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Invalid quantity");
				System.exit(0);
			}
			Item item = null;
			if(isTaxable) {
				item = new TaxableGoods(name, price, isImported, quantity);
			}
			else {
				item = new NonTaxableGoods(name, price, isImported, quantity);
			}
			itemList.add(item);
		}
		return itemList;
	}

	private void printOutput(final List<Item> itemList) {
		double totalSalesTax = 0.0;
		double total = 0.0;
		double roundedFinalPrice=0.0;
		for(Item item : itemList) {
			double individualSalesTax = item.quantity * (item.getSalesTax() + item.getImportDuty());
			double finalPrice = (item.quantity*item.price) + individualSalesTax;
			totalSalesTax += individualSalesTax;

			roundedFinalPrice=roundOff(finalPrice);
			total+=roundedFinalPrice;
			System.out.println(item.quantity + " " + item.name + ": "
					+ roundedFinalPrice);
		}
		double roundedSalesTax=roundOff(totalSalesTax);

		System.out.println("Sales Tax: " + roundedSalesTax);


		System.out.println("Total: " + roundOff(total));
	}


	public double roundOff(double roundedValue)
	{
		String convertedString=roundedValue+"";
		StringBuffer convertedStringBuffer=new StringBuffer(convertedString);
		int count=0;
		for(int i=0;i<convertedStringBuffer.length();i++)
		{
			char extractedCharacter=convertedStringBuffer.charAt(i);
			if(extractedCharacter=='.')
			{
				count=i;
			}
		}

		String finalRoundedValue=null;

		if((count+2)<=convertedStringBuffer.length()-1)
		{
			finalRoundedValue=convertedStringBuffer.substring(0,count+3);
		}
		else {
			finalRoundedValue = convertedStringBuffer.toString();
		}

		char ch1=finalRoundedValue.charAt(finalRoundedValue.length()-1);
		double newRoundedValue=0.0;
		if(ch1=='1' || ch1=='2'|| ch1=='3' || ch1=='4')
		{
			char arr[]=finalRoundedValue.toCharArray();
			arr[finalRoundedValue.length()-1]='5';
			finalRoundedValue=String.copyValueOf(arr);
			newRoundedValue=Double.parseDouble(finalRoundedValue);

		}
		else{
			newRoundedValue = Math.round(roundedValue * 100.0) / 100.0;
		}

		return newRoundedValue;
	}

}
