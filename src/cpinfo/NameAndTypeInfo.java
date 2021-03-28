package cpinfo;

public final class NameAndTypeInfo implements ConstantPoolInfo{
    private final int tag = 12;
    private final int nameIndex;
    private final int descriptorIndex;

    public NameAndTypeInfo(final int nameIndex, 
                           final int descriptorIndex){
         this.nameIndex = nameIndex;
         this.descriptorIndex = descriptorIndex;
     }
     
     public int nameIndex() { 
         return nameIndex; 
     }

     public int descriptorIndex() { 
         return descriptorIndex; 
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
         return String.format("NameAndType %s #%d.#%d %n", 
                              ConstantPoolInfo.SPACE_PADDING, 
                              nameIndex, 
                              descriptorIndex); 
     }
}
