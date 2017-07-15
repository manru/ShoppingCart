import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.amaysim.deals.Deal;
import com.amaysim.deals.freebies.impl.TwoGigFreebieDeal;
import com.amaysim.deals.pricing.impl.FiveGigDeal;
import com.amaysim.deals.pricing.impl.OneGigDataPackDeal;
import com.amaysim.deals.pricing.impl.OneGigDeal;
import com.amaysim.deals.pricing.impl.TwoGigDeal;
import com.amaysim.deals.promocode.impl.PromoCodeDeal;
import com.amaysim.lookup.Products;
import com.amaysim.lookup.PromoCode;
import com.amaysim.products.Product;
import com.amaysm.cart.AmaysimShoppingCart;
import com.amaysm.cart.impl.CustomerShoppingCart;

/**
 * The Class Main.
 */
public class Main 
{

	/**
	 * The main method. To simulate UI, No specific validations done...
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		try {
			System.out.println("Welcome to Amsaysim!!!!");

			boolean isCheckOut = false;
			AmaysimShoppingCart customerCart = new CustomerShoppingCart(getPricingRules());
			while (!isCheckOut){
				Scanner input = new Scanner(System.in);
				Map<Integer,Product> productSelections = new HashMap<>();
				productSelections.put(1, Products.ULT_SMALL);
				productSelections.put(2, Products.ULT_MEDIUM);
				productSelections.put(3, Products.ULT_LARGE);
				productSelections.put(4, Products.GB_1);
				
				System.out.println("Please input the number beside the product you want to Add in your Cart:");
				int i = 1;
				for(Product p: Products.values())
				{
					System.out.println(i + " - " + p.productName() + " $"  + p.price());
					i++;
				}
				
				String selection = input.next();
				Product productSelected = productSelections.get(Integer.valueOf(selection));
				System.out.println("Please enter quantity:");
				Integer quantity = input.nextInt();
				System.out.println("Do you have a PromoCode? Y/N");
				String promoCodeExist = input.next();
				if(promoCodeExist.toUpperCase().equals("Y"))
				{
					System.out.println("Please Enter Promo Code:");
					String promoCode = input.next();
					if(promoCode.equals(PromoCode.AMAYSIM_PROMOCODE.getPromoCode()))
					{
						for(int x = 0; x < quantity; x++)
						customerCart.add(productSelected, PromoCode.AMAYSIM_PROMOCODE);
					} 
					else
					{
						System.out.println("Invalid PromoCode. Product not Added to Cart.");
					}
				}
				else
				{
					for(int x = 0; x < quantity; x++)
					customerCart.add(productSelected);
				}
				System.out.println("Do you want to check out? Y/N");
				String isCheckOutInput = input.next();
				if(isCheckOutInput.toUpperCase().equals("Y"))
				{
					isCheckOut = true;
				}
			}
			
			System.out.println("Your total price is : $" + customerCart.getTotal());
			List<Product> productsBought = customerCart.getItems().stream().distinct().collect(Collectors.toList());
			productsBought.stream().forEach(d -> 
			System.out.println(
			customerCart.getItems().stream().filter(c-> c.productCode().equals(d.productCode())).count() + " X " + d.productName()));
		} catch (Exception e) {
			System.out.println("System problem encountered. Please contact Customer Service.");
			//Log in logfile
		}
		
	}
	
	/**
	 * Gets the pricing rules.
	 *
	 * @return the pricing rules
	 */
	private static List<Deal> getPricingRules()
	{
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
