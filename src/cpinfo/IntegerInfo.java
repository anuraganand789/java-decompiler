package cpinfo;

public final class IntegerInfo implements ConstantPoolInfo{
    private final int tag = 3;
    private final int intValue;

    public IntegerInfo(final int intValue){
        this.intValue = intValue;
    }

    public int intValue() { return intValue; }

    @Override
    public String utf8(){ return String.valueOf(intValue); }

    @Override 
    public int tag() { return tag; } 

    @Override 
    public String value() { return toString(); }

    @Override
    public String toString(){
        return String.format("%-19s %d", 
                             "Integer",
                             intValue);
    }


}
