package cpinfo;

import classfile.ClassFile;

public record DynamicInfo(int bootstrapMethodAttributeIndex, 
                          int nameAndTypeIndex,
                          ClassFile classFile) implements ConstantPoolInfo{

    @Override public int tag() { return 17; }

    @Override public String utf8(){
        return 
        classFile.constantPool[nameAndTypeIndex].utf8();
    }

    @Override
    public String value(){ 
        return toString();
    }

   @Override
   public String toString(){
       return 
       String.format("%-19s %-15s", 
                      "DynamicInfo",
                      String.format("#%d.#%d", bootstrapMethodAttributeIndex, nameAndTypeIndex)
                     );
   }
}
