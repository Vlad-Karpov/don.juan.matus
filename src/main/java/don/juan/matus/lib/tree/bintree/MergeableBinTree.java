package don.juan.matus.lib.tree.bintree;


public interface MergeableBinTree<T extends Comparable<T>> {

    BinTreeNodeInterface<T> merge(BinTreeNodeInterface<T> left, BinTreeNodeInterface<T> right);

    void split(MergeParts parts, BinTreeNodeInterface<T> tree, T key);

    class MergeParts {

        public BinTreeNodeInterface leftTree;
        public BinTreeNodeInterface rightTree;

        public MergeParts() {
            leftTree = null;
            rightTree = null;
        }

        public MergeParts(BinTreeNodeInterface leftTree, BinTreeNodeInterface rightTree) {
            this.rightTree = rightTree;
            this.leftTree = leftTree;
        }
    }

}
