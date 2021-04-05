package cpinfo;

import classfile.ClassFile;

public final class ClassInfo implements ConstantPoolInfo{
    private final int tag = 7;
    private final int nameIndex;
    private final ClassFile classFile;

    public ClassInfo(final int nameIndex,
                     final ClassFile classFile){
        this.nameIndex = nameIndex;
        this.classFile = classFile;
    }

    public int nameIndex() { return nameIndex; }

    @Override public int tag() { return tag; }

    @Override public String value(){ return toString(); }

    @Override public String utf8(){ return classFile.constantPool[nameIndex].utf8(); }

    @Override
    public String toString(){
        return String.format("%-19s %-15s", 
                             "Class",
                              String.format("#%d",nameIndex)
                             );
    }
}
