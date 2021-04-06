package cpinfo;

public record FloatInfo(float floatValue) implements ConstantPoolInfo{

    @Override public String utf8() { return String.valueOf(floatValue); }

    @Override public int tag() { return 4; } 

    @Override public String value() { return toString(); }

    @Override
    public String toString(){
        return 
        String.format("%-19s %.2f", 
                      "Float",
                      floatValue);
    }


}
