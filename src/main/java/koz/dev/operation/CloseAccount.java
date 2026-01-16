package koz.dev.operation;


import koz.dev.ConsoleInputService;
import koz.dev.model.Account;
import koz.dev.model.User;
import koz.dev.service.AccountService;
import koz.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class CloseAccount implements OperationProcessor {
    private final ConsoleInputService consoleInputService;
    private final AccountService accountService;
    private final UserService userService;
    @Autowired
    public CloseAccount(ConsoleInputService consoleInputService, AccountService accountService, UserService userService) {
        this.consoleInputService = consoleInputService;
        this.accountService = accountService;
        this.userService=userService;

    }
    public void operation() throws Throwable {
        System.out.println("Закрытие счета,введите id счета: ");
        int accountId=consoleInputService.readInt();
        Account account=accountService.closeAccount(accountId);
        User user = userService.findById(account.getUserId())
                .orElseThrow(()->new IllegalArgumentException("No such user with id: "+account.getUserId()));
        user.getAccountList().remove(account);
    }
}
