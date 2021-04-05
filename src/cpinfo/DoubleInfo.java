package cpinfo;

public final class DoubleInfo implements ConstantPoolInfo{
    private final int  tag = 6;
    private final double doubleValue;

    public DoubleInfo(final double doubleValue){
        this.doubleValue = doubleValue;
    }

    public double doubleValue() { return doubleValue; }

    @Override public int tag() { return tag; } 

    @Override public String value() { return toString(); }

    @Override public String utf8(){ return String.valueOf(doubleValue); }

    @Override
    public String toString(){
        return String.format("%-19s %d", 
                             "Double", 
                             doubleValue);
    }


}


