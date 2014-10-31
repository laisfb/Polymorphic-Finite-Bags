public class Test {

    public static void testLeaf() {
        Leaf l = new Leaf();
        
        //System.out.println("Color: " + l.color);
        System.out.println("Seq: " + l.seq());
        System.out.println("hasNext: " + l.hasNext());
        //System.out.println("here: " + l.here());
        //System.out.println("next: " + l.next());
        
        System.out.println("howMany: " + l.howMany(10));
        System.out.println("empty: " + l.empty());
        System.out.println("isEmpty: " + l.isEmpty());
        System.out.println("cardinality: " + l.cardinality());
        System.out.println("member: " + l.member(20));
 
        System.out.println("add: " + l.add(20));
        //System.out.println("add w/ parent: " + l.add(20, new Branch(10)));
        //System.out.println("remove: " + l.remove(10));
        
        System.out.println("union1: " + l.union(l));
        System.out.println("union2: " + l.union(l.add(10)));
        
        System.out.println("inter1: " + l.inter(l));
        System.out.println("inter2: " + l.inter(l.add(10)));
        
        System.out.println("diff1: " + l.diff(l));
        System.out.println("diff2: " + l.diff(l.add(10)));
        
        System.out.println("subset1: " + l.subset(l));
        System.out.println("subset2: " + l.subset(l.add(10)));
    
    }

    public static void testBranch() {
        Branch b = new Branch(20);
        
        //System.out.println("Color: " + b.color);
        System.out.println("Seq: " + b.seq());
        System.out.println("hasNext: " + b.hasNext());
        System.out.println("here: " + b.here());
        System.out.println("next: " + b.next());
        
        System.out.println("howMany1: " + b.howMany(10));
    	System.out.println("howMany2: " + b.howMany(20));
        System.out.println("empty: " + b.empty());
        System.out.println("isEmpty: " + b.isEmpty());
        System.out.println("cardinality1: " + b.cardinality());
        System.out.println("cardinality1: " + b.add(20).cardinality());
        System.out.println("member1: " + b.member(10));
        System.out.println("member2: " + b.member(20));
 
        System.out.println("add1: " + b.add(10));
        System.out.println("add2: " + b.add(20));
        System.out.println("add3: " + b.add(20).add(10));

        System.out.println("remove1: " + b.remove(10));
        System.out.println("remove2: " + b.remove(20));
        System.out.println("remove3: " + b.add(10).remove(10));
        
        System.out.println("union1: " + b.union(b));
        System.out.println("union2: " + b.union(b.add(10)));
        
        System.out.println("inter1: " + b.inter(b.add(10)));
        System.out.println("inter2: " + b.inter(b.add(20)));
        
        System.out.println("diff1: " + b.diff(b));
        System.out.println("diff2: " + b.diff(b.add(10)));
        System.out.println("diff2: " + (b.add(10)).diff(b));

	    System.out.println("equal1: " + b.equal(b));
	    System.out.println("equal2: " + b.equal(b.add(10)));
	    System.out.println("equal3: " + b.equal(b.add(10).remove(10)));
        
        System.out.println("\nsubset1: " + b.subset(b));
        System.out.println("subset2: " + b.subset(b.add(10)));
        System.out.println("subset2: " + (b.add(10)).subset(b));
    }
    
    public static void main(String[] args) {
        System.out.println("Let's get this party started!");
        
        System.out.println("---------- LEAF ----------");
        testLeaf();
        System.out.println("---------- BRANCH ----------");
        testBranch();
    }

}
