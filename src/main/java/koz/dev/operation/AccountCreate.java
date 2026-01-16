package koz.dev.operation;

import koz.dev.ConsoleInputService;
import koz.dev.model.Account;
import koz.dev.model.User;
import koz.dev.service.AccountService;
import koz.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountCreate implements OperationProcessor {
    private final ConsoleInputService consoleInputService;
    private final UserService userService;
    private final AccountService accountService;
    @Autowired
    public AccountCreate(ConsoleInputService consoleInputService, UserService userService, AccountService accountService) {
        this.consoleInputService = consoleInputService;
        this.userService = userService;
        this.accountService = accountService;
    }
    @Override
    public void operation(){
        System.out.println("Создаю счет....");
        System.out.println("Введите ID какому пользователю создать счет : ");
        int userId = consoleInputService.readInt();
        User user=userService.findById(userId).
                orElseThrow(()->new IllegalArgumentException("User not found: " + userId));
        Account newAccount= accountService.createAccount(user);
        user.getAccountList().add(newAccount);
        System.out.println("Аккаунт создан "+newAccount.toString());
    }
}
