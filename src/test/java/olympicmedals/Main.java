package olympicmedals;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<String> countryList = new ArrayList<String>();
		countryList.add("123 Uzbekistan 212");
		countryList.add("123 USA 212");
		countryList.add("123 RUSSIA 212");

		String countryName = "";
		ArrayList<String> countries = new ArrayList<String>();
		for (int i = 0; i < countryList.size(); i++) {
			System.out.println(countryList.get(i));
			String line = countryList.get(i);
			for (int j = 0; j < line.length(); j++) {
				System.out.print(line.charAt(j));
				char ch = line.charAt(i);
				if (ch >=65 && ch <= 90 || ch >=97 && ch <= 122) {
					countryName += ch;
				}
			}
			System.out.println();
			countries.add(countryName + ",");
//			countryName = "";
		}
		System.out.println("Countries with 18 silver " + countries);
	}
}
