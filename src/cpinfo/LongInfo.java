package cpinfo;

public class LongInfo implements ConstantPoolInfo{
    private final int  tag;
    private final long longValue;

    public LongInfo(final int tag, final long longValue){
        this.tag       = tag;
        this.longValue = longValue;
    }

    public long longValue() { return this.longValue; }

    @Override public int tag() { return this.tag; } 
    @Override public String value() { return this.toString(); }

    @Override
    public String toString(){
        return String.format("Long %s %d %n", ConstantPoolInfo.SPACE_PADDING, this.longValue);
    }


}

