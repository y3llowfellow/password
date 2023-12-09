import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;


public class CommonPasswords {
    public String password;

    //user will input -p for plaintext, -m for MD5, -b for BCrypt, and -s for SHA256
    public String mode;
    //object to convert to hashes

    //constructor
    public CommonPasswords(String password, String mode) throws NoSuchAlgorithmException {
        this.password = password;
        this.mode = mode;
        //if user didn't enter a correct mode, then default to plain text
        if(!(mode.equals("-p") || mode.equals("-m") || mode.equals("-s")|| mode.equals("-b"))){
            System.out.println("Invalid mode entered, defaulting to plain text dictionary search");
            this.mode = "-p";
        }
    }

    public void guessPassword() throws NoSuchAlgorithmException {
        boolean found  = false;
        //use bufferedreader to read file line by line
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\colin\\Documents\\GitHub\\password\\10-million-password-list-top-10000.txt"))) {
            String line;
            //reading the line
            while ((line = br.readLine()) != null) {
                //Convert line to MD5 Hash
                switch (mode) {
                    case "-m": {
                        String hashedLine = Hasher.MD5(line);
                        if (hashedLine.equals(password)) {
                            System.out.println("Password found in the list of 10k most common passwords!");
                            System.out.println(line);
                            found = true;
                        }
                        break;
                    }
                    //Convert line to BCrypt Hash
                    case "-b":

                        break;
                    //Convert line to SHA-256 hash
                    case "-s": {
                        String hashedLine = Hasher.SHA256(line);
                        if (hashedLine.equals(password)) {
                            System.out.println("Password found in the list of 10k most common passwords!");
                            System.out.println(line);
                            found = true;
                        }
                        break;
                    }
                    //Keep the line as plain text and search
                    default:
                        if (line.equals(password)) {
                            System.out.println("Password found in the list of 10k most common passwords!");
                            System.out.println(line);
                            found = true;
                        }
                        break;
                }
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //if password not found, then print out to indicate
        if(!found){
            System.out.println("Password not found in 10k common datalist");
        }


    }


}
