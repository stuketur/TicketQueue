import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * arrayListJoin:
 *      Meant as a template because we can't overload a method by changing the generic type
 *      Must create a local version
 */
public class Util {
    static String arrayListJoin(List<Integer> list, String sep) {
        return list.stream().map(Object::toString).collect(Collectors.joining(sep));
    }

/*    public static void arrayListJoin(int[] arr, String sep) {
        Arrays.stream(arr).map(Object::toString).collect(Collectors.joining(sep));
    } */
}
