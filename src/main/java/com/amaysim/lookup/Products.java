package com.amaysim.lookup;
import java.math.BigDecimal;

import com.amaysim.products.Product;

public enum Products implements Product
{
	
	
	ULT_SMALL("Unlimited 1 GB","ult_small",BigDecimal.valueOf(24.90)),
	ULT_MEDIUM("Unilimited 2 GB","ult_medium",BigDecimal.valueOf(29.90)),
	ULT_LARGE("Unlimited 5 GB","ult_large",BigDecimal.valueOf(44.90)),
	GB_1("1 GB Data-pack" , "1_gb",BigDecimal.valueOf(9.90));
	
	String productName;
	String productCode;
	BigDecimal price;
	
	Products(String productName, String productCode, BigDecimal price)
	{
		this.productName = productName;
		this.productCode = productCode;
		this.price = price;
	}
	
	public String productName()
	{
		return productName;
	}
	
	public String productCode()
	{
		return productCode;
	
	}
	public BigDecimal price() {
		return price;
	}
	
	

	
}
