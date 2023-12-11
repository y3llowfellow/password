import java.io.IOException;
import java.security.NoSuchAlgorithmException;
//runs using command line
public class Password {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        //user inputs plain text, SHA256, MD5, or BCrypt text
        String password = args[0];

        //user determines what technique to use. -bf is brute force, -d is dictionary
        String mode = args[1];

        //user inputs that type of hash the password is. -p is plain text, -b is Bcrypt, -s is SHA256, -m is MD5.
        String hashType = args[2];

        //-rt for rainbow table
        boolean useRainbowTable = args.length == 4 && args[3].equals("-rt");

        //Brute Force mode
        if(mode.equals("-bf") && !useRainbowTable){
            (new BruteForce(password,hashType)).guessPassword();
        }
        else if (mode.equals("-bf") && useRainbowTable){
            System.out.println("Rainbow table can only be used for dictionary attack!");
        }

        //Dictionary Attack
        else{
            //if user wants to use Rainbow table
            if(useRainbowTable){
                (new CommonPasswords(password, hashType)).guessPasswordRT();
            }
            //user doesn't use rainbow table
            else {
                (new CommonPasswords(password, hashType)).guessPassword();
            }
        }

    }
}
