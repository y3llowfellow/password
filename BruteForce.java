import java.security.NoSuchAlgorithmException;
import JBCrypt.BCrypt;
public class BruteForce {
    static boolean found = false;
    static String[] allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".split("");
    public String password;

    //user will input -p for plaintext, -m for MD5, -b for BCrypt, and -s for SHA256
    public String mode;

    public BruteForce(String password, String mode) {
        this.password = password;
        this.mode = mode;
        //if user inputs the wrong mode
        if (!(mode.equals("-p") || mode.equals("-m") || mode.equals("-s") || mode.equals("-b"))) {
            System.out.println("Invalid mode entered, defaulting to plain text dictionary search");
            this.mode = "-p";
        }
    }

    public void guessPassword() throws NoSuchAlgorithmException {
        int passwordLength = 1;
        //use while loop to test every single password length, starting at 1 and increasing by 1 each time
        //stop brute forcing once password is found (found == true)
        found = false;
        while (!found) {
            //call recursive method to brute force every permutation
            permute(passwordLength, "");
            passwordLength++;
        }
    }

    public void permute(int a, String word) throws NoSuchAlgorithmException {
        //keep preforming recursion and adding characters until String word reaches the desired length
        if (a != 0 && !found) {
            for (int i = 0; i < allCharacters.length; i++) {
                //lines 24-27 add a new character to the word
                if (i > 0) {
                    word = word.substring(0, word.length() - 1);
                }
                word += allCharacters[i];
                //call the recursive method to keep on adding different characters
                permute(a - 1, word);
            }
        }
        //convert the word into the correct hash, by default, hashedword is set to plain text in line 45
        String hashedWord = word;
        if (mode.equals("-m")) {
            hashedWord = Hasher.MD5(word);
        }
        if (mode.equals("-s")) {
            hashedWord = Hasher.SHA256(word);
        }
        //if the word matches password, print it out and set the boolean found to true
        //however, if user chose b crypt
        if (hashedWord.equals(password) || (mode.equals("-b") && BCrypt.checkpw(word, password))) {
            System.out.println("Password found using Brute Force!");
            System.out.println(word);
            found = true;
        }
    }
}
