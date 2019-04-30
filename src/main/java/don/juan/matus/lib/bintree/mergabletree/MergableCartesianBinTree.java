package don.juan.matus.lib.bintree.mergabletree;

public interface MergableCartesianBinTree<T extends Comparable<T>> {

    BinTreeNodeCartesianBinTree<T> merge(MergeParts theParts);

    MergeParts split(BinTreeNodeCartesianBinTree<T> theRoot, T theKey);

    class MergeParts {
        public BinTreeNodeCartesianBinTree leftTree;
        public BinTreeNodeCartesianBinTree rightTree;
    }

}
