package cpinfo;

public class IntegerInfo implements ConstantPoolInfo{
    private final int tag;
    private final int intValue;

    public IntegerInfo(final int tag, final int intValue){
        this.tag     = tag;
        this.intValue = intValue;
    }

    public int intValue() { return this.intValue; }

    @Override 
    public int tag() { return this.tag; } 

    @Override 
    public String value() { return this.toString(); }

    @Override
    public String toString(){
        return String.format("Integer %s %d %n", ConstantPoolInfo.SPACE_PADDING, this.intValue);
    }


}
