import java.util.ArrayList;
import java.util.List;

public class NamesOld {
    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();
        names.add("Foo");
        names.add("Bar");
        for(String name : names) {
            System.out.println(name);
        }
    }
}
