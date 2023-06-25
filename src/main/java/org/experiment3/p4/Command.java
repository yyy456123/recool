package com.github.permissiondog.os.exp3.p4;

import java.io.IOException;

public interface Command {
    void run(NEUOS os) throws IOException;
    String getCommandName();
    String getDescription();
    boolean needLogin();
}
