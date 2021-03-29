package cpinfo;

public class DynamicInfo implements ConstantPoolInfo{
    private final int tag = 17;
    private final int bootstrapMethodAttributeIndex;
    private final int nameAndTypeIndex;

    public DynamicInfo(final int bootstrapMethodAttributeIndex, 
                       final int nameAndTypeIndex){
        this.bootstrapMethodAttributeIndex = bootstrapMethodAttributeIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int bootstrapMethodAttributeIndex() {
        return bootstrapMethodAttributeIndex;
    }

    public int nameAndTypeIndex() {
        return nameAndTypeIndex;
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
   public String toString(){
       return String.format("DynamicInfo %s #%d.#%d %n", 
                             ConstantPoolInfo.SPACE_PADDING,
                             bootstrapMethodAttributeIndex,
                             nameAndTypeIndex);
   }
}
