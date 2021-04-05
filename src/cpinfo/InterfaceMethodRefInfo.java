package cpinfo;

import classfile.ClassFile;

public final class InterfaceMethodRefInfo implements ConstantPoolInfo{
    private final int tag = 11;
    private final int classIndex;
    private final int nameAndTypeIndex;
    private final ClassFile classFile;

    public InterfaceMethodRefInfo(final int classIndex, 
                                  final int nameAndTypeIndex,
                                  final ClassFile classFile){
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        this.classFile = classFile;
    }

    public int classIndex() { return classIndex; }

    public int nameAndTypeIndex() { return nameAndTypeIndex; } 

   @Override 
   public String utf8() { 
       return 
       String.format("%s.%s", 
                     classFile.constantPool[classIndex].utf8(),
                     classFile.constantPool[nameAndTypeIndex].utf8()
                     ); 
    }

    @Override public int tag(){ return tag; } 
    
    @Override public String value(){ return toString(); }

    @Override
    public String toString(){
        return String.format("%-19s %-15s", 
                             "InterfaceMethodRef",
                             String.format("#%d.#%d", classIndex, nameAndTypeIndex)
                             );
    }

}
