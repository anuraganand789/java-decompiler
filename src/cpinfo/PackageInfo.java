package cpinfo;

import classfile.ClassFile;

public record PackageInfo(int nameIndex, ClassFile classFile) implements ConstantPoolInfo{

    @Override 
    public String utf8() { return classFile.constantPool[nameIndex].utf8(); }

    @Override public int tag() { return 20; }

    @Override public String value(){ return toString(); }

    @Override
    public String toString(){
        return 
        String.format("%-19s %-15s ", 
                      "Package",
                      String.format("#%d", nameIndex)
                      );
    }

}

