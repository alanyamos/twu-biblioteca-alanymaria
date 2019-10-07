package com.twu.biblioteca.service;

import com.twu.biblioteca.LoginException;
import com.twu.biblioteca.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService {

    private List<User> users = new ArrayList<User>(
        Arrays.asList(new User(0, "Alany Maria", "alany@email.com","0000000", "123-4567", "123456", "unlogged"),
            new User(1, "Lucas Cristiano", "lucas@email.com","1111111", "344-5656", "654321", "unlogged"),
            new User(2, "Renata Lima", "renata@email.com","2222222","454-1298", "abcdef", "unlogged"),
            new User(3, "Lucas de Aguilar", "lucas2@email.com","3333333", "854-7892", "fedcba", "unlogged"),
            new User(4, "Tamiris Fernandes", "tamiris@email.com","44444444", "954-1289", "123abc", "unlogged")
        )
    );

    public void login(String number, String password) throws LoginException {
        User user = null;
        try {
            user = users.stream().filter(u -> u.getPassword().equals(password) && u.getNumber().equals(number)).findFirst().get();
            user.setStatus("logged");
        } catch (Exception error) {
            throw new LoginException();
        }
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getLoggedUser() {
        User user = users.stream().filter(u -> u.getStatus().equals("logged")).findFirst().get();
        return user;
    }

    public String getUserById(Integer userId) {
        User user = users.stream().filter(u -> u.getId() == userId).findFirst().get();
        return user.getName();
    }
}
