import cpinfo.ConstantPoolInfo;

public class ClassFile{
    //#CAFEBABE
    public int                magicNumber;

    public int                minorVersion;
    public int                majorVersion;

    public int                constantPoolCount;
    public ConstantPoolInfo[] constantPool;

    public int                accessFlags;
    public int                thisClass;
    public int                superClass;
    public int                interfacesCount;
    public int[]              interfaces;
}
