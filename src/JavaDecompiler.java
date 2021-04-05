import java.io.DataInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.Date;

import classfile.ClassFile;

import cpinfo.ClassInfo;
import cpinfo.ConstantPoolInfo;
import cpinfo.Utf8Info;


public class JavaDecompiler{
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

             classFile.absolutePath = file.getAbsolutePath();
             classFile.lastModified = new Date(file.lastModified());
             classFile.fileLength   = file.length();
             classFile.minorVersion = dataInputStream.readUnsignedShort();
             classFile.majorVersion = dataInputStream.readUnsignedShort();

             Functions.readConstantPool(classFile, dataInputStream);

             classFile.accessFlags = dataInputStream.readUnsignedShort();
             classFile.thisClass   = dataInputStream.readUnsignedShort();
             classFile.superClass  = dataInputStream.readUnsignedShort();
             interfaces(classFile, dataInputStream);

         } catch(FileNotFoundException ex){
             ex.printStackTrace();
         }catch(IOException ex){
             ex.printStackTrace();
         }

         Functions.printClassFile(classFile);
    }
}
