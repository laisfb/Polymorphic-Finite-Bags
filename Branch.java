import java.awt.Color;

class Branch<T> implements PFB<T> {
    
    private T datum;
    private Color color; // RED or BLACK
    private Branch<T> parent;
    
    private Branch<T> leff;
    private Branch<T> right;
    
    Branch(T elem) {
    	this.datum = elem;
    	this.color = Color.BLACK;
    	this.parent = null;
    	
    	this.left = new Leaf<T>();
    	this.right = new Leaf<T>();
    }
    
    Branch(T elem, Branch<T> parent) {
    	this.datum = elem;
    	this.color = Color.BLACK;
    	this.parent = parent;
    	
    	this.left = new Leaf<T>();
    	this.right = new Leaf<T>();
    }
    
    public GSequence seq() {
	return this; //xxxxxxxxxxx
    }
    
    public T here() {
	return this.datum;
    }
    
    public boolean isEmpty() {
	return false;
    }
    
    public GSequence<T> next() {
	return this; //xxxxxxxxxxx
    }

    
    public int howMany(T elem) {
	return 0; //xxxxxxxxxxx
    }
    
    public PFB<T> empty() {
	return new Leaf<T>();
    }
    
    public int cardinality() {
	return (left.cardinality() + right.cardinality() + 1);
    }
    
    public boolean member(T elem) {
	if (elem == this.datum)
	    return true;
	
	else if (elem < this.datum)
	    return left.member(elem);
	    
	else
	    return right.member(elem);
	
    }
    
    // Not considering repeated elements yet
    public PFB<T> add(T elem) {    	
    	// Case 2: The current node's parent P is black, 
    	// so property 4 (both children of every red node 
    	// are black) is not invalidated. In this case, 
    	// the node can be inserted as in the "normal" tree.
    	if(this.parent.color == Color.BLACK) {
    	    if (elem < this.datum)
    	        return left.add(elem, this);
    	        
    	    else if (elem > this.datum)
    	        return right.add(elem, this);
    	}
    	
    	// Case 3: If both the parent P and the uncle U are red.
    	// Then both of them can be repainted black and the 
    	// grandparent G becomes red.
    	...
    	
    	// Case 4: The parent P is red but the uncle U is black.
    	// Also, the current node N is the right child of P, 
    	// and P in turn is the left child of its parent G.
    	...
    	
    }

    public PFB<T> add(T elem, Branch<T> parent) {
    	if (elem < this.datum)
    	    return left.add(elem, this);
    	    
    	else if (elem > this.datum)
    	    return right.add(elem, this);

    }
    
    public PFB<T> remove(T elem) {
	return this; //xxxxxxxxxxx
    }
    
    public PFB<T> union(PFB<T> b) {
	return this; //xxxxxxxxxxx
    }

    public PFB<T> inter(PFB<T> b) {
	return this; //xxxxxxxxxxx
    }

    public PFB<T> diff(PFB<T> b) {
	return this; //xxxxxxxxxxx
    }

    public boolean equal(PFB<T> b) {
	return false; //xxxxxxxxxxx
    }
    
    public boolean subset(PFB<T> b) {
	return false; //xxxxxxxxxxx
    }
}
