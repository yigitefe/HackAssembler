package com.hack;

import com.hack.type.CommandType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static final int VARIABLE_START_LOCATION = 0x0010;

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("You need to provide a file path.");
            System.exit(0);
        }
        String filePath = args[0];
        Parser parser = new Parser(filePath);
        Code code = new Code();
        SymbolTable symbolTable = firstPass(filePath);
        int n = VARIABLE_START_LOCATION;

        BufferedWriter writer = new BufferedWriter(new FileWriter(
                Util.getFileNameWithoutExtension(filePath) + ".hack"));
        while (parser.advance() != null) {
            if (CommandType.C_COMMAND.equals(parser.commandType())) {
                String binaryComp = code.comp(parser.comp());
                String binaryDest = code.dest(parser.dest());
                String binaryJump = code.jump(parser.jump());
                writer.append(String.format("111%s%s%s\n", binaryComp, binaryDest, binaryJump));
            } else if (CommandType.A_COMMAND.equals(parser.commandType())) {
                String symbol = parser.symbol();
                int value;
                if (Util.isNumeric(symbol)) {
                    value = Integer.parseInt(symbol);
                } else {
                    if (symbolTable.contains(symbol)) {
                        value = symbolTable.getAddress(symbol);
                    } else {
                        symbolTable.addEntry(symbol, n);
                        value = n;
                        n++;
                    }
                }
                writer.append(String.format("0%s\n", Util.get15BitBinaryRepresenation(value)));
            }
        }

        writer.close();
        parser.closeResource();
    }

    private static SymbolTable firstPass(String filePath) throws IOException {
        Parser parser = new Parser(filePath);
        SymbolTable symbolTable = new SymbolTable();

        while (parser.advance() != null) {
            if (CommandType.L_COMMAND.equals(parser.commandType())) {
                if (!symbolTable.contains(parser.symbol()))
                    symbolTable.addEntry(parser.symbol(), parser.instructionCount + 1);
            }
        }

        return symbolTable;
    }
}
