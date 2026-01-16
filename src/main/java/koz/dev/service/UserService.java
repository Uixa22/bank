package koz.dev.service;

import koz.dev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class UserService {
    private final Map<Integer,User> userMap;
    private int idCounter;
    private final Set<String> userSet;
    private final AccountService accountService;


    @Autowired
    public UserService(AccountService accountService) {
        this.userMap = new HashMap<>();
        this.accountService = accountService;
        this.userSet = new HashSet<>();
        this.idCounter = 0;
    }


    public User createUser(String login){
        if(userSet.contains(login)){
            throw new IllegalArgumentException("Такой логин (имя пользователя) уже есть. Введите новый :  ");
        }
        userSet.add(login);
        idCounter++;
        User newUser=new User(idCounter,login,new ArrayList<>());
        userMap.put(newUser.getId(),newUser);
        var newAccount=accountService.createAccount(newUser);
        newUser.getAccountList().add(newAccount);
        return newUser;

    }
    public Optional<User> findById(int id){
        return Optional.ofNullable(userMap.get(id));
    }
    public List<User> findAllUsers(){
        return userMap.values().stream().toList();
    }
}
