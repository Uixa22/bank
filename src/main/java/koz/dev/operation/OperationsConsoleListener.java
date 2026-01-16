package koz.dev.operation;

import koz.dev.ConsoleInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.Integer.parseInt;

@Component
public class OperationsConsoleListener {
    private final ConsoleInputService consoleInputService;
    private final UserCreate userCreate;
    private final FindAllUsers findAllUsers ;
    private final AccountCreate accountCreate;
    private final CloseAccount closeAccount;
    private final DepositAccount depositAccount;
    private final WithdrawAccount withdrawAccount;
    private final TransferAccount transferAccount;

    @Autowired
    public OperationsConsoleListener(UserCreate userCreate, ConsoleInputService consoleInputService, FindAllUsers findAllUsers, AccountCreate accountCreate, CloseAccount closeAccount, DepositAccount depositAccount, WithdrawAccount withdrawAccount, TransferAccount transferAccount) {
        this.consoleInputService = consoleInputService;
        this.userCreate = userCreate;
        this.findAllUsers = findAllUsers;
        this.accountCreate = accountCreate;
        this.closeAccount = closeAccount;
        this.depositAccount = depositAccount;
        this.withdrawAccount = withdrawAccount;
        this.transferAccount = transferAccount;
    }

    public void logOperationsConsoleListener(){
        System.out.println("Operations Console Listener");
    }
    public void operationsConsoleListener(){
        while (true){
            try {
                int nextOperation= parseInt(listenNextOperation(consoleInputService));
                processNextOperation(nextOperation);
            } catch (Exception e){
                System.out.println(e.getMessage());
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String listenNextOperation(ConsoleInputService consoleInputService) {
        System.out.println("\nВведите действие:");
        System.out.println("1. Создать пользователя");
        System.out.println("2. Показать всех пользователей");
        System.out.println("3. Создать счёт");
        System.out.println("4. Закрыть счёт");
        System.out.println("5. Пополнить счёт");
        System.out.println("6. Перевести деньги");
        System.out.println("7. Снять деньги");
        System.out.println("0. Выход");
        var operation=consoleInputService.readline();
        return operation;
    }



    private void processNextOperation(int nextOperation) throws Throwable {
        if(nextOperation==1) {
            userCreate.operation();
        }
        else if(nextOperation==2){
            findAllUsers.operation();
        }
        else if(nextOperation==3){
           accountCreate.operation();
        }
        else if(nextOperation==4){
            closeAccount.operation();
        } else if (nextOperation==5) {
            depositAccount.operation();
        } else if (nextOperation==6) {
            transferAccount.operation();

        }else if (nextOperation==7) {
            withdrawAccount.operation();
        }


    }
}


