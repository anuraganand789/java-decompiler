package cpinfo;

import classfile.ClassFile;

public record MethodTypeInfo(int descriptorIndex, 
                             ClassFile classFile) implements ConstantPoolInfo {

    @Override
    public String utf8() { 
        return classFile.constantPool[descriptorIndex].utf8();
    }

    @Override public int tag() { return 16; }
    
    @Override public String value(){ return toString(); }

    @Override
    public String toString() { 
        return 
        String.format("%-19s %-15s", 
                      "MethodType",
                      String.format("#%d", descriptorIndex)
                      );
     }
    
}
