package com.geek.refactor.rent;

import info.solidsoft.mockito.java8.api.WithBDDMockito;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerTest implements WithBDDMockito, WithAssertions {
    /**
     * 场景
     *
     * 路人甲 租 电影 《东邪西毒》
     *
     * case 输入:
     *
     *
     * case 预期:
     *
     *
     * Rental Record for 路人甲
     * 	东邪西毒	9.0
     * 	闻香识女人	2.0
     * 	龙猫	1.5
     * Amount owed is 12.5
     * You earned 4 frequent renter points
     */
    @Test
    public void testStatement() {
        /*
         * 假设
         */
        Movie movie = new Movie("东邪西毒", Movie.NEW_RELEASE);
        Movie movie2 = new Movie("闻香识女人", Movie.REGULAR);
        Movie movie3 = new Movie("龙猫", Movie.CHILDRENS);
        Customer customer = new Customer("路人甲");
        customer.addRental(new Rental(movie, 3));
        customer.addRental(new Rental(movie2, 2));
        customer.addRental(new Rental(movie3, 2));

        /*
         * 执行
         */
        String result = customer.statement();
        /*
         * 验证
         */
        String expect = "Rental Record for 路人甲\n" +
            "\t东邪西毒\t9.0\n" +
            "\t闻香识女人\t2.0\n" +
            "\t龙猫\t1.5\n" +
            "Amount owed is 12.5\n" +
            "You earned 4 frequent renter points";
        assertThat(result).isEqualTo(expect);

    }
}
