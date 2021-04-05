package cpinfo;

import classfile.ClassFile;

public final class NameAndTypeInfo implements ConstantPoolInfo{
    private final int tag = 12;
    private final int nameIndex;
    private final int descriptorIndex;
    private final ClassFile classFile;

    public NameAndTypeInfo(final int nameIndex, 
                           final int descriptorIndex,
                           final ClassFile classFile){
         this.nameIndex = nameIndex;
         this.descriptorIndex = descriptorIndex;
         this.classFile = classFile;
     }
     
     public int nameIndex() { return nameIndex; }

     public int descriptorIndex() { return descriptorIndex; }

     @Override public String utf8() { 
        return 
        String.format("\"%s\":%s", 
                       classFile.constantPool[nameIndex].utf8(),
                       classFile.constantPool[descriptorIndex].utf8()
                      );
     }

     @Override public int tag() { return tag; }
    
     @Override public String value() { return toString(); }

     @Override
     public String toString(){ 
         return 
         String.format("%-19s %-15s", 
                       "NameAndType",
                       String.format("#%d.#%d", nameIndex, descriptorIndex)
                       ); 
     }
}
