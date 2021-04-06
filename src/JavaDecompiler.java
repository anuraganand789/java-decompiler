import java.io.DataInputStream;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Date;
import java.util.Base64;

import classfile.ClassFile;

import cpinfo.ClassInfo;
import cpinfo.ConstantPoolInfo;
import cpinfo.Utf8Info;


public class JavaDecompiler{
    public static void main(final String ...args) throws NoSuchAlgorithmException{
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

         byte[] classData = null;
         try( final FileInputStream fileInputStream     = new FileInputStream(file)){
             classData        = fileInputStream.readAllBytes();
             //classFile.sha256 = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(classData));
             classFile.sha256 = Functions.sha256(MessageDigest.getInstance("SHA-256").digest(classData));
         }catch(FileNotFoundException ex) {
             ex.printStackTrace();
         } catch(IOException ex){
             ex.printStackTrace();
         }

         if(classData == null) return;

         try( final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(classData)); ){
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
             Functions.interfaces(classFile, dataInputStream);

         } catch(IOException ex){
             ex.printStackTrace();
         }

         Functions.printClassFile(classFile);
    }
}
