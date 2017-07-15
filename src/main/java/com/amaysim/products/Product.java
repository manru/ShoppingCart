package com.amaysim.products;
import java.math.BigDecimal;

public interface Product {
	String productName();
	String productCode();
	BigDecimal price();
}
