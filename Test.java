	import java.util.Random;

public class Test {

    // Helper methods

    public static int randomInt(int max) {
	Random r = new Random();
	return r.nextInt(max + 1);
    }

    public static Character randomChar() {
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	int n = alphabet.length();
	Random r = new Random();
	return Character.valueOf(alphabet.charAt(r.nextInt(n)));
    }

    public static PFB bagOfInt(int size) {
        if (size == 0) {
            return new Leaf();
        } else {
            return bagOfInt(size-1).add(Integer.valueOf(randomInt(100)));
        }
    }

    public static PFB bagOfChar(int size) {
        if (size == 0) {
            return new Leaf();
        } else {
            return bagOfChar(size-1).add(randomChar());
        }
    }

    public static PFB newBag(int size) {
	int coin = randomInt(1);
	if (coin == 0)
	    return bagOfInt(size);
	else
	    return bagOfChar(size);
    }

    public static PFB randomBag() {
	int size = randomInt(20);
	if (size == 0)
	    return new Leaf();
	else
	    return newBag(size);
    }


    // Methods to check properties

    public static void check_howMany() {
	// If it is a member, howMany must be >= 1

	int size = randomInt(20);
	PFB b1 = bagOfInt(size);
	int x = randomInt(100);
	if ( b1.member(x) && b1.howMany(x) <= 0 )
	    throw new RuntimeException("ERROR IN: check_howMany");

	PFB b2 = bagOfChar(size);
	Character c = randomChar();
	if ( b2.member(c) && b2.howMany(c) <=0 )
	    throw new RuntimeException("ERROR IN: check_howMany");
    }

    public static void check_empty() {
	PFB b = randomBag();
	if (!b.empty().isEmpty())
	    throw new RuntimeException("ERROR IN: check_empty");	
    }

    public static void check_isEmpty() {
	int size = randomInt(20);
	PFB b = newBag(size);
	if ( (size == 0 && !b.isEmpty() ) ||
	     (size > 0  &&  b.isEmpty() ) )
	    throw new RuntimeException("ERROR IN: check_isEmpty");
    }
    
    public static void check_cardinality() {
	int size = randomInt(20);
	PFB b = newBag(size);
	if ( b.cardinality() != size )
	    throw new RuntimeException("ERROR IN: check_cardinality");
    }

    public static void check_member() {
	// Must be a member after was add
	// If it is a member, howMany must be >= 1

	PFB b1 = bagOfInt(randomInt(20));
	int x = randomInt(100);
	if ( !b1.add(x).member(x) ||
	     (b1.member(x) && b1.howMany(x) <= 0) )
	    throw new RuntimeException("ERROR IN: check_member");

	PFB b2 = bagOfChar(randomInt(20));
	Character c = randomChar();
	if ( !b2.add(c).member(c) ||
	     (b2.member(c) && b2.howMany(c) <= 0) )
	    throw new RuntimeException("ERROR IN: check_member");
    }

    public static void check_add() {
	PFB b1 = bagOfInt(randomInt(20));
	int x = randomInt(100);
	int y = randomInt(100);
	if ( b1.add(x).cardinality() != (b1.cardinality() + 1) )
	    throw new RuntimeException("ERROR IN: check_add");
	if ( b1.add(x).member(y) && !(b1.member(y) || x == y) )
	    throw new RuntimeException("ERROR IN: check_add");

	PFB b2 = bagOfChar(randomInt(20));
	Character c = randomChar();
	Character d = randomChar();
	if ( b2.add(c).cardinality() != (b2.cardinality() + 1) )
	    throw new RuntimeException("ERROR IN: check_add");
	if ( b2.add(c).member(d) && !(b2.member(d) || c == d) )
	    throw new RuntimeException("ERROR IN: check_add");
    }

    public static void check_remove() {
	// If it was a member, then the cardinality decreases
	// But if not, the cardinality must stay the same
	// And the "howMany" can never go less than 0

	PFB b1 = bagOfInt(randomInt(20));
	int x = randomInt(100);
	int y = randomInt(100);
	if ( (  b1.member(x) && (b1.remove(x).cardinality() != (b1.cardinality() - 1)) ) ||
	     ( !b1.member(x) && (b1.remove(x).cardinality() != b1.cardinality()) ) ||
	     (  b1.remove(x).howMany(x) < 0 ) )
	    throw new RuntimeException("ERROR IN: check_remove");

	if ( b1.remove(x).member(y) )
	    if (x == y && (b1.remove(x).howMany(y) != (b1.howMany(y) - 1)))
		throw new RuntimeException("ERROR IN: check_remove");

	PFB b2 = bagOfChar(randomInt(20));
	Character c = randomChar();
	Character d = randomChar();
	if ( (  b2.member(c) && (b2.remove(c).cardinality() != (b2.cardinality() - 1)) ) ||
	     ( !b2.member(c) && (b2.remove(c).cardinality() != b2.cardinality()) ) ||
	     (  b2.remove(c).howMany(c) < 0 ) )
	    throw new RuntimeException("ERROR IN: check_remove");

	if ( b2.remove(c).member(d) )
	    if (c == d && (b2.remove(c).howMany(d) != (b2.howMany(d) - 1)))
		throw new RuntimeException("ERROR IN: check_remove");
    }

    public static void check_union() {
	int size1 = randomInt(20);
	int size2 = randomInt(20);
	PFB b1 = bagOfInt(size1);
	PFB b2 = bagOfInt(size2);

	// Since it is a multiset, the cardinality of the union
	// is the sum of both cardinalities
	
	if (b1.union(b2).cardinality() != (b1.cardinality() + b2.cardinality())) {
	    throw new RuntimeException("ERROR IN: check_union");
	}

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
