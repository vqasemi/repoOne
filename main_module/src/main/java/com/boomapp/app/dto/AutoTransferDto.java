package com.boomapp.app.dto;

import android.util.Log;
import com.boomapp.app.utils.SharedPref;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mohsen
 * @since 11/6/2015.
 */
public class AutoTransferDto {

    public enum TermType{
        DAILY,
        WEEKLY,
        MONTHLY
    }
    BigDecimal amount;
    String destinationDepositNumber;
    String sourceDepositNumber;
    String startDate;
    short termLength;
    TermType termType;
    short transactionCount;

    public AutoTransferDto() {
    }

    public AutoTransferDto(BigDecimal amount, String destinationDepositNumber, String sourceDepositNumber, Date startDate, short termLength, TermType termType, short transactionCount) {
        this.amount = amount;
        this.destinationDepositNumber = destinationDepositNumber;
        this.sourceDepositNumber = sourceDepositNumber;
        this.startDate = SharedPref.getInstance().getDate();
        Log.e("startDate",this.startDate);
        this.termLength = termLength;
        this.termType = termType;
        this.transactionCount = transactionCount;
    }
}
