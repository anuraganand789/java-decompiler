package cpinfo;

import classfile.ClassFile;

public class MethodTypeInfo implements ConstantPoolInfo {
    private final int tag = 16;
    private final int descriptorIndex;
    private final ClassFile classFile;

    public MethodTypeInfo(final int descriptorIndex,
                          final ClassFile classFile){
        this.descriptorIndex = descriptorIndex;
        this.classFile       = classFile;
    }

    public int descriptorIndex() { return descriptorIndex; }

    @Override
    public String utf8() { 
        return classFile.constantPool[descriptorIndex].utf8();
    }

    @Override public int tag() { return tag; }
    
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
