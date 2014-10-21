class Leaf<T> implements PFB<T> {

    Leaf() {}
    //Methods not necessarily implemented correctly
    
    public GSequence seq() {
	return this;
    }
    
    public T here() {
	throw new RuntimeException("No X here");
    }
    
    public boolean isEmpty() {
	return true;
    }
    
    public GSequence<T> next() {
	return this;
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
	return this;
    }
    
    public PFB<T> remove(T elem) {
	return this;
    }
    
    public PFB<T> union(PFB<T> b) {
	return this;
    }

    public PFB<T> inter(PFB<T> b) {
	return this;
    }

    public PFB<T> diff(PFB<T> b) {
	return this;
    }

    public boolean equal(PFB<T> b) {
	return false;
    }
    
    public boolean subset(PFB<T> b) {
	return false;
    }
}
