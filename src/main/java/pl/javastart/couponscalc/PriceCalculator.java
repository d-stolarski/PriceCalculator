package pl.javastart.couponscalc;

import java.util.List;

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

            return Math.round(sumPrice * 100.0) / 100.0;

        } else if (coupons.size() == 1 && coupons.get(0).getCategory() == null) {
            double sumPrice = 0.0;
            double priceDiscount = 0.0;
            double finalPrice = 0.0;
            for (Product prod : products) {
                sumPrice += prod.getPrice();
            }
            priceDiscount = (coupons.get(0).getDiscountValueInPercents() * sumPrice) / 100;
            finalPrice = sumPrice - priceDiscount;

            return Math.round(finalPrice * 100.0) / 100.0;

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


            return Math.round(sumPrice * 100.0) / 100.0;

        } else if (coupons.size() > 1) {
            for (Product prod : products) {
                for (Coupon coup : coupons) {
                    if (prod.getCategory().equals(coup.getCategory())) {

                    }
                }
            }
        }
        return 2.0;
    }

}
