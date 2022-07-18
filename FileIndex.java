import java.io.File;
public class FileIndex 
{
    private FileIndex(){}
    public static void main(String[] args)
    {
        ST<String,SET<File>> st=new ST<String,SET<File>>();
        System.out.println("Indexing files");
        for(String file_name:args)
        {
            System.out.println("*"+file_name);
            File file=new File(file_name);
            In in=new In(file);
            while(!in.isEmpty())
            {
                String word=in.readString();
                if(!st.contains(word)) st.put(word,new SET<File>());
                SET<File> set=st.get(word);
                set.add(file);
            }
        }
        while(!StdIn.isEmpty())
        {
            String query=StdIn.readString();
            if(st.contains(query))
            {
                SET<File> set=st.get(query);
                for(File file:set)
                {
                    System.out.println("$"+file.getName());
                }
            }
        }
    }
}
