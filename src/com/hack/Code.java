package com.hack;

import java.util.HashMap;
import java.util.Map;

public class Code {

    private Map<String, Integer> compMap = new HashMap<>();
    private Map<String, Integer> destMap = new HashMap<>();
    private Map<String, Integer> jumpMap = new HashMap<>();

    public Code() {
        // Fill Comp Map
        compMap.put("0", 0x002A); // a = 0 onwards
        compMap.put("1", 0x003F);
        compMap.put("-1", 0x003A);
        compMap.put("D", 0x000C);
        compMap.put("A", 0x0030);
        compMap.put("!D", 0x000D);
        compMap.put("!A", 0x0031);
        compMap.put("-D", 0x000F);
        compMap.put("-A", 0x0033);
        compMap.put("D+1", 0x001F);
        compMap.put("A+1", 0x0037);
        compMap.put("D-1", 0x000E);
        compMap.put("A-1", 0x0032);
        compMap.put("D+A", 0x0002);
        compMap.put("D-A", 0x0013);
        compMap.put("A-D", 0x0007);
        compMap.put("D&A", 0x0000);
        compMap.put("D|A", 0x0015);
        compMap.put("M", 0x0070); // a = 1 onwards
        compMap.put("!M", 0x0071);
        compMap.put("-M", 0x0073);
        compMap.put("M+1", 0x0077);
        compMap.put("M-1", 0x0072);
        compMap.put("D+M", 0x0042);
        compMap.put("D-M", 0x0053);
        compMap.put("M-D", 0x0047);
        compMap.put("D&M", 0x0040);
        compMap.put("D|M", 0x0055);

        // Fill Dest Map
        destMap.put(null, 0x0000);
        destMap.put("M", 0x0001);
        destMap.put("D", 0x0002);
        destMap.put("MD", 0x0003);
        destMap.put("A", 0x0004);
        destMap.put("AM", 0x0005);
        destMap.put("AD", 0x0006);
        destMap.put("AMD", 0x0007);

        // Fill Jump Map
        jumpMap.put(null, 0x0000);
        jumpMap.put("JGT", 0x0001);
        jumpMap.put("JEQ", 0x0002);
        jumpMap.put("JGE", 0x0003);
        jumpMap.put("JLT", 0x0004);
        jumpMap.put("JNE", 0x0005);
        jumpMap.put("JLE", 0x0006);
        jumpMap.put("JMP", 0x0007);
    }

    // 3 bits
    public String dest(String mnemonic) {
        String asBinary = Integer.toBinaryString(destMap.get(mnemonic));
        return ("000" + asBinary).substring(asBinary.length());
    }

    // 7 bits
    public String comp(String mnemonic) {
        String asBinary = Integer.toBinaryString(compMap.get(mnemonic));
        return ("0000000" + asBinary).substring(asBinary.length());
    }

    // 3 bits
    public String jump(String mnemonic) {
        String asBinary = Integer.toBinaryString(jumpMap.get(mnemonic));
        return ("000" + asBinary).substring(asBinary.length());
    }
}
