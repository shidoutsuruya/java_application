import java.util.Collections;
import java.util.Arrays;
import java.util.List;
public class KendallTau 
{
    public static long distance(int[] a,int[] b)
    {
        if(a.length!=b.length)
        {
            throw new IllegalArgumentException("Array dimensions disagree");
        }
        int n=a.length;
        int ainv[]=new int[n];
        for(int i=0;i<n;i++)
        {
            ainv[a[i]]=i;
        }
        Integer[] bnew=new Integer[n];
        for(int i=0;i<n;i++)
        {
            bnew[i]=ainv[b[i]];
        }
        return bnew.length;
    }
    public static Integer[] permutation(int n)
    {
        Integer[] a=new Integer[n];
        for(int i=0;i<n;i++)
        {
            a[i]=i;
        }
        List<Integer> lst=Arrays.asList(a);
        Collections.shuffle(lst);
        Integer[] b=lst.toArray(new Integer[0]);
        return b;
    }
    public static void main(String args[])
    {
        Integer n=5;
        Integer[] a=KendallTau.permutation(n);
        Integer[] b=KendallTau.permutation(n);
        for(int i=0;i<a.length;i++)
        {
             System.out.println("a:"+a[i]);
            System.out.println("b:"+b[i]);
        }
       

    }
}
