package net.razey.cointracker.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

@Getter
@Builder
@ToString
public class Transaction {

    @Builder.Default
    private DateTime dateTime = DateTime.now();

    private Money purchased;
    private Money sold;
    private Money snapshotPrice;

    private String exchange;

    private String notes;

}
