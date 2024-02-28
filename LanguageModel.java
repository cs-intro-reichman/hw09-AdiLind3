import java.util.HashMap;
import java.util.Random;

public class LanguageModel {

    // The map of this model.
    // Maps windows to lists of charachter data objects.
    HashMap<String, List> CharDataMap;
    
    // The window length used in this model.
    int windowLength;
    
    // The random number generator used by this model. 
	private Random randomGenerator;

    /** Constructs a language model with the given window length and a given
     *  seed value. Generating texts from this model multiple times with the 
     *  same seed value will produce the same random texts. Good for debugging. */
    public LanguageModel(int windowLength, int seed) {
        this.windowLength = windowLength;
        randomGenerator = new Random(seed);
        CharDataMap = new HashMap<String, List>();
    }

    /** Constructs a language model with the given window length.
     * Generating texts from this model multiple times will produce
     * different random texts. Good for production. */
    public LanguageModel(int windowLength) {
        this.windowLength = windowLength;
        randomGenerator = new Random();
        CharDataMap = new HashMap<String, List>();
    }

    /** Builds a language model from the text in the given file (the corpus). */
	public void train(String fileName) {
		// Your code goes here
	}

    // Computes and sets the probabilities (p and cp fields) of all the
	// characters in the given list. */
	public void calculateProbabilities(List probs) {				
		// Your code goes here
        int charsum =0;
        double cphelper = 0;
        //take the list and sum up how many chars we have in order to calculate p and cp
        for(int i = 0; i < probs.getSize(); i++)
        {
            CharData temp = probs.get(i);
            charsum = charsum + temp.count;
        }
        //calculate p and cp
        for(int j=0 ; j < probs.getSize(); j++)
        {
            CharData temp = probs.get(j);
            temp.p = (double) temp.count / charsum; //put the p value of each char by taking the count of exist and divison of total numchars
            temp.cp = (double) (cphelper + temp.p);
            cphelper = cphelper + temp.p;
        }
	}

    // Returns a random character from the given probabilities list.
    
	public char getRandomChar(List probs) {
		// Your code goes here
        double r = Math.random();
        for(int i = 0; i < probs.getSize(); i++)
        {
            if(probs.get(i).cp > r)
            {
                return probs.get(i).chr;
            }
        }
        return probs.get(probs.getSize()-1).chr; //in ideal world this command will never happened
	}
    

    /**
	 * Generates a random text, based on the probabilities that were learned during training. 
	 * @param initialText - text to start with. If initialText's last substring of size numberOfLetters
	 * doesn't appear as a key in Map, we generate no text and return only the initial text. 
	 * @param numberOfLetters - the size of text to generate
	 * @return the generated text
	 */

     
	public String generate(String initialText, int textLength) {
		// Your code goes here
        return "";
	}


    /** Returns a string representing the map of this language model. */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String key : CharDataMap.keySet()) {
			List keyProbs = CharDataMap.get(key);
			str.append(key + " : " + keyProbs + "\n");
		}
		return str.toString();
	}

    public static void main(String[] args) {
		// Your code goes here
        String test = " eettimmoc";
        List tlist = new List();
        for(int i = 0; i < test.length(); i++)
        {
            tlist.update(test.charAt(i));
        }
        LanguageModel a = new LanguageModel(0);
        a.calculateProbabilities(tlist);
        System.out.println(a);
    }
}
