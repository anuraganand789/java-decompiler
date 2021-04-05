package cpinfo;

import classfile.ClassFile;

public class DynamicInfo implements ConstantPoolInfo{
    private final int tag = 17;
    private final int bootstrapMethodAttributeIndex;
    private final int nameAndTypeIndex;
    private final ClassFile classFile;

    public DynamicInfo(final int bootstrapMethodAttributeIndex, 
                       final int nameAndTypeIndex,
                       final ClassFile classFile){
        this.bootstrapMethodAttributeIndex = bootstrapMethodAttributeIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        this.classFile = classFile;
    }

    public int bootstrapMethodAttributeIndex() { return bootstrapMethodAttributeIndex; }

    public int nameAndTypeIndex() { return nameAndTypeIndex; }

    @Override public int tag() { return tag; }

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
