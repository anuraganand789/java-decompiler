package cpinfo;

public final class StringInfo implements ConstantPoolInfo{
    private final int tag = 8 ;
    private final int stringIndex;

    public StringInfo(final int stringIndex){
        this.stringIndex = stringIndex;
    }

    public int stringIndex() { 
        return this.stringIndex; 
    }

    @Override 
    public int tag() { 
        return this.tag; 
    } 

    @Override 
    public String value() { 
        return this.toString(); 
    }

    @Override
    public String toString(){
        return String.format("String %s #%d %n", 
                             ConstantPoolInfo.SPACE_PADDING, 
                             stringIndex);
    }


}
