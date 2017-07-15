package com.amaysim.deals.freebies.impl;

import com.amaysim.deals.Deal;
import com.amaysim.lookup.DealType;
import com.amaysim.lookup.Products;
import com.amaysm.cart.MyShoppingCart;

// TODO: Auto-generated Javadoc
/**
 * The Class TwoGigDeal.
 */
public class TwoGigFreebieDeal implements Deal 
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
		
		for(int i=0; i < count2GB; i++)
		{
			cart.add(Products.GB_1);
		}		
	}

	/* (non-Javadoc)
	 * @see com.amaysim.deals.Deal#getDealType()
	 */
	@Override
	public DealType getDealType() 
	{
		return DealType.FREEBIES;
	}

}
