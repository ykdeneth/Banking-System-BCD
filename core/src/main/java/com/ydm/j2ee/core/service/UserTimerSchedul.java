package com.ydm.j2ee.core.service;

public interface UserTimerSchedul {
    void createTimer(int year, int month, int day);
    void createTransferTimer(int year, int month, int day,
                             String sourceAccountNo,
                             String destinationAccountNo,
                             double amount,
                             String reason,
                             String type);
}
