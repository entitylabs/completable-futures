package org.entitylabs;

import java.util.List;
import java.util.stream.Collectors;

public class SimulatorParallelStream {

	public static void main(String[] args) {

		var start = System.nanoTime() / 1_000_000;

		Shop shop1 = new Shop();
		Shop shop2 = new Shop();
		Shop shop3 = new Shop();
		Shop shop4 = new Shop();

		System.out.println(start);
		var product = "First product";
		var resp = List.of(shop1, shop2, shop3, shop4).parallelStream().map(shopSelected -> shopSelected.getPrice(product))
				.collect(Collectors.toList());
		System.out.println(resp);
		var end = System.nanoTime() / 1_000_000;
		System.out.println(end - start);

	}
}
