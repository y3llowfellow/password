import java.security.NoSuchAlgorithmException;
//runs using command line
public class Password {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //user inputs plain text, SHA256, MD5, or BCrypt text
        String password = args[0];

        //user determines what technique to use. -bf is brute force, -d is dictionary
        String mode = args[1];

        //user inputs that type of hash the password is. -p is plain text, -b is Bcrypt, -s is SHA256, -m is MD5.
        String hashType = args[2];

        if(mode.equals("-bf")){
            (new BruteForce(password,hashType)).guessPassword();
        }
        else{
            (new CommonPasswords(password,hashType)).guessPassword();
        }

    }
}
