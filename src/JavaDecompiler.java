import java.io.DataInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import constants.ReferenceKind;

import cpinfo.ConstantPoolInfo;
import cpinfo.MethodRefInfo;
import cpinfo.Utf8Info;
import cpinfo.IntegerInfo;
import cpinfo.ClassInfo;
import cpinfo.FloatInfo;
import cpinfo.DoubleInfo;
import cpinfo.LongInfo;
import cpinfo.StringInfo;
import cpinfo.FieldRefInfo;
import cpinfo.InterfaceMethodRefInfo;
import cpinfo.MethodHandleInfo;
import cpinfo.NameAndTypeInfo;

import static constants.ConstantTypes.CONSTANT_Utf8                ;
import static constants.ConstantTypes.CONSTANT_Integer             ;
import static constants.ConstantTypes.CONSTANT_Float               ;
import static constants.ConstantTypes.CONSTANT_Long                ;
import static constants.ConstantTypes.CONSTANT_Double              ;
import static constants.ConstantTypes.CONSTANT_Class               ;
import static constants.ConstantTypes.CONSTANT_String              ;
import static constants.ConstantTypes.CONSTANT_Fieldref            ;
import static constants.ConstantTypes.CONSTANT_MethodRef           ;
import static constants.ConstantTypes.CONSTANT_InterfaceMethodRef  ;
import static constants.ConstantTypes.CONSTANT_NameAndType         ;
import static constants.ConstantTypes.CONSTANT_MethodHandle        ;
import static constants.ConstantTypes.CONSTANT_MethodType          ;
import static constants.ConstantTypes.CONSTANT_Dynamic             ;
import static constants.ConstantTypes.CONSTANT_InvokeDynamic       ;
import static constants.ConstantTypes.CONSTANT_Module              ;
import static constants.ConstantTypes.CONSTANT_Package             ;

public class JavaDecompiler{
    private static String spacePadding = "                    ";
    
    private static ConstantPoolInfo[] printConstantPool(final DataInputStream dataInputStream) throws IOException{
        final int numberOfItemsInConstantPool = dataInputStream.readUnsignedShort();
        final ConstantPoolInfo[] constantPool = new ConstantPoolInfo[numberOfItemsInConstantPool];
        System.out.println("Constant Pool : ");

        for(int constantIndex = 1; constantIndex < numberOfItemsInConstantPool; ++constantIndex){
            final int tag = dataInputStream.readUnsignedByte();

            switch(tag){
            case CONSTANT_MethodRef : 
                final MethodRefInfo 
                methodRefInfo = new MethodRefInfo(dataInputStream.readUnsignedShort(),
                                                  dataInputStream.readUnsignedShort());

                constantPool[constantIndex - 1] = methodRefInfo;

                System.out.format("#%-2d = MethodRef %s #%d.#%d %n", 
                                  constantIndex, 
                                  spacePadding,
                                  methodRefInfo.classIndex(),
                                  methodRefInfo.nameAndTypeIndex());

            break;
            case CONSTANT_Utf8 : 
                final Utf8Info utf8Info = new Utf8Info(dataInputStream.readUTF());
                constantPool[constantIndex - 1] = utf8Info;
                System.out.format("#%2d = Utf8 %s %s%n", 
                                  constantIndex, 
                                  spacePadding,
                                  utf8Info.utf8Value());
                break;
            case CONSTANT_Integer : 
                final IntegerInfo integerInfo   = new IntegerInfo(dataInputStream.readInt());
                constantPool[constantIndex - 1] = integerInfo;

                System.out.format("#%2d = Integer %s %d%n", 
                                  constantIndex, 
                                  spacePadding, 
                                  integerInfo.intValue());
                break;

            case CONSTANT_Class: 
                final ClassInfo 
                classInfo = new ClassInfo(dataInputStream.readUnsignedShort());
                constantPool[constantIndex - 1] = classInfo;

                System.out.format("#%2d = Class %s #%d%n", 
                                  constantIndex, 
                                  spacePadding, 
                                  classInfo.nameIndex());
                break;
           case CONSTANT_Float              : 
                final FloatInfo floatInfo = new FloatInfo(dataInputStream.readFloat());
                constantPool[constantIndex - 1] = floatInfo;

                System.out.format("#%2d = Float %s %f%n", 
                                  constantIndex, 
                                  spacePadding, 
                                  floatInfo.floatValue());
           break;
           case CONSTANT_Long               : 
                final LongInfo longInfo = new LongInfo(dataInputStream.readLong());
                constantPool[constantIndex - 1] = longInfo;
                
                System.out.format("#%2d = Integer %s %d%n", 
                                  constantIndex, 
                                  spacePadding, 
                                  longInfo.longValue());
           break;
           case CONSTANT_Double             : 
                final DoubleInfo doubleInfo     = new DoubleInfo(dataInputStream.readDouble());
                constantPool[constantIndex - 1] = doubleInfo;

                System.out.format("#%2d = Integer %s %f%n", 
                                  constantIndex, 
                                  spacePadding, 
                                  doubleInfo.doubleValue());
           break;
           case CONSTANT_String             : 
                final StringInfo 
                stringInfo = new StringInfo(dataInputStream.readUnsignedShort());
                constantPool[constantIndex - 1] = stringInfo;

                System.out.format("#%2d = String %s #%d %n", 
                                  constantIndex, 
                                  spacePadding, 
                                  stringInfo.stringIndex());
           break;
           case CONSTANT_Fieldref           : 
               final FieldRefInfo 
               fieldRefInfo = new FieldRefInfo(dataInputStream.readUnsignedShort(),
                                               dataInputStream.readUnsignedShort());
               constantPool[constantIndex - 1] = fieldRefInfo;

               System.out.format("#%2d = FieldRef %s #%d.#%d %n", 
                                 constantIndex, 
                                 spacePadding, 
                                 fieldRefInfo.classIndex(),
                                 fieldRefInfo.nameAndTypeIndex());
               break;
           case CONSTANT_InterfaceMethodRef : 
               final InterfaceMethodRefInfo 
               interfaceMethodRef = new InterfaceMethodRefInfo(dataInputStream.readUnsignedShort(),
                                                           dataInputStream.readUnsignedShort());
               constantPool[constantIndex - 1] = interfaceMethodRef;
               System.out.format("#%2d = InterfaceMethodRef %s #%d.#%d %n", 
                                 constantIndex, 
                                 spacePadding, 
                                 interfaceMethodRef.classIndex(), 
                                 interfaceMethodRef.nameAndTypeIndex());
               break;
           case CONSTANT_NameAndType        : 
               final NameAndTypeInfo 
               nameAndType = new NameAndTypeInfo(dataInputStream.readUnsignedShort(), 
                                                 dataInputStream.readUnsignedShort());
               constantPool[constantIndex - 1] = nameAndType;

               System.out.format("#%2d = NameAndType %s #%d.#%d %n", 
                                 constantIndex, 
                                 spacePadding, 
                                 nameAndType.nameIndex(), 
                                 nameAndType.descriptorIndex());
           break;
           case CONSTANT_MethodHandle       : 
               final MethodHandleInfo 
               methodHandle = new MethodHandleInfo(dataInputStream.readUnsignedByte(),
                                                  dataInputStream.readUnsignedShort());
               System.out.format("#%2d = MethodHandle %s #%d.#%d %s %n", 
                                 constantIndex, 
                                 spacePadding, 
                                 methodHandle.referenceKind(), 
                                 methodHandle.referenceIndex(),
                                 ReferenceKind.getKind(methodHandle.referenceKind()));
           break;

           case CONSTANT_MethodType         : 
               final int method_type_descriptor_index = dataInputStream.readUnsignedShort();
               System.out.format("#%2d = MethodType %s #%d %n" , constantIndex, spacePadding, method_type_descriptor_index);
           break;
           case CONSTANT_Dynamic            : 
               final int dynamic_bootstrap_method_attr_index  = dataInputStream.readUnsignedShort();
               final int dynamic_name_and_type_index          = dataInputStream.readUnsignedShort();
               System.out.format("#%2d = DynamicInfo %s #%d.#%d %n" , constantIndex, spacePadding, dynamic_bootstrap_method_attr_index, dynamic_name_and_type_index);
               break;
           case CONSTANT_InvokeDynamic      : 
               final int invoke_dynamic_bootstrap_method_attr_index  = dataInputStream.readUnsignedShort();
               final int invoke_dynamic_name_and_type_index          = dataInputStream.readUnsignedShort();
               System.out.format("#%2d = InvokeDynamicInfo %s #%d.#%d %n" , constantIndex, spacePadding, invoke_dynamic_bootstrap_method_attr_index, invoke_dynamic_name_and_type_index);
               break;
           case CONSTANT_Module             : 
               final int module_name_index = dataInputStream.readUnsignedShort();
               System.out.format("#%2d = Module %s #%d %n" , constantIndex, spacePadding, module_name_index);
           break;
           case CONSTANT_Package            : 
               final int package_name_index = dataInputStream.readUnsignedShort();
               System.out.format("#%2d = Package %s #%d %n" , constantIndex, spacePadding, package_name_index);
           break;

            default: 
                throw new IllegalArgumentException("Invalid constant type found in constant table");
            }
        }
        return constantPool;
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
             System.out.println("magic - " + classFile.magicNumber);

             classFile.minorVersion = dataInputStream.readUnsignedShort();
             System.out.println("Minor Version   - " + classFile.minorVersion);

             classFile.majorVersion = dataInputStream.readUnsignedShort();
             System.out.println("Major Version  - " + classFile.majorVersion);

             classFile.constantPool = printConstantPool(dataInputStream);
         } catch(FileNotFoundException ex){
             ex.printStackTrace();
         }catch(IOException ex){
             ex.printStackTrace();
         }


    }
}
