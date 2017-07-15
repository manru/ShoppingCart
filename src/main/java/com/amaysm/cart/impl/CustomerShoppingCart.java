package com.amaysm.cart.impl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.amaysim.deals.Deal;
import com.amaysim.lookup.DealType;
import com.amaysim.lookup.PromoCode;
import com.amaysim.products.Product;
import com.amaysm.cart.MyShoppingCart;


/**
 * The Class CustomerShoppingCart.
 */
public class CustomerShoppingCart implements MyShoppingCart 
{
	
	/** The deals. */
	List<Deal> deals = new ArrayList<>();
	
	/** The product in cart. */
	List<Product> productInCart = new ArrayList<Product>();
	
	/** The promo code. */
	List<PromoCode> promoCode = new ArrayList<PromoCode>();
	
	/** The total price. */
	BigDecimal totalPrice = BigDecimal.ZERO;
	

				
	/**
	 * Instantiates a new customer shopping cart.
	 *
	 * @param deals the deals
	 */
	public CustomerShoppingCart(List<Deal> deals)
	{
		List<Deal> currentPricingRules = deals.stream()
				.filter(deal -> deal.getDealType().equals(DealType.PRICING))
				.collect(Collectors.toList());
		List<Deal> currentFreebiesRules = deals.stream()
				.filter(deal -> deal.getDealType().equals(DealType.FREEBIES))
				.collect(Collectors.toList());
		List<Deal> currentPromoRules = deals.stream()
				.filter(deal -> deal.getDealType().equals(DealType.PROMOCOODE))
				.collect(Collectors.toList());
		
		//Should be in this order
		this.deals.addAll(currentPricingRules);
		this.deals.addAll(currentFreebiesRules);
		this.deals.addAll(currentPromoRules);
	}

	/* (non-Javadoc)
	 * @see com.amaysm.cart.AmaysimShoppingCart#add(com.amaysim.products.Product)
	 */
	public void add(Product product) 
	{
		productInCart.add(product);
	}

	/* (non-Javadoc)
	 * @see com.amaysm.cart.AmaysimShoppingCart#add(com.amaysim.products.Product, com.amaysim.lookup.PromoCode)
	 */
	public void add(Product product, PromoCode promo) 
	{
		productInCart.add(product);
		promoCode.add(promo);
	}

	/* (non-Javadoc)
	 * @see com.amaysm.cart.AmaysimShoppingCart#getTotal()
	 */
	public BigDecimal getTotal() 
	{
		
		this.deals.stream().forEach(deal -> deal.apply(this));
		totalPrice = totalPrice.setScale(2,
				RoundingMode.HALF_EVEN);
		
		return totalPrice;
	}

	/* (non-Javadoc)
	 * @see com.amaysm.cart.AmaysimShoppingCart#getItems()
	 */
	public List<Product> getItems() 
	{
		return productInCart;
	}
	
	/* (non-Javadoc)
	 * @see com.amaysm.cart.MyShoppingCart#addTotal(java.math.BigDecimal)
	 */
	public void addTotal(BigDecimal subTotal)
	{
		totalPrice = totalPrice.add(subTotal);
	}

	/* (non-Javadoc)
	 * @see com.amaysm.cart.MyShoppingCart#getPromoCode()
	 */
	@Override
	public List<PromoCode> getPromoCode() 
	{
		return this.promoCode;
	}

	/* (non-Javadoc)
	 * @see com.amaysm.cart.MyShoppingCart#setTotal(java.math.BigDecimal)
	 */
	@Override
	public void setTotal(BigDecimal newTotal)
	{
		this.totalPrice = newTotal;
	}

	/* (non-Javadoc)
	 * @see com.amaysm.cart.MyShoppingCart#getTotalPrice()
	 */
	@Override
	public BigDecimal getTotalPrice() 
	{
		return this.totalPrice;
	}

}
