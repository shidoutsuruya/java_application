import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
public class Distinct 
{
    public static int distinct(Comparable[] a)
    {
        if(a.length==0)
        {
            return 0;
        }
        Arrays.sort(a);
        int distinct=1;
        for(int i=1;i<a.length;i++)
        {
            if(a[i].compareTo(a[i-1])!=0)
            {
                distinct+=1;
            }
        }
        return distinct;
    }
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        Random r=new Random();
        int m=Integer.parseInt(in.next());
        int n=Integer.parseInt(in.next());
        int trials=Integer.parseInt(in.next());
        in.close();
        int[] distinct=new int[trials];
        for(int t=0;t<trials;t++)
        {
            Integer[] a=new Integer[n];
            for(int i=0;i<n;i++)
            {
                a[i]=r.nextInt();
            }
            distinct[t]=distinct(a);
        }
        double empirical=StdStats.mean(distinct);
        double alpha=(double) n/m;
        double theoretical=m*(1-Math.exp(-alpha));
        System.out.println(theoretical+":"+empirical);
    }
}
