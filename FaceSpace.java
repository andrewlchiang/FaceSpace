package FaceSpace;
import java.util.Stack;
public class FaceSpace{
    
    private FaceGraph graph;
    private int numVertices;
    private FaceSpaceHashTable<String, Integer> hs;
    private ConnectedFaceSpaces cfs;

    public FaceSpace() {
        this.graph = new FaceGraph(0);
        this.numVertices = 0;
        this.hs = new FaceSpaceHashTable<String, Integer>(16);
    }
    public int getNum(){
        return this.numVertices;            
    }
    
    public String getNames(int n) {
        return this.hs.getKey(this.graph.getNames(n));
    }
    
    public void addUser(String name) {
        Integer n = hs.hash(name);
        if(! hs.put(name, n)) {
            
        ++numVertices;
        FaceGraph newgraph = new FaceGraph(numVertices);
        for (int v = 0; v < numVertices-1; v++) {
            newgraph.setName(v, graph.getNames(v));
            for (int w = 0; w < numVertices-1; w++) {
                if(graph.checkEdge(v, w)) {
                    newgraph.addEdge(v, w);
                    newgraph.addEdge(w, v);
                }
            }
        }
        graph = newgraph;
        this.graph.setName(numVertices-1, n);
        }
        else System.out.println("This user has already been added!");

    }
    public void removeUser(String name) {
        Integer p = hs.hash(name);
        if (hs.contains(name)){
            hs.delete(name);
            numVertices--;
            FaceGraph newgraph = new FaceGraph(numVertices);
            for (int v = 0; v < numVertices; v++) {
                if(v < p)
                    newgraph.setName(v, graph.getNames(v));
                else
                    newgraph.setName(v, graph.getNames(v+1));
                for (int w = 0; w < numVertices; w++) {
                     if(v<p && w<p) {
                         if(graph.checkEdge(v, w)) {
                             newgraph.addEdge(v, w);
                             newgraph.addEdge(w, v);
                         }
                     }
                     else if(v>=p && w<p) {
                         if(graph.checkEdge(v+1, w)) {
                             newgraph.addEdge(v, w);
                             newgraph.addEdge(w, v);
                         }
                     }
                     else if(v<p && w>=p) {
                         if(graph.checkEdge(v, w+1)) {
                             newgraph.addEdge(v, w);
                             newgraph.addEdge(w, v);
                         }
                     }
                     else if(v>=p && w>=p) {
                         if(graph.checkEdge(v+1, w+1)) {
                             newgraph.addEdge(v, w);
                             newgraph.addEdge(w, v);
                         }
                     }
                }
            }
            graph = newgraph;
        }
        else System.out.println("Name not Found");

    }
    
    public int Search(String name1) {
        Integer n = hs.hash(name1);
        this.cfs = new ConnectedFaceSpaces(graph, n);
        if(cfs.getIndex() == -1) {
            System.out.println(name1 + " is not a user");
            return -1;
        }
        Boolean[] friends = this.graph.getFriends(cfs.getIndex());
        Stack<Integer> friendindex = new Stack<Integer>();
        for(int i=0; i<this.getNum();i++){
            if(friends[i]==true) friendindex.push(i);
        }
        if(friendindex.isEmpty()) System.out.println(name1 + " is sad and does not have any friends in this face space");
        else System.out.println(name1 + " has the following friends:");
        while(!friendindex.isEmpty()){
            Integer nameoffriend = friendindex.pop();
            System.out.println(this.getNames(nameoffriend));
        }
        return cfs.getIndex();
    }
    
    public void addFriend(String name1, String name2) {
        Integer n1 = hs.hash(name1);
        this.cfs = new ConnectedFaceSpaces(graph, n1);
        int f1 = cfs.getIndex();
        Integer n2 = hs.hash(name2);
        this.cfs = new ConnectedFaceSpaces(graph, n2);
        int f2 = cfs.getIndex();
        if(f1>=0 && f2>=0) {
            graph.addEdge(f1, f2);
            System.out.println(name1 + " and " + name2 + " are friends now");
        }
        else System.out.println("Not both of them are users");
    }
    
    public void removeFriend(String name1, String name2) {
        Integer n1 = hs.hash(name1);
        this.cfs = new ConnectedFaceSpaces(graph, n1);
        int f1 = cfs.getIndex();
        Integer n2 = hs.hash(name2);
        this.cfs = new ConnectedFaceSpaces(graph, n2);
        int f2 = cfs.getIndex();
        if(f1>=0 && f2>=0) {
            graph.removeEdge(f1, f2);
            System.out.println(name1 + " and " + name2 + " are no longer friends now");
        }
        else System.out.println("Not both of them are users");
    }

    public void findPath(String name1, String name2) {

        Integer originPath = hs.hash(name1);
        this.cfs = new ConnectedFaceSpaces(graph, originPath);
        int f1 = cfs.getIndex();
        Integer endPath = hs.hash(name2);
        this.cfs = new ConnectedFaceSpaces(graph, endPath);
        int f2 = cfs.getIndex();
        if(f1>=0 && f2>=0) {
            System.out.println("The distance between " + name1 + " and " + name2 + " is " + cfs.traverse(graph, originPath, endPath));
        }
        else System.out.println("Not both of them are users");

    }

}
