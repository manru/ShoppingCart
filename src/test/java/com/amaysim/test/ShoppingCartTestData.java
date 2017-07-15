package com.amaysim.test;
import java.util.ArrayList;
import java.util.List;

import com.amaysim.deals.Deal;
import com.amaysim.lookup.Products;
import com.amaysim.lookup.PromoCode;
import com.amaysim.products.Product;
import com.amaysm.cart.AmaysimShoppingCart;
import com.amaysm.cart.MyShoppingCart;
import com.amaysm.cart.impl.CustomerShoppingCart;

public class ShoppingCartTestData  extends CustomerShoppingCart{
	
	private List<Product> cartItems = new ArrayList<>();
	private List<PromoCode> promoCodes = new ArrayList<>();
	
	private ShoppingCartTestData(List<Deal> pricingRules)
	{
		super(pricingRules);
		super.getItems().addAll(cartItems);
		super.getPromoCode().addAll(promoCodes);
	}
	
	public static class Builder {
		
		private List<Product> cart = new ArrayList<>();
		public List<PromoCode> promoCode = new ArrayList<>();
		public List<Deal> pricingRules = new ArrayList<>();
		public ShoppingCartTestData.Builder addOneGig(int pcs)
		{
			cart.addAll(multiplyProducts(Products.ULT_SMALL, pcs));
			return this;
			
		}
		public ShoppingCartTestData.Builder addTwoGig(int pcs)
		{
			cart.addAll(multiplyProducts(Products.ULT_MEDIUM, pcs));
			return this;
			
		}
		
		public ShoppingCartTestData.Builder addFiveGig(int pcs)
		{
			cart.addAll(multiplyProducts(Products.ULT_LARGE, pcs));
			return this;
			
		}
		public ShoppingCartTestData.Builder addOneGigDataPack(int pcs)
		{
			cart.addAll(multiplyProducts(Products.GB_1, pcs));
			return this;
			
		}
		public ShoppingCartTestData.Builder addPromoCode(PromoCode promoCode)
		{
			this.promoCode.add(promoCode);
			return this;
			
		}
		public ShoppingCartTestData.Builder addPricingRules(List<Deal> pricingRules)
		{
			this.pricingRules.addAll(pricingRules);
			return this;
			
		}
	
		
		public AmaysimShoppingCart build()
		{
			MyShoppingCart shoppingCartTest = new ShoppingCartTestData(pricingRules);
			shoppingCartTest.getItems().addAll(cart);
			shoppingCartTest.getPromoCode().addAll(promoCode);
			return shoppingCartTest;
		}
		
		private List<Product> multiplyProducts(Products product, int pcs)
		{
			List<Product> prod = new ArrayList<Product>();
			for(int i = 0; i < pcs ; i++)
			{
				prod.add(product);
			}
			return prod;
		}
		
	}


}
