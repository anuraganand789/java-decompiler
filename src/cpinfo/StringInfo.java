package cpinfo;

import classfile.ClassFile;

public record StringInfo(int stringIndex, ClassFile classFile) implements ConstantPoolInfo{

    @Override public String utf8() { return classFile.constantPool[stringIndex].utf8(); }

    @Override public int tag() { return 8; } 

    @Override public String value() { return this.toString(); }

    @Override
    public String toString(){
        return 
        String.format("%-19s %-15s", 
                      "String",
                      String.format("#%d", stringIndex)
                      );
    }


}
