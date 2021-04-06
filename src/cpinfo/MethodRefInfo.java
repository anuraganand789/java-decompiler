package cpinfo;

import classfile.ClassFile;

public record MethodRefInfo(int classIndex,
                            int nameAndTypeIndex,
                            ClassFile classFile) implements ConstantPoolInfo{

    @Override 
    public String utf8() { 
       return 
       String.format("%s.%s",
                      classFile.constantPool[classIndex].utf8(), 
                      classFile.constantPool[nameAndTypeIndex].utf8()
                     );
    }  

    @Override public int tag(){ return 10; } 

    @Override public String value(){ return this.toString(); }

    @Override
    public String toString(){
        return String.format("%-19s %-15s", 
                              "MethodRef", 
                              String.format("#%d.#%d", classIndex, nameAndTypeIndex)
                              );
    }

}
