import java.util.List;

public class NamesNew {
    public static void main(String[] args) {
        var names = List.of("Foo", "Bar");
        names.forEach(System.out::println);
    }
}
