package com.amaysim.test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.amaysim.deals.Deal;
import com.amaysim.deals.freebies.impl.TwoGigFreebieDeal;
import com.amaysim.deals.pricing.impl.FiveGigDeal;
import com.amaysim.deals.pricing.impl.OneGigDataPackDeal;
import com.amaysim.deals.pricing.impl.OneGigDeal;
import com.amaysim.deals.pricing.impl.TwoGigDeal;
import com.amaysim.deals.promocode.impl.PromoCodeDeal;
import com.amaysim.lookup.Products;
import com.amaysim.lookup.PromoCode;
import com.amaysm.cart.AmaysimShoppingCart;


public class ShoppingCartTest 
{

	@Test
	public void testScenario1() 
	{
		
		AmaysimShoppingCart myShoppingCart = new ShoppingCartTestData.Builder()
				.addPricingRules(getPricingRules()).addOneGig(3).addFiveGig(1)
				.build();
		BigDecimal expectedTotal = BigDecimal.valueOf(94.70).setScale(2, RoundingMode.HALF_EVEN);
		assertThat(myShoppingCart.getTotal(), is(equalTo(expectedTotal)));
		assertThat(myShoppingCart.getItems().stream().filter(p -> p.productCode().equals(Products.ULT_SMALL.productCode())).count() ,is(equalTo(3L)));
		assertThat(myShoppingCart.getItems().stream().filter(p -> p.productCode().equals(Products.ULT_LARGE.productCode())).count(),is(equalTo(1L)));
		assertThat(myShoppingCart.getItems().size(),is(equalTo(4)));
		
	}
	@Test
	public void testScenario2()
	{
		AmaysimShoppingCart myShoppingCart = new ShoppingCartTestData.Builder()
				.addPricingRules(getPricingRules()).addOneGig(2).addFiveGig(4)
				.build();
		BigDecimal expectedTotal = BigDecimal.valueOf(209.40).setScale(2, RoundingMode.HALF_EVEN);
		assertThat(myShoppingCart.getTotal(), is(equalTo(expectedTotal)));
		assertThat(myShoppingCart.getItems().stream().filter(p -> p.productCode().equals(Products.ULT_SMALL.productCode())).count(), is(equalTo(2L)));
		assertThat(myShoppingCart.getItems().stream().filter(p -> p.productCode().equals(Products.ULT_LARGE.productCode())).count(), is(equalTo(4L)));
		assertThat(myShoppingCart.getItems().size(), is(equalTo(6)));


	}
	
	@Test
	public void testScenario3(){
		AmaysimShoppingCart myShoppingCart = new ShoppingCartTestData.Builder()
				.addPricingRules(getPricingRules()).addOneGig(1).addTwoGig(2)
				.build();
		BigDecimal expectedTotal = BigDecimal.valueOf(84.70).setScale(2, RoundingMode.HALF_EVEN);
		assertThat(myShoppingCart.getTotal(), is(equalTo(expectedTotal)));
		assertThat(myShoppingCart.getItems().stream().filter(p -> p.productCode().equals(Products.ULT_SMALL.productCode())).count(), is(equalTo(1L)));
		assertThat(myShoppingCart.getItems().stream().filter(p -> p.productCode().equals(Products.ULT_MEDIUM.productCode())).count(), is(equalTo(2L)));
		assertThat(myShoppingCart.getItems().stream().filter(p -> p.productCode().equals(Products.GB_1.productCode())).count(), is(equalTo(2L)));

		assertThat(myShoppingCart.getItems().size(), is(equalTo(5)));


	}
	
	@Test
	public void testScenario4(){
		AmaysimShoppingCart myShoppingCart = new ShoppingCartTestData.Builder()
				.addPricingRules(getPricingRules()).addOneGig(1).addOneGigDataPack(1).addPromoCode(PromoCode.AMAYSIM_PROMOCODE)
				.build();			
		BigDecimal expectedTotal = BigDecimal.valueOf(31.32).setScale(2, RoundingMode.HALF_EVEN);
		assertThat(myShoppingCart.getTotal(), is(equalTo(expectedTotal)));
		assertThat(myShoppingCart.getItems().stream().filter(p -> p.productCode().equals(Products.ULT_SMALL.productCode())).count(), is(equalTo(1L)));
		assertThat(myShoppingCart.getItems().stream().filter(p -> p.productCode().equals(Products.GB_1.productCode())).count(), is(equalTo(1L)));
		assertThat(myShoppingCart.getItems().size(), is(equalTo(2)));


	}
	
	
	@Test
	public void testOneGigDeal_withSixProducts() 
	{
	
		AmaysimShoppingCart myShoppingCart = new ShoppingCartTestData.Builder()
				.addPricingRules(getPricingRules())
				.addOneGig(6)
				.build();

		BigDecimal expectedTotal = Products.ULT_SMALL.price().multiply(BigDecimal.valueOf(4)).setScale(2);
		assertThat(myShoppingCart.getTotal(), is(equalTo(expectedTotal)));
	}
	
	@Test
	public void testOneGigDeal_withSevenProducts() 
	{
		AmaysimShoppingCart myShoppingCart = new ShoppingCartTestData.Builder()
				.addPricingRules(getPricingRules())
				.addOneGig(7)
				.build();

		BigDecimal expectedTotal = Products.ULT_SMALL.price().multiply(BigDecimal.valueOf(5)).setScale(2);
		assertThat(myShoppingCart.getTotal(), is(equalTo(expectedTotal)));
	}

	@Test
	public void testEmptyCart() 
	{
	
		AmaysimShoppingCart myShoppingCart = new ShoppingCartTestData.Builder()
				.addPricingRules(getPricingRules())
				.build();
		assertThat(myShoppingCart.getTotal(), is(equalTo(BigDecimal.ZERO.setScale(2))));
	}
	
	

	
	private List<Deal> getPricingRules(){
		List<Deal> deals = new ArrayList<>();
		deals.add(new PromoCodeDeal());
		deals.add(new TwoGigFreebieDeal());
		deals.add(new OneGigDeal());
		deals.add(new OneGigDataPackDeal());
		deals.add(new TwoGigDeal());
		deals.add(new FiveGigDeal());

		return deals;
	
	}

}
