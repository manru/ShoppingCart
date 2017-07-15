package com.amaysim.deals.pricing.impl;
import java.math.BigDecimal;

import com.amaysim.deals.Deal;
import com.amaysim.lookup.DealType;
import com.amaysim.lookup.Products;
import com.amaysm.cart.MyShoppingCart;

/**
 * The Class TwoGigDeal.
 */
public class TwoGigDeal implements Deal
{

	/* (non-Javadoc)
	 * @see com.amaysim.deals.Deal#apply(com.amaysm.cart.MyShoppingCart)
	 */
	@Override
	public void apply(MyShoppingCart cart) 
	{
		Long count2GB = cart.getItems().stream().filter(p -> p.productCode()
				.equals(Products.ULT_MEDIUM.productCode()))
				.count();
		BigDecimal subtotal = getSubTotal(count2GB);
		cart.addTotal(subtotal);
		
	}
	
	/* (non-Javadoc)
	 * @see com.amaysim.deals.Deal#getDealType()
	 */
	@Override
	public DealType getDealType() 
	{
		return DealType.PRICING;
	}
	
	/**
	 * Gets the sub total.
	 *
	 * @param count2GB no of 2 GB Product in Cart
	 * @return the sub total
	 */
	private BigDecimal getSubTotal(Long count2GB) 
	{
		return Products.ULT_MEDIUM.price().multiply(BigDecimal.valueOf(count2GB));
	}

	
}
