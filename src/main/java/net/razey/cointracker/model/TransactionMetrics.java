package net.razey.cointracker.model;

import lombok.Getter;
import lombok.ToString;
import net.razey.cointracker.enums.Currency;

import java.math.BigDecimal;

//Ugh.
@Getter
@ToString
public class TransactionMetrics {

    Double usdEquivalentPerPurchasedCoin;
    Double btcEquivalentPerPurchasedCoin;
    Double totalUsdPaid;
    Double totalBtcPaid;
    Double percentProfit;
    Double usdProfit;

    public TransactionMetrics(Transaction transaction) {
        this.btcEquivalentPerPurchasedCoin = calculateBtcEquivalentPerCoin(transaction);
        this.usdEquivalentPerPurchasedCoin = calculateUsdEquivalentPerCoin(transaction);
        this.totalBtcPaid = calculateTotalBtcPaid(transaction);
        this.totalUsdPaid = calculateTotalUsdPaid(transaction);
        this.percentProfit = calculatePercentProfit(transaction);
        this.usdProfit = calculateUsdProfit(transaction);
    }

    private Double calculateBtcEquivalentPerCoin(Transaction transaction) {
        Money converted = convertToCurrency(transaction.getPurchased(), transaction.getSnapshotPrice(), Currency.BTC);

        //???
        return divide(converted.getAmount(), transaction.getPurchased().getAmount());
    }

    private Double calculateUsdEquivalentPerCoin(Transaction transaction) {
        Money converted = convertToCurrency(transaction.getPurchased(), transaction.getSnapshotPrice(), Currency.USD);

        //???
        return divide(converted.getAmount(), transaction.getPurchased().getAmount());
    }

    private Double calculateTotalBtcPaid(Transaction transaction) {
        return convertToCurrency(transaction.getSold(), transaction.getSnapshotPrice(), Currency.BTC).getAmount();
    }

    private Double calculateTotalUsdPaid(Transaction transaction) {
        return convertToCurrency(transaction.getSold(), transaction.getSnapshotPrice(), Currency.USD).getAmount();
    }

    private Double calculatePercentProfit(Transaction transaction) {
        Double amount = transaction.getPurchased().getCurrency().equals(Currency.USD) ? transaction.getSold().getAmount() : transaction.getPurchased().getAmount();
        Double currentValue = multiply(getCurrentBtcPriceInUsd().getAmount(), amount);
        Double decimal = divide(currentValue, getTotalUsdPaid()) - 1;
        return multiply(decimal, 100.);
    }

    private Double calculateUsdProfit(Transaction transaction) {
        Double currentValue = multiply(getCurrentBtcPriceInUsd().getAmount(), transaction.getPurchased().getAmount());
        return currentValue - getTotalUsdPaid();
    }

    private Money convertToCurrency(Money original, Money snapshotPrice, Currency newCurrency) {
        if (original.getCurrency().equals(newCurrency)) {
            return new Money(original.getCurrency(), original.getAmount());
        }

        if (snapshotPrice.getCurrency().equals(newCurrency)) {
            return new Money(newCurrency, divide(original.getAmount(), snapshotPrice.getAmount()));
        }

        return new Money(newCurrency, multiply(original.getAmount(), snapshotPrice.getAmount()));
    }

    private Money getCurrentBtcPriceInUsd() {
        return new Money(Currency.BTC, 20000.);
    }

    private Double multiply(Double a, Double b) {
        BigDecimal x = new BigDecimal(a);
        BigDecimal y = new BigDecimal(b);
        return x.multiply(y).setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private Double divide(Double a, Double b) {
        BigDecimal x = new BigDecimal(a);
        BigDecimal y = new BigDecimal(b);
        return x.divide(y, 8, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
