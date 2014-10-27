package net.dg.generic.money;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode(of={"amount", "currency"})
public class Money {
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    static final Currency KRW = Currency.getInstance("KRW");
    static final Currency USD = Currency.getInstance("USD");

    @Column(name = "FEE_AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;
    @Column(name = "FEE_CURRENCY")
    private Currency currency = KRW;

    public static Money wons(long amount) {
        return valueOf(amount, KRW);
    }

    public static Money wons(double amount) {
        return valueOf(amount, KRW);
    }

    public static Money wons(double amount, RoundingMode rounding) {
        return valueOf(amount, KRW, rounding);
    }

    public static Money dollars(long amount) {
        return valueOf(amount, USD);
    }

    public static Money dollars(double amount) {
        return valueOf(amount, USD);
    }

    public static Money dollars(double amount, RoundingMode rounding) {
        return valueOf(amount, USD, rounding);
    }

    public static Money valueOf(long amount, Currency currency) {
        return valueOf(amount, currency, DEFAULT_ROUNDING);
    }

    public static Money valueOf(long amount, Currency currency, RoundingMode rounding) {
        return valueOf(BigDecimal.valueOf(amount), currency, rounding);
    }

    public static Money valueOf(double amount, Currency currency) {
        return valueOf(amount, currency, DEFAULT_ROUNDING);
    }

    public static Money valueOf(double amount, Currency currency, RoundingMode rounding) {
        return valueOf(BigDecimal.valueOf(amount), currency, rounding);
    }

    public static Money valueOf(BigDecimal amount, Currency currency) {
        return valueOf(amount, currency, DEFAULT_ROUNDING);
    }

    public static Money valueOf(BigDecimal amount, Currency currency, RoundingMode rounding) {
        return new Money(amount, currency, rounding);
    }

    Money(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING);
    }

    Money(BigDecimal amount, Currency currency, RoundingMode rounding) {
        this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding.ordinal());
        this.currency = currency;
    }

    public Money times(int multiplicand) {
        return new Money(amount.multiply(BigDecimal.valueOf(multiplicand)),currency);
    }

    public Money times(double multiplicand) {
        return new Money(amount.multiply(BigDecimal.valueOf(multiplicand)),currency);
    }

    public Money plus(Money added) {
        if (!getCurrency().equals(added.getCurrency())) {
            throw new IllegalArgumentException("Currency is not same!");
        }

        return Money.valueOf(this.amount.add(added.amount), getCurrency());
    }

    public Money minus(Money added) {
        if (!getCurrency().equals(added.getCurrency())) {
            throw new IllegalArgumentException("Currency is not same!");
        }

        return Money.valueOf(this.amount.subtract(added.amount), getCurrency());
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
