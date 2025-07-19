package com.ydm.j2ee.ejb.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ydm.j2ee.core.service.TransferService;
import com.ydm.j2ee.core.service.UserTimerSchedul;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.*;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Stateless
public class UserSchedulTimerBean implements UserTimerSchedul {

    @Resource
    private TimerService timerService;

    @EJB
    private TransferService transferService;

    @Override
    public void createTimer(int year, int month, int day) {
        ScheduleExpression schedule = new ScheduleExpression()
                .year(year)
                .month(month)
                .dayOfMonth(day);


        TimerConfig config = new TimerConfig();
        config.setPersistent(false);

        timerService.createCalendarTimer(schedule, config);
    }

    @Override
    public void createTransferTimer(int year, int month, int day, String sourceAccountNo, String destinationAccountNo, double amount, String reason, String type) {
        ScheduleExpression schedule = new ScheduleExpression()
                .year(year)
                .month(month)
                .dayOfMonth(day);

        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("sourceAccountNo", sourceAccountNo);
        infoMap.put("destinationAccountNo", destinationAccountNo);
        infoMap.put("amount", amount);
        infoMap.put("reason", reason);
        infoMap.put("type", type);

        String infoJson = new Gson().toJson(infoMap);

        TimerConfig config = new TimerConfig();
        config.setPersistent(false);
        config.setInfo(infoJson);

        timerService.createCalendarTimer(schedule, config);
        System.out.println("Transfer scheduled successfully");

    }

    @Timeout
    public void timeoutHandler(Timer timer) {
        System.out.println("Timer fired! Running interest calculation...");
        String json = (String) timer.getInfo();
        Map<String, Object> transferData = new Gson().fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());

        String sourceAccountNo = (String) transferData.get("sourceAccountNo");
        String destinationAccountNo = (String) transferData.get("destinationAccountNo");
        double amount = ((Number) transferData.get("amount")).doubleValue();
        String reason = (String) transferData.get("reason");
        String type = (String) transferData.get("type");

        transferService.transferAmount(sourceAccountNo, destinationAccountNo, amount, reason, type);
    }
}
