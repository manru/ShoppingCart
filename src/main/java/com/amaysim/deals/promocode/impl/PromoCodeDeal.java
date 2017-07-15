package com.amaysim.deals.promocode.impl;
import java.math.BigDecimal;

import com.amaysim.deals.Deal;
import com.amaysim.lookup.DealType;
import com.amaysim.lookup.PromoCode;
import com.amaysm.cart.MyShoppingCart;

/**
 * The Class PromoCodeDeal.
 */
public class PromoCodeDeal implements Deal
{
	
	/** The 100% subtracted the 10% discount. */
	BigDecimal percent = BigDecimal.valueOf(90);
	
	/** The percentage. */
	final BigDecimal percentage = percent.divide(BigDecimal.valueOf(100));
	
	/* (non-Javadoc)
	 * @see com.amaysim.deals.Deal#apply(com.amaysm.cart.MyShoppingCart)
	 */
	@Override
	public void apply(MyShoppingCart cart) {
		if(cart.getPromoCode().contains(PromoCode.AMAYSIM_PROMOCODE))
		{
			BigDecimal newTotal = cart.getTotalPrice().multiply(percentage);
			cart.setTotal(newTotal);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.amaysim.deals.Deal#getDealType()
	 */
	@Override
	public DealType getDealType() 
	{
		return DealType.PROMOCOODE;
	}

}
