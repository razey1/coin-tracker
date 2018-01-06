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
//        System.out.println(transaction);
        System.out.println(metrics);
        System.out.println();
    }

    @DataProvider
    private Object[][] transactionDataProvider() {
        Transaction btcUsdBtc = Transaction.builder()
                .purchased(new Money(Currency.BTC, 0.00831050))
                .sold(new Money(Currency.USD, 144.24))
                .snapshotPrice(new Money(Currency.BTC, 17356.36))
                .build();

        Transaction xrbBtcBtc = Transaction.builder()
                .purchased(new Money(Currency.XRB, 43.37220000))
                .sold(new Money(Currency.BTC, 0.04838429))
                .snapshotPrice(new Money(Currency.BTC, 14913.35))
                .build();
//
//        Transaction xrbBtcBtc = Transaction.builder()
//                .purchased(new Money(Currency.XRB, 43.37220000))
//                .sold(new Money(Currency.BTC, 0.04838429))
//                .snapshotPrice(new Money(Currency.BTC, 14913.35))
//                .build();


        return new Object[][]{
                {btcUsdBtc},
                {xrbBtcBtc}};

    }
}


//OG currency = USD 100
//NEW curency = btc
//snapshot = btc 17000