package don.juan.matus.lib.bintree.mergabletree.cartesian;

import don.juan.matus.lib.bintree.BinTreeBase;


public class CartesianBinTree<T extends Comparable<T>> extends BinTreeBase<T> implements MergableCartesianBinTree<T> {

    public CartesianBinTree() {
        super();
        root = new BinTreeNodeWithPriorityInNode<>(null, null, null, null);
    }

    @Override
    public BinTreeNodeCartesianBinTree<T> merge(MergeParts theParts) {
        BinTreeNodeCartesianBinTree<T> result;
        if (theParts.leftTree == null) {
            return theParts.rightTree;
        }
        if (theParts.rightTree == null) {
            return theParts.leftTree;
        }
        MergeParts mp = new MergeParts();
        if (theParts.leftTree.getPriority() < theParts.rightTree.getPriority()) {
            mp.rightTree = (BinTreeNodeCartesianBinTree) theParts.rightTree.getLeft();
            theParts.rightTree.setLeft(null);
            if (mp.rightTree != null) mp.rightTree.setParent(null);
            result = theParts.rightTree;
            result.setLeft(theParts.leftTree);
            theParts.leftTree.setParent(result);
            mp.leftTree = theParts.leftTree;
            return merge(mp);
        } else {
            mp.leftTree = (BinTreeNodeCartesianBinTree) theParts.leftTree.getRight();
            theParts.leftTree.setRight(null);
            if (mp.leftTree != null) mp.leftTree.setParent(null);
            result = theParts.leftTree;
            result.setRight(theParts.rightTree);
            theParts.rightTree.setParent(result);
            mp.rightTree = theParts.rightTree;
            return merge(mp);
        }
    }

    @Override
    public MergeParts split(BinTreeNodeCartesianBinTree<T> theRoot, T theKey) {
        return null;
    }

}
