package com.ydm.j2ee.core.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NamedQueries({
        //@NamedQuery(name = "User.findByEmail", query = "select u from User u where u.email = ?1"),
        @NamedQuery(name = "User.findByEmail", query = "select u from User u where u.email =:email"),
        @NamedQuery(name = "User.findByEmailAndPassword",
                query = "select u from User u where u.email =:email and u.password=:password"),
        @NamedQuery(
                name = "User.findNameByAccountNo",
                query = "SELECT u.name FROM User u JOIN u.accounts a WHERE a.accountNo = :accountNo2"
        ),
        @NamedQuery(
                name = "User.findAccStatusByAccountNo",
                query = "SELECT u.status FROM User u JOIN u.accounts a WHERE a.accountNo = :accountNo2"
        ),
        @NamedQuery(
                name = "UserAll.findByEmail",
                query = "SELECT u FROM User u LEFT JOIN FETCH u.accounts WHERE u.email = :email"
        )

})
@Cacheable(false)
public class User implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contact;
    @Column(unique = true)
    private String email;
    private String password;
    private String verificationCode;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;
    @OneToMany(mappedBy = "user")
    private List<Account> accounts = new ArrayList<Account>();

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private AccType accType;

    @Enumerated(EnumType.STRING)
    private VerifyState verifyState = VerifyState.NOT_VERIFIED_ACCOUNT;

    public User() {
    }

    public User(String name, String email, String contact, String password, String verificationCode, UserType userType, Status status, AccType accType, List<Account> accounts, VerifyState verifyState) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.verificationCode = verificationCode;
        this.userType = userType;
        this.status = status;
        this.accType = accType;
        this.accounts = accounts;
        this.verifyState = verifyState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AccType getAccType() {
        return accType;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setAccType(AccType accType) {
        this.accType = accType;
    }

    public VerifyState getVerifyState() {
        return verifyState;
    }

    public void setVerifyState(VerifyState verifyState) {
        this.verifyState = verifyState;
    }

    @Override
    public String  toString() {
        return "User [id=" + id + ", name="+name+", email="+email+"]";
    }
}
