package cpinfo;

import classfile.ClassFile;

public final class MethodRefInfo implements ConstantPoolInfo{
    private final int tag = 10;
    private final int classIndex;
    private final int nameAndTypeIndex;
    private final ClassFile classFile;

    public MethodRefInfo(final int classIndex, 
                         final int nameAndTypeIndex,
                         final ClassFile classFile){
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        this.classFile = classFile;
    }

    public int classIndex() { return  this.classIndex; }

    public int nameAndTypeIndex() { return this.nameAndTypeIndex; } 

    @Override 
    public String utf8() { 
       return 
       String.format("%s.%s",
                      classFile.constantPool[classIndex].utf8(), 
                      classFile.constantPool[nameAndTypeIndex].utf8()
                     );
    }  

    @Override public int tag(){ return this.tag; } 

    @Override public String value(){ return this.toString(); }

    @Override
    public String toString(){
        return String.format("%-19s %-15s", 
                              "MethodRef", 
                              String.format("#%d.#%d", classIndex, nameAndTypeIndex)
                              );
    }

}
