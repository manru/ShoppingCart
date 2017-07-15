package com.amaysim.deals.pricing.impl;
import java.math.BigDecimal;

import com.amaysim.deals.Deal;
import com.amaysim.lookup.DealType;
import com.amaysim.lookup.Products;
import com.amaysm.cart.MyShoppingCart;

/**
 * The Class OneGigDataPackDeal.
 */
public class OneGigDataPackDeal implements Deal
{

	/* (non-Javadoc)
	 * @see com.amaysim.deals.Deal#apply(com.amaysm.cart.MyShoppingCart)
	 */
	@Override
	public void apply(MyShoppingCart cart) 
	{
		long count1GBPack = cart.getItems().stream()
				.filter(p -> p.productCode().equals(Products.GB_1.productCode()))
				.count();
		BigDecimal subTotal = Products.GB_1.price().multiply(BigDecimal.valueOf(count1GBPack));
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

}
