package cpinfo;

import classfile.ClassFile;

public record NameAndTypeInfo(int nameIndex, 
                              int descriptorIndex,
                              ClassFile classFile) implements ConstantPoolInfo{

     @Override public String utf8() { 
        return 
        String.format("\"%s\":%s", 
                       classFile.constantPool[nameIndex].utf8(),
                       classFile.constantPool[descriptorIndex].utf8()
                      );
     }

     @Override public int tag() { return 12; }
    
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
