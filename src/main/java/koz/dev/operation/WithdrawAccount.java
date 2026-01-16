package koz.dev.operation;


import koz.dev.ConsoleInputService;
import koz.dev.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WithdrawAccount implements OperationProcessor{
    private final AccountService accountService;
    private final ConsoleInputService consoleInputService;
    @Autowired
    public WithdrawAccount(AccountService accountService, ConsoleInputService consoleInputService) {
        this.accountService = accountService;
        this.consoleInputService = consoleInputService;
    }
    @Override
    public void operation() throws Throwable {
        System.out.println("Введите id-Account: ");
        int accountId=consoleInputService.readInt();
        System.out.println("Введите сумму снятия: ");
        int accountWithdraw=consoleInputService.readInt();
        accountService.withdrawAccount(accountId,accountWithdraw);
        System.out.println("Успешно, id-Account: %s , Сумма снятия: %d".formatted(accountId,accountWithdraw));
    }
}
