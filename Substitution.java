import java.util.*;

/* This class enables the user to use a substitution cipher, which has one letter correspond
 * to a different letter, with the goal of substituting each letter in a word with its 
 * corresponding letter. Users can encrypt and decrypt words using the substitution cipher.
 */
public class Substitution extends Cipher {
    private Map<Character, Character> decodeMap;
    private Map<Character, Character> encodeMap;

    /* Creating a new Substitution cipher to encode and decode words
     * No returns, exceptions, or parameters
     */
    public Substitution() {
        this.decodeMap = new HashMap<>();
        this.encodeMap = new HashMap<>();
    }

    /* Creating a new Substitution cipher using a given code
     * Parameters:
     *  => String encoding: given code in which each letter corresponds to a different one 
     *                      in the alphabet
     * Exceptions: throws IllegalArgumentException if:
     *  => the encoding is null
     *  => the length of the encoding is the same as the defined encodable range
     *  => encoding contains a duplicate character
     *  => if any character in encoding is not within the defined alphabetic range
     */
    public Substitution(String encoding) {
        setMaps(encoding); 
    }

    /* Creating a new Substitution cipher using a given code 
     * Parameters:
     *  => String encoding: given code in which each letter corresponds to a different one 
     *                      in the alphabet
     * Exceptions: throws IllegalArgumentException if:
     *  => the encoding is null
     *  => the length of the encoding is the same as the defined encodable range
     *  => encoding contains a duplicate character
     *  => if any character in encoding is not within the defined alphabetic range
     */
    public void setEncoding(String encoding) {
        setMaps(encoding);   
    }

    /* Determines if any of the rules for the encoding are broken
     * Parameters:
     *  => String encoding: given code in which each letter corresponds to a different one
     *                      in the alphabet
     * Exceptions: throws IllegalArgumentException if:
     *  => the encoding is null
     *  => the length of the encoding is the same as the defined encodable range
     *  => encoding contains a duplicate character
     *  => if any character in encoding is not within the defined alphabetic range
     */
    private void checkExceptions(String encoding) {
        if (encoding == null || encoding.equals("") || encoding.length() != Cipher.TOTAL_CHARS) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < encoding.length(); i++) {
            char currentChar = encoding.charAt(i);

            if (currentChar < Cipher.MIN_CHAR || currentChar > Cipher.MAX_CHAR
                || encoding.substring(i + 1).contains(currentChar + "")) {
                throw new IllegalArgumentException();
            } 
        }
    }

    /* Encrypts the given input using a substitution cipher
     * Exceptions:
     *  => if the letter substitution has not been given, IllegalStateException
     *  => if the input is empty, IllegalArgumentException
     * Returns: a String output, which is the encrypted input 
     * Parameters:
     *  => String input: the word to be encrypted that must be within encodable range in terms
     *     of being the same length and within the alphabetic range
     */
    public String encrypt(String input) {
        if (encodeMap.isEmpty()) {
            throw new IllegalStateException();
        }
        
        if (input == null) {
            throw new IllegalArgumentException();
        }

        String output = "";

        for (int i = 0; i < input.length(); i++) {
            output += encodeMap.get(input.charAt(i));
        }

        return output;
    }

    /* Decrypts the given input using a substitution cipher
     * Exceptions:
     *  => if the letter substitution has not been given, IllegalStateException
     *  => if the input is empty, IllegalArgumentException
     * Returns: a String output, which is the decrypted input
     * Parameters:
     *  => String input: the word to be decrypted that must be within decodable range in terms
     *     of being the same length and within the alphabetic range
     */
    public String decrypt(String input) {
        if (decodeMap.isEmpty()) {
            throw new IllegalStateException();
        }

        if (input == null) {
            throw new IllegalArgumentException();
        }

        String output = "";

        for (int i = 0; i < input.length(); i++) {
            output += decodeMap.get(input.charAt(i));
        }

        return output;
    }

    /* Creates an association between the original letter and the encrypted/ decrypted letter
     * Exceptions: throws IllegalArgumentException if:
     *  => the encoding is null
     *  => the length of the encoding is the same as the defined encodable range
     *  => encoding contains a duplicate character
     *  => if any character in encoding is not within the defined alphabetic range
     * Parameters: String encoding, the shuffled letters associated with the decrypted letter
     */
    private void setMaps(String encoding) {
        checkExceptions(encoding);
        this.decodeMap = new HashMap<>();
        this.encodeMap = new HashMap<>();

        for (int i = 0; i < encoding.length(); i++) {
            encodeMap.put((char)(Cipher.MIN_CHAR + i), encoding.charAt(i));
            decodeMap.put(encoding.charAt(i), (char)(Cipher.MIN_CHAR + i));
        }
    }
}
