package com.github.permissiondog.os.exp3.p4;

import com.github.permissiondog.os.exp3.p4.commands.Exit;
import com.github.permissiondog.os.exp3.p4.commands.Help;
import com.github.permissiondog.os.exp3.p4.commands.Login;
import com.github.permissiondog.os.exp3.p4.commands.Logout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NEUOS {
    private final BufferedReader in;
    private final Map<String, Command> commands = new HashMap<>();
    private boolean stopped = false;
    private final File root = File.directory("", null);
    private final Map<String, User> users = new HashMap<>();
    private User userLoggedIn;
    private File workingDirectory;

    public NEUOS() {
        in = new BufferedReader(new InputStreamReader(System.in));

    }
    public NEUOS addUser(User user) {
        users.put(user.getUsername(), user);
        root.addChild(File.directory(user.getUsername(), null));
        return this;
    }

    public File getUserFileDirectory(User user) {
        return root.getChild(user.getUsername());
    }

    public void boot() {
        System.out.println("""
                \033[96m
                  _   _ ______ _    _  ____   _____\s
                 | \\ | |  ____| |  | |/ __ \\ / ____|
                 |  \\| | |__  | |  | | |  | | (___ \s
                 | . ` |  __| | |  | | |  | |\\___ \\\s
                 | |\\  | |____| |__| | |__| |____) |
                 |_| \\_|______|\\____/ \\____/|_____/\s
                                                   \s
                \033[0m
                """);
        try {
            commandLoop();
        } catch (IOException e) {
            System.out.println("错误");
            e.printStackTrace();
        }
    }

    public void exit() {
        System.out.println("Bye");
        stopped = true;
    }

    public NEUOS registerCommand(Command cmd) {
        commands.put(cmd.getCommandName(), cmd);
        return this;
    }

    public List<Command> getCommandList() {
        return commands.values().stream().toList();
    }

    public User getUserLoggedIn() {
        return userLoggedIn;
    }

    public BufferedReader in() {
        return in;
    }

    public void login(String username) {
        var user = users.get(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        userLoggedIn = user;
        workingDirectory = root.getChild(user.getUsername());
    }

    public void logout() {
        userLoggedIn = null;
    }

    public File getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public File getRoot() {
        return root;
    }

    private void commandLoop() throws IOException {
        while (!stopped) {
            printInfo();
            var line = in.readLine().trim();
            if (line.equals("")) {
                continue;
            }
            handleCommand(line);
        }
    }

    // 打印当前用户和目录信息
    private void printInfo() {
        if (userLoggedIn == null) {
            System.out.print("\033[1;35mNEUOS\033[0m $ ");
            return;
        }
        System.out.print("\033[1;35m" + userLoggedIn.getUsername() + "@NEUOS \033[1;96m" + workingDirectory.getFullPath() + "\033[0m $ ");
    }

    private void handleCommand(String cmd) throws IOException {
        var command = commands.get(cmd);
        if (command == null) {
            System.out.println("\033[31m命令 \033[1m" + cmd + " \033[0;31m未找到\033[0m");
            return;
        }
        if (command.needLogin() && userLoggedIn == null) {
            System.out.println("\033[31m未登录, 请登录后再使用该命令\033[0m");
            return;
        }
        command.run(this);
    }


    public void create(File directory, String fileName, int protectionCode) {
        directory.addChild(File.file(fileName, directory, protectionCode));
    }
}
