package org.entitylabs;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class SimulatorWithCustomExecutor {

	public static void main(String[] args) {

		Executor executor = Executors.newFixedThreadPool(100);

		Shop shop1 = new Shop();
		Shop shop2 = new Shop();
		Shop shop3 = new Shop();
		Shop shop4 = new Shop();
		var startTime = System.nanoTime() / 1_000_000;
		List<CompletableFuture<String>> priceFutures = List.of(shop1, shop2, shop3, shop4).parallelStream()
				.map(shop -> CompletableFuture.supplyAsync(() -> Double.toString(shop.getPrice("Item 1")), executor))
				.collect(Collectors.toList());

		var list = priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());

		System.out.println(list);
		var endTime = System.nanoTime() / 1_000_000;

		System.out.println(endTime - startTime);

	}
}
