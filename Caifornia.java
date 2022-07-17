import java.util.Arrays;
import java.util.Comparator;
public class Caifornia 
{
    public static final Comparator<String> CANDIDATE_ORDER=new CandidateComparator();
    private static class CandidateComparator implements Comparator<String>
    {
        private static String order="zyxwhelsa";
        public int compare(String a,String b)
        {
            int n=Math.min(a.length(),b.length());
            for(int i=0;i<n;i++)
            {
                int aindex=order.indexOf(a.charAt(i));
                int bindex=order.indexOf(b.charAt(i));
                if(aindex<bindex) return -1;
                else if(aindex>bindex) return +1;
            }
            return a.length()-b.length();
        }
    }
    public static void main(String[] args)
    {
        String[] candidates={"dsds","apple","linux","windows"};
        Arrays.sort(candidates,Caifornia.CANDIDATE_ORDER);
        for(String str:candidates)
        {
            System.out.println(str);
        }
        System.out.println("sdsds".indexOf("d"));
    }
    
}
