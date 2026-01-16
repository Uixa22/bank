package koz.dev.service;

import koz.dev.model.Account;
import koz.dev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AccountService {
    private final Map<Integer,Account> AccountMap;
    private int idCounter;
    private final Set<String> AccountSet;
    private final int defaultAccountAmount;
    private final double transferCommission;




    public AccountService(
            @Value("${account.default-amount:100}") int defaultAccountAmount,
            @Value("${account.transfer-commission:0.01}") double transferCommission
    ) {
        this.defaultAccountAmount = defaultAccountAmount;
        this.transferCommission = transferCommission;
        this.idCounter = 0;
        this.AccountSet = new HashSet<>();
        this.AccountMap = new HashMap<>();

    }

    public Account createAccount(User user){
        idCounter++;
        Account newAccount=new Account(idCounter, user.getId(),defaultAccountAmount);//config
        AccountMap.put(newAccount.getAccountId(),newAccount);
        return newAccount;

    }
    public Optional<Account> findAccountById(int id){
        return Optional.ofNullable(AccountMap.get(id));
    }
    public List<Account> findAllAccounts(int userId){
        return AccountMap.values().
                stream().
                filter(account -> account.getUserId()==userId).
                toList();
    }
    public void depositAccount(int accountId, int moneyToDeposit) throws Exception {
        var account = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountId));
        if (moneyToDeposit<=0){
            throw new IllegalArgumentException("Can't deposit amount to more than 0!");
        }
        account.setMoneyAmount(account.getMoneyAmount() + moneyToDeposit);
    }
    public void withdrawAccount(int accountId, int moneyToWithdraw) throws Exception {
        var account = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountId));
        if (moneyToWithdraw<0){
            throw new IllegalArgumentException("Can't deposit amount to more than 0!");
        }
        if (account.getMoneyAmount()<moneyToWithdraw){
            throw new IllegalArgumentException("Can't withdraw amount to more than 0!");
        }
        account.setMoneyAmount(account.getMoneyAmount() - moneyToWithdraw);
    }
    

    public Account closeAccount(int accountId) throws Exception {
        var account = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountId));
        List<Account> accountList = findAllAccounts(account.getUserId());
        if(accountList.size()==1){
            AccountMap.remove(accountId);
            throw new IllegalArgumentException("Cannot close account with more than 1 account!");
        }
        return account;
    }

    public void transferAccount(int fromAccountId, int toAccountId, int moneyToTransfer) {
        var fromAccount = findAccountById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + fromAccountId));
        var toAccount = findAccountById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + toAccountId));
        if (moneyToTransfer<=0){
            throw new IllegalArgumentException("Can't transfer amount to more than 0!");
        }
        if (fromAccount.getMoneyAmount()<moneyToTransfer){
            throw new IllegalArgumentException("Can't transfer amount to more than 0!");
        }
        double totalAmountToDeposit= toAccount.getUserId() != fromAccount.getUserId()
                ? moneyToTransfer-moneyToTransfer * transferCommission
                :moneyToTransfer;
        fromAccount.setMoneyAmount(fromAccount.getMoneyAmount()- moneyToTransfer);
        toAccount.setMoneyAmount(toAccount.getMoneyAmount()+ moneyToTransfer);
    }
}

