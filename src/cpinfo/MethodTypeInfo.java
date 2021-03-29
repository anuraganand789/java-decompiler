package cpinfo;

public class MethodTypeInfo implements ConstantPoolInfo {
    private final int tag = 16;
    private final int descriptorIndex;

    public MethodTypeInfo(final int descriptorIndex){
        this.descriptorIndex = descriptorIndex;
    }

    public int descriptorIndex() { 
        return descriptorIndex; 
    }

    @Override
    public int tag() { 
        return tag; 
    }
    
    @Override
    public String value(){ 
        return toString(); 
    }

    @Override
    public String toString() { 
        return String.format("MethodType %s #%d %n", 
                   ConstantPoolInfo.SPACE_PADDING, 
                   descriptorIndex);
     }
    
}
