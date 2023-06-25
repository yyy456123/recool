package com.github.permissiondog.os.exp3.p1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var pages = new ArrayList<Page>();
        pages.add( new Page(0, true, 5, 11));
        pages.add( new Page(1, true, 8, 12));
        pages.add(new Page(2, true, 9, 13));
        pages.add( new Page(3, true, 1, 21));
        pages.add( new Page(4, false, 22));
        pages.add( new Page(5, false, 23));
        pages.add( new Page(6, false, 121));
        var pageTable = new PageTable(128, pages);
        var instructions = new ArrayList<Instruction>();
        instructions.add(new Instruction("plus", 0, 70));
        instructions.add(new Instruction("plus", 1, 50));
        instructions.add(new Instruction("multiply", 2, 15));
        instructions.add(new Instruction("save", 3, 21));
        instructions.add(new Instruction("load", 0, 56));
        instructions.add(new Instruction("minus", 6, 40));
        instructions.add(new Instruction("shift", 4, 53));
        instructions.add(new Instruction("plus", 5, 23));
        instructions.add(new Instruction("save", 1, 37));
        instructions.add(new Instruction("load", 2, 78));
        instructions.add(new Instruction("plus", 4, 1));
        instructions.add(new Instruction("save", 6, 84));

        System.out.println("Initial Page Table");
        System.out.println(pageTable);

        System.out.println("Address");
        instructions.forEach(instruction -> {
            var address = pageTable.getAddress(instruction);
            System.out.println(address);
        });

    }
}
