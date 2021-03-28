package cpinfo;

public class StringInfo implements ConstantPoolInfo{
    private final int tag;
    private final int stringIndex;

    public StringInfo(final int tag, final int stringIndex){
        this.tag = tag;
        this.stringIndex = stringIndex;
    }

    public int stringIndex() { return this.stringIndex; }
    @Override public int tag() { return this.tag; } 
    @Override public String value() { return this.toString(); }

    @Override
    public String toString(){
        return String.format("String %s #%d %n", ConstantPoolInfo.SPACE_PADDING, stringIndex);
    }


}
