package cse12pa1student;

import java.util.Collection;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import jdk.jfr.Timestamp;

@RunWith(Parameterized.class)
public class BasketTest {

	public static Collection<Object[]> BAGNUMS =
			Arrays.asList(new Object[][] { {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12} });
	private int bagType;

	public BasketTest(int bagType) {
		super();
		this.bagType = bagType;
	}

	@Parameterized.Parameters(name = "Basket{index}")
	public static Collection<Object[]> bags() {
		return BAGNUMS;
	}
	
	private Basket makeBasket() {
		switch(this.bagType) {
			case 0: return new Basket0();
			case 1: return new Basket1();
			case 2: return new Basket2();
			case 3: return new Basket3();
			case 4: return new Basket4();
			case 5: return new Basket5();
			case 6: return new Basket6();
			case 7: return new Basket7();
			case 8: return new Basket8();
			case 9: return new Basket9();
			case 10: return new Basket10();
			case 11: return new Basket11();
			case 12: return new Basket12();
		}
		return null;
	}
	
	@Test
	public void addedHasCount1() {
		Basket basketToTest = makeBasket();

		Item i = new Item("Shampoo", 5);
		basketToTest.addToBasket(i);
		assertEquals(1, basketToTest.count());
	}
	
	@Test
	public void addDuplicateHasCount3() {
		Basket basket2 = makeBasket();
		
		Item j = new Item("Body Wash", 7);
		Item k = new Item("Apple", 2);
		Item l = k;
		basket2.addToBasket(j);
		basket2.addToBasket(k);
		basket2.addToBasket(l);
		assertEquals(3, basket2.count());
	}

	@Test
	public void removedFromBasketHasCount2() {
		Basket basket3 = makeBasket();
		
		Item d = new Item("Chips", 3);
		Item e = new Item("Oreos", 3);
		Item f = new Item("Gatorade", 2);
		basket3.addToBasket(d);
		basket3.addToBasket(e);
		basket3.addToBasket(f);
		basket3.removeFromBasket(f);
		assertEquals(2, basket3.count());
	}

	@Test
	public void calculateTotalCost() {
		Basket basket4 = makeBasket();
		
		Item s = new Item("Yogurt", 2);
		Item t = new Item("Cookies", 2);
		Item u = new Item("Cheese", 2);
		Item v = new Item("Milk", 2);
		basket4.addToBasket(s);
		basket4.addToBasket(t);
		basket4.addToBasket(u);
		basket4.addToBasket(v);
		assertEquals(8, basket4.totalCost());
	}
	
	@Test
	public void removeAllGatoradeDrinks() {
		Basket basket5 = makeBasket();
		
		Item a = new Item("Gatorade", 2);
		Item b = a;
		Item c = new Item("Lemonade", 2);
		Item d = new Item("Water", 1);
		basket5.addToBasket(a);
		basket5.addToBasket(b);
		basket5.addToBasket(c);
		basket5.addToBasket(d);
		basket5.removeAllFromBasket(a);
		assertEquals(basket5.count(), 2);
	}
	
	@Test
	public void countIceCream() {
		Basket basket6 = makeBasket();
		
		Item i = new Item("Ice cream", 4);
		Item j = i;
		Item k = i;
		basket6.addToBasket(i);
		basket6.addToBasket(j);
		basket6.addToBasket(k);
		assertEquals(3, basket6.countItem(i));
	}
	
	@Test
	public void takeOutDrinks() {
		Basket basket7 = makeBasket();
		
		Item p = new Item("Coke", 3);
		Item q = new Item("Chips", 3);
		Item r = new Item("Root Beer", 4);
		Item s = new Item("Cheez-Its", 2);
		Item t = new Item("Pepsi", 3);
		Item u = new Item("Sprite", 2);
		basket7.addToBasket(p);
		basket7.addToBasket(q);
		basket7.addToBasket(r);
		basket7.addToBasket(s);
		basket7.addToBasket(t);
		basket7.addToBasket(u);
		basket7.removeFromBasket(p);
		basket7.removeFromBasket(r);
		basket7.removeFromBasket(t);
		basket7.removeFromBasket(u);
		assertEquals(2, basket7.count());
	}
	
	@Test
	public void takeOutAllOreos() {
		Basket basket8 = makeBasket();
		
		Item a = new Item("Oreos", 3);
		Item b = new Item("Chips Ahoy", 3);
		Item c = a;
		Item d = a;
		basket8.addToBasket(a);
		basket8.addToBasket(b);
		basket8.addToBasket(c);
		basket8.addToBasket(d);
		basket8.removeAllFromBasket(a);
		assertEquals(basket8.count(), 1);
	}
	
	@Test
	public void countBobaDrinks() {
		Basket basket9 = makeBasket();
		
		Item a = new Item("Boba Milk Tea", 4);
		Item b = new Item("Jasmine Green Tea", 3);
		Item c = a;
		Item d = a;
		basket9.addToBasket(a);
		basket9.addToBasket(b);
		basket9.addToBasket(c);
		basket9.addToBasket(d);
		assertEquals(3, basket9.countItem(a));
	}
	
	@Test
	public void removeAllSandwiches() {
		Basket basket10 = makeBasket();
		
		Item a = new Item("Roast Beef Sandwich", 5);
		Item b = new Item("Garden Salad", 9);
		Item c = a;
		Item d = a;
		Item e = a;
		basket10.addToBasket(a);
		basket10.addToBasket(b);
		basket10.addToBasket(c);
		basket10.addToBasket(d);
		basket10.addToBasket(e);
		basket10.removeAllFromBasket(a);
		assertEquals(basket10.count(), 1);
	}
	
	@Test
	public void takeAwayAllBoxes() {
		Basket basket11 = makeBasket();
		
		Item p = new Item("Box", 3);
		Item q = p;
		Item r = p;
		Item s = p;
		basket11.addToBasket(p);
		basket11.addToBasket(q);
		basket11.addToBasket(r);
		basket11.addToBasket(s);
		basket11.removeAllFromBasket(p);
		assertEquals(basket11.count(), 0);
	}
	
	@Test
	public void takeOutFlowers() {
		Basket basket12 = makeBasket();
		
		Item x = new Item("Rose", 2);
		Item y = new Item("Rose", 2);
		Item z = new Item("Rose", 2);
		basket12.addToBasket(x);
		basket12.addToBasket(y);
		basket12.addToBasket(z);
		basket12.removeAllFromBasket(x);
		assertEquals(basket12.count(), 0);
	}
	
	@Test
	public void removeEveryApple() {
		Basket basket13 = makeBasket();
		
		Item a = new Item("Cucumber", 2);
		Item b = new Item("Apple", 1);
		Item c = new Item("Apple", 1);
		basket13.addToBasket(a);
		basket13.addToBasket(b);
		basket13.addToBasket(c);
		basket13.removeAllFromBasket(b);
		assertEquals(basket13.removeAllFromBasket(b), true);
	}
	
	@Test
	public void addNullsHasCount2() {
		Basket basket14 = makeBasket();
		
		Item x = null;
		Item y = null;
		basket14.addToBasket(x);
		basket14.addToBasket(y);
		assertEquals(2, basket14.count());
	}
	
	@Test
	public void removeNulls() {
		Basket basket15 = makeBasket();
		
		Item x = null;
		Item y = null;
		basket15.addToBasket(x);
		basket15.addToBasket(y);
		basket15.removeAllFromBasket(x);
		assertEquals(0, basket15.count());
	}
	
	@Test
	public void getRidOfBlanks() {
		Basket basket16 = makeBasket();
		
		Item a = new Item("", 0);
		Item b = new Item("", 10);
		basket16.addToBasket(a);
		basket16.addToBasket(b);
		basket16.removeAllFromBasket(a);
		assertEquals(basket16.removeAllFromBasket(b), true);
	}

	@Test
	public void countEveryPhone() {
		Basket basket17 = makeBasket();
		
		Item a = new Item("IPhone", 800);
		Item b = new Item("IPhone", 800);
		basket17.addToBasket(a);
		basket17.addToBasket(b);
		basket17.addToBasket(null);
		basket17.countItem(b);
		assertEquals(basket17.countItem(a), 2);
	}
	
	@Test
	public void noItemsRemaining() {
		Basket lastBasket = makeBasket();
		
		Item m = new Item("Iphone", 600);
		Item n = m;
		Item o = new Item("Macbook Pro", 1300);
		Item p = new Item("AirPods", 150);
		Item q = o;
		lastBasket.addToBasket(m);
		lastBasket.addToBasket(n);
		lastBasket.addToBasket(o);
		lastBasket.addToBasket(p);
		lastBasket.addToBasket(q);
		lastBasket.empty();
		assertEquals(0, lastBasket.count());
	}

}
