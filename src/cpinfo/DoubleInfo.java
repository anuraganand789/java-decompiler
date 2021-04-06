package cpinfo;

public record DoubleInfo(double doubleValue) implements ConstantPoolInfo{

    @Override public int tag() { return 6; } 

    @Override public String value() { return toString(); }

    @Override public String utf8(){ return String.valueOf(doubleValue); }

    @Override
    public String toString(){
        return String.format("%-19s %d", 
                             "Double", 
                             doubleValue);
    }


}


