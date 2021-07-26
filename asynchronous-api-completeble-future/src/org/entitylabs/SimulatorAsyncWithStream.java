package org.entitylabs;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class SimulatorAsyncWithStream {

	public static void main(String[] args) {

		var startTime = System.nanoTime() / 1_000_000;

		Shop shop1 = new Shop();
		Shop shop2 = new Shop();
		Shop shop3 = new Shop();
		Shop shop4 = new Shop();

		List<CompletableFuture<String>> priceFutures = List.of(shop1, shop2, shop3, shop4).parallelStream()
				.map(shop -> CompletableFuture.supplyAsync(() -> Double.toString(shop.getPrice("Item 1"))))
				.collect(Collectors.toList());

		var list = priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());

		System.out.println(list);
		var endTime = System.nanoTime() / 1_000_000;

		System.out.println(endTime - startTime);
	}
}
