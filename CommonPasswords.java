import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import JBCrypt.BCrypt;

public class CommonPasswords {
    public String password;

    //user will input -p for plaintext, -m for MD5, -b for BCrypt, and -s for SHA256
    public String mode;


    //constructor
    public CommonPasswords(String password, String mode) {
        this.password = password;
        this.mode = mode;
        //if user didn't enter a correct mode, then default to plain text
        if (!(mode.equals("-p") || mode.equals("-m") || mode.equals("-s") || mode.equals("-b"))) {
            System.out.println("Invalid mode entered, defaulting to plain text dictionary search");
            this.mode = "-p";
        }
    }
    public void guessPasswordRT() throws IOException {
        boolean found = false;
        // search the MD5 hashes instead, if user inputs -m
        if (mode.equals("-m")){
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\colin\\Documents\\GitHub\\password\\10k-rainbowTable-MD5.txt"));
            String line;
            int lineNumber = 1;
            //reading the line
            while ((line = br.readLine()) != null && !found) {
                line = line.substring(6);
                //if the hashes match, then the word is found
                if (line.equals(password)) {
                    //print out the output
                    System.out.println("Password found in the list of 10k most common passwords!");
                    //print out corresponding line number of the plain text

                    try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\colin\\Documents\\GitHub\\password\\10-million-password-list-top-10000.txt"))) {
                        System.out.println(lines.skip((lineNumber+1)/2).findFirst().get());
                    }
                    lineNumber++;
                    found = true;
                }

            }
        }
        else if(mode.equals("-s")){
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\colin\\Documents\\GitHub\\password\\10k-rainbowTable-SHA256.txt"));
            String line;
            int lineNumber = 1;
            //reading the line
            while ((line = br.readLine()) != null && !found) {
                line = line.substring(6);
                //if the hashes match, then the word is found
                if (line.equals(password)) {
                    //print out the output
                    System.out.println("Password found in the list of 10k most common passwords!");
                    //print out corresponding line number of the plain text

                    try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\colin\\Documents\\GitHub\\password\\10-million-password-list-top-10000.txt"))) {
                        System.out.println(lines.skip((lineNumber)/2).findFirst().get());
                    }
                    lineNumber++;
                    found = true;
                }

            }
        } else{
            System.out.println("Invalid mode entered. Rainbow Table can only be used for MD5 and SHA256 passwords");
        }

        //if password not found, then print out to indicate
        if (!found) {
            System.out.println("Password not found in 10k common datalist");
        }

    }

    public void guessPassword() throws NoSuchAlgorithmException {
        boolean found = false;
        //use bufferedreader to read file line by line
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\colin\\Documents\\GitHub\\password\\10-million-password-list-top-10000.txt"))) {
            String line;
            //reading the line
            while ((line = br.readLine()) != null && !found) {
                //Convert line to MD5 Hash if user inputed -m
                switch (mode) {
                    case "-m": {
                        String hashedLine = Hasher.MD5(line);
                        if (hashedLine.equals(password)) {
                            //print out output and corresponding plain text
                            System.out.println("Password found in the list of 10k most common passwords!");
                            System.out.println(line);
                            found = true;
                        }
                        break;
                    }
                    //Check if line matches BCrypt Hash
                    case "-b":{
                        if (BCrypt.checkpw(line, password)){
                            //print out output and corresponding plain text
                            System.out.println("Password found in the list of 10k most common passwords!");
                            System.out.println(line);
                            found = true;
                        }
                    }

                        break;
                    //Convert line to SHA-256 hash
                    case "-s": {
                        String hashedLine = Hasher.SHA256(line);
                        if (hashedLine.equals(password)) {
                            //print out output and corresponding plain text
                            System.out.println("Password found in the list of 10k most common passwords!");
                            System.out.println(line);
                            found = true;
                        }
                        break;
                    }
                    //Keep the line as plain text and search
                    default:
                        if (line.equals(password)) {
                            //print out output and corresponding plain text
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
        if (!found) {
            System.out.println("Password not found in 10k common datalist");
        }


    }


}
