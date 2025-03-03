import java.util.*;

/* This class enables the user to use a caesar key cipher, which has one word/ key corresponding
 * to the first few letters of the alphabet, and the rest are corresponding to the remaining 
 * letters. The user can encrypt and decrypt words using the caesar key cipher. 
*/
public class CaesarKey extends Substitution {
    /* Creating a new caesar key cipher to encode and decode words
     * Exceptions: IllegalArgumentException if 
     *  => the key is empty or null 
     *  => if the key has duplicate letters
     *  => if the key is outside the chosen alphabet range to be encoded
     * Parameters: a String key which is the beginning of the cipher's first chosen letter
    */
    public CaesarKey(String key) {
        if (key == null || key.equals("")) {
            throw new IllegalArgumentException();
        } 

        for (int i = 0; i < key.length() - 1; i++) {
            char currentChar = key.charAt(i);
            
            if (key.substring(i + 1).contains(currentChar + "")) {
                throw new IllegalArgumentException();
            }
        
            if ((int) currentChar < super.MIN_CHAR || (int) currentChar > super.MAX_CHAR) {
                throw new IllegalArgumentException();
            }
        }


        String code = "";

        for (int i = 0; i < key.length(); i++) {
            code += key.charAt(i) + "";
        }

        for (int i = 0; i < super.TOTAL_CHARS; i++) {
            if (!code.contains((char) (super.MIN_CHAR + i) + "")) {
                code += (char) (super.MIN_CHAR + i);
            }
        }

        super.setEncoding(code);
    }
}
