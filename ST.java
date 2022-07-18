import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.Queue;
public class ST<Key extends Comparable<Key>,Value>
{
    private static final int INIT_CAPACITY=2;
    private Key[] keys;
    private Value[] vals;
    private int n=0;
    public ST()
    {
        this(INIT_CAPACITY);
    }
    public ST(int capacity)
    {
        keys=(Key[]) new Comparable[capacity];
        vals=(Value[]) new Object[capacity];
    }
    private void resize(int capacity)
    {
        assert capacity>=n;
        Key[] tempk=(Key[]) new Comparable[capacity];
        Value[] tempv=(Value[]) new Object[capacity];
        for(int i=0;i<n;i++)
        {
            tempk[i]=keys[i];
            tempv[i]=vals[i];
        }
        vals=tempv;
        keys=tempk;
    }
    public int size()
    {
        return n;
    }
    public boolean isEmpty()
    {
        return size()==0;
    }
    public boolean contains(Key key)
    {
        if(key==null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key)!=null;
    }
    public Value get(Key key)
    {
        if(key==null) throw new IllegalArgumentException("argument to get() is null");
        if(isEmpty()) return null;
        int i=rank(key);
        if(i<n&&keys[i].compareTo(key)==0) return vals[i];
        return null;
    }
    public int rank(Key key)
    {
        if(key==null) throw new IllegalArgumentException("argument to rank() is null");
        int low=0,high=n-1;
        while(low<=high)
        {
            int mid=low+(high-low)/2;
            int cmp=key.compareTo(keys[mid]);
            if(cmp<0) high=mid-1;
            else if(cmp>0) low=mid+1;
            else return mid;
        }
        return low;
    }
    public void put(Key key,Value val)
    {
        if(key==null) throw new IllegalArgumentException("first argument to put() is null");
        if(val==null)
        {
            delete(key);
            return;
        }
        int i=rank(key);
        if(i<n&&keys[i].compareTo(key)==0)
        {
            vals[i]=val;
            return;
        }
        if(n==keys.length)
        {
            resize(2*keys.length);
        }
        for(int j=n;j>i;j--)
        {
            keys[j]=keys[j-1];
            vals[j]=vals[j-1];
        }
        keys[i]=key;
        vals[i]=val;
        n+=1;
        assert check();
    }
    public void delete(Key key)
    {
        if(key==null) throw new IllegalArgumentException("argument to delete() is null");
        if(isEmpty()) return;
        int i=rank(key);
        if(i==n||keys[i].compareTo(key)!=0)
        {
            return;
        }
        for(int j=i;j<n-1;j++)
        {
            keys[j]=keys[j+1];
            vals[j]=vals[j+1];
        }
        n-=1;
        keys[n]=null;
        vals[n]=null;
        if(n>0&&n==keys.length/4)
        {
            resize(keys.length/2);
        }
        assert check();
    }
    public void deleteMin()
    {
        if(isEmpty()) throw new NoSuchElementException("symbol table underflow error");
        delete(min());
    }
    public void deleteMax()
    {
        if(isEmpty()) throw new NoSuchElementException("symbol table underflow error");
        delete(max());
    }
    public Key min()
    {
        if(isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0];
    }
    public Key max()
    {
        if(isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[n-1];
    }
    public Key select(int k)
    {
        if(k<0||k>=size())
        {
            throw new IllegalArgumentException("called select() with invalid argument"+k);
        }
        return keys[k];
    }
    public Key floor(Key key)
    {
        if(key==null) throw new IllegalArgumentException("argument to floor() is null");
        int i=rank(key);
        if(i<n&&key.compareTo(keys[i])==0) return keys[i];
        else return keys[i-1];
    }
    public Key ceiling(Key key)
    {
        if(key==null) throw new IllegalArgumentException("argument to ceiling() is null");
        int i=rank(key);
        if(i==n) throw new NoSuchElementException("argument to ceiling() is too large");
        else return keys[i];
    }
    public int size(Key low,Key high)
    {
        if(low==null) throw new IllegalArgumentException("first argument to size() is null");
        if(high==null) throw new IllegalArgumentException("second argument to size() is null");
        if(low.compareTo(high)>0) return 0;
        if(contains(high)) return rank(high)-rank(low)+1;
        else return rank(high)-rank(low);
    }
    public Iterable<Key> keys()
    {
        return keys(min(),max());
    }
    public Iterable<Key> keys(Key low,Key high)
    {
        if(low==null)
        {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if(high==null)
        {
            throw new IllegalArgumentException("second argument to keys() is null");
        }
        Queue<Key> queue=new LinkedList<Key>();
        if(low.compareTo(high)>0) return queue;
        for(int i=rank(low);i<rank(high);i++)
        {
            queue.add(keys[i]);
        }
        if(contains(high))
        {
            queue.add(keys[rank(high)]);
        }
        return queue;
    }
    private boolean check()
    {
        return isSorted()&&rankCheck();
    }
    private boolean isSorted()
    {
        for(int i=1;i<size();i++)
        {
            if(keys[i].compareTo(keys[i-1])<0) 
            {
                return false;
            }
           
        }
        return true;
    }
    private boolean rankCheck()
    {
        for(int i=0;i<size();i++)
        {
            if(i!=rank(select(i)))
            {
                return false;
            }
        }
        for(int i=0;i<size();i++)
        {
            if(keys[i].compareTo(select(rank(keys[i])))!=0)
            {
                return false;
            }
        }
        return true;
    }
    /* 
    public static void main(String[] args)
    {
        ST<String,Integer> st=new ST<String,Integer>();
        st.put("hello",5);
        st.put("232",3);
        for(String s:st.keys())
        {
            System.out.println(st.get(s));
        }
    }*/

}
