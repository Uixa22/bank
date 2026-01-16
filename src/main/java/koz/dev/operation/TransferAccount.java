package koz.dev.operation;


import koz.dev.ConsoleInputService;
import koz.dev.service.AccountService;
import org.springframework.stereotype.Component;

@Component
public class TransferAccount implements  OperationProcessor {
    private final ConsoleInputService consoleInputService;
    private final AccountService accountService;

    public TransferAccount(ConsoleInputService consoleInputService, AccountService accountService) {
        this.consoleInputService = consoleInputService;
        this.accountService = accountService;
    }

    @Override
    public void operation() {
        System.out.println("Введите id-Account отправителя: ");
        int fromAccountId= consoleInputService.readInt();
        System.out.println("Введите id-Account получателя: ");
        int toAccountId= consoleInputService.readInt();
        System.out.println("Введите сумму: ");
        int moneyToTransfer = consoleInputService.readInt();
        accountService.transferAccount(fromAccountId,toAccountId,moneyToTransfer);
        System.out.printf("Успешно: Отправитель %s ; Получатель %s ; Сумма %s%n", fromAccountId,toAccountId,moneyToTransfer);


    }
}
