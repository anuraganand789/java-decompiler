package cpinfo;

public class DoubleInfo implements ConstantPoolInfo{
    private final int  tag;
    private final double doubleValue;

    public DoubleInfo(final int tag, final double doubleValue){
        this.tag         = tag;
        this.doubleValue = doubleValue;
    }

    public double doubleValue() { return this.doubleValue; }

    @Override 
    public int tag() { return this.tag; } 

    @Override 
    public String value() { return this.toString(); }

    @Override
    public String toString(){
        return String.format("Double %s %d %n", ConstantPoolInfo.SPACE_PADDING, this.doubleValue);
    }


}


