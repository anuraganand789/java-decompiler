package cpinfo;

public class MethodTypeInfo implements ConstantPoolInfo {
    private final int tag;
    private final int descriptorIndex;

    public MethodTypeInfo(final int tag, final int descriptorIndex){
        this.tag = tag;
        this.descriptorIndex = descriptorIndex;
    }

    public int descriptorIndex() { return this.descriptorIndex; }

    @Override
    public int tag() { return this.tag; }
    
    @Override
    public String value(){ return this.toString(); }

    @Override
    public String toString() { 
        return String.format("MethodType %s #%d %n", 
                   ConstantPoolInfo.SPACE_PADDING, 
                   descriptorIndex);
     }
    
}
