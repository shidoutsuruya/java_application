import java.util.Stack;
public class Cycle 
{
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    public Cycle(Graph G)
    {
        if(hasParallelEdges(G))
        {
            return;
        }
        marked=new boolean[G.V()];
        edgeTo=new int[G.V()];
        for(int v=0;v<G.V();v+=1)
        {
            if(!marked[v])
            {
                dfs(G,-1,v);
            }
        }
    }
    private boolean hasSelfLoop(Graph G)
    {
        for(int v=0;v<G.V();v+=1)
        {
            for(int w:G.adj(v))
            {
                if(v==w)
                {
                    cycle=new Stack<Integer>();
                    cycle.push(v);
                    cycle.push(v);
                    return true;
                }
            }
        }
        return false;
    }
    private boolean hasParallelEdges(Graph G)
    {
        marked=new boolean[G.V()];
        for(int v=0;v<G.V();v++)
        {
            for(int w:G.adj(v))
            {
                if(marked[w])
                {
                    cycle=new Stack<Integer>();
                    cycle.push(v);
                    cycle.push(w);
                    cycle.push(v);
                    return true;
                }
                marked[w]=true;    
            }
            for(int w:G.adj(v))
                {
                    marked[w]=false;
                }
        }
        return false;
    }
    public boolean hasCycle()
    {
        return cycle!=null;
    }
    public Iterable<Integer> cycle()
    {
        return cycle;
    }
    private void dfs(Graph G,int u,int v)
    {
        marked[v]=true;
        for(int w:G.adj(v))
        {
            if(cycle!=null)
            {
                return;
            }
            if(!marked[w])
            {
                edgeTo[w]=v;
                dfs(G,v,w);
            }
            else if(w!=u)
            {
                cycle=new Stack<Integer>();
                for(int x=v;x!=w;x=edgeTo[x])
                {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
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
        System.out.println(G.toString());
        Cycle finder=new Cycle(G);
        if(finder.hasCycle())
        {
            for(int v:finder.cycle())
            {
                System.out.print(v+"->");
            }
            System.out.println();
        }
        else 
        {
            System.out.println("Graph is acyclic");
        }
    }



}
