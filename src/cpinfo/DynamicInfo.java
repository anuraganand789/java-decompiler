package cpinfo;

public class DynamicInfo implements ConstantPoolInfo{
    private final int tag;
    private final int bootstrapMethodAttributeIndex;
    private final int nameAndTypeIndex;

    public DynamicInfo(final int tag, 
                       final int bootstrapMethodAttributeIndex, 
                       final int nameAndTypeIndex){
        this.tag = tag;
        this.bootstrapMethodAttributeIndex = bootstrapMethodAttributeIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int bootstrapMethodAttributeIndex() {
        return this.bootstrapMethodAttributeIndex;
    }

    public int nameAndTypeIndex() {
        return this.nameAndTypeIndex;
    }

    @Override
    public int tag() { 
        return this.tag; 
    }

    @Override
    public String value(){ 
        return this.toString();
    }

   @Override
   public String toString(){
       return String.format("DynamicInfo %s #%d.#%d %n", 
                             ConstantPoolInfo.SPACE_PADDING,
                             this.bootstrapMethodAttributeIndex,
                             this.nameAndTypeIndex);
   }
}
