package net.razey.cointracker.model;

import lombok.Getter;
import lombok.ToString;
import net.razey.cointracker.enums.Currency;

import java.math.BigDecimal;

@Getter
@ToString
public class TransactionMetrics {

    Double usdEquivalentPerCoin;
    Double btcEquivalentPerCoin;
    Double totalUsdPaid;
    Double totalBtcPaid;
    Double percentProfit;
    Double usdProfit;

    public TransactionMetrics(Transaction transaction) {
        this.btcEquivalentPerCoin = calculateEquivalentPerCoin(transaction, Currency.BTC);
        this.usdEquivalentPerCoin = calculateEquivalentPerCoin(transaction, Currency.USD);
        this.totalBtcPaid = calculateTotalCurrencyPaid(transaction, Currency.BTC);
        this.totalUsdPaid = calculateTotalCurrencyPaid(transaction, Currency.USD);
        this.percentProfit = calculatePercentProfit(transaction);
        this.usdProfit = calculateUsdProfit(transaction);
    }

    private Double calculateEquivalentPerCoin(Transaction transaction, Currency currency) {
        Double converted = convertToCurrency(transaction.getPurchased(), transaction.getSnapshotPrice(), currency);
        return divide(converted, transaction.getPurchased().getAmount());
    }

    private Double calculateTotalCurrencyPaid(Transaction transaction, Currency currency) {
        return convertToCurrency(transaction.getSold(), transaction.getSnapshotPrice(), currency);
    }

    private Double calculatePercentProfit(Transaction transaction) {
        return 0D;
    }

    private Double calculateUsdProfit(Transaction transaction) {
        return 0D;
    }

    private Double convertToCurrency(Money original, Money snapshotPrice, Currency newCurrency) {
        if (original.getCurrency().equals(newCurrency)) {
            return original.getAmount();
        }

        if (snapshotPrice.getCurrency().equals(newCurrency)) {
            return divide(original.getAmount(), snapshotPrice.getAmount());
        }

//        if (originalCurrency.equals(Currency.USD)) {
            return multiply(original.getAmount(), snapshotPrice.getAmount());

    }

    private Double getCurrentBtcPrice() {
        return 17000.;
    }

    private Double multiply(Double a, Double b) {
        BigDecimal x = new BigDecimal(a);
        BigDecimal y = new BigDecimal(b);
        return x.multiply(y).doubleValue();
    }

    private Double divide(Double a, Double b) {
        BigDecimal x = new BigDecimal(a);
        BigDecimal y = new BigDecimal(b);
        return x.divide(y, 8, BigDecimal.ROUND_UP).doubleValue();
    }
}
