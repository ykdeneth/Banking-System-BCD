package com.ydm.j2ee.core.service;

public interface TransferService {
    void transferAmount(String sourceAccountNo, String destinationAccountNo, double amount, String reason, String type);
}
