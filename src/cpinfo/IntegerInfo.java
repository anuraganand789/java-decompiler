package cpinfo;

public record IntegerInfo(int intValue) implements ConstantPoolInfo{

    @Override
    public String utf8(){ return String.valueOf(intValue); }

    @Override 
    public int tag() { return 3; } 

    @Override 
    public String value() { return toString(); }

    @Override
    public String toString(){
        return String.format("%-19s %d", 
                             "Integer",
                             intValue);
    }


}
