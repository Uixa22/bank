package koz.dev.operation;

import koz.dev.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class FindAllUsers implements OperationProcessor{
    private final UserService userService;
    public FindAllUsers(UserService userService) {
        this.userService=userService;
    }

    public  void operation(){
        System.out.println("Все пользователи ");
        userService.findAllUsers().stream().forEach(System.out::println);
    }
}
