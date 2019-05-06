package don.juan.matus.lib.bintree.mergeabletree.cartesian;

import junit.framework.TestCase;

public class CartesianBinTreeTest extends TestCase {

    public void testMerge() {

        CartesianBinTree ct = new CartesianBinTree();

        BinTreeNodeWithPriorityInNode<Long> leftTree = new BinTreeNodeWithPriorityInNode<Long>(2L, null, null, null);
        leftTree.setPriority(10.0d);
        leftTree.setLeft(new BinTreeNodeWithPriorityInNode<>(1L, null, leftTree, null));
        ((BinTreeNodeWithPriorityInNode<Long>)leftTree.getLeft()).setPriority(9.0d);
        leftTree.setRight(new BinTreeNodeWithPriorityInNode<>(3L, null, leftTree, null));
        ((BinTreeNodeWithPriorityInNode<Long>)leftTree.getRight()).setPriority(8.0d);

        BinTreeNodeWithPriorityInNode<Long> rightTree = new BinTreeNodeWithPriorityInNode<Long>(5L, null, null, null);
        rightTree.setPriority(7.0d);
        rightTree.setLeft(new BinTreeNodeWithPriorityInNode<>(4L, null, rightTree, null));
        ((BinTreeNodeWithPriorityInNode<Long>)rightTree.getLeft()).setPriority(6.0d);
        rightTree.setRight(new BinTreeNodeWithPriorityInNode<>(6L, null, rightTree, null));
        ((BinTreeNodeWithPriorityInNode<Long>)rightTree.getRight()).setPriority(5.0d);

        BinTreeNodeWithPriorityInNode<Long> result = (BinTreeNodeWithPriorityInNode<Long> ) ct.merge(new MergeableCartesianBinTree.MergeParts(leftTree, rightTree));

        assertEquals("result.getObjectNode() != 2L", 2L, (long) result.getObjectNode());
        assertEquals("result.getLeft().getObjectNode() != 1L", 1L, (long) result.getLeft().getObjectNode());
        assertEquals("result.getRight().getObjectNode() != 3L", 3L, (long) result.getRight().getObjectNode());

    }

}
