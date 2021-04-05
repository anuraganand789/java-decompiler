package cpinfo;

public class FloatInfo implements ConstantPoolInfo{
    private final int   tag = 4;
    private final float floatValue;

    public FloatInfo(final float floatValue){
        this.floatValue = floatValue;
    }

    public float floatValue(){ return floatValue; }

    @Override public String utf8() { return String.valueOf(floatValue); }

    @Override public int tag() { return tag; } 

    @Override public String value() { return toString(); }

    @Override
    public String toString(){
        return 
        String.format("%-19s %.2f", 
                      "Float",
                      floatValue);
    }


}
