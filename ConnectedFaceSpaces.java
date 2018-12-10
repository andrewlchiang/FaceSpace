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

    public int traverse(FaceGraph g, int a , int b) {

        boolean[] visited = new boolean[g.getNum()];   // algorithm needs to keep track of visited
        QueueArray<Integer> q = new QueueArray<Integer>();  // and routes to explore in the future...

        visited[a] = true;                        // put source in queue and mark it visited
        q.enqueue(a);

        Boolean[] friends = g.getFriends(this.getIndex());
        int distance = 0;

        while (!q.isEmpty()) {                    // Repeat until queue is empty:
            int v = q.dequeue();                  //    dequeue v from queue
            for(int i=0; i<g.getNum();i++){
                if(friends[i]==true) {
                    if (!visited[i]) {                //      marking them visited as you do so
                        visited[i] = true;
                        System.out.print(i + " ");    // <-- Task
                        q.enqueue(i);
                        distance++;
                    }
                }
            }              //    enqueue all unvisited adjacent vertices

        }

        return distance;

    }

    public int count() {
        return numComponents;
    }
    
    public int id(int v) {
        return id[v];
    }
}