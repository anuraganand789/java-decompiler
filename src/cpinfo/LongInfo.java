package cpinfo;

public final class LongInfo implements ConstantPoolInfo{
    private final int  tag = 5;
    private final long longValue;

    public LongInfo(final long longValue){
        this.longValue = longValue;
    }

    public long longValue() { 
        return longValue; 
    }

    @Override 
    public int tag() { 
        return tag; 
    } 

    @Override 
    public String value() { 
        return toString(); 
    }

    @Override
    public String toString(){
        return String.format("Long %s %d %n", 
                              ConstantPoolInfo.SPACE_PADDING, 
                              longValue);
    }


}

