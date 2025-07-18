package com.ydm.j2ee.ejb.bean;


import com.ydm.j2ee.core.service.AccountService;
import com.ydm.j2ee.core.service.TransferService;
import com.ydm.j2ee.core.service.UService;
import com.ydm.j2ee.ejb.annotation.TimerSchedulSalary;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;

@Stateless
@TimerSchedulSalary
public class TimerSessionInterestBean {
    @EJB
    TransferService transferService;
    @EJB
    private AccountService accountService;

//    @Schedule( hour = "*", minute = "*", second = "*/5", persistent = false)
    @Schedule( dayOfMonth = "20", hour = "0", minute = "0", second = "0", persistent = false)
    @Timeout
    public void doTask(){
       boolean b = accountService.addInterestToAllAccounts(0.2);
        System.out.println("Interest added to all accounts.");
    }
}
