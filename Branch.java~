class Branch<T extends Comparable> implements PFB<T> {
    public T datum;
    public int count;
    
    public Color color = Color.red;
    public Branch<T> parent = null;
    
    public PFB<T> left;
    public PFB<T> right;
    
    Branch(T elem) {
        this.datum = elem;
        this.count = 1;
        
        this.left = new Leaf<T>();
    	this.right = new Leaf<T>();
    }

    Branch(T elem, Branch<T> parent) {
    	this.datum = elem;
        this.count = 1;
        
    	this.parent = parent;
    	
    	this.left = new Leaf<T>();
    	this.right = new Leaf<T>();
    }
    
    Branch(T elem, int count) {
        this.datum = elem;
        this.count = count;
                
        this.left = new Leaf<T>();
    	this.right = new Leaf<T>();
    }

    Branch(T elem, int count, Branch<T> parent) {
    	this.datum = elem;
        this.count = count;
        
    	this.parent = parent;
    	
    	this.left = new Leaf<T>();
    	this.right = new Leaf<T>();
    }
    
    Branch(T elem, int count, Color color, Branch<T> parent, PFB<T> left, PFB<T> right) {
        this.datum = elem;
        this.count = count;
        
	this.color = color;
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
	return add(elem, this);
    }

    public PFB<T> addMany(T elem, int n) {
        return addMany(elem, n, this);
    }
    
    public PFB<T> remove(T elem) {
        if (elem.compareTo(this.datum) == 0)
	    if (this.count > 0)
		return new Branch(this.datum, (this.count-1), this.color, this.parent, this.left, this.right);
	    else
		return this;

	if (elem.compareTo(this.datum) < 0) // elem < datum
    	    return new Branch(this.datum, this.count, this.color, this.parent, this.left.remove(elem), this.right);
    	    
    	else // elem > datum
    	    return new Branch(this.datum, this.count, this.color, this.parent, this.left, this.right.remove(elem));
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
	    return (this.diff(b)).isEmpty();
    }
    
    // Is this a subset of b?
    public boolean subset(PFB<T> b) {
	// If (this) is equal to their intersection
	// then (this) is a subset of b
	return (this.equal(this.inter(b)));
    }
    

    // ============ METHODS TO PRINT THE TREE ============

    public String toString() {
        return   
        	"( " + this.datum + " , " + this.count + " , " + this.color + " )\n" +
	    this.left.spacing(this.space) + this.left + "\n" +
	    this.right.spacing(this.space) + this.right;
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
    
    // ============ METHODS FOR THE RED-BLACK TREE ============

    public PFB<T> add(T elem, Branch<T> parent) {
	if (elem.equals(this.datum))
	    return new Branch(this.datum, (this.count+1), this.color, this.parent, this.left, this.right);
	
	else if (elem.compareTo(this.datum) < 0) {
	    /*
	    Branch<T> b = (Branch)this.left.add(elem, this);
	    Branch<T> p = ((Branch)b).parent;
	    p.left = b;

	    b = (Branch)balanceTree(b);

	    return new Branch(p.datum, p.count, p.color, p.parent, b, p.right);
	    */

	    /*
	    Branch<T> b = (Branch)this.left.add(elem, this);
	    Branch<T> p = new Branch(this.datum, this.count, this.color, this.parent, b, this.right);
	    b.parent = p;

	    b = (Branch)balanceTree(b);
	    p = b.parent;

	    return new Branch(p.datum, p.count, p.color, p.parent, b, p.right);
	    */
	    return new Branch(this.datum, this.count, this.color, this.parent, 
			      this.left.add(elem, this), this.right);
	}
	    
	else {
	    /*
	    Branch<T> b = (Branch)this.right.add(elem, this);
	    Branch<T> p = ((Branch)b).parent;
	    p.right = b;

	    b = (Branch)balanceTree(b);

	    return new Branch(p.datum, p.count, p.color, p.parent, p.left, b);
	    */

	    /*
	    Branch<T> b = (Branch)this.right.add(elem, this);
	    System.out.println("B: " + b);
	    Branch<T> p = new Branch(this.datum, this.count, this.color, this.parent, this.left, b);
	    b.parent = p;

	    System.out.println("B: " + b);
	    b = (Branch)balanceTree(b);
	    System.out.println("Balanced b: " + b);
	    p = b.parent;
	    
	    return new Branch(p.datum, p.count, p.color, p.parent, p.left, b);
	    */

	    return new Branch(this.datum, this.count, this.color, this.parent, 
			      this.left, this.right.add(elem, this));
	}
    }

    public PFB<T> addMany(T elem, int n, Branch<T> parent) {
	if (elem.equals(this.datum))
	    return new Branch(this.datum, (this.count+n), this.color,  this.parent, this.left, this.right);
	
	else if (elem.compareTo(this.datum) < 0) {
	    PFB<T> b = balanceTree((Branch)this.left.addMany(elem, n, this));
	    Branch p = ((Branch)b).parent;

	    return new Branch(p.datum, p.count, p.color, p.parent, b, p.right);
	}

	else {
	    PFB<T> b = balanceTree((Branch)this.right.addMany(elem, n, this));
	    Branch p = ((Branch)b).parent;

	    return new Branch(p.datum, p.count, p.color, p.parent, p.left, b);
	}
    }

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
	    b.parent.parent = (Branch)balanceTree(g);
	    return b;
	}

	// Case 4 and 5: Uncle is black (meaning it could be a leaf)
	else{
	    
	    // These conditions are all being evaluated to false
	    // even when they should be true

	    // Left - Right

	    if ( (b.equal(b.parent.right)) && (b.parent.equal(g.left)) ) {

		// Rotate Left b.parent 		        

		PFB<T> saved_left = b.left;
		Branch<T> saved_p = b.parent;

		g.left = b;
		b.parent.right = saved_left;
		b.left = saved_p;
		saved_p = g.parent;

		b = (Branch)b.left;

	    }

	    // Right - Left
	    else if ( (b.equal(b.parent.left)) && (b.parent.equal(g.right)) ) {
		System.out.println("RL");
		PFB<T> saved_right = b.right;
		Branch<T> saved_p = b.parent;

		g.right = b;
		b.parent.left = saved_right;
		b.right = saved_p;
		b.parent = g.parent;

		b = (Branch)b.right;

	    }

	    // Case 5:
	    // Left - Left or Right - right
	    
	    /*
	    b.parent.color = Color.black;
	    g.color = Color.red;
	    
	    if ( b == b.parent.left ) {
		// Right rotation of G
		Branch<T> saved_left = b.parent;
	        g.left = saved_left.right;
		saved_left.right = g;
	    }
	    else {
		System.out.println("oi!");
		// Left rotation of G
		Branch<T> saved_right = b.parent;
	        g.right = saved_right.left;
		saved_right.left = g;
	    }
	    */
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
        else if (this.parent.equal(g.left))
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
