package pl.javastart.couponscalc;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PriceCalculator {

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {

        if (coupons == null && products == null) {
            return 0.0;
        } else if (coupons == null) {
            double sum = 0.0;
            for (Product prod : products) {
                sum += prod.getPrice();
            }
            return sum;
        } else if (coupons.size() == 1 && products.size() == 1) {
            double sumPrice = 0.0;
            double priceDiscount = 0.0;
            priceDiscount = (coupons.get(0).getDiscountValueInPercents() * products.get(0).getPrice()) / 100;
            sumPrice = products.get(0).getPrice() - priceDiscount;

            return roundScore(sumPrice);

        } else if (coupons.size() == 1 && coupons.get(0).getCategory() == null) {
            double sumPrice = 0.0;
            double priceDiscount = 0.0;
            double finalPrice = 0.0;
            for (Product prod : products) {
                sumPrice += prod.getPrice();
            }
            priceDiscount = (coupons.get(0).getDiscountValueInPercents() * sumPrice) / 100;
            finalPrice = sumPrice - priceDiscount;

            return roundScore(finalPrice);

        } else if (coupons.size() == 1 && coupons.get(0).getCategory() != null) {
            Category category = coupons.get(0).getCategory();
            double sumPrice = 0.0;
            double categoryPrice = 0.0;
            double noCategoryPrice = 0.0;
            for (Product prod : products) {
                if (prod.getCategory().equals(category)) {
                    categoryPrice += prod.getPrice() - (prod.getPrice() * coupons.get(0).getDiscountValueInPercents() / 100);
                } else {
                    noCategoryPrice += prod.getPrice();
                }

            }
            sumPrice = categoryPrice + noCategoryPrice;

            return roundScore(sumPrice);

        } else if (coupons.size() > 1) {
            double betterDiscount = 0.0;

            TreeSet<Double> discounts = new TreeSet<>();
            for (int i = 0; i < coupons.size(); i++) {
                discounts.add(calculatePriceForOneCoupon(coupons.get(i), products));
            }
            betterDiscount = discounts.first();

            return betterDiscount;
        }
        return 0.0;
    }

    public static double roundScore(double score) {
        return Math.round(score * 100.0) / 100.0;
    }

    public static double calculatePriceForOneCoupon(Coupon coupon, List<Product> products) {
        double price = 0.0;
        Category category = coupon.getCategory();
        double sumPrice = 0.0;
        double categoryPrice = 0.0;
        double noCategoryPrice = 0.0;
        for (Product prod : products) {
            if (prod.getCategory().equals(category)) {
                categoryPrice += prod.getPrice() - (prod.getPrice() * coupon.getDiscountValueInPercents() / 100);
            } else {
                noCategoryPrice += prod.getPrice();
            }

        }
        sumPrice = categoryPrice + noCategoryPrice;

        return roundScore(sumPrice);
    }
}
