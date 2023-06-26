package org.experiment3.work1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class PageTable {
    private final List<Page> pages;
    private final int blockSize;
    public PageTable(int blockSize, List<Page> pages) {
        this.pages = new ArrayList<>(pages);
        this.blockSize = blockSize;
    }

    public Address getAddress(Instruction instruction) {
        final var page = pages.get(instruction.getPageNumber());
        if (!page.isInMemory()) {
            return new Address(true, instruction.getPageNumber());
        }
        return new Address(false, page.getBlockNumber() * blockSize + instruction.getCellNumber());

    }

    @Override
    public String toString() {
        var temp = new ByteArrayOutputStream();
        var ps = new PrintStream(temp);
        ps.printf("\033[1m%-9s%-10s%-10s%s\033[0m\n", "PageNum", "InMemory", "BlockNum", "DiskPos");
        pages.forEach(p -> ps.printf(
                "%-9d%-10s%-10s%03d\n",
                p.getNumber(),
                p.isInMemory(),
                p.getBlockNumber() == -1 ? "" : p.getBlockNumber(),
                p.getDiskPosition()
        ));
        return temp.toString();
    }
}
