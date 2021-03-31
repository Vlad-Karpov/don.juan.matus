package don.juan.matus.lib.util;

public class util {
    public static <E> int compareHelper(Comparable<E> arg1, E arg2) {
        return arg1.compareTo(arg2);
    }

}
