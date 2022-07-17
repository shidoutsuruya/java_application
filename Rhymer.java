import java.util.Arrays;

public class Rhymer {
    private static String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return new String(sb);
    }

    public static void main(String[] args) {
        String[] strings = { "hellos", "worlds", "dream" };
        for (String str : strings) {
            str = reverse(str);
            System.out.println(str);
        }
    }
}
