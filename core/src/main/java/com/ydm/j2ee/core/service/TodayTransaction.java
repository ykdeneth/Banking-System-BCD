package com.ydm.j2ee.core.service;

import com.ydm.j2ee.core.model.TransferDetails;

import java.util.List;

public interface TodayTransaction {
    List<TransferDetails> getTodaysTransactions();
}
