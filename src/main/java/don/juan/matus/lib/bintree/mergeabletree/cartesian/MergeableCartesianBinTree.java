package don.juan.matus.lib.bintree.mergeabletree.cartesian;

public interface MergeableCartesianBinTree<T extends Comparable<T>> {

    BinTreeNodeCartesianBinTree<T> mergeCartesian(BinTreeNodeCartesianBinTree<T> left, BinTreeNodeCartesianBinTree<T> right);

    void splitCartesian(MergeParts parts, BinTreeNodeCartesianBinTree<T> tree, T key);

    class MergeParts {

        public BinTreeNodeCartesianBinTree leftTree;
        public BinTreeNodeCartesianBinTree rightTree;

        public MergeParts() {
            leftTree = null;
            rightTree = null;
        }

        public MergeParts(BinTreeNodeWithPriorityInNode leftTree, BinTreeNodeWithPriorityInNode rightTree) {
            this.rightTree = rightTree;
            this.leftTree = leftTree;
        }
    }

}
