package com.github.permissiondog.os.exp3.p2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class PageTable {
    private final List<Page> pages;
    private final int blockSize;
    private int pos = 0;
    private final int memSize;
    private final List<Integer> p;
    public PageTable(int blockSize, List<Page> pages, int memSize) {
        this.pages = new ArrayList<>(pages);
        this.blockSize = blockSize;
        this.memSize = memSize;
        p = new ArrayList<>(pages.stream().filter(Page::isInMemory).map(Page::getNumber).toList());
    }


    private void handlePageFault(int pageNumber) {
        var inPage = pages.get(pageNumber);
        if (inPage.isInMemory()) {
            throw new RuntimeException("未出现页错误");
        }
        var earliestPage = pages.get(p.get(pos));
        System.out.println(earliestPage.getNumber() + " -> " + pageNumber);
        if (earliestPage.isModified()) {
            System.out.println("Save page: " + earliestPage.getNumber());
        }
        earliestPage.setInMemory(false);
        earliestPage.setBlockNumber((int) (Math.random() * 9) + 1);
        earliestPage.setModified(false);

        inPage.setInMemory(true);
        inPage.setModified(false);
        inPage.setBlockNumber(-1);
        p.set(pos, inPage.getNumber());
        pos = (pos + 1) % memSize;
    }

    public Page getPage(Instruction instruction) {
        final var pageNumber = instruction.getPageNumber();
        var page = pages.get(pageNumber);
        if (page.isInMemory()) {
            return page;
        }
        handlePageFault(pageNumber);
        page = pages.get(pageNumber);
        if (!page.isInMemory()) {
            throw new RuntimeException("置换失败");
        }
        return page;
    }

    @Override
    public String toString() {
        var temp = new ByteArrayOutputStream();
        var ps = new PrintStream(temp);
        ps.printf("\033[1m%-9s%-10s%-10s%-10s%s\033[0m\n", "PageNum", "InMemory", "BlockNum", "Modified", "DiskPos");
        pages.forEach(p -> ps.printf(
                "%-9d%-10s%-10s%-10s%03d\n",
                p.getNumber(),
                p.isInMemory(),
                p.getBlockNumber() == -1 ? "" : p.getBlockNumber(),
                p.isModified(),
                p.getDiskPosition()
        ));
        ps.print("p: " + p);
        return temp.toString();
    }
}
