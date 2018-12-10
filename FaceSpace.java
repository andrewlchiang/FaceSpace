
public class FaceSpace{
    
    private FaceGraph graph;
    private int numVertices;
    private FaceSpaceHashTable<String, Integer> hs;

    public FaceSpace() {
        this.graph = new FaceGraph(0);
        this.numVertices = 0;
        this.hs = new FaceSpaceHashTable<String, Integer>(16);
    }
    public int getNum(){
        System.out.println(this.numVertices);
        return this.numVertices;

                
    }
    
    public String getNames(int n) {
        return this.hs.getKey(this.graph.getNames(n));
    }
    
    public void addUser(String name) {
        ++numVertices;
        Integer n = hs.hash(name);
        hs.put(name, n);
        this.graph.setName(numVertices, n);
        FaceGraph newgraph = new FaceGraph(numVertices);
        for (int v = 0; v < numVertices - 1; v++) {
            newgraph.setName(v, graph.getNames(v));
            for (int w = 0; w < numVertices - 1; w++) {
                if(graph.checkEdge(v, w)) {
                    newgraph.addEdge(v, w);
                    newgraph.addEdge(w, v);
                }
            }
        }
        System.out.println("Gabe Newell is added");
        graph = newgraph;
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
    
    
    public static void main(String[] args) {
        
    }

    

}
