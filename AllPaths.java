import java.util.Stack;
public class AllPaths 
{
    private boolean[] onPath;
    private Stack<Integer> path;
    private int numberOfPaths;
    public AllPaths(Graph G,int s,int t)
    {
        onPath=new boolean[G.V()];
        path=new Stack<Integer>();
        dfs(G,s,t);
    }
    private void dfs(Graph G,int v,int t)
    {
        path.push(v);
        onPath[v]=true;
        if(v==t)
        {
            processCurrentPath();
            numberOfPaths+=1;
        }
        else 
        {
            for(int w:G.adj(v))
            {
                if(!onPath[w])
                {
                    dfs(G,w,t);
                }
            }
        }
        path.pop();
        onPath[v]=false;
    }
    public int numberOfPaths()
    {
        return numberOfPaths;
    }
    private void processCurrentPath()
    {
        Stack<Integer> reverse=new Stack<Integer>();
        for(int v:path)
        {
            reverse.push(v);
        }
        if(reverse.size()>=1)
        {
            System.out.print(reverse.pop());
        }
        while(!reverse.isEmpty())
        {
            System.out.print("-"+reverse.pop());
        }
        System.out.println();
    }
    public static void main(String[] args)
    {
        Graph G = new Graph(7);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 4);
        G.addEdge(2, 5);
        G.addEdge(1, 5);
        G.addEdge(5, 4);
        G.addEdge(3, 6);
        G.addEdge(4, 6);
        StdOut.println(G);
        AllPaths allpaths1 = new AllPaths(G, 0, 6);
        System.out.println("all paths:"+allpaths1.numberOfPaths());
    }


}
