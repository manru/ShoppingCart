package com.amaysim.deals.pricing.impl;
import java.math.BigDecimal;

import com.amaysim.deals.Deal;
import com.amaysim.lookup.DealType;
import com.amaysim.lookup.Products;
import com.amaysm.cart.MyShoppingCart;

/**
 * The Class FiveGigDeal.
 */
public class FiveGigDeal implements Deal 
{

	/** The no of set. */
	private final int noOfSet = 3;
	
	/** The new price. */
	private final BigDecimal newPrice = BigDecimal.valueOf(39.90);

	/* (non-Javadoc)
	 * @see com.amaysim.deals.Deal#apply(com.amaysm.cart.MyShoppingCart)
	 */
	public void apply(MyShoppingCart cart) 
	{
		Long count5GB = cart.getItems().stream().filter(p -> p.productCode()
				.equals(Products.ULT_LARGE.productCode()))
				.count();
		BigDecimal subTotal = getSubTotal(count5GB);
		cart.addTotal(subTotal);
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
	 * @param count5GB no of 5 GB Product in Cart
	 * @return the sub total
	 */
	private BigDecimal getSubTotal(Long count5GB) 
	{
		BigDecimal subTotal = BigDecimal.ZERO;
		BigDecimal noOfItems = BigDecimal.valueOf(count5GB);
		if (count5GB.intValue() > noOfSet) 
		{
			subTotal = newPrice.multiply(BigDecimal.valueOf(count5GB));
		} 
		else 
		{
			subTotal = noOfItems.multiply(Products.ULT_LARGE.price());
		}
		return subTotal;
	}

}
