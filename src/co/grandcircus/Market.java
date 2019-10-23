package co.grandcircus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Market {

	public static void main(String[] args) {

		Map<String, Double> market = new HashMap<>();
		market.put("apple", 0.95);
		market.put("orange", 1.99);
		market.put("asparagus", 2.99);
		market.put("grapefruit", 2.95);
		market.put("pear", 0.95);
		market.put("banana", 0.99);
		market.put("onion", 1.95);
		market.put("soda", 3.95);
		market.put("juice", 3.95);

		List<String> names = new ArrayList<>();
		List<Double> prices = new ArrayList<>();
		List<Integer> quantities = new ArrayList<>();

		Scanner scan = new Scanner(System.in);

		System.out.printf("%-12s $%-7s\n", "Item", "Price");
		System.out.println("======================");

		for (String i : market.keySet()) {

			System.out.printf("%-12s $%-5.2f\n", i, market.get(i));

		}

		String choice = "not exit";
		double total = 0.0;

		while (!choice.equalsIgnoreCase("exit")) {

			choice = Validator.getString(scan,
					"What would you like to add?\n(enter as seen above, or exit to finish)\n");

			while (!market.containsKey(choice)) {

				if (choice.equalsIgnoreCase("exit")) {

					break;

				}

				System.out.println(choice + " not available.");

				choice = Validator.getString(scan,
						"What would you like to add?\n(enter as seen above, or exit to finish)\n");

			}

			if (!names.contains(choice) && !choice.equalsIgnoreCase("exit")) {

				names.add(choice);
				prices.add(market.get(choice));
				quantities.add(1);

			} else if (!choice.equalsIgnoreCase("exit")) {

				quantities.set(names.indexOf(choice), quantities.get(names.indexOf(choice)) + 1);

			}

			if (!choice.equals("exit")) {

				total += prices.get(names.indexOf(choice));

			}

		}

		List<String> highItems = getHighItem(names, prices);
		List<String> lowItems = getLowItem(names, prices);

		System.out.printf("Total cost: %.2f\n" , total);

		System.out.printf("\nAverage cost: %.2f\n" , getAverage(prices, quantities));

		System.out.print("\nFor " + getHighValue(prices) + " each, the most expensive product");

		if (highItems.size() > 1) {

			System.out.println("s bought were:");

		} else {

			System.out.println(" bought was:");

		}

		for (String i : highItems) {

			System.out.println(i);

		}

		System.out.print("\nFor " + getLowValue(prices) + " each, the least expensive product");

		if (lowItems.size() > 1) {

			System.out.println("s bought were:");

		} else {

			System.out.println(" bought was:");

		}

		for (String i : lowItems) {

			System.out.println(i);

		}

		scan.close();

	}

	public static double getAverage(List<Double> prices, List<Integer> quantity) {

		int totalQuantity = 0;
		double totalCost = 0.0;

		for (int i = 0; i < prices.size(); i++) {

			totalQuantity += quantity.get(i);
			totalCost += prices.get(i);

		}

		return totalCost / totalQuantity;

	}

	public static double getHighValue(List<Double> arr) {

		double biggestValue = 0.0;

		for (int i = 0; i < arr.size(); i++) {

			if (arr.get(i) >= biggestValue) {

				biggestValue = arr.get(i);

			}

		}

		return biggestValue;

	}

	public static double getLowValue(List<Double> arr) {

		double smallestValue = arr.get(0);

		for (int i = 1; i < arr.size(); i++) {

			if (arr.get(i) <= smallestValue) {

				smallestValue = arr.get(i);

			}

		}

		return smallestValue;

	}

	public static List<String> getHighItem(List<String> names, List<Double> prices) {

		double biggestValue = 0;
		List<String> highItems = new ArrayList<>();

		for (int i = 0; i < prices.size(); i++) {

			if (prices.get(i) >= biggestValue) {

				biggestValue = prices.get(i);

			}

		}

		for (int j = 0; j < prices.size(); j++) {

			if (prices.get(j) == biggestValue) {

				highItems.add(names.get(j));

			}

		}

		return highItems;

	}

	public static List<String> getLowItem(List<String> names, List<Double> prices) {

		double lowestValue = prices.get(0);
		List<String> lowItems = new ArrayList<>();

		for (int i = 1; i < prices.size(); i++) {

			if (prices.get(i) <= lowestValue) {

				lowestValue = prices.get(i);

			}

		}

		for (int j = 0; j < prices.size(); j++) {

			if (prices.get(j) == lowestValue) {

				lowItems.add(names.get(j));

			}

		}

		return lowItems;

	}

}
