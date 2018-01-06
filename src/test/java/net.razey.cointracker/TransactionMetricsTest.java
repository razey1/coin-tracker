package net.razey.cointracker;

import net.razey.cointracker.enums.Currency;
import net.razey.cointracker.model.Money;
import net.razey.cointracker.model.Transaction;
import net.razey.cointracker.model.TransactionMetrics;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TransactionMetricsTest {


    @Test(dataProvider = "transactionDataProvider")
    public void testBtcEquivalentPerCoin(Transaction transaction) {
        TransactionMetrics metrics = new TransactionMetrics(transaction);

//        assertThat(metrics.getBtcEquivalentPerCoin()).isEqualTo(1.);
        System.out.println(transaction);
        System.out.println(metrics);
        System.out.println();
    }

    @DataProvider
    private Object[][] transactionDataProvider() {
        Transaction btcUsdBtc = Transaction.builder()
                .purchased(new Money(Currency.BTC, 1.5))
                .sold(new Money(Currency.USD, 22500.))
                .snapshotPrice(new Money(Currency.BTC, 15000.))
                .build();

        Transaction usdBtcBtc = Transaction.builder()
                .purchased(new Money(Currency.USD, 22500.))
                .sold(new Money(Currency.BTC, 1.5))
                .snapshotPrice(new Money(Currency.BTC, 15000.))
                .build();


        return new Object[][]{
                {btcUsdBtc},
                {usdBtcBtc}};

    }
}


//OG currency = USD 100
//NEW curency = btc
//snapshot = btc 17000