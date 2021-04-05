package cpinfo;

import classfile.ClassFile;

public final class LongInfo implements ConstantPoolInfo{
    private final int  tag = 5;
    private final long longValue;

    public LongInfo(final long longValue){
        this.longValue = longValue;
    }

    public long longValue() { return longValue; }

    @Override public String utf8() { return String.valueOf(longValue); }

    @Override public int tag() { return tag; } 

    @Override public String value() { return toString(); }

    @Override
    public String toString(){
        return 
        String.format("%-19s %d", 
                      "Long",
                      longValue);
    }


}

