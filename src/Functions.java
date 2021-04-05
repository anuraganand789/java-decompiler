import java.io.DataInputStream;
import java.io.IOException;

import classfile.ClassFile;

import constants.ReferenceKind;
import constants.AccessFlags;

import cpinfo.ClassInfo                    ;
import cpinfo.ConstantPoolInfo             ;
import cpinfo.DoubleInfo                   ;
import cpinfo.DynamicInfo                  ;
import cpinfo.FieldRefInfo                 ;
import cpinfo.FloatInfo                    ;
import cpinfo.IntegerInfo                  ;
import cpinfo.InterfaceMethodRefInfo       ;
import cpinfo.InvokeDynamicInfo            ;
import cpinfo.LongInfo                     ;
import cpinfo.MethodHandleInfo             ;
import cpinfo.MethodRefInfo                ;
import cpinfo.MethodTypeInfo               ;
import cpinfo.ModuleInfo                   ;
import cpinfo.NameAndTypeInfo              ;
import cpinfo.PackageInfo                  ;
import cpinfo.StringInfo                   ;
import cpinfo.Utf8Info                     ;

import static constants.ConstantTypes.CONSTANT_Class               ;
import static constants.ConstantTypes.CONSTANT_Double              ;
import static constants.ConstantTypes.CONSTANT_Dynamic             ;
import static constants.ConstantTypes.CONSTANT_Fieldref            ;
import static constants.ConstantTypes.CONSTANT_Float               ;
import static constants.ConstantTypes.CONSTANT_Integer             ;
import static constants.ConstantTypes.CONSTANT_InterfaceMethodRef  ;
import static constants.ConstantTypes.CONSTANT_InvokeDynamic       ;
import static constants.ConstantTypes.CONSTANT_Long                ;
import static constants.ConstantTypes.CONSTANT_MethodHandle        ;
import static constants.ConstantTypes.CONSTANT_MethodRef           ;
import static constants.ConstantTypes.CONSTANT_MethodType          ;
import static constants.ConstantTypes.CONSTANT_Module              ;
import static constants.ConstantTypes.CONSTANT_NameAndType         ;
import static constants.ConstantTypes.CONSTANT_Package             ;
import static constants.ConstantTypes.CONSTANT_String              ;
import static constants.ConstantTypes.CONSTANT_Utf8                ;

public class Functions{

    public static void readConstantPool(final ClassFile classFile, final DataInputStream dataInputStream) throws IOException{
            final int constantPoolCount = dataInputStream.readUnsignedShort();
            final ConstantPoolInfo[] constantPool = new ConstantPoolInfo[constantPoolCount];

            classFile.constantPoolCount = constantPoolCount;
            classFile.constantPool      = constantPool;

            for(int constantIndex = 1; constantIndex < constantPoolCount; ++constantIndex){
                constantPool[constantIndex] = 
                switch(dataInputStream.readUnsignedByte()){
                case CONSTANT_MethodRef     ->  new MethodRefInfo(dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), classFile);
                case CONSTANT_Utf8          ->  new Utf8Info(dataInputStream.readUTF());
                case CONSTANT_Integer       ->  new IntegerInfo(dataInputStream.readInt());
                case CONSTANT_Class         ->  new ClassInfo(dataInputStream.readUnsignedShort(), classFile);
                case CONSTANT_Float         ->  new FloatInfo(dataInputStream.readFloat());
                case CONSTANT_Long          ->  new LongInfo(dataInputStream.readLong());
                case CONSTANT_Double        ->  new DoubleInfo(dataInputStream.readDouble());
                case CONSTANT_String        ->  new StringInfo(dataInputStream.readUnsignedShort(), classFile);
                case CONSTANT_Fieldref      ->  new FieldRefInfo(dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), classFile);
                case CONSTANT_NameAndType   ->  new NameAndTypeInfo(dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), classFile);
                case CONSTANT_MethodHandle  ->  new MethodHandleInfo(dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedShort(), classFile);
                case CONSTANT_MethodType    ->  new MethodTypeInfo(dataInputStream.readUnsignedShort(), classFile);
                case CONSTANT_Dynamic       ->  new DynamicInfo(dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), classFile);
                case CONSTANT_InvokeDynamic ->  new InvokeDynamicInfo(dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), classFile);
                case CONSTANT_Module        ->  new ModuleInfo(dataInputStream.readUnsignedShort(), classFile);
                case CONSTANT_Package       ->  new PackageInfo(dataInputStream.readUnsignedShort(), classFile);
                case CONSTANT_InterfaceMethodRef ->  new InterfaceMethodRefInfo(dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), classFile);
                default -> throw new IllegalArgumentException("Invalid constant type found in constant table");
                };
            }
        }

    public static void printClassFile(final ClassFile classFile){
        final int constantPoolCount = classFile.constantPoolCount;
        final ConstantPoolInfo[] constantPool = classFile.constantPool;

        final String paddingOf10 = " ".repeat(10);
        final String paddingOf24 = " ".repeat(24);
        final String classHierarchy = constantPool[classFile.thisClass].utf8();
        System.out.format("Classfile %s%n", classFile.absolutePath);
        System.out.format("  Last modified %s; size %d bytes%n", 
                          classFile.lastModified,
                          classFile.fileLength);

        System.out.format("  Compiled From \"%s.java\"%n", classHierarchy.substring(classHierarchy.indexOf("/") + 1));
        System.out.println("Class");
        System.out.format("  minor version: %d%n", classFile.minorVersion);
        System.out.format("  major version: %d%n", classFile.majorVersion);
        System.out.format("  flags : (0x%04x) %s %n" , 
                classFile.accessFlags,
                AccessFlags.accessFlags(classFile.accessFlags));

        final ClassInfo classInfoThisClass = (ClassInfo) classFile.constantPool[classFile.thisClass];
        final Utf8Info thisClassUtf8       = (Utf8Info)  classFile.constantPool[classInfoThisClass.nameIndex()];
        System.out.format("  this_class : #%d %s // %s %n", 
                classFile.thisClass,
                paddingOf24, 
                thisClassUtf8.utf8Value());

        final ClassInfo classInfoSuperClass = (ClassInfo) classFile.constantPool[classFile.superClass];
        final Utf8Info  superClassUtf8       = (Utf8Info) classFile.constantPool[classInfoSuperClass.nameIndex()];
        System.out.format("  super_class : #%d %s // %s %n", 
                classFile.superClass,
                paddingOf24, 
                superClassUtf8.utf8Value());

        System.out.format("""
                %s interfaces: %d, \
                fields: %d \
                methods: %d, \
                attributes: %d \
                %n""".stripIndent(), 
                " ",
                classFile.interfacesCount,
                0,
                0,
                0);

        System.out.println("Constant Pool:");

        ConstantPoolInfo constantPoolItem;
        for(int constantIndex = 1; constantIndex < constantPoolCount; ++constantIndex){
            constantPoolItem = constantPool[constantIndex];
            System.out.format("  %3s = %s %s %n", 
                              "#"+constantIndex, 
                              constantPoolItem.value(),
                              constantPoolItem.tag() != CONSTANT_Utf8 ?  "//" + constantPoolItem.utf8() : ""
                             );
        }
        System.out.println();
    }
}
