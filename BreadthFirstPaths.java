import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstPaths 
{
    private static final int INFINITY=Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    public BreadthFirstPaths(Graph G,int s)
    {
        marked=new boolean[G.V()];
        distTo=new int[G.V()];
        edgeTo=new int[G.V()];
        validateVertex(s);
        bfs(G,s);
        assert check(G,s);
    }
    private void bfs(Graph G,int s)
    {
        Queue<Integer> q=new LinkedList<Integer>();
        for(int v=0;v<G.V();v++)
        {
            distTo[v]=INFINITY;
        }
        distTo[s]=0;
        marked[s]=true;
        q.add(s);
        while(!q.isEmpty())
        {
            int v=q.poll();
            for(int w:G.adj(v))
            {
                if(!marked[w])
                {
                    edgeTo[w]=v;
                    distTo[w]=distTo[v]+1;
                    marked[w]=true;
                    q.add(w);
                }
            }
        }
    }
    public boolean hasPathTo(int v)
    {
        validateVertex(v);
        return marked[v];
    }
    public int distTo(int v)
    {
        validateVertex(v);
        return distTo[v];
    }
    public Iterable<Integer> pathTo(int v)
    {
        validateVertex(v);
        if(!hasPathTo(v)) return null;
        Stack<Integer> path=new Stack<Integer>();
        int x;
        for(x=v;distTo[x]!=0;x=edgeTo[x])
        {
            path.push(x);
        }
        path.push(x);
        return path;
    }
    private boolean check(Graph G,int s)
    {
        if(distTo[s]!=0)
        {
            System.out.println("dstance of source "+s+" to itself="+distTo[s]);
            return false;
        }
        for(int v=0;v<G.V();v++)
        {
            for(int w:G.adj(v))
            {
                if(hasPathTo(v)!=hasPathTo(w))
                {
                    System.out.println("edge "+v+"-"+w);
                    System.out.println("hasPathTo("+v+")="+hasPathTo(v));
                    System.out.println("hasPathTo"+w+")="+hasPathTo(w));
                    return false;
                }
                if(hasPathTo(v)&&(distTo[w]>distTo[v]+1))
                {
                    System.out.println("edge "+v+"-"+w);
                    System.out.println("distTo["+v+"]="+distTo[v]);
                    System.out.println("distTo["+w+"]="+distTo[w]);
                    return false;
                }
            }
        }
        for(int w=0;w<G.V();w++)
        {
            if(!hasPathTo(w)||w==s) continue;
            int v=edgeTo[w];
            if(distTo[w]!=distTo[v]+1)
            {
                System.out.println("shortest path edge"+v+"-"+w);
                System.out.println("distTo["+v+"]="+distTo[v]);
                System.out.println("distTo["+w+"]="+distTo[w]);
                return false;
            }
        }
        return true;
    }
    private void validateVertex(int v)
    {
        int V=marked.length;
        if(v<0||v>V)
        {
            throw new IllegalArgumentException("vertex "+v+" is not between 0 and "+(V-1));
        }
    }
    private void validateVertices(Iterable<Integer> vertices)
    {
        if(vertices==null)
        {
            throw new IllegalArgumentException("argument is null");
        }
        int count=0;
        for(Integer v:vertices)
        {
            count+=1;
            if(v==null)
            {
                throw new IllegalArgumentException("vertex is null");
            }
            validateVertex(v);
        }
        if(count==0)
        {
            throw new IllegalArgumentException("zero vertices");
        }
    }
    public static void main(String[] args)
    {
        Graph G=new Graph(5);
        int s=3;
        G.addEdge(2, 1);
        G.addEdge(1, 3);
        BreadthFirstPaths bfs=new BreadthFirstPaths(G,s);
        for(int v=0;v<G.V();v++)
        {
            if(bfs.hasPathTo(v))
            {
                System.out.printf("%d to %d (%d):",s,v,bfs.distTo(v));
                for(int x:bfs.pathTo(v))
                {
                    if(x==s) System.out.print(x);
                    else System.out.print("-"+x);
                }
                System.out.println();
            }
            else
            {
                System.out.printf("%d to %d (-): not connected\n",s,v);
            }
            
        }
    }
}
