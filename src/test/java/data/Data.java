package data;

import java.util.ArrayList;
import java.util.List;


import utils.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

	 
	 //Data for filters check box
	    private static List<String> choosenFilltersNameList=new ArrayList<>();
	
	    public static List<String>  getChoosenFilltersNames() { 
			return choosenFilltersNameList;
		 }

		 public static void setChoosenFilltersNames(String  Names) {
			 choosenFilltersNameList.add(Names);
		 }
		 
		 private static String category;
		 public static void setCategory(String i)
		 {
			category=i; 
		 }
		 public static String getCategory()
		 {
		 return category;
		
		 }	
		 
		 private static String brand;
		 public static void setBrand(String i)
		 {
			 brand=i; 
		 }
		 public static String getBrand()
		 {
		 return brand;
		
		 }	
		 
		 
		 private static String choosenBrand;
		 public static void setChoosenBrand(String i)
		 {
			 choosenBrand=i; 
		 }
		 public static String getChoosenBrand()
		 {
		 return brand;
		
		 }	
		 //Data for slider
		 private static int minValue;
		 public static void setMinValue(int i)
		 {
			 minValue=i;
		 }
		 public static int getMinValue()
		 {return minValue;}
		 
		 private static int maxValue;
		 public static void setMaxValue(int i)
		 {
			 maxValue=i;
		 }
		 public static int getMaxValue()
		 {
			 return maxValue;
		 }
		 private static List <Integer> itemsPrices=new ArrayList<>();
		 public static void setItemsPrices(int i)
		 {itemsPrices.add(i);}
		   public static List<Integer>  getItemsPrices() {
				return itemsPrices;
			 }
		 
		 //Data for search box
		 private static  String [] tooles = {"Hammer","Gloves","Helmet","Sander"};
		 public static String getTooles()
		 {String choosenString=tooles[CommonUtils.random(tooles.length)];
			 return choosenString;
			 }
		 
		 private static String choosenToole;
		 public static void setChoosenToole(String i)
		 {
			 choosenToole=i;
		 }
		 public static String getChoosenToole()
		 {
			 return choosenToole;
			 }
		 
		 
private static String linkStrings;

public static void setLinks(String i)
{linkStrings=i;}
  public static String getLinks() {
		return linkStrings;
	 }

}
