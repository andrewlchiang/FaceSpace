
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
        else System.out.println("This name has been added already");

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
        return cfs.getIndex() + 1;
    }
    
    public void addFriend(String name1, String name2) {
        int f1 = Search(name1) - 1;
        int f2 = Search(name2) - 1;
        graph.addEdge(f1, f2);
        System.out.println(name1 + " and " + name2 + " are friends now");
    }
    
    public void removeFriend(String name1, String name2) {
        int f1 = Search(name1) - 1;
        int f2 = Search(name2) - 1;
        graph.removeEdge(f1, f2);
        System.out.println(name1 + " and " + name2 + " are no longer friends now");
    }
    
    public static void main(String[] args) {
        
    }

    

}
