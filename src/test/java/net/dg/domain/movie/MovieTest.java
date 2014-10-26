package net.dg.domain.movie;

import com.google.common.collect.Lists;
import net.dg.domain.movie.discount.DiscountStrategy;
import net.dg.domain.movie.discount.NoneDiscountStrategy;
import net.dg.domain.movie.discount.PercentDiscountStrategy;
import net.dg.domain.movie.rule.Rule;
import net.dg.domain.movie.rule.SequenceRule;
import net.dg.generic.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class MovieTest {

    private DiscountStrategy discountStrategy;
    private final Money fixedFee = Money.wons(10000);
    private final String title = "title";
    private final int runningTime = 120;
    private Showing showing;
    
    private Movie sut;

    @Before
    public void setup() {
    }

    @Test
    public void test_영화정보() throws Exception {

        sut = new Movie(title, runningTime, fixedFee);

        assertThat(sut.getTitle()).isEqualTo(title);
        assertThat(sut.getFee()).isEqualTo(fixedFee);
        assertThat(sut.getRunningTime()).isEqualTo(runningTime);
    }

    @Test
    public void test_할인정책_없음() throws Exception {
        discountStrategy = new NoneDiscountStrategy();
        sut = new Movie(title, runningTime, fixedFee, discountStrategy);
        showing = new Showing(sut, 1, null);

        Money fee = sut.calculateFee(showing);

        assertThat(fee.getAmount()).isEqualTo(fixedFee.getAmount());
    }

    @Test
    public void test_퍼센테이지_할인정책() throws Exception {
        Money expected = Money.wons(9000);
        Rule rule = new SequenceRule(1);
        List<Rule> rules = Lists.newArrayList(rule);
        discountStrategy = new PercentDiscountStrategy(10, rules);

        sut = new Movie(title, runningTime, fixedFee, discountStrategy);
        showing = new Showing(sut, 1, null);

        Money fee = sut.calculateFee(showing);

        assertThat(fee.getAmount()).isEqualTo(expected.getAmount());
    }
}
