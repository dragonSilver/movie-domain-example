package net.dg.generic.base;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Ratio {

    private BigDecimal numerator;
    private BigDecimal denominator;

    public static Ratio of(double ratio) {
        String ratioString = new Double(ratio).toString();
        int scale = ratioString.length() - ratioString.lastIndexOf(".") - 1;
        BigDecimal coversionRatio = new BigDecimal(ratio).setScale(scale, RoundingMode.HALF_EVEN.ordinal());
        return new Ratio(new BigDecimal(coversionRatio.unscaledValue()), new BigDecimal(Math.pow(10, scale)));
    }

    Ratio(BigDecimal numerator, BigDecimal denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    BigDecimal getNumerator() {
        return numerator;
    }

    BigDecimal getDenominator() {
        return denominator;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public Ratio plus(Ratio other) {
        return new Ratio(numerator.add(other.numerator), denominator);
    }

    public boolean isOne() {
        return numerator.equals(denominator);
    }
}
