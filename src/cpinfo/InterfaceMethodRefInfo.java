package cpinfo;

import classfile.ClassFile;

public record InterfaceMethodRefInfo(int classIndex,
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

    @Override public int tag(){ return 11; } 
    
    @Override public String value(){ return toString(); }

    @Override
    public String toString(){
        return String.format("%-19s %-15s", 
                             "InterfaceMethodRef",
                             String.format("#%d.#%d", classIndex, nameAndTypeIndex)
                             );
    }

}
