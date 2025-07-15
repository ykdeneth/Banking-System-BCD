package com.ydm.j2ee.core.service;

public interface UserTimerSchedul {
    void createTimer(int hour, int minute, int second);
    void createTransferTimer(int hour, int minute, int second,
                             String sourceAccountNo,
                             String destinationAccountNo,
                             double amount,
                             String reason,
                             String type);
}
