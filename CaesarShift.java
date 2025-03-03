import java.util.*;

/* This class enables users to use a caesar shift cipher to encrypt a word or phrase. The caesar
 * shift cipher shifts all the letters in the alphabet a certain number of times to get a letter
 * to correspond to another different letter. Users can use the caesar cipher to encrypt and 
 * decrypt a word.
*/
public class CaesarShift extends Substitution {
    /* Creating a ceasar shift cipher to encode and decode words
     * Exceptions:
     *  => IllegalArgumentException if the given shift is less than or equal to 0
     * Parameters: an int shift which represents the number of "slots" to the left to shift
    */
    public CaesarShift(int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException();
        }

        Queue<Character> caesarShiftCode = new LinkedList<>();


        for (int i = 0; i < super.TOTAL_CHARS; i++) {
            caesarShiftCode.add((char)(super.MIN_CHAR + i));
        }

        for (int i = 0; i < shift; i++) {
            caesarShiftCode.add(caesarShiftCode.remove());
        }

        String code = "";

        for (int i = 0; i < super.TOTAL_CHARS; i++) {
            code += caesarShiftCode.remove();
        }

        super.setEncoding(code);
    }
}
