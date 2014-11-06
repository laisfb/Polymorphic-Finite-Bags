class Branch<T extends Comparable> implements PFB<T> {
    public T datum;
    public int count;
    
    //public enum Color { black, red };
    public Color color = Color.red;
    public Branch<T> parent;
    
    public PFB<T> left;
    public PFB<T> right;
    
    Branch(T elem) {
        this.datum = elem;
        this.count = 1;
        
        this.left = new Leaf<T>();
    	this.right = new Leaf<T>();
    }
    
    Branch(T elem, int count) {
        this.datum = elem;
        this.count = count;
                
        this.left = new Leaf<T>();
    	this.right = new Leaf<T>();
    }
    
    
    Branch(T elem, int count, PFB<T> left, PFB<T> right) {
        this.datum = elem;
        this.count = count;
    
        this.left = left;
    	this.right = right;
    }

    Branch(T elem, Branch<T> parent) {
    	this.datum = elem;
        this.count = 1;
        
    	this.parent = parent;
    	
    	this.left = new Leaf<T>();
    	this.right = new Leaf<T>();
    }
    
    Branch(T elem, int count, Branch<T> parent, PFB<T> left, PFB<T> right) {
        this.datum = elem;
        this.count = count;
        
    	this.parent = parent;
    	
    	this.left = left;
    	this.right = right;
    }

    
    public GSequence<T> seq() {
	return new NonEmptySequence<T>(this.datum, this.count, (this.left).seq(), (this.right).seq());
    }

    public Color getColor() {
	return this.color;
    }

    public void setColor(Color c) {
	this.color = c;
    }


    public int howMany(T elem) {
        if (elem.equals(this.datum)) {
	    return this.count;
        }
        
        else if (elem.compareTo(this.datum) < 0) // elem < datum
	    return this.left.howMany(elem);
        
        else // elem > datum
	    return this.right.howMany(elem);
    }

    
    public PFB<T> empty() {
	return new Leaf<T>();
    }
    
    public boolean isEmpty() {
	return (this.count == 0) &&
	    (this.left.cardinality() == 0) &&
	    (this.right.cardinality() == 0);
    }
    
    public int cardinality() {
	return (left.cardinality() + right.cardinality() + count);
    }
    
    public boolean member(T elem) {
	if (elem.equals(this.datum) && this.count >= 1)
	    return true;
	
	else if (elem.compareTo(this.datum) < 0)
	    return this.left.member(elem);
	    
	else
	    return this.right.member(elem);	
    }
    
    public PFB<T> add(T elem) {
	Branch b = new Branch(10);
	if (elem.equals(this.datum))
	    return new Branch(this.datum, (this.count+1), this.left, this.right);
	
	else if (elem.compareTo(this.datum) < 0)
	    b = new Branch(this.datum, this.count, this.left.add(elem), this.right);
	    
	else
	    b = new Branch(this.datum, this.count, this.left, this.right.add(elem));

	return balanceTree(b);

    }

    public PFB<T> addMany(T elem, int n) {
	if (elem.equals(this.datum))
	    return new Branch(this.datum, (this.count+n), this.left, this.right);
	
	else if (elem.compareTo(this.datum) < 0)
	    return new Branch(this.datum, this.count, this.left.addMany(elem,n), this.right);
	    
	else
	    return new Branch(this.datum, this.count, this.left, this.right.addMany(elem,n));
    }
    
    public PFB<T> remove(T elem) {
        if (elem.compareTo(this.datum) == 0)
	    if (this.count > 0)
		return new Branch(this.datum, (this.count-1), this.left, this.right);
	    else
		return this;

	if (elem.compareTo(this.datum) < 0) // elem < datum
    	    return new Branch(this.datum, this.count, this.left.remove(elem), this.right);
    	    
    	else // elem > datum
    	    return new Branch(this.datum, this.count, this.left, this.right.remove(elem));
    }
    
    public PFB<T> union(PFB<T> b) {
	// ((left U right) U b) + datum
	return (((this.left).union(this.right)).union(b)).addMany(this.datum, this.count);
    }

    // Union of both intersections
    public PFB<T> inter(PFB<T> b) {
	if(b.member(this.datum))
	    return  (b.inter(this.left)).union(b.inter(this.right)).addMany(this.datum, this.count);
	else
	    return (b.inter(this.left)).union(b.inter(this.right));
    }

    // B - this : all elements of B that are not in (this)
    public PFB<T> diff(PFB<T> b) {
	// If datum is in the other bag
	if(b.member(this.datum))
	    // Remove it from B
	    // Find the difference between B and the rest of (this)
	    return ((this.left).union(this.right)).diff(b.remove(this.datum));
	else
	    return ((this.left).union(this.right)).diff(b);
    }

    public boolean equal(PFB<T> b) {
	if(b.cardinality() != this.cardinality())
	    return false;
	else
	    // If heir  difference between them is
	    return (this.diff(b)).isEmpty();
    }
    
    // Is this a subset of b?
    public boolean subset(PFB<T> b) {
	// If (this) is equal to their intersection
	// then (this) is a subset of b
	return (this.equal(this.inter(b)));
    }
    
    public String toString() {
        return 
        	" ( " + this.datum + " , " + this.count + " )\n" +
        	this.left + "\n" + this.right;
    }
    
    
    // ============ METHODS FOR THE RED-BLACK TREE ============


    public PFB<T> balanceTree(Branch<T> b) {
	PFB<T> u = b.uncle();
	Branch<T> g = b.grandparent();
	
	// Case 1: Root node
	if (b.parent == null) {
	    b.color = Color.black;
	    return b;
	}

	// Case 2: Parent is black
	else if (b.parent.color == Color.black) {
	    return b;
	}

	// From now on, there are always two sequential red nodes

	// Case 3: Parent and Uncle are red
	else if (u != null && u.getColor() == Color.red) {
	    b.parent.color = Color.black;
	    u.setColor(Color.black);
	    g.color = Color.red;
	    return balanceTree(g);
	}

	// Case 4 and 5: Uncle is black (meaning it could be a leaf)
	else {
	    // Left - Right
	    if ( (b == b.parent.right) && (b.parent == g.left) ) {
		// Rotate Left
		b.parent.right = b.left;
		b.left = b.parent;
		g.left = b;
	    }

	    // Right - Left
	    else if ( (b == b.parent.left) && (b.parent == g.right) ) {
		// Rotate Right
		b.parent.left = b.right;
		b.right = b.parent;
		g.right = b;
	    }

	    // Left - Left or Right - right
	    else {
		b.parent.color = Color.black;
		g.color = Color.red;

		if ( (b == b.parent.left) && (b.parent == g.left) ) {
		    // Rotate right
		    g.left = parent.right;
		    parent.right = g;
		}
		else {
		    // Rotate Left
		    g.right = parent.left;
		    parent.left = g;
		}
	    }

	    return b;
	}
    }

    public Branch<T> grandparent() {
        if(this.parent != null)
	    return this.parent.parent;
        else
	    return null;
    }
    
    public PFB<T> uncle() {
        Branch<T> g = grandparent();
        if (g == null)
	    return null;
        else if (this.parent.equals(g.left))
	    return g.right;
        else
	    return g.left;
    }
  
    /*
    public Branch<T> rotateLeft() {  
        Branch<T> g = grandparent();
        
        PFB<T> new_left = new Branch(this.parent.datum, this.parent.count, this, 
                                            this.parent.left, this.left);

        return new Branch(this.datum, this.count, g, new_left, this.right);
        
    }
  
    public Branch<T> rotateRight() {
        Branch<T> g = grandparent();
        
        PFB<T> new_right = new Branch(this.parent.datum, this.parent.count, this, 
                                            this.right, this.parent.right);

       return new Branch(this.datum, this.count, g, this.left, new_right);

    }
    
    
    public PFB<T> balanceTree(Branch<T> b) {
        PFB<T> u = uncle();
        Branch<T> g = grandparent();
        
        if (b.parent == null) {
	    b.color = Color.black;
	    return b; // b is a root node
        }
        
        else if (b.parent.color == Color.black)
	    return this;
	    
        else if (u != null && u.getColor() == Color.red) {
	    b.parent.color = Color.black;
	    u.setColor(Color.black);
	    g.color = Color.red;
	    return this.balanceTree(g);
        }
        
        else {
	    if (b.equals(b.parent.right) && b.parent.equals(g.left))
	    	b = b.parent.rotateLeft();
	    else if (b.equals(b.parent.left) && b.parent.equals(g.right))
	    	b = b.parent.rotateRight();
	    
	    b.parent.color = Color.black;
	    g.color = Color.red;
	    if (b.equals(b.parent.left))
	    	return g.rotateRight();
	    else
	    	return g.rotateLeft();
        }        
    }
    */
}
