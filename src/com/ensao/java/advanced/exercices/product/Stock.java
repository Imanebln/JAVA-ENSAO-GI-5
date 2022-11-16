package com.ensao.java.advanced.exercices.product;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Stock extends TreeSet<Product> {
	private static final ProductComparator COMPARATOR = new ProductComparator();
	// another way is to implement Comparable interface
	public Stock(Comparator<Product> comparator){
		super((comparator));
	}
	public Stock(){
		super(COMPARATOR); // appel au constructeur de la classe mere (by default)
	}
	public Stock filter(Predicate<Product> predicate) {
		return this.stream()
				.filter(predicate)
				.collect(Collectors.toCollection(Stock::new));
	}
	public Stock invertedFilter(Predicate<Product> predicate) {
		return filter(predicate.negate());
	}
	public Stock combine(Predicate<Product> predicateA, Predicate<Product> predicateB){
		return filter(predicateA.and(predicateB));
	}
	
	public void discount(Discount discount,double amount) {
		Consumer<Product> consumer = product -> {
			discount.discount(product,amount);
		};
		stream().forEach(consumer);
	}
	
	public <R> Collection<R> map(Function<Product, R> mapper) {
		throw new ToBeCompletedException("Retrun a collection of mapped property " +
				"of type 'R' of a product");
	}
	
	public void print(ProductPrinter printer) {
		super.stream()
				.forEach(product -> {
					printer.print(product);
				});
	}
	
	public Map<String, Product> groupByCategory() {
		throw new ToBeCompletedException("Retrun a map of a stock of products grouped by the category");
		
	}
	
	public Object findProduct(String name) {
		return stream()
//				.filter(product -> product.getName().equals(name))
				.findFirst();
	}
	
	public Stock moreExpensiveThan(Product product) {
		throw new ToBeCompletedException("return a new Stock of products" +
				" that are more expensive that a given product");
	}
	
	public Collection<Product> sorted() {
		return stream()
				.sorted(COMPARATOR) //id we delete COMPARATOR we should implement comparable interface
				.collect(Collectors.toList());
	}
}
