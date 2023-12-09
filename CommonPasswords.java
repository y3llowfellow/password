import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CommonPasswords {
    public String password;
    public CommonPasswords(String password){
        this.password = password;
    }

    public void guessPassword(){
        boolean found  = false;
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\colin\\Documents\\GitHub\\password\\10-million-password-list-top-10000.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.equals(password)){
                    System.out.println("Password found in the list of 10k most common passwords!");
                    System.out.println(line);
                    found = true;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(!found){
            System.out.println("Password not found in 10k common datalist");
        }


    }


}
