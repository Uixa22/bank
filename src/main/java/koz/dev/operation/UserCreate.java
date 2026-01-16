package koz.dev.operation;


import koz.dev.ConsoleInputService;
import koz.dev.model.User;
import koz.dev.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserCreate implements  OperationProcessor {
    private final ConsoleInputService consoleInputService;
    private final UserService userService;
    public UserCreate(UserService userService,ConsoleInputService consoleInputService){
        this.consoleInputService = consoleInputService;
        this.userService = userService;

    }
    public void  operation(){
        System.out.println("Введите логин(имя пользователя): ");
        String login = consoleInputService.readline();
        User newUser = userService.createUser(login);
        System.out.println("Пользователь создан - login:" + newUser.toString());
    }
}
