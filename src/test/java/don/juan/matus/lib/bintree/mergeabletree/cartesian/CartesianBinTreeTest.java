package don.juan.matus.lib.bintree.mergeabletree.cartesian;

import junit.framework.TestCase;

public class CartesianBinTreeTest extends TestCase {

    public void testMerge() {
        BinTreeNodeWithPriorityInNode<Long> result = someMerge();
        assertEquals("result.getObjectNode() != 2L", 2L, (long) result.getObjectNode());
        assertEquals("result.getLeft().getObjectNode() != 1L", 1L, (long) result.getLeft().getObjectNode());
        assertEquals("result.getRight().getObjectNode() != 3L", 3L, (long) result.getRight().getObjectNode());
        assertNull("result.getRight().getLeft() != null", result.getRight().getLeft());
        assertEquals("result.getRight().getRight() != 5", 5L, (long) result.getRight().getRight().getObjectNode());
        assertEquals("result.getRight().getRight().getLeft() != 4", 4L, (long) result.getRight().getRight().getLeft().getObjectNode());
        assertEquals("result.getRight().getRight().getRight() != 6", 6L, (long) result.getRight().getRight().getRight().getObjectNode());
    }

    public void testSplit() {
        BinTreeNodeWithPriorityInNode<Long> tree = someMerge();
        MergeableCartesianBinTree.MergeParts parts = new MergeableCartesianBinTree.MergeParts();
        someSplit(parts, tree, 4L);
        assertEquals("parts.leftTree.getObjectNode() != 2L", 2L, (long) parts.leftTree.getObjectNode());
        assertEquals("parts.leftTree.getLeft().getObjectNode() != 1L", 1L, (long) parts.leftTree.getLeft().getObjectNode());
        assertEquals("parts.leftTree.getRight().getObjectNode() != 3L", 3L, (long) parts.leftTree.getRight().getObjectNode());
        assertEquals("parts.rightTree.getObjectNode() != 5L", 5L, (long) parts.rightTree.getObjectNode());
        assertEquals("parts.rightTree.getLeft().getObjectNode() != 4L", 4L, (long) parts.rightTree.getLeft().getObjectNode());
        assertEquals("parts.rightTree.getRight().getObjectNode() != 6L", 6L, (long) parts.rightTree.getRight().getObjectNode());
    }


    private BinTreeNodeWithPriorityInNode<Long> someMerge() {
        CartesianBinTree ct = new CartesianBinTree();
        BinTreeNodeWithPriorityInNode<Long> leftTree = new BinTreeNodeWithPriorityInNode<Long>(2L, null, null, null);
        leftTree.setPriority(10.0d);
        leftTree.setLeft(new BinTreeNodeWithPriorityInNode<>(1L, null, leftTree, null));
        ((BinTreeNodeWithPriorityInNode<Long>) leftTree.getLeft()).setPriority(9.0d);
        leftTree.setRight(new BinTreeNodeWithPriorityInNode<>(3L, null, leftTree, null));
        ((BinTreeNodeWithPriorityInNode<Long>) leftTree.getRight()).setPriority(8.0d);
        BinTreeNodeWithPriorityInNode<Long> rightTree = new BinTreeNodeWithPriorityInNode<Long>(5L, null, null, null);
        rightTree.setPriority(7.0d);
        rightTree.setLeft(new BinTreeNodeWithPriorityInNode<>(4L, null, rightTree, null));
        ((BinTreeNodeWithPriorityInNode<Long>) rightTree.getLeft()).setPriority(6.0d);
        rightTree.setRight(new BinTreeNodeWithPriorityInNode<>(6L, null, rightTree, null));
        ((BinTreeNodeWithPriorityInNode<Long>) rightTree.getRight()).setPriority(5.0d);
        return (BinTreeNodeWithPriorityInNode<Long>) ct.mergeCartesian(leftTree, rightTree);
    }

    private void someSplit(MergeableCartesianBinTree.MergeParts parts, BinTreeNodeCartesianBinTree<Long> tree, Long key) {
        CartesianBinTree ct = new CartesianBinTree();
        ct.splitCartesian(parts, tree, key);
    }

    public void testAdd() {
        LongBinTreeNodeWithPriorityInNode[] values = {
                new LongBinTreeNodeWithPriorityInNode(0.8741d, 1L)
                ,new LongBinTreeNodeWithPriorityInNode(0.7317d, 1L)
                ,new LongBinTreeNodeWithPriorityInNode(0.7274d, 1L)
                ,new LongBinTreeNodeWithPriorityInNode(0.7003d, 1L)
        };
        CartesianBinTree<LongBinTreeNodeWithPriorityInNode> ct = new CartesianBinTree<>();
        for (LongBinTreeNodeWithPriorityInNode value : values) {
            ct.add(value);
        }
        assertNotNull(ct);
    }

}
