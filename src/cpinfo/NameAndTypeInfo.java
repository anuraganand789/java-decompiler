package cpinfo;

public class NameAndTypeInfo implements ConstantPoolInfo{
    private final int tag;
    private final int nameIndex;
    private final int descriptorIndex;

    public NameAndTypeInfo(final int tag, final int nameIndex, 
                           final int descriptorIndex){
         this.tag = tag;
         this.nameIndex = nameIndex;
         this.descriptorIndex = descriptorIndex;
     }
     
     public int nameIndex() { return nameIndex; }
     public int descriptorIndex() { return descriptorIndex; }

     @Override
     public int tag() {
         return this.tag;
     }
    
     @Override
     public String value() { return this.toString(); }

     @Override
     public String toString(){ 
         return String.format("NameAndType %s #%d.#%d %n", ConstantPoolInfo.SPACE_PADDING, nameIndex, descriptorIndex); 
     }
}
