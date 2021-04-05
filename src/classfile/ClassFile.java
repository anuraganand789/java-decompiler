package classfile;

import java.util.Date;
import cpinfo.ConstantPoolInfo;

public class ClassFile{
    public String absolutePath;
    public Date lastModified;
    public long fileLength;

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
