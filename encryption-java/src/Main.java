import crypt.*;
import java.math.BigInteger;
import java.util.*;



public class Main {

    public final static int space = 32;
    // public final static int period = 46;

    public static void main(String[] args) throws Exception {
       // 24317, 15139
       System.out.println("Enter two prime numbers: ");
       Scanner pq = new Scanner(System.in);
       String [] pAndq = pq.nextLine().split(" ");
       RSAEncryption thoralf =  new RSAEncryption(Integer.parseInt(pAndq[0]),Integer.parseInt(pAndq[1]));

       // DEBUG LINES
       // System.out.println(Integer.parseInt(pAndq[0]));
       // System.out.println(Integer.parseInt(pAndq[1]))
       // =====

       System.out.println("Would you like to encrypt a message to Sonny or decrypt? (Enter E or D)");
       Scanner ED = new Scanner(System.in);
       String ed = ED.nextLine();
       // System.out.println(ed);

       String cipher = "";
       String decipher = "";
       String message = "";

    if (ed.equals("E") ){
        System.out.println("Enter your message: ");
        Scanner in =  new Scanner(System.in);
        message = in.nextLine();

        for (int i = 0; i < message.length(); i++){

            if (message.charAt(i) != ' '){ 
                cipher = cipher + thoralf.encrypt((long)(message.charAt(i)) - 64, thoralf.e).toString() + " ";
            }
            else{
                cipher = cipher + thoralf.encrypt((long)space, thoralf.e) + " ";
            }
        }
        System.out.println("Encrypted message: " + cipher);
    }
    else if (ed.equals("D")){
        System.out.println("Enter your cipher text: ");
        Scanner in =  new Scanner(System.in);
        cipher = in.nextLine();

        String [] splitCipher = cipher.split(" ");

        for (String s : splitCipher){
            if (thoralf.decrypt(new BigInteger(s)).intValue() != space){
                decipher = decipher + (char) (thoralf.decrypt(new BigInteger(s)).intValue() + 64);
            }
            else{
                decipher = decipher + " ";
            }
        }
        System.out.println("Decrypted message: " + decipher);

    }
        /*
        long m = 26;
        BigInteger cipher = thoralf.encrypt(m, thoralf.e);
        System.out.println(cipher);
        BigInteger message = thoralf.decrypt(cipher);
        System.out.println(message);
        */
        // System.out.println((thoralf.e * thoralf.d)%((thoralf.p - 1)*(thoralf.q - 1)));
    }
}
