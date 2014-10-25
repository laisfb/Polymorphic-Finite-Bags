public class Test {

    public static void testLeaf() {
        Leaf l = new Leaf();
        
        System.out.println("Color: " + l.color);
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
        System.out.println("add w/ parent: " + l.add(20, new Branch(10)));
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
    
    public static void main(String[] args) {
        Branch b = new Branch(20);
 
        System.out.println("Let's get this party started!");
        
        testLeaf();
        //testBranch();
    }

}
