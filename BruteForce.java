public class BruteForce {
    static boolean found = false;
    static String[] allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".split("");
    public String password;
    public BruteForce(String password){
        this.password = password;
    }

    public void permute (int a, String word){
        //keep preforming recursion and adding characters until String word reaches the desired length
        if(a !=0 && !found) {
            for (int i = 0; i < allCharacters.length; i++) {
                //lines 24-27 add a new character to the word
                if(i>0){
                    word = word.substring(0, word.length()-1);
                }
                word += allCharacters[i];
                //call the recursive method to keep on adding different characters
                permute(a - 1, word);
            }
        }
        //if the word matches password, print it out and set the boolean found to true
        if(word.equals(password)){
            System.out.println("password found!");
            System.out.println(word);
            found = true;
        }
    }
}
