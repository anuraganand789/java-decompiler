import java.io.DataInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import constants.ReferenceKind;

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
    
    private static void printConstantPool(final DataInputStream dataInputStream) throws IOException{
        final int numberOfItemsInConstantPool = dataInputStream.readUnsignedShort();
        System.out.println("Constant Pool : ");

        for(int constantIndex = 1; constantIndex < numberOfItemsInConstantPool; ++constantIndex){
            final int tag = dataInputStream.readUnsignedByte();

            switch(tag){
            case CONSTANT_MethodRef : 
                final int method_ref_class_index         = dataInputStream.readUnsignedShort();
                final int method_ref_name_and_type_index = dataInputStream.readUnsignedShort();
                System.out.format("#%-2d = MethodRef %s #%d.#%d %n", constantIndex, spacePadding,method_ref_class_index, method_ref_name_and_type_index);

            break;
            case CONSTANT_Utf8 : 
                System.out.format("#%2d = Utf8 %s %s%n", constantIndex, spacePadding,dataInputStream.readUTF());
                break;
            case CONSTANT_Integer : 
                final int int_value = dataInputStream.readInt();
                System.out.format("#%2d = Integer %s %d%n", constantIndex, spacePadding, int_value);
                break;

            case CONSTANT_Class: 
                final int class_name_index = dataInputStream.readUnsignedShort();
                System.out.format("#%2d = Class %s #%d%n", constantIndex, spacePadding, class_name_index);
                break;
           case CONSTANT_Float              : 
                System.out.format("#%2d = Integer %s %f%n", constantIndex, spacePadding, dataInputStream.readFloat());
           break;
           case CONSTANT_Long               : 
                System.out.format("#%2d = Integer %s %d%n", constantIndex, spacePadding, dataInputStream.readLong());
           break;
           case CONSTANT_Double             : 
                System.out.format("#%2d = Integer %s %f%n", constantIndex, spacePadding, dataInputStream.readDouble());
           break;
           case CONSTANT_String             : 
                final int string_index = dataInputStream.readUnsignedShort();
                System.out.format("#%2d = String %s #%d %n", constantIndex, spacePadding, string_index);
           break;
           case CONSTANT_Fieldref           : 
               final int field_ref_class_index = dataInputStream.readUnsignedShort();
               final int field_ref_name_and_type_index = dataInputStream.readUnsignedShort();
               System.out.format("#%2d = FieldRef %s #%d.#%d %n", constantIndex, spacePadding, field_ref_class_index, field_ref_name_and_type_index);
               break;
           case CONSTANT_InterfaceMethodRef : 
               final int interface_method_ref_class_index = dataInputStream.readUnsignedShort();
               final int interface_method_ref_name_and_type_index = dataInputStream.readUnsignedShort();
               System.out.format("#%2d = InterfaceMethodRef %s #%d.#%d %n", constantIndex, spacePadding, interface_method_ref_class_index, interface_method_ref_name_and_type_index);
               break;
           case CONSTANT_NameAndType        : 
               final int name_and_type_name_index       = dataInputStream.readUnsignedShort(); 
               final int name_and_type_descriptor_index = dataInputStream.readUnsignedShort();
               System.out.format("#%2d = NameAndType %s #%d.#%d %n", constantIndex, spacePadding, name_and_type_name_index, name_and_type_descriptor_index);
           break;
           case CONSTANT_MethodHandle       : 
               final int method_handle_reference_kind = dataInputStream.readUnsignedByte();
               final int method_handle_reference_index = dataInputStream.readUnsignedShort();
               System.out.format("#%2d = MethodHandle %s #%d.#%d %s %n", constantIndex, spacePadding, method_handle_reference_kind, method_handle_reference_index, ReferenceKind.getKind(method_handle_reference_kind));
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
    }
    public static void main(final String ...args){
        if(args.length < 1) { 
            System.out.println("Provide a class name"); 
            return ;
         }

         final File file = new File(args[0]);
         if(!file.exists()) {
             System.out.println("File Does not exists you moron");
             return ;
         }

         try(final FileInputStream fileInputStream = new FileInputStream(file);
             final DataInputStream dataInputStream = new DataInputStream(fileInputStream)){
             System.out.println("magic - " + dataInputStream.readInt());
             System.out.println("MinorVersion   - " + dataInputStream.readUnsignedShort());
             System.out.println("Major Version  - " + dataInputStream.readUnsignedShort());
             printConstantPool(dataInputStream);
         } catch(FileNotFoundException ex){
             ex.printStackTrace();
         }catch(IOException ex){
             ex.printStackTrace();
         }


    }
}
