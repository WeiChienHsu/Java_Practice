import java.util.*;

public class setMethod {
    public static void main(String[] args) {

        SetUsed set = new SetUsed();
        set.setA(new TreeSet<>(Arrays.asList(0,2,4,6,8)));
        set.setB(new TreeSet<Integer>(Arrays.asList(0,1,2,3,4)));
        System.out.println("SetA: " + set.getA());
        System.out.println("SetB: " + set.getB());
        System.out.println("Union: ");
        set.union(set.getA(),set.getB());
        System.out.println("Intersection: ");
        set.intersection(set.getA(),set.getB());
        System.out.println("Difference: ");
        set.difference(set.getA(),set.getB());
        System.out.println("Reverse: ");
        set.reverse(set.getA());
    }
}

class SetUsed {
    private Set<Integer> a;
    private Set<Integer> b;

    public Set<Integer> getA() {
        return a;
    }

    public Set<Integer> getB() {
        return b;
    }

    public void setA(Set<Integer> a) {
        this.a = a;
    }

    public void setB(Set<Integer> b) {
        this.b = b;
    }

    public void union(Set a, Set b) {
        Set<Integer> c = new HashSet<>(a);
        c.addAll(b);
        System.out.println(c);
    }

    public void intersection(Set a, Set b) {
        Set<Integer> d = new HashSet<>(a);
        d.retainAll(b);
        System.out.println(d);
    }

    public void difference(Set a, Set b) {
        Set<Integer> e = new HashSet<>(a);
        e.removeAll(b);
        System.out.println(e);
    }

    public void reverse(Set a) {
        List<Integer> list = new ArrayList<>(a);
        java.util.Collections.reverse(list);
        System.out.println(list);
    }
}


