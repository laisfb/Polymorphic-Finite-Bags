// M-x compile
// java -cp . *.java && java -cp . Test /// Test is the name of the class you are running
// M-x global-set-key
// F5
// compile

import java.util.Random;

public class Test {

    // Helper methods

    public int randomInt(int max) {
    Random r = new Random();
        return r.nextInt(max + 1);
    }

    public Character randomChar() {
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	int n = alphabet.length();
	Random r = new Random();
	return Character.valueOf(alphabet.charAt(r.nextInt(n)));
    }

    public PFB bagOfInt(int size) {
        if (size == 0)
            return new Leaf();
	else
            return bagOfInt(size-1).add(Integer.valueOf(randomInt(100)));
    }

    public PFB bagOfChar(int size) {
        if (size == 0)
            return new Leaf();
	else
            return bagOfChar(size-1).add(randomChar());
    }

    public PFB randomBag(int size) {
	int coin = randomInt(1);
	if (coin == 0)
	    return bagOfInt(randomInt(20));
	else
	    return bagOfChar(randomInt(20));
    }


    // Methods to check properties

    public static void check_howMany() {
    }

    public static void check_empty() {
    }

    public static void check_isEmpty() {
    }
    
    public static void check_cardinality() {
    }

    public static void check_member() {
    }

    public static void check_add() {
    }

    public static void check_remove() {
    }

    public static void check_union() {
    }

    public static void check_inter() {
    }

    public static void check_diff() {
    }

    public static void check_equal() {
    }

    public static void check_subset() {
    }


    public static void check_seq() {
    }

    public static void check_here() {
    }

    public static void check_hasNext() {
    }

    public static void check_next() {
    }


    public static void main(String[] args) {
        System.out.println("Let's get this party started!");
        
	// Check properties of Polymorphic Finite Bag
        check_howMany();
	check_empty();
	check_isEmpty();
	check_cardinality();
	check_member();
	check_add();
	check_remove();
	check_union();
	check_inter();
	check_diff();
	check_equal();
	check_subset();

	// Check properties of Sequence
	check_seq();
	check_here();
	check_hasNext();
	check_next();

	// Check properties of Red-black Tree
	// ...
    }

}
