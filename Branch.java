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

	else if (elem.equals(this.datum) && this.count == 0)
	    return false;
	
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


    // ============ METHODS FOR THE RED-BLACK TREE 2 ============
    
        public PFB<T> add(T elem, Branch<T> parent) {
	    if (elem.equals(this.datum))
		return new Branch(this.datum, (this.count+1), this.color, this.parent, this.left, this.right);
	
	    else if (elem.compareTo(this.datum) < 0) {
		Branch<T> b = new Branch(this.datum, this.count, this.color, this.parent, 
					 ((Branch)this.left.add(elem,this)).balance(), this.right);
		return b.balance();
	    }
	    
	    else {  
		Branch<T> b = new Branch(this.datum, this.count, this.color, this.parent,
					 this.left, ((Branch)this.right.add(elem,this)).balance());
		return b.balance();
	    }
	}

        public PFB<T> addMany(T elem, int n, Branch<T> parent) {
	    if (elem.equals(this.datum))
		return new Branch(this.datum, (this.count+n), this.color, this.parent, this.left, this.right);
	
	    else if (elem.compareTo(this.datum) < 0) {
		Branch<T> b = new Branch(this.datum, this.count, this.color, this.parent, 
					 ((Branch)this.left.addMany(elem,n,this)).balance(), this.right);
		return b.balance();
	    }
	    
	    else {  
		Branch<T> b = new Branch(this.datum, this.count, this.color, this.parent,
					 this.left, ((Branch)this.right.addMany(elem,n,this)).balance());
		return b.balance();
	    }
	}

	public PFB<T> balance() { 
	    
	    // Case 1: root node
	    Branch<T> p = this.parent;
	    if (p == null)
		this.color = Color.black;

	    // Case 2: both children are black
	    if (this.left.getColor() == Color.black && this.right.getColor() == Color.black)
		return this;
	    
	    // Case 3: both children are red
	    else if (this.left.getColor() == Color.red && this.right.getColor() == Color.red) {

		// Case 3a: all grandchildren are black
		Branch<T> L = (Branch)this.left;
		Branch<T> R = (Branch)this.right;
		if ( L.left.getColor() == Color.black && L.right.getColor() == Color.black &&
		     R.left.getColor() == Color.black && R.right.getColor() == Color.black )
		    return this;
		
		// Case 3b: some grandchild is red
		this.left.setColor(Color.black);
		this.right.setColor(Color.black);
		this.color = Color.red;
		return this.balance();
	    }

	    // Case 4: one child is red, the other is black
	    else {
		// Case 4a: left child is red
		if (this.left.getColor() == Color.red) {
		    PFB<T> LR = ((Branch)this.left).right;
		    
		    // Case 4a': its right child is red
		    if (LR.getColor() == Color.red) {
			// Rotate Child Left
			Branch<T> old_left = (Branch)this.left;
			this.left = LR;
			old_left.right = ((Branch)LR).left;
			((Branch)LR).left = old_left;
		    }


		    PFB<T> LL = ((Branch)this.left).left;
		    if (LL.getColor() == Color.red) {
			this.color = Color.red;
			this.left.setColor(Color.black);
			// Rotate Right
			Branch<T> old_left = (Branch)this.left;
			this.left = old_left.right;
			old_left.parent = this.parent;
			old_left.right = this;
			return old_left.balance();
		    }
		    
		}
		
		// Case 4b: right child is red
		if (this.right.getColor() == Color.red ) {
		    PFB<T> RL = ((Branch)this.right).left;
		    
		    if (RL.getColor() == Color.red) {
			// Rotate Child Right
			Branch<T> old_right = (Branch)this.right;
			this.right = RL;
			old_right.left = ((Branch)RL).right;
			((Branch)RL).right = old_right;
		    }
		    

		    PFB<T> RR = ((Branch)this.right).right;
		    if (RR.getColor() == Color.red) {
			this.color = Color.red;
			this.right.setColor(Color.black);
			// Rotate Left
			Branch<T> old_right = (Branch)this.right;
			this.right = old_right.left;
			old_right.parent = this.parent;
			old_right.left = this;
			return old_right.balance();
		    }
		}
		return this;
	    }
	}
}
