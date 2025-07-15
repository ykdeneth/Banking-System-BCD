package com.ydm.j2ee.ejb.bean;


import com.ydm.j2ee.core.service.AccountService;
import com.ydm.j2ee.core.service.TransferService;
import com.ydm.j2ee.ejb.annotation.TimerSchedulSalary;
import com.ydm.j2ee.ejb.annotation.TimerSchedulSalary2;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;

@Stateless
@TimerSchedulSalary2
public class TimerSessionSalaryBean {
    @EJB
    TransferService transferService;
    @EJB
    private AccountService accountService;


    @Schedule( dayOfMonth = "20", hour = "0", minute = "0", second = "0", persistent = false)
    @Timeout
    public void doTask(){
//       boolean b = accountService.addInterestToAllAccounts(0.2);
        System.out.println("Salary added to all accounts.");
    }
}
