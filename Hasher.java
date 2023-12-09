import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {


    public Hasher(){

    }

    //Method to return an MD5 hash of a plaintext message
    public static String MD5(String message) throws NoSuchAlgorithmException {
        //convert to byte array, process byte array
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytesofMessage = message.getBytes();
        byte[] digest = md.digest(bytesofMessage);

        //convert to string using stringbuilder
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String SHA256(String message) throws NoSuchAlgorithmException {
        //convert to byte array, process byte array
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] bytesofMessage = message.getBytes();
        byte[] digest = md.digest(bytesofMessage);

        //convert to string using stringbuilder
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String BCrypt(String message){
        return "";
    }
}
