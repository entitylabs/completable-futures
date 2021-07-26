package org.entitylabs;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class SimulatorSequentialProcessingApi {

	public static void main(String[] args) {

		Shop shop = new Shop();
		var startTime = System.nanoTime() / 1_000_000;
		Future<Double> result = shop.getPriceAsync("First product");

		try {
			var price = result.get();
			// var price=result.get(100, TimeUnit.MICROSECONDS); //with a timeout
			System.out.println("Found price-->" + price);
		} catch (Exception e) {

			throw new RuntimeException(e);
		}

		var endTime = System.nanoTime() / 1_000_000;

		System.out.println(endTime - startTime);

		var start = System.nanoTime() / 1_000_000;

		Shop shop1 = new Shop();
		Shop shop2 = new Shop();
		Shop shop3 = new Shop();
		Shop shop4 = new Shop();

		System.out.println(start);
		var product = "First product";

		var startParallel = System.nanoTime() / 1_000_000;

		System.out.println(startParallel);
		var resp1 = List.of(shop1, shop2, shop3, shop4).stream()
				.map(shopSelected -> shopSelected.getPrice(product)).collect(Collectors.toList());
		System.out.println(resp1);
		var endParallel = System.nanoTime() / 1_000_000;
		System.out.println(endParallel - startParallel);
	}
}
