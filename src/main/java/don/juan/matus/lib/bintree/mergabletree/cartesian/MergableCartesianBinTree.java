package don.juan.matus.lib.bintree.mergabletree.cartesian;

public interface MergableCartesianBinTree<T extends Comparable<T>> {

    BinTreeNodeCartesianBinTree<T> merge(MergeParts theParts);

    MergeParts split(BinTreeNodeCartesianBinTree<T> theRoot, T theKey);

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
