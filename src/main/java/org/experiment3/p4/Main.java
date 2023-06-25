package com.github.permissiondog.os.exp3.p4;

import com.github.permissiondog.os.exp3.p4.commands.*;

public class Main {
    public static void main(String[] args) {
        new NEUOS()
                .addUser(new User("admin"))
                .addUser(new User("zhangsan1"))
                .addUser(new User("zhangsan2"))
                .addUser(new User("zhangsan3"))
                .addUser(new User("zhangsan4"))
                .addUser(new User("zhangsan5"))
                .addUser(new User("zhangsan6"))
                .addUser(new User("zhangsan7"))
                .addUser(new User("zhangsan8"))
                .addUser(new User("lisi"))
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
