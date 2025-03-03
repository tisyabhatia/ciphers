import java.util.*;

/* This class enables the user to use a multiple ciphers on the same input, with the goal of a 
 * stronger encryption than just one cipher. The user can incorporate any Cipher, including
 * substitution caesar shift, and caesar key ciphers.
 */
public class MultiCipher extends Cipher {
    private List<Cipher> ciphers;

    /* Creating a multicipher to encode and decode words
     * Exceptions:
     *  => IllegalArgumentException if the given list of ciphers is empty/ does not exist
     * Parameters: List<Cipher> ciphers: ciphers to be applied to the word
     */
    public MultiCipher(List<Cipher> ciphers) {
        if (ciphers == null || ciphers.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.ciphers = ciphers;
    }

    /* Encrypts the given input using the given ciphers
     * Parameters: String input: the word to be encrypted
     * Returns: a String with the completely encrypted word
    */
    public String encrypt(String input) {
        String cipherOutput = input;
        for (int i = 0; i < ciphers.size(); i++) {
            cipherOutput = ciphers.get(i).encrypt(cipherOutput);
        }

        return cipherOutput;
    }

    /* Decrypts the given input using the given ciphers
     * Parameters: String input: the word to be decrypted
     * Returns: a String with the completely decrypted word
    */
    public String decrypt(String input) {
        String cipherOutput = input;
        for (int i = ciphers.size() - 1; i >= 0; i--) {
            cipherOutput = ciphers.get(i).decrypt(cipherOutput);
        }

        return cipherOutput;
    }
}
