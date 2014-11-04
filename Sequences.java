class EmptySequence<T extends Comparable> implements GSequence<T> {
    EmptySequence() {}

    // If hasNext() returns false, then here()
    // and next() can return anything

    public GSequence<T> seq() {
	return this;
    }

    public T here() {
	return null;
	//throw new RuntimeException("There are no elements in this sequence");
    }

    public boolean hasNext() {
	return false;
    }

    public GSequence<T> next() {
	return null;
	//throw new RuntimeException("There are no elements after this sequence");
    }

    public String toString() {
	return "";
    }

}

class NonEmptySequence<T extends Comparable> implements GSequence<T> {
    T datum;
    int count;
    GSequence<T> right;
    GSequence<T> left;

    NonEmptySequence(T datum, int count, GSequence<T> left, GSequence<T> right) {
	this.datum = datum;
	this.count = count;
	this.left = left;
	this.right = right;
    }

    public GSequence<T> seq() {
	return this;
    }

    public T here() {
	return this.datum;
    }

    public boolean hasNext() {
	return true;
    }

    public GSequence<T> next() {
	if (count > 1)
	    return new NonEmptySequence(this.datum, (this.count - 1), this.left, this.right);

	else if (this.left instanceof NonEmptySequence)
	    return new NonEmptySequence(((NonEmptySequence)(this.left)).datum,
					 1,
					 this.left.next(), this.right);

	else
	    return this.right;
    }

    public String toString() {
	return (this.datum + " " + this.next());
    }

}
