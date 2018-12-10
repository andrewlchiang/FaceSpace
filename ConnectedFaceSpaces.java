package FaceSpace;

public class ConnectedFaceSpaces {

    private boolean visited[];
    private int[] id;
    private int numComponents;
    private int index;
    
    public int getIndex() {
        return this.index;
    }
    
    public ConnectedFaceSpaces(FaceGraph g, int val) {
        this.index = -1;
        visited = new boolean[g.getNum()];
        id = new int[g.getNum()];
        for (int v = 0; v < g.getNum(); v++) {
            if (! visited[v]) {
                dfs(g,v, val);
                numComponents++;
            }
        }
    }
    
    private void dfs(FaceGraph g, int v, int val) {
        visited[v] = true;
        id[v] = numComponents;
        if(g.getNames(v).equals(val)) this.index = v;
        for (int w = 0; w < g.getNum(); w++) {
           if(g.checkEdge(v, w)){
               if (! visited[w]) {
                   dfs(g,w, val);
               }
           }
        }
    }
    
    public int count() {
        return numComponents;
    }
    
    public int id(int v) {
        return id[v];
    }
}