import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Transaction implements Comparable<Transaction> {
    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Amount cannot be Nan or infinite");
        }
        this.who = who;
        this.when = when;
        this.amount = amount;
    }
    /* 
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Transaction(String transaction) {
        String[] a = transaction.split("\\s+");
        try {
            when = sdf.parse(a[1]);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("date format is wrong,please check.");
        }
        who = a[0];
        amount = Double.parseDouble(a[2]);
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Amount cannot be Nan or infinite");
        }
    }*/

    public String who() {
        return who;
    }

    public Date when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    public String toString() {
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }

    public int compareTo(Transaction that) {
        return Double.compare(this.amount, that.amount);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (other == null)
            return false;
        if (other.getClass() != this.getClass())
            return false;
        Transaction that = (Transaction) other;
        return (this.amount == that.amount) && (this.who.equals(that.who)) && (this.when.equals(that.when));
    }

    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + ((Double) amount).hashCode();
        return hash;
    }
    public static class WhoOrder implements Comparator<Transaction>
    {
        @Override
        public int compare(Transaction v,Transaction w)
        {
            return v.who.compareTo(w.who);
        }
    }
    public static class WhenOrder implements Comparator<Transaction>
    {
        @Override
        public int compare(Transaction v,Transaction w)
        {
            return v.when.compareTo(w.when);
        }
    }
    public static class HowMuchOrder implements Comparator<Transaction>
    {
        @Override
        public int compare(Transaction v,Transaction w)
        {
            return Double.compare(v.amount,w.amount);
        }
    }
    public static void main(String[] args) throws ParseException
    {
        Transaction[] a=new Transaction[3];
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        a[0]=new Transaction("hello",sdf.parse("2000-09-30"),45.2);
        a[1]=new Transaction("John",sdf.parse("2001-01-2"),90.3);
        a[2]=new Transaction("Gater",sdf.parse("1992-02-3"),90.2);
        System.out.println("unsorted:");
        for(Transaction t:a)
        {
            System.out.println(t);
        }
        System.out.println("sort by name");
        Arrays.sort(a,new Transaction.WhoOrder());
        for(Transaction t:a)
        {
            System.out.println(t);
        }
        System.out.println("sort by date");
        Arrays.sort(a,new Transaction.WhenOrder());
        for(Transaction t:a)
        {
            System.out.println(t);
        }
        System.out.print(a[0].hashCode());
    }

}