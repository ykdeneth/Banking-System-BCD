package com.ydm.j2ee.core.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Account.findByAccountNo",
                query = "SELECT a FROM Account a WHERE a.accountNo=:accountNo"),
        @NamedQuery(name = "Account.findByAccountAndEmail", query = "SELECT a FROM Account a WHERE a.accountNo = :accountNo2 AND a.user.email = :email"),
        @NamedQuery(name = "Account.findByAccountNo2",
                query = "SELECT a.accountNo FROM Account a WHERE a.accountNo=:accountNo"),
        @NamedQuery(
                name = "Account.findAll",
                query = "SELECT a FROM Account a"
        )


})
@Cacheable(value = false)
@org.hibernate.annotations.Cache(region = "account-cache",usage = CacheConcurrencyStrategy.READ_ONLY)
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String accountNo;
    private double balance;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "account")
    private List<Loan> loan = new ArrayList<Loan>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
