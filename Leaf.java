class Leaf<T extends Comparable> implements PFB<T> {

    public Color color = Color.black;

    Leaf() {}
    
    public GSequence<T> seq() {
	return new EmptySequence<T>();
    }

    public Color getColor() {
	return this.color;
    }

    public void setColor(Color c) {
	this.color = c;
    }

    public int howMany(T elem) {
	return 0;
    }
    
    public PFB<T> empty() {
	return this;
    }
    
    public boolean isEmpty() {
	return true;
    }
    
    public int cardinality() {
	return 0;
    }
    
    public boolean member(T elem) {
	return false;
    }

    public PFB<T> add(T elem) {
	return new Branch<T>(elem);
    }

    public PFB<T> addMany(T elem, int n) {
	return new Branch<T>(elem, n);
    }
    
    public PFB<T> remove(T elem) {
    	return this;
    }
    
    public PFB<T> union(PFB<T> b) {
	return b;
    }

    public PFB<T> inter(PFB<T> b) {
	return this;
    }

    // Everything in the "second" bag except those that are in the current bag
    public PFB<T> diff(PFB<T> b) {
	return b;
    }

    // They are equal if and only if b is empty (meaning it is a Leaf)
    public boolean equal(PFB<T> b) {
	return b.isEmpty();
    }
    
    // This bag is a subset of b if and only if b is empty (meaning it is a Leaf)
    public boolean subset(PFB<T> b) {
	return b.isEmpty();
    }
    


    public String toString() {
	//return "_";
	return "( " + this.color + " )";
    }

    int space = 0;
    public String spacing(int s) {
	this.space = s + 2;
	int i = 0;
	String str = "";
	while(i < space) {
	    str += " ";
	    i++;
	}
	return str;
    }

}
