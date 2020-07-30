package testAutomationProject.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * AES encryption
 */
public class AES
{
	final static String publicKey = "nifty_encryptor";
    private static SecretKeySpec secretKey ;
    private static byte[] key ;
    
    private static String decryptedString;
    private static String encryptedString;
    
    public static void setKey(String myKey){
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
           // System.out.println(key.length);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); // use only first 128 bit
         //   System.out.println(key.length);
           // System.out.println(new String(key,"UTF-8"));
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            // 
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // 
            e.printStackTrace();
        }
    }
    
    public static String getDecryptedString() {
        return decryptedString;
    }
    
    public static void setDecryptedString(String decryptedString) {
        AES.decryptedString = decryptedString;
    }
    
    public static String getEncryptedString() {
        return encryptedString;
    }
    
    public static void setEncryptedString(String encryptedString) {
        AES.encryptedString = encryptedString;
    }
    
    public static String encrypt(String strToEncrypt)
    {
        try
        {
        	setKey(publicKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            setEncryptedString(Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: "+e.toString());
        }
        return null;
    }
    
    public static String decrypt(String strToDecrypt)
    {
        try
        {   setKey(publicKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            setDecryptedString(new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt))));
            
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: "+e.toString());
        }
        return null;
    }
    
    public static void main(String args[])
    {     
	           // The name of the files
        String inputFile = "_usersData.csv";
        String outputFile = "users.csv";
        // This will reference one line at a time
        String line = null;
        String fileToOpen = inputFile;
        String line2 = null;
     
        try {   
         	
           // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileToOpen);
            fileToOpen = outputFile;
            FileWriter fileWriter = new FileWriter(fileToOpen);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            while((line = bufferedReader.readLine()) != null) {
                // System.out.println(line);
                if(line.contains(",") && !line.contains(",,")){
   				              
    				AES.encrypt(line);
    				final String encryptedLine =  AES.getEncryptedString();
    				line = encryptedLine;

    				System.out.println("Encrypted line: " +line); 
    				line2 = line;
                }

                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }   
            // Always close files.
            bufferedReader.close(); 
            bufferedWriter.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                		fileToOpen + "'"+ ex.getMessage());                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '"+fileToOpen+"'"+ex.getMessage());                  
        }	 
        

    } // end main
} // end class
