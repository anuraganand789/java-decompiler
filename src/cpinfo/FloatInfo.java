package cpinfo;

public class FloatInfo implements ConstantPoolInfo{
    private final int   tag;
    private final float floatValue;

    public FloatInfo(final int tag, final float floatValue){
        this.tag   = tag;
        this.floatValue = floatValue;
    }

    public float floatValue(){
        return this.floatValue;
    }

    @Override public int tag() { return this.tag; } 
    @Override public String value() { return this.toString(); }

    @Override
    public String toString(){
        return String.format("Float %s %d %n", ConstantPoolInfo.SPACE_PADDING, this.floatValue);
    }


}
