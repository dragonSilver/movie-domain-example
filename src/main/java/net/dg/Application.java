package net.dg;

import com.google.common.collect.Lists;
import net.dg.domain.movie.*;
import net.dg.domain.movie.discount.AmountDiscountStrategy;
import net.dg.domain.movie.rule.Rule;
import net.dg.domain.movie.rule.SequenceRule;
import net.dg.domain.movie.rule.TimeOfDayRule;
import net.dg.generic.base.DateTimeInterval;
import net.dg.generic.base.DayOfWeek;
import net.dg.generic.base.TimeInterval;
import net.dg.generic.money.Money;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.List;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application extends WebMvcConfigurerAdapter {

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        importInitData(ctx);
    }

    private static void importInitData(ApplicationContext ctx) {

        CustomerRepository customerRepository = ctx.getBean(CustomerRepository.class);
        MovieRepository movieRepository = ctx.getBean(MovieRepository.class);
        ShowingRepository showingRepository = ctx.getBean(ShowingRepository.class);


        customerRepository.save(new Customer("최용은"));
        customerRepository.save(new Customer("문채영"));

        SequenceRule sequenceRuleOfFirst = new SequenceRule(1);
        SequenceRule sequenceRuleOfSecond = new SequenceRule(2);

        TimeOfDayRule timeOfDayRuleOfFirst = new TimeOfDayRule(DayOfWeek.TUE, TimeInterval.of("T12", "T22"));
        TimeOfDayRule timeOfDayRuleOfSecond =new TimeOfDayRule(DayOfWeek.WED, TimeInterval.of("T18", "T20"));

        List<Rule> rulesOfFirst = Lists.newArrayList(sequenceRuleOfFirst, timeOfDayRuleOfFirst);
        AmountDiscountStrategy discountOfFirst = new AmountDiscountStrategy(rulesOfFirst, Money.wons(1000));

        List<Rule> rulesOfSecond = Lists.newArrayList(sequenceRuleOfSecond, timeOfDayRuleOfSecond);
        AmountDiscountStrategy discountOfSecond = new AmountDiscountStrategy(rulesOfSecond, Money.wons(2000));

        Movie movieOfFirst = movieRepository.save(new Movie("신림프로그래머", 120, Money.wons(20000), discountOfFirst));
        Movie movieOfSecond = movieRepository.save(new Movie("살찐프로그래머", 120, Money.wons(10000), discountOfSecond));

        List<DateTimeInterval> dateTimeIntervals =
                Lists.newArrayList(
                        DateTimeInterval.of("2014-10-28T12", "2014-10-28T14"),
                        DateTimeInterval.of("2014-10-28T13", "2014-10-28T15"),
                        DateTimeInterval.of("2014-10-28T16", "2014-10-28T18"),
                        DateTimeInterval.of("2014-10-28T19", "2014-10-28T21"),
                        DateTimeInterval.of("2014-10-28T22", "2014-10-29T0") );

        for(Movie eachMovie : Lists.newArrayList(movieOfFirst, movieOfSecond)) {
            int sequence = 1;
            for(DateTimeInterval eachInterval : dateTimeIntervals) {
                showingRepository.save(new Showing(eachMovie, sequence++, eachInterval));
            }
        }
    }
}
