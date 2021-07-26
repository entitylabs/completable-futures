package org.entitylabs;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

	public double getPrice(String product) {

		return calculatePrice(product);
	}

	public Future<Double> getPriceAsync(String product) {

//		CompletableFuture<Double> computedPrice = new CompletableFuture<Double>();
//
//		new Thread(() -> {
//
//			try {
//				double price = calculatePrice(product);
//				computedPrice.complete(price);
//			} catch (Exception e) {
//				computedPrice.completeExceptionally(e);
//			}
//		}).start();
//
//		return computedPrice;

		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
	}

	private double calculatePrice(String product) {

		Random random = new Random();
		delay();
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}

	public static void delay() {
		try {

			Thread.sleep(10000);
		} catch (InterruptedException e) {

			throw new RuntimeException(e);
		}
	}

}
