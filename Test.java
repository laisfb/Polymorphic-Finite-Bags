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

    public static PFB randomBag(int size) {
	int coin = randomInt(1);
	if (coin == 0)
	    return bagOfInt(size);
	else
	    return bagOfChar(size);
    }

    public static PFB randomBag() {
	int size = randomInt(20);
	int coin = randomInt(1);
	if (coin == 0)
	    return bagOfInt(size);
	else
	    return bagOfChar(size);
    }


    // Methods to check properties of a finite bag

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
	PFB b = randomBag(size);
	if ( (size == 0 && !b.isEmpty() ) ||
	     (size  > 0 &&  b.isEmpty() ) )
	    throw new RuntimeException("ERROR IN: check_isEmpty");
    }
    
    public static void check_cardinality() {
	int size = randomInt(20);
	PFB b = randomBag(size);
	if ( b.cardinality() != size )
	    throw new RuntimeException("ERROR IN: check_cardinality");
    }

    public static void check_member() {

	// Must be a member after was add
	// If it is a member, howMany must be >= 1

	PFB b1 = bagOfInt(randomInt(20));
	int x = randomInt(100);
	if ( !b1.add(x).member(x) )
	    throw new RuntimeException("ERROR IN: check_member");

	PFB b2 = bagOfChar(randomInt(20));
	Character c = randomChar();
	if ( !b2.add(c).member(c) )
	    throw new RuntimeException("ERROR IN: check_member");
    }

    public static void check_add() {
	PFB b1 = bagOfInt(randomInt(20));
	int x = randomInt(100);
	int y = randomInt(100);
	if ( (b1.cardinality() + 1) != b1.add(x).cardinality() )
	    throw new RuntimeException("ERROR IN: check_add");

	if ( b1.add(x).member(y) && !(b1.member(y) || x == y) )
	    throw new RuntimeException("ERROR IN: check_add");

	PFB b2 = bagOfChar(randomInt(20));
	Character c = randomChar();
	Character d = randomChar();
	if ( (b2.cardinality() + 1) != b2.add(c).cardinality() )
	    throw new RuntimeException("ERROR IN: check_add");
	
	if ( b2.add(c).member(d) && !(b2.member(d) || c == d) )
	    throw new RuntimeException("ERROR IN: check_add");
    }

    public static void check_addMany() {
	PFB b1 = bagOfInt(randomInt(20));
	int x = randomInt(100);
	int y = randomInt(100);
	int m = randomInt(10);
	if ( (b1.cardinality() + m) !=  b1.addMany(x,m).cardinality())
	    throw new RuntimeException("ERROR IN: check_add");
	if ( b1.addMany(x,m).member(y) && !(b1.member(y) || x == y) )
	    throw new RuntimeException("ERROR IN: check_add");

	PFB b2 = bagOfChar(randomInt(20));
	Character c = randomChar();
	Character d = randomChar();
	int n = randomInt(10);
	if ( (b2.cardinality() + n) != b2.addMany(c,n).cardinality() )
	    throw new RuntimeException("ERROR IN: check_add");
	if ( b2.addMany(c,n).member(d) && !(b2.member(d) || c == d) )
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

	// Since it is a multiset, the cardinality of the union
	// is the sum of both cardinalities

	// And the "howMany" of the union, must be the sum
	// of each "howMany" for every element

	int size1 = randomInt(20);
	int size2 = randomInt(20);
	PFB b1 = bagOfInt(size1);
	PFB b2 = bagOfInt(size2);
	PFB union12 = b1.union(b2);
	int x = randomInt(100);
	
	if ( (union12.cardinality() != (b1.cardinality() + b2.cardinality())) ||
	     ((!b1.member(x) && b2.member(x)) && !union12.member(x)) ) {
	    System.out.println("U: " + union12.cardinality());
	    System.out.println("U: " + (b1.cardinality() + b2.cardinality()));
	    throw new RuntimeException("ERROR IN: check_union");
	}


	PFB b3 = bagOfChar(size1);
	PFB b4 = bagOfChar(size2);
	PFB union34 = b3.union(b4);
	Character c = randomChar();
	
	if ( (union34.cardinality() != (b3.cardinality() + b4.cardinality())) ||
	     ((!b3.member(c) && b4.member(c)) && !union34.member(c)) )
	    throw new RuntimeException("ERROR IN: check_union");

    }

    public static void check_inter() {

	// Test if an element was a member of both bags, 
	// but it's not a member of their intersection

	int size1 = randomInt(20);
	int size2 = randomInt(20);
	PFB b1 = bagOfInt(size1);
	PFB b2 = bagOfInt(size2);
	PFB inter12 = b1.inter(b2);
	int x = randomInt(100);

	if ( (b1.member(x) && b2.member(x)) && !inter12.member(x) )
	    throw new RuntimeException("ERROR IN: check_inter");


	PFB b3 = bagOfChar(size1);
	PFB b4 = bagOfChar(size2);
	PFB inter34 = b3.inter(b4);
	Character c = randomChar();

	if ( (b3.member(c) && b4.member(c)) && !inter34.member(c) )
	    throw new RuntimeException("ERROR IN: check_inter");
	
    }

    public static void check_diff() {

	// If an element is a member of T, but not a member of S
	// it should be a member of  S.diff(T)

	// The cardinality of the difference can't be greater
	// than the cardinality of T

	int size1 = randomInt(20);
	int size2 = randomInt(20);
	PFB b1 = bagOfInt(size1);
	PFB b2 = bagOfInt(size2);
	PFB diff12 = b1.diff(b2);
	int x = randomInt(100);
	
	if ( ((b2.member(x) && !b1.member(x)) && !diff12.member(x)) ||
	     !(diff12.cardinality() <= b2.cardinality()) )
	    throw new RuntimeException("ERROR IN: check_diff");
    

	PFB b3 = bagOfChar(size1);
	PFB b4 = bagOfChar(size2);
	PFB diff34 = b3.diff(b4);
	Character c = randomChar();
	
	if ( ((b4.member(c) && !b3.member(c)) && !diff34.member(c)) ||
	     !(diff34.cardinality() <= b4.cardinality())  )
	    throw new RuntimeException("ERROR IN: check_diff");
    }

    public static void check_equal() {

	// If they are equal, this means the difference between
	// them must be empty

	int size1 = randomInt(20);
	int size2 = randomInt(20);
	PFB b1 = bagOfInt(size1);
	PFB b2 = bagOfInt(size2);

	if ( b1.equal(b2) && !( b1.diff(b2).isEmpty() &&
			        b2.diff(b1).isEmpty() ))
	    throw new RuntimeException("ERROR IN: check_equal");

	PFB b3 = bagOfChar(size1);
	PFB b4 = bagOfChar(size2);

	if ( b3.equal(b4) && !( b3.diff(b4).isEmpty() &&
			        b4.diff(b2).isEmpty() ))
	    throw new RuntimeException("ERROR IN: check_equal");

    }

    public static void check_subset() {

	// T is a subset of S if
	// the difference between T and the
	//  intersection of S and T is empty

	int size1 = randomInt(20);
	int size2 = randomInt(20);
	PFB b1 = bagOfInt(size1);
	PFB b2 = bagOfInt(size2);

	if ( b1.subset(b2) && !( b1.inter(b2).diff(b1).isEmpty() ) )
	    throw new RuntimeException("ERROR IN: check_subset");


	PFB b3 = bagOfChar(size1);
	PFB b4 = bagOfChar(size2);

	if ( b3.subset(b4) && !( b3.inter(b4).diff(b3).isEmpty() ) )
	    throw new RuntimeException("ERROR IN: check_subset");

    }

    // Tests for sequences

    public static void check_here() {
	PFB b = randomBag();
	if ( (!b.seq().hasNext() && b.seq().here() != null ) ||
	     ( b.seq().hasNext() && b.seq().here() == null ) )
	    throw new RuntimeException("ERROR IN: check_here");
    }

    public static void check_hasNext() {

	// If it empty, it can't have "next"
	// If it's not empty, it must have a "next"

	PFB b = randomBag();      
	if (b.isEmpty() ==  b.seq().hasNext())
	    throw new RuntimeException("ERROR IN: check_hasNext");
    }

    public static void check_next() {
	PFB b = randomBag();
	if ( (!b.seq().hasNext() && b.seq().next() != null ) ||
	     ( b.seq().hasNext() && b.seq().next() == null ) )
	    throw new RuntimeException("ERROR IN: check_next");
    }

    public static void check_rbt() {
	int size = randomInt(20);
	PFB b = bagOfInt(size);
	
	if ( b.getColor() != Color.black )
	    throw new RuntimeException("ERROR IN: check_color");

    }

    public static void main(String[] args) {
        System.out.println("Let's get this party started!");
	
	int i;
	for (i=0; i<50; i++) {
	    // Check properties of Polymorphic Finite Bag
	    check_howMany();
	    check_empty();
	    check_isEmpty();
	    check_cardinality();
	    check_member();
	    check_add();
	    check_addMany();
	    check_remove();
	    check_union();
	    check_inter();
	    check_diff();
	    check_equal();
	    check_subset();

	    // Check properties of Sequence
	    check_here();
	    check_hasNext();
	    check_next();

	    // Check properties of Red-black Tree
	    check_rbt();
	}
	
        System.out.println("All tests passed " + i + " times!");
	
	System.out.println(randomBag());

     }

}
