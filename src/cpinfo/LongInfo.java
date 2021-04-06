package cpinfo;

import classfile.ClassFile;

public record LongInfo(long longValue) implements ConstantPoolInfo{

    @Override public String utf8() { return String.valueOf(longValue); }

    @Override public int tag() { return 5; } 

    @Override public String value() { return toString(); }

    @Override
    public String toString(){
        return 
        String.format("%-19s %d", 
                      "Long",
                      longValue);
    }


}

