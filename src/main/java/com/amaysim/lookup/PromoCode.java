package com.amaysim.lookup;

public enum PromoCode {
	
	AMAYSIM_PROMOCODE("1<3AMAYSIM");

	private String promoCode;

	
	PromoCode(String promoCode)
	{
		this.promoCode = promoCode;
	}
	
	public String getPromoCode()
	{
		return this.promoCode;
	}
}
