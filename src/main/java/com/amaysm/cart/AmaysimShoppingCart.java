package com.amaysm.cart;
import java.math.BigDecimal;
import java.util.List;

import com.amaysim.lookup.PromoCode;
import com.amaysim.products.Product;

/**
 * The Interface AmaysimShoppingCart.
 */
public interface AmaysimShoppingCart 
{

	/**
	 * Adds product to the cart.
	 *
	 * @param product the product
	 */
	void add(Product product);
	
	/**
	 * Adds product to the cart with the promocod.
	 *
	 * @param product the product
	 * @param promo the promo code
	 */
	void add(Product product, PromoCode promo);
	
	/**
	 * Gets the total price to pay upon check out.
	 *
	 * @return the total
	 */
	BigDecimal getTotal();
	
	/**
	 * Gets the items.
	 *
	 * @return the added cart items
	 */
	List<Product> getItems();
	


}
