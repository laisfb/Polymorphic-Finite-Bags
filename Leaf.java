class Leaf<T> implements PFB<T> {

    Leaf() {}
    
    public GSequence seq() {
	return this;
    }
    
    public boolean isEmpty() {
	return true;
    }
    
    // here() is not supposed to be
    // called unless isEmpty is false
    public T here() {
	throw new RuntimeException("There are no elements in this bag");
    }
    
    // next() is not supposed to be
    // called unless isEmpty is false
    public GSequence<T> next() {
	throw new RuntimeException("There are nothing after this bag");
    }

    
    public int howMany(T elem) {
	return 0;
    }
    
    public PFB<T> empty() {
	return this;
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
}
