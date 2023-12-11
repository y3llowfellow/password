import JBCrypt.BCrypt;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;  // Import the IOException class to handle errors

public class RainbowTableGenerator {


    public static void main(String[] args) throws FileNotFoundException {

        //use buffered reader to read file
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\colin\\Documents\\GitHub\\password\\10-million-password-list-top-10000.txt"))) {
            String line;
            //counter to demarcate corresponding word in the regular file
            int i = 1;
            //use printwriter to create and change a text file
            PrintWriter reader = new PrintWriter("C:\\Users\\colin\\Documents\\GitHub\\password\\10k-rainbowTable-MD5");
            //reading the line
            while ((line = br.readLine()) != null) {
                //hash word. tag the word with a 5 digit padded number to its easier to find the corresponding word

                reader.println(String.format("%05d",i) + Hasher.MD5(line));
                //reader.println(String.format("%05d",i) + Hasher.SHA256(line));
                i++;
                }
            reader.flush();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

}
