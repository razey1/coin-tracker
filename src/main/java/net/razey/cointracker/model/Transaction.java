package net.razey.cointracker.model;

import lombok.Getter;
import lombok.Setter;
import net.razey.cointracker.enums.Currency;
import org.joda.time.DateTime;

@Getter
@Setter
public class Transaction {

    //Default to current DateTime
    DateTime dateTime;

    Currency currencyPurchased;
    Float purchasedQuantity;

    Currency currencySold;
    Float soldQuantity;

    Currency currentPriceCurrency;
    Float currentPrice;

    String notes;

}
