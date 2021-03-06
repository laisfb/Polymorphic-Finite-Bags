interface Sequence<T extends Comparable> {
    public GSequence<T> seq();
}

interface GSequence<T extends Comparable> {
    public T here();
    public boolean hasNext();
    public GSequence<T> next();
    // If hasNext() returns false, then here()
    // and next() can return anything

    public String toString();
}

// PFB stands for Polymorphic Finite Bags

// A finite bag is also called a multiset,
// and it is like a set, but each element
// may occur many times.

// Polymorphic means is should use generics
// to allow any kind of contents.

// The PFB should be "pure", as in, operations
// on the bag return new bags and do not modify
// the old bag.

// In this case, a red-black tree will be used.
// Since a PFB can have many of the same element,
// the right tree can contain elements greater
// than to the node, and the left tree can 
// contain elements less than the node. When a 
// repeated element is inserted, it will increase
// the counter of that element.

// Also, a PFB can be two things: either a Leaf
// or a Branch. A Leaf is just a representation
// of an empty Branch. A Branch contains a Node,
// a left Branch and a right Branch. A Node is
// a structure that contains the datum, its color
// (either red or black), and a reference to its
// parent (which is a Branch).

enum Color { black , red };

interface PFB<T extends Comparable> extends Sequence<T> {

    // Returns the color of the node
    public Color getColor();

    // Changes the color of the node
    public void setColor(Color c);

    // Method to make printing pretty
    public String spacing(int space);

    // Returns how many of "elem" are in the bag
    public int howMany(T elem);
    
    // --- Properties of the old Finite Set ---
    
    // Returns a fresh empty bag
    public PFB<T> empty();
    
    // Returns if the bag is empty
    public boolean isEmpty();
    
    // Returns the number of elements in the bag
    public int cardinality();
    
    // Determines if elem is in the bag
    public boolean member(T elem);

    // Returns a new bag containing elem and everything in the bag
    public PFB<T> add(T elem);
    public PFB<T> addMany(T elem, int n);

    public PFB<T> add(T elem, Branch<T> parent);
    public PFB<T> addMany(T elem, int n, Branch<T> parent);
    
    // Returns a new bag containing everything in the bag, except elem
    public PFB<T> remove(T elem);
    
    // Returns a new bag containing everything in the current bag and b
    public PFB<T> union(PFB<T> b);

    // Returns a new bag containing everything that is
    // both in the current bag and in b
    public PFB<T> inter(PFB<T> b);

    // Returns a new bag containing everything in b
    // except those that are in the current bag
    public PFB<T> diff(PFB<T> b);

    // Determines if the current bag and b contain the same elements
    public boolean equal(PFB<T> b);
    
    // Determines if the current bag is a subset of b
    public boolean subset(PFB<T> b);

}

// --- Some properties related to the Red-Black Tree
// 1. A node is either red or black
// 2. The root is black
// 3. All leaves are black
// 4. Every red node must have two black child nodes
// 5. Every path from a given node to any of its descendant
//    leaves contains the same number of black nodes
