package cpinfo;

import classfile.ClassFile;

public final class StringInfo implements ConstantPoolInfo{
    private final int tag = 8 ;
    private final int stringIndex;
    private final ClassFile classFile;

    public StringInfo(final int stringIndex,
                      final ClassFile classFile){ 
        this.stringIndex = stringIndex;
        this.classFile = classFile;
    }

    public int stringIndex() { return this.stringIndex; }

    @Override public String utf8() { return classFile.constantPool[stringIndex].utf8(); }

    @Override public int tag() { return this.tag; } 

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
