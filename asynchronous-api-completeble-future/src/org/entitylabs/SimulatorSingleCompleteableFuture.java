package org.entitylabs;

import java.util.concurrent.Future;

public class SimulatorSingleCompleteableFuture {

	public static void main(String[] args) {

		Shop shop = new Shop();
		var startTime = System.nanoTime() / 1_000_000;
		Future<Double> result = shop.getPriceAsync("First product");

		try {
			var price = result.get();
			System.out.println("Found price-->" + price);
		} catch (Exception e) {

			throw new RuntimeException(e);
		}

		var endTime = System.nanoTime() / 1_000_000;

		System.out.println(endTime - startTime);

	}
}
