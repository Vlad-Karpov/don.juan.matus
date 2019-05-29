package don.juan.matus.lib.bintree;

@FunctionalInterface
public interface MergeStrategy<T extends Comparable<T>> {

    boolean solveIt(BinTreeNodeInterface<T> left, BinTreeNodeInterface<T> right);

}
