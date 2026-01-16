package koz.dev.model;

import org.springframework.stereotype.Component;

import java.util.Optional;


public class Account {
    private final int accountId;
    private final int userId;
    private int moneyAmount;

    public Account(int accountId, int userId, int moneyAmount) {
        this.accountId = accountId;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }
}
