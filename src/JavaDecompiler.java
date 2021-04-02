import java.io.DataInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.Date;

import constants.ReferenceKind;
import constants.AccessFlags;

import cpinfo.ClassInfo;
import cpinfo.ConstantPoolInfo;
import cpinfo.DoubleInfo;
import cpinfo.DynamicInfo;
import cpinfo.FieldRefInfo;
import cpinfo.FloatInfo;
import cpinfo.IntegerInfo;
import cpinfo.InterfaceMethodRefInfo;
import cpinfo.InvokeDynamicInfo;
import cpinfo.LongInfo;
import cpinfo.MethodHandleInfo;
import cpinfo.MethodRefInfo;
import cpinfo.MethodTypeInfo;
import cpinfo.ModuleInfo;
import cpinfo.NameAndTypeInfo;
import cpinfo.PackageInfo;
import cpinfo.StringInfo;
import cpinfo.Utf8Info;

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

public class JavaDecompiler{
    private static String spacePadding = "                    ";
    
    private static void readConstantPool(final ClassFile classFile, final DataInputStream dataInputStream) throws IOException{
        final int numberOfItemsInConstantPool = classFile.constantPoolCount = dataInputStream.readUnsignedShort();
        final ConstantPoolInfo[] constantPool = classFile.constantPool      = new ConstantPoolInfo[numberOfItemsInConstantPool - 1];
        System.out.println("Constant Pool : ");

        for(int constantIndex = 1; constantIndex < numberOfItemsInConstantPool; ++constantIndex){
            final int tag = dataInputStream.readUnsignedByte();

            switch(tag){
            case CONSTANT_MethodRef : 
                final MethodRefInfo 
                methodRefInfo = new MethodRefInfo(dataInputStream.readUnsignedShort(),
                                                  dataInputStream.readUnsignedShort());

                constantPool[constantIndex - 1] = methodRefInfo;


            break;
            case CONSTANT_Utf8 : 
                final Utf8Info utf8Info = new Utf8Info(dataInputStream.readUTF());
                constantPool[constantIndex - 1] = utf8Info;
                break;
            case CONSTANT_Integer : 
                final IntegerInfo integerInfo   = new IntegerInfo(dataInputStream.readInt());
                constantPool[constantIndex - 1] = integerInfo;

                break;

            case CONSTANT_Class: 
                final ClassInfo 
                classInfo = new ClassInfo(dataInputStream.readUnsignedShort());
                constantPool[constantIndex - 1] = classInfo;

                break;
           case CONSTANT_Float              : 
                final FloatInfo floatInfo = new FloatInfo(dataInputStream.readFloat());
                constantPool[constantIndex - 1] = floatInfo;

           break;
           case CONSTANT_Long               : 
                final LongInfo longInfo = new LongInfo(dataInputStream.readLong());
                constantPool[constantIndex - 1] = longInfo;
                
           break;
           case CONSTANT_Double             : 
                final DoubleInfo doubleInfo     = new DoubleInfo(dataInputStream.readDouble());
                constantPool[constantIndex - 1] = doubleInfo;

           break;
           case CONSTANT_String             : 
                final StringInfo 
                stringInfo = new StringInfo(dataInputStream.readUnsignedShort());
                constantPool[constantIndex - 1] = stringInfo;

           break;
           case CONSTANT_Fieldref           : 
               final FieldRefInfo 
               fieldRefInfo = new FieldRefInfo(dataInputStream.readUnsignedShort(),
                                               dataInputStream.readUnsignedShort());
               constantPool[constantIndex - 1] = fieldRefInfo;

               break;
           case CONSTANT_InterfaceMethodRef : 
               final InterfaceMethodRefInfo 
               interfaceMethodRef = new InterfaceMethodRefInfo(dataInputStream.readUnsignedShort(),
                                                           dataInputStream.readUnsignedShort());
               constantPool[constantIndex - 1] = interfaceMethodRef;
               break;
           case CONSTANT_NameAndType        : 
               final NameAndTypeInfo 
               nameAndType = new NameAndTypeInfo(dataInputStream.readUnsignedShort(), 
                                                 dataInputStream.readUnsignedShort());
               constantPool[constantIndex - 1] = nameAndType;

           break;
           case CONSTANT_MethodHandle       : 
               final MethodHandleInfo 
               methodHandle = new MethodHandleInfo(dataInputStream.readUnsignedByte(),
                                                  dataInputStream.readUnsignedShort());
           break;

           case CONSTANT_MethodType         : 
               final MethodTypeInfo methodType = new MethodTypeInfo(dataInputStream.readUnsignedShort());
           break;
           case CONSTANT_Dynamic            : 
               final DynamicInfo dynamic = new DynamicInfo(dataInputStream.readUnsignedShort(),
                                                           dataInputStream.readUnsignedShort());
               break;
           case CONSTANT_InvokeDynamic      : 
               final InvokeDynamicInfo invokeDynamic = new InvokeDynamicInfo(dataInputStream.readUnsignedShort(),
                                                                             dataInputStream.readUnsignedShort());
               break;
           case CONSTANT_Module             : 
               final ModuleInfo moduleInfo = new ModuleInfo(dataInputStream.readUnsignedShort());
           break;
           case CONSTANT_Package            : 
               final PackageInfo packageInfo = new PackageInfo(dataInputStream.readUnsignedShort());
           break;

            default: 
                throw new IllegalArgumentException("Invalid constant type found in constant table");
            }
        }
    }

    private static void interfaces(final ClassFile classFile, final DataInputStream dataInputStream) throws IOException{
        final int   interfacesCount = classFile.interfacesCount = dataInputStream.readUnsignedShort();
        final int[] interfaces      = classFile.interfaces      = new int[interfacesCount];
        for(int index = 0 ; index < interfacesCount; ++index){
            interfaces[index] = dataInputStream.readUnsignedShort();
        }
    }
    public static void main(final String ...args){
        if(args.length < 1) { 
            System.out.println("Provide a class name"); 
            return ;
         }

         final ClassFile classFile = new ClassFile();

         final File file = new File(args[0]);
         if(!file.exists()) {
             System.out.println("File Does not exists you moron");
             return ;
         }

         try(final FileInputStream fileInputStream = new FileInputStream(file);
             final DataInputStream dataInputStream = new DataInputStream(fileInputStream)){
             classFile.magicNumber = dataInputStream.readInt();
             if(classFile.magicNumber != 0xCAFEBABE) {
                 throw new IllegalArgumentException("Invalid Class File");
             }

             System.out.format("Classfile %s%n", file.getAbsolutePath());
             System.out.format("Last modified %s; size %d bytes%n", 
                                new Date(file.lastModified()),
                                file.length());

             classFile.minorVersion = dataInputStream.readUnsignedShort();
             classFile.majorVersion = dataInputStream.readUnsignedShort();

             readConstantPool(classFile, dataInputStream);

             classFile.accessFlags = dataInputStream.readUnsignedShort();
             classFile.thisClass   = dataInputStream.readUnsignedShort();
             classFile.superClass  = dataInputStream.readUnsignedShort();
             interfaces(classFile, dataInputStream);

         } catch(FileNotFoundException ex){
             ex.printStackTrace();
         }catch(IOException ex){
             ex.printStackTrace();
         }


         final String paddingOf10 = " ".repeat(10);
         final String paddingOf24 = " ".repeat(24);
         System.out.println("Class");
         System.out.format(" minor version: %d%n", classFile.minorVersion);
         System.out.format(" major version: %d%n", classFile.majorVersion);
         System.out.format(" flags : (0x%04x) %s %n" , 
                           classFile.accessFlags,
                           AccessFlags.accessFlags(classFile.accessFlags));

         final ClassInfo classInfoThisClass = (ClassInfo) classFile.constantPool[classFile.thisClass - 1];
         final Utf8Info thisClassUtf8       = (Utf8Info)  classFile.constantPool[classInfoThisClass.nameIndex() - 1];
         System.out.format(" this_class : #%d %s // %s %n", 
                             classFile.thisClass,
                             paddingOf24, 
                             thisClassUtf8.utf8Value());

         final ClassInfo classInfoSuperClass = (ClassInfo) classFile.constantPool[classFile.superClass - 1];
         final Utf8Info  superClassUtf8       = (Utf8Info) classFile.constantPool[classInfoSuperClass.nameIndex() - 1];
         System.out.format(" super_class : #%d %s // %s %n", 
                             classFile.superClass,
                             paddingOf24, 
                             superClassUtf8.utf8Value());

         System.out.format("""
                              %s interfaces: %d, \
                              fields: %d \
                              methods: %d, \
                              attributes: %d \
                              %n""".stripIndent(), 
                              "",
                              classFile.interfacesCount,
                              0,
                              0,
                              0);

         System.out.println("Constant Pool:");
         int constantIndex = 1;
         for(ConstantPoolInfo constantPoolItem : classFile.constantPool){
             System.out.format("%3s = %s ", 
                               "#"+constantIndex, 
                               constantPoolItem.value()
                               );
             ++constantIndex;
         }
         System.out.println();
    }
}
