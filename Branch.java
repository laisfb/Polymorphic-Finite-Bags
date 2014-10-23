import java.awt.Color;

class Node<T> {
    T datum;
    Color color; // RED or BLACK
    Node<T> parent;
    
    Node(T elem, Color color, Node<t> p) {
    	this.datum = elem;
    	this.color = color;
    	this.parent = p;
    }
}

class Branch<T> implements PFB<T> {
    private Node<T> here;
    private Branch<T> leff;
    private Branch<T> right;
    
    Branch(Node n) {
    	this.here = n;
    	this.left = new Leaf<T>();
    	this.right = new Leaf<T>();
    }
    
    Branch(Node n, Branch<T> left, Branch<T> right) {
    	this.here = n;
    	this.left = left;
    	this.right = right;
    }
    
    public GSequence seq() {
	return this;
    }
    
    public T here() {
	return here.datum;
    }
    
    public boolean isEmpty() {
	return false;
    }
    
    public GSequence<T> next() {
	return this;
    }

    
    public int howMany(T elem) {
	return 0;
    }
    
    public PFB<T> empty() {
	return new Leaf<T>();
    }
    
    public int cardinality() {
	return (left.cardinality() + right.cardinality() + 1);
    }
    
    public boolean member(T elem) {
	if(elem == here.datum)
	    return true;
	
	else if (elem < here.datum)
	    return left.member(elem);
	    
	else
	    return right.member(elem);
	
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
