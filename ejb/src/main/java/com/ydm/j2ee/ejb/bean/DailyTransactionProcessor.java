package com.ydm.j2ee.ejb.bean;

import com.ydm.j2ee.core.model.TransferDetails;
import com.ydm.j2ee.core.service.TodayTransaction;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Version;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Stateless
public class DailyTransactionProcessor implements TodayTransaction {

    @PersistenceContext
    private EntityManager em;

    // This method can be called from servlet or scheduled method
    @Override
    public List<TransferDetails> getTodaysTransactions() {
        Date todayStart = getStartOfToday();
        Date todayEnd = getEndOfToday();

        return getTransactionsBetween(todayStart, todayEnd);
    }

    private List<TransferDetails> getTransactionsBetween(Date start, Date end) {
        TypedQuery<TransferDetails> query = em.createQuery(
                "SELECT t FROM TransferDetails t WHERE t.transactionDate BETWEEN :start AND :end", TransferDetails.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }

    private Date getStartOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private Date getEndOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    // Optional: Your scheduled method can stay to trigger this automatically
    @Schedule(hour = "23", minute = "59", second = "59", persistent = false)
    public void doDailyTransactionProcessing() {
        List<TransferDetails> todaysTransactions = getTodaysTransactions();
        // Your scheduled processing logic here (e.g., logging)
    }
}


