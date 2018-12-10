
public class FaceGraph {

    private int numVertices;
    private Boolean[][] adj;
    private Integer[] hashedname;
    
    public FaceGraph(int numVertices) {
        this.numVertices = numVertices;
        initializeEmptyGraph(numVertices);
    }
    
    public void initializeEmptyGraph(int numVertices) {
        // initialize instance variables
       
        this.hashedname = new Integer[numVertices+1];

        // construct array of bags (using ugly cast due to generics in Bag)
        adj = new Boolean[numVertices][numVertices];

        // construct all the individual (initially empty) bags
        for (int u = 0; u < numVertices; u++) {
            hashedname[u] = -1;
            for (int v = 0; v < numVertices; v++) {
                adj[u][v] = false;
               
            }
        }
    }
    
    public int getNum(){
        return this.numVertices;            
    }
    
    public Integer[] getNames() {
        return this.hashedname;
    }
    
    public Integer getNames(int n) {
        return this.hashedname[n];
    }
    
    public void setName(int n, int name) {
        this.hashedname[n] = name;
    }
    
    public void addEdge(int v, int w) { 
        adj[v][w] = true;
        adj[w][v] = true;
    }
    public boolean checkEdge(int v, int w) {
        return adj[v][w];
    }
    
    public void removeEdge(int v, int w) {
        if(adj[v][w] == true) {
            adj[v][w] = false;
            adj[w][v] = false;
            System.out.println("They weren't friends anyway");
        }
    }
}
