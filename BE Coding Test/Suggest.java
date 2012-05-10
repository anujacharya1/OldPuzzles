import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Template for predictive text suggester
 */
public class Suggest {
    /**
     * Please implement this method and any supporting methods/classes
     */
    private static void printSuggestions(File f, String seq) {
        log("Parsing file " + f.getAbsolutePath());
        // TODO
        log("Exact matches for " + seq + ": ");
        // TODO
        log("Prefix matches for " + seq + ": ");
        // TODO
    }

    /**
     * Utility method to convert a word (e.g. "cat") to its numeric
     * representation (e.g. 228). Input must be lowercase. A runtime
     * exception is thrown in case non alphabet characters are provided.
     */
    private static String toNumeric(String word) {
        char[] numeric = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            numeric[i] = convert(c);
        }
        return new String(numeric);
    }

    private static final Map<String, Character> KEYPAD_MAP =
            new HashMap<String, Character>() {
        {   put("abc",  '2');
            put("def",  '3');
            put("ghi",  '4');
            put("jkl",  '5');
            put("mno",  '6');
            put("pqrs", '7');
            put("tuv",  '8');
            put("wxyz", '9');
        }
    };

    private static char convert(char c) {
        for (String s : KEYPAD_MAP.keySet()) {
            if (s.contains(Character.toString(c))) {
                return KEYPAD_MAP.get(s);
            }
        }
        throw new RuntimeException("Can't convert char: " + c);
    }

    public static void main(String... args) {
        if (args.length != 2) {
            log("Usage: java Suggest filename seq");
            System.exit(1);
        }
        File f = new File(args[0]);
        if (!f.exists() || !f.isFile()) {
            log(args[0] + " is not a valid file");
            System.exit(2);
        }
        String seq = args[1];

        printSuggestions(f, seq);
    }

    private static void log(String s) {
        System.out.println(s);
    }
}
