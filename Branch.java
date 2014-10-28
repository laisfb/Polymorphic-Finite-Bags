
public enum Color { black, red };

class Branch<T extends Comparable> implements PFB<T> {
    public T datum;
    public int count;
    
    public Color color;
    public Branch<T> parent;
    
    public PFB<T> left;
    public PFB<T> right;
    
    Branch(T elem) {
        this.datum = elem;
        this.count = 1;
        
        this.color = Color.black;
        this.parent = null;
        
        this.left = new Leaf<T>();
    	this.right = new Leaf<T>();
    }
    
    Branch(T elem, Branch<T> parent) {
    	this.datum = elem;
        this.count = 1;
        
        this.color = Color.red;
    	this.parent = parent;
    	
    	this.left = new Leaf<T>();
    	this.right = new Leaf<T>();
    }
    
    Branch(T elem, int count, Branch<T> parent, PFB<T> left, PFB<T> right) {
        this.datum = elem;
        this.count = count;
        
        this.color = Color.red;
    	this.parent = parent;
    	
    	this.left = left;
    	this.right = right;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(Color c) {
        this.color = c;
    }
    
    public GSequence seq() {
	return this; //xxxxxxxxxxx
    }
    
    public boolean hasNext() {
    	return !(left.isEmpty() && right.isEmpty());
    }
    
    public T here() {
	return this.datum;
    }
    
    public GSequence<T> next() {
	return this.left.union(this.right);
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
    	return false;
    }
    
    public int cardinality() {
	return (left.cardinality() + right.cardinality() + count); // <<<<<<
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
        if (elem.equals(this.datum)) {
	    Branch b = new Branch(this.datum, this.count+1, this.parent,
                                    this.left, this.right);
	    b.color = this.color;
	    return b;
        }
        
        else {
	    Branch<T> b = (Branch)generateBranch(elem, this);
	    Branch<T> tree = (Branch)add(elem, this);
	    return tree.balanceTree(b);
        }
        

    }

    public PFB<T> generateBranch(T elem, PFB<T> parent) {  // <<<<<<
        if (elem.equals(this.datum)) {
	    Branch b = new Branch(this.datum, this.count+1, this.parent,
                                    this.left, this.right);
	    b.color = this.color;
	    return b;
        }
        
        else if (elem.compareTo(this.datum) < 0) // elem < datum
	    return this.left.generateBranch(elem, this);
    	    
    	else // elem > datum
	    return this.right.generateBranch(elem, this);
    }

    public PFB<T> add(T elem, PFB<T> parent) {  // <<<<<<
        if (elem.equals(this.datum)) {
	    Branch b = new Branch(this.datum, this.count+1, this.parent,
                                    this.left, this.right);
	    b.color = this.color;
	    return b;
        }
        
        else if (elem.compareTo(this.datum) < 0) // elem < datum
	    return new Branch(this.datum, this.count, this, this.left.add(elem, this), right);
    	    
    	else // elem > datum
    	    return new Branch(this.datum, this.count, this, this.left, this.right.add(elem, this));
    }
    
    public PFB<T> remove(T elem) {
        if (elem.compareTo(this.datum) == 0) {
	    if (count >= 0)
	    	this.count--;
	    return this;
        }
        
        else if (elem.compareTo(this.datum) < 0) // elem < datum
    	    return new Branch(this.datum, this.count, this, this.left.remove(elem), this.right);
    	    
    	else // elem > datum
    	    return new Branch(this.datum, this.count, this, this.left, this.right.remove(elem));
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
	    if (b.equals(b.parent.right) && b.parent.equals(g.left)
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
    
    public String toString() {
        return 
        	"( " + this.datum + " , " + this.count + " , " + this.color + " )\n" +
        	"  " + this.left + "\n" +
        	"  " + this.right;
    }
    
}
