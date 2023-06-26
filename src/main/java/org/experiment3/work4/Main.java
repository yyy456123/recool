package org.experiment3.work4;


import org.experiment3.work4.commands.*;

public class Main {
    public static void main(String[] args) {
        new MyOS()
                .addUser(new User("admin"))
                .addUser(new User("user1"))
                .addUser(new User("user2"))
                .addUser(new User("user3"))
                .addUser(new User("user4"))
                .addUser(new User("user5"))
                .addUser(new User("user6"))
                .addUser(new User("user7"))
                .addUser(new User("user8"))
                .addUser(new User("user9"))
                .registerCommand(new Help())
                .registerCommand(new Login())
                .registerCommand(new Logout())
                .registerCommand(new Exit())
                .registerCommand(new Dir())
                .registerCommand(new Md())
                .registerCommand(new Cd())
                .registerCommand(new Create())
                .registerCommand(new Read())
                .registerCommand(new Write())
                .registerCommand(new Delete())
                .boot();
    }
}
