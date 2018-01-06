package net.razey.cointracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.razey.cointracker.enums.Currency;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Money {
    private Currency currency;
    private Double amount;
}
