package don.juan.matus.lib.collection.sorted.tree.bintree;

@FunctionalInterface
public interface MergeStrategy<T extends Comparable<? extends T>> {

    boolean solveIt(BinTreeNodeInterface<T> left, BinTreeNodeInterface<T> right);

}
