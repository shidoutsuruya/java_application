import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class SET<Key extends Comparable<Key>> implements Iterable<Key> {
    private TreeSet<Key> set;

    public SET() {
        set = new TreeSet<Key>();
    }

    public SET(SET<Key> x) {
        set = new TreeSet<Key>(x.set);
    }

    public void add(Key key) {
        if (key == null)
            throw new IllegalArgumentException("called add() with a null key");
        set.add(key);
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("called contains with a null key");
        return set.contains(key);
    }
    public void delete(Key key)
    {
        if(key==null) throw new IllegalArgumentException("called delete() with a null key");
        set.remove(key);
    }
    public int size()
    {
        return set.size();
    }
    public boolean isEmpty()
    {
        return size()==0;
    }
    public Iterator<Key> iterator()
    {
        return set.iterator();
    }
    public Key max()
    {
        if(isEmpty()) throw new NoSuchElementException("called max() with empty set");
        return set.last();
    }
    public Key min()
    {
        if(isEmpty()) throw new NoSuchElementException("called min() with empty set");
        return set.first();
    }
    public Key ceiling(Key key)
    {
        if(key==null) throw new IllegalArgumentException("called ceiling() with a null key");
        Key k=set.ceiling(key);
        return k;
    }
    public Key floor(Key key)
    {
        if(key==null) throw new IllegalArgumentException("called floor() with a null key");
        Key k=set.floor(key);
        return k;
    }
    public SET<Key> union(SET<Key> that)
    {
        if(that==null) throw new IllegalArgumentException("called union() with a null argument");
        SET<Key> c=new SET<Key>();
        for(Key x:this)
        {
            c.add(x);
        }
        for(Key x:that)
        {
            c.add(x);
        }
        return c;
    }
    public SET<Key> intersects(SET<Key> that)
    {
        if(that==null) throw new IllegalArgumentException("called intersects() with a null argument");
        SET<Key> c=new SET<Key>();
        if(this.size()<that.size())
        {
            for(Key x:this)
            {
                if(that.contains(x))
                {
                    c.add(x);
                }
            }
        }
        else
        {
            for(Key x:that)
            {
                if(this.contains(x))
                {
                    c.add(x);
                }
            }
        }
        return c;
    }
    @Override
    public boolean equals(Object other)
    {
        if(other==this) return true;
        if(other==null) return false;
        if(other.getClass()!=this.getClass()) return false;
        SET that=(SET) other;
        return this.set.equals(that.set);
    }
    @Override
    public int hashCode()
    {
        throw new UnsupportedOperationException("sets are mutable!");
    }
    @Override
    public String toString()
    {
        String s=set.toString();
        return "{"+s.substring(1,s.length()-1)+"}";
    }
    public static void main(String[] args)
    {
        SET<String> set=new SET<String>();
        set.add("www.cs.princeton.edu");
        set.add("www.cs.princeton.edu");    // overwrite old value
        set.add("www.princeton.edu");
        set.add("art");
        SET<String> set1=new SET<String>();
        set1.add("hello");
        set1.add("art");
        SET<String> set2=set1.intersects(set);
        System.out.println(set2);
        Iterator<String> iter=set.iterator();
        while(iter.hasNext())
        {
             System.out.println(iter.next());
        }
       
    }
}
