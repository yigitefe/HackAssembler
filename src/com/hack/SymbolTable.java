package com.hack;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    Map<String, Integer> data = new HashMap<>();

    public SymbolTable() {
        // Add Predefined Symbols
        data.put("SP", 0x0000);
        data.put("LCL", 0x0001);
        data.put("ARG", 0x0002);
        data.put("THIS", 0x0003);
        data.put("THAT", 0x0004);
        data.put("R0", 0x0000);
        data.put("R1", 0x0001);
        data.put("R2", 0x0002);
        data.put("R3", 0x0003);
        data.put("R4", 0x0004);
        data.put("R5", 0x0005);
        data.put("R6", 0x0006);
        data.put("R7", 0x0007);
        data.put("R8", 0x0008);
        data.put("R9", 0x0009);
        data.put("R10", 0x000A);
        data.put("R11", 0x000B);
        data.put("R12", 0x000C);
        data.put("R13", 0x000D);
        data.put("R14", 0x000E);
        data.put("R15", 0x000F);
        data.put("SCREEN", 0x4000);
        data.put("KBD", 0x6000);
    }

    public void addEntry(String symbol, Integer address) {
        data.put(symbol, address);
    }

    public boolean contains(String symbol) {
        return data.containsKey(symbol);
    }

    public int getAddress(String symbol) {
        return data.get(symbol);
    }
}
