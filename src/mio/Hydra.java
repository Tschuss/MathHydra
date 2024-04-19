package mio;

import java.util.ArrayList;

public class Hydra {

    static int MAX=3;
    static int NEW_HEADS = 1;

    static Node hydra=new Node("0", null);
    public static void main(String[] args) {
        System.out.println();
        System.out.println();
        System.out.println();
        buildHydra();
        killHydra(hydra,1,0);

    }

    static void killHydra (Node n,int newheads, int count) {
        System.out.println("try to kill "+n.id);
        if (!n.isHead()){
            killHydra((Node)n.getChildren()[0], newheads, count);
        } else {
            Node parent=n.parent;
            if (parent != null) {
                System.out.println(((Node) parent.getChildren()[0]).id+ " killed!");
                parent.removeChild();

                Node grandparent=parent.parent;

                if (grandparent!=null) {
                    for (int i=0;i<newheads;i++) {
                        grandparent.addChild(grandparent.id+"."+count+"."+i, grandparent);
                    }
                    System.out.println(newheads+" more...");
                } 
                System.out.println(hydra);
                killHydra(hydra, newheads+1, count+1);
            } else {
                System.out.println("Hydra killed in "+count+" attacks");
            }
        }

    }

    static void buildHydra () {
        Node n = hydra;
        for (int i=0;i<MAX;i++) {
            n.addChild(n.id+"."+i,n);
            n=(Node)n.getChildren()[0];
        }
        System.out.println(hydra);
    }
}

class Node {
    String id;
    Node parent;
    ArrayList<Node> children; 

    public Node (String id, Node parent) {
        this.id=id;
        this.parent = parent;
        children=new ArrayList<>();
    }

    public Object[] getChildren() {
        return  children.toArray();
    }

    public boolean isHead() {
        return children.size()==0;
    }

    public boolean addChild(String num, Node parent) {
        return children.add (new Node(num, parent));
    }

    public boolean removeChild() {
        if (isHead()) {
            return false;
        } else {
            children.remove(0);
            return true;
        }
    }

    @Override
    public String toString() {
        String s=  "{\""+id+"\"" + ":[";
        for (int i=0;i<children.size();i++) {
            s+=children.get(i).toString();
            if (i<children.size()-1) s+=", ";
        }
        return s + "]}";
    }
    
}
