import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
public class FileSorter 
{
    public static void main(String[] args)
    {
        System.out.println("please input file path:");
        Scanner in=new Scanner(System.in);
        String file_path=in.nextLine();
        File directory=new File(file_path);
        if(!directory.exists())
        {
            System.out.println("file not found");
            in.close();
            return;
        }
        if(!directory.isDirectory())
        {
            System.out.println(directory.toString()+" is not a directory");
            in.close();
            return;
        }
        File[] files=directory.listFiles();
        if(files==null)
        {
            System.out.println("could not read files");
            in.close();
            return;
        }
        Arrays.sort(files);
        for(File f:files)
        {
            System.out.println(f.getName());
        }
        in.close();

    }
}
