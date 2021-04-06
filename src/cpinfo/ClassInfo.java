package cpinfo;

import classfile.ClassFile;

public record ClassInfo(int nameIndex, ClassFile classFile) implements ConstantPoolInfo{

    @Override public int tag() { return 7; }

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
