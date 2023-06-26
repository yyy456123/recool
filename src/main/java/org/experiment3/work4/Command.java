package org.experiment3.work4;

import java.io.IOException;

public interface Command {
    void run(MyOS os) throws IOException;
    String getCommandName();
    String getDescription();
    boolean needLogin();
}
