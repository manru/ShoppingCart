package com.amaysim.deals;
import com.amaysim.lookup.DealType;
import com.amaysm.cart.MyShoppingCart;

/**
 * The Interface Deal.
 */
public interface Deal 
{

	/**
	 * Apply Current Promotions.
	 *
	 * @param cart the cart
	 */
	void apply(MyShoppingCart cart);
	
	/**
	 * Gets the deal type.
	 *
	 * @return the deal type
	 */
	DealType getDealType();
	
}
