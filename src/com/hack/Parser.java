package com.hack;

import com.hack.type.CommandType;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {

    private BufferedReader reader;
    private String nextLine;
    public int instructionCount = -1;

    public Parser(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        reader = Files.newBufferedReader(path);
    }

    public String advance() throws IOException {
        for (nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine()) {
            nextLine = nextLine.trim();
            if (!("".equals(nextLine) || nextLine.startsWith("/"))) {
                break;
            }
        }
        if (nextLine != null) {
            if (nextLine.contains("//")) nextLine = nextLine.substring(0, nextLine.indexOf("//")).trim();
            if (!CommandType.L_COMMAND.equals(commandType())) instructionCount++;
        }
        return nextLine;
    }

    public void closeResource() throws IOException {
        if (reader != null) reader.close();
    }

    public CommandType commandType() {
        if (nextLine.startsWith("@")) return CommandType.A_COMMAND;
        if (nextLine.startsWith("(")) return CommandType.L_COMMAND;
        return CommandType.C_COMMAND;
    }

    public String symbol() {
        if (CommandType.A_COMMAND.equals(commandType())) return nextLine.substring(1);
        if (CommandType.L_COMMAND.equals(commandType())) return nextLine.substring(1, nextLine.length() - 1);
        return null;
    }

    public String dest() {
        if (CommandType.C_COMMAND.equals(commandType()) && nextLine.contains("="))
            return nextLine.substring(0, nextLine.indexOf("="));
        return null;
    }

    public String comp() {
        if (CommandType.C_COMMAND.equals(commandType())) {
            if (nextLine.contains("=")) {
                int indexOfEqualsSign = nextLine.indexOf("=");
                if (nextLine.contains(";"))
                    return nextLine.substring(indexOfEqualsSign + 1, nextLine.indexOf(";"));
                else
                    return nextLine.substring(indexOfEqualsSign + 1);
            } else {
                return nextLine.substring(0, nextLine.indexOf(";"));
            }
        }
        return null;
    }

    public String jump() {
        if (CommandType.C_COMMAND.equals(commandType()) && nextLine.contains(";"))
            return nextLine.substring(nextLine.indexOf(";") + 1);
        return null;
    }
}
