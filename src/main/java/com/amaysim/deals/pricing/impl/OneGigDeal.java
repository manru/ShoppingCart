package com.amaysim.deals.pricing.impl;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.amaysim.deals.Deal;
import com.amaysim.lookup.DealType;
import com.amaysim.lookup.Products;
import com.amaysm.cart.MyShoppingCart;

/**
 * The Class OneGigDeal.
 */
public class OneGigDeal implements Deal 
{

	/** The no of set. */
	private final int noOfSet = 3;
	
	/** The price multiplier. */
	private final BigDecimal priceMultiplier = BigDecimal.valueOf(2);

	/* (non-Javadoc)
	 * @see com.amaysim.deals.Deal#apply(com.amaysm.cart.MyShoppingCart)
	 */
	public void apply(MyShoppingCart cart) {

		Long count1GB = cart.getItems().stream().filter(p -> p.productCode()
				.equals(Products.ULT_SMALL.productCode()))
				.count();
		BigDecimal subTotal = getSubTotal(count1GB);
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
	 * @param count1GB no of 1 GB Product in Cart
	 * @return the sub total
	 */
	private BigDecimal getSubTotal(Long count1GB) 
	{
		BigDecimal subTotal = BigDecimal.ZERO;
		BigDecimal noOfItems = BigDecimal.valueOf(count1GB);
		if (count1GB.intValue() >= noOfSet)
		{
			BigDecimal noOfSet = BigDecimal.valueOf(this.noOfSet);
			BigDecimal sets = noOfItems.divide(noOfSet,0, RoundingMode.FLOOR);
			subTotal = Products.ULT_SMALL.price().multiply(priceMultiplier).multiply(sets);
			BigDecimal remainingItems = noOfItems.remainder(noOfSet);
			BigDecimal remainingItemsPrice = remainingItems.multiply(Products.ULT_SMALL.price());
			subTotal  = subTotal.add(remainingItemsPrice);
			
		} 
		else 
		{
			subTotal = noOfItems.multiply(Products.ULT_SMALL.price());
		}
		
		return subTotal;
	}
	
	

	
}
