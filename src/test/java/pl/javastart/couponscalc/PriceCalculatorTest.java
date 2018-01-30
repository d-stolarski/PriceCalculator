package pl.javastart.couponscalc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PriceCalculatorTest {

    @Test
    public void shouldReturnZeroForNoProducts() {
        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        // when
        double result = priceCalculator.calculatePrice(null, null);
        // then
        assertThat(result, is(0.0));
    }

    @Test
    public void shouldReturnPriceForSingleProductAndNoCoupons() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));

        // when
        double result = priceCalculator.calculatePrice(products, null);

        // then
        assertThat(result, is(5.99));
    }

    @Test
    public void shouldReturnPriceForSingleProductAndOneCoupon() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 20));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result, is(4.79));
    }

    @Test
    public void shouldReturnPriceWhenCouponHaveNoCategory(){

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));
        products.add(new Product("Sweter", 54.44, Category.HOME));
        products.add(new Product("Dżem", 1.89, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(null, 10));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result, is(56.09));
    }

    @Test
    public void shouldReturnPriceForCouponWithCategory(){

        //given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Odświeżacz", 4.00, Category.HOME));
        products.add(new Product("Sweter", 3.00, Category.HOME));
        products.add(new Product("Dżem", 2.00, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.HOME, 10));

        //when
        double result = priceCalculator.calculatePrice(products, coupons);

        //then
        assertThat(result, is(8.30));
    }

    @Test
    public void shouldReturnPriceForCouponWithBetterDiscount(){
        //given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 6.00, Category.FOOD));
        products.add(new Product("Opony", 100.00, Category.CAR));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 50));
        coupons.add(new Coupon(Category.CAR, 10));

        //when
        double result = priceCalculator.calculatePrice(products, coupons);

        //then
        assertThat(result, is(96.00));

    }
}