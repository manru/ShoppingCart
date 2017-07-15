package com.amaysm.cart;

import java.math.BigDecimal;
import java.util.List;

import com.amaysim.lookup.PromoCode;

/**
 * The Interface MyShoppingCart.
 */
public interface MyShoppingCart extends AmaysimShoppingCart 
{
	
	/**
	 * Adds the total.
	 *
	 * @param subTotal the sub total
	 */
	void addTotal(BigDecimal subTotal);
	
	/**
	 * Sets the  new total.
	 *
	 * @param newTotal the new total
	 */
	void setTotal(BigDecimal newTotal);
	
	/**
	 * Gets the total price without bulk discount.
	 *
	 * @return the total price
	 */
	BigDecimal getTotalPrice();
	
	/**
	 * Gets the promo code.
	 *
	 * @return the promo code
	 */
	List<PromoCode> getPromoCode();

}
