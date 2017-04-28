import java.util.ArrayList;
import java.util.List;

public class NamesNew {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Foo");
        names.add("Bar");
        names.forEach(System.out::println);
    }
}
