package com.ydm.j2ee.web.dto;

import com.google.gson.annotations.Expose;
import java.util.List;
import java.util.stream.Collectors;

public class UserResponse_DTO {

    @Expose
    private Long id;

    @Expose
    private String name;

    @Expose
    private String contact;

    @Expose
    private String email;

    @Expose
    private String verificationCode;

    @Expose
    private String userType;

    @Expose
    private String status;

    @Expose
    private String accType;

    @Expose
    private String verifyState;

    // Assuming you want to include accounts as a list of simple account DTOs
    @Expose
    private List<AccountResponse_DTO> accounts;

    public UserResponse_DTO() {}

    // Constructor to map from User entity to this DTO
    public UserResponse_DTO(com.ydm.j2ee.core.model.User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.contact = user.getContact();
        this.email = user.getEmail();
        this.verificationCode = user.getVerificationCode();

        this.userType = user.getUserType() != null ? user.getUserType().name() : null;
        this.status = user.getStatus() != null ? user.getStatus().name() : null;
        this.accType = user.getAccType() != null ? user.getAccType().name() : null;
        this.verifyState = user.getVerifyState() != null ? user.getVerifyState().name() : null;

        // Map accounts if not null
        if (user.getAccounts() != null) {
            this.accounts = user.getAccounts().stream()
                    .map(AccountResponse_DTO::new)
                    .collect(Collectors.toList());;
        }
    }

    // Getters and setters here (or use Lombok for brevity)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // ... repeat for other fields, omitted here for brevity

    public List<AccountResponse_DTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountResponse_DTO> accounts) {
        this.accounts = accounts;
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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getVerifyState() {
        return verifyState;
    }

    public void setVerifyState(String verifyState) {
        this.verifyState = verifyState;
    }
}

