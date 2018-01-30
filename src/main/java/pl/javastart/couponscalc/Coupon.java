package pl.javastart.couponscalc;

public class Coupon {

    private final Category category;
    private final int discountValueInPercents;

    public Coupon(Category category, int discountValueInPercents) {
        this.category = category;
        this.discountValueInPercents = discountValueInPercents;
    }

    public Category getCategory() {
        return category;
    }

    public int getDiscountValueInPercents() {
        return discountValueInPercents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coupon coupon = (Coupon) o;

        if (discountValueInPercents != coupon.discountValueInPercents) return false;
        return category == coupon.category;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + discountValueInPercents;
        return result;
    }
}
