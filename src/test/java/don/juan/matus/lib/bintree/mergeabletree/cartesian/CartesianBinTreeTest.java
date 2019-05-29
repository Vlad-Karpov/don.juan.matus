package don.juan.matus.lib.bintree.mergeabletree.cartesian;

import don.juan.matus.lib.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.bintree.BinTreeIterator;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.bintree.MergeableBinTree;
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
        MergeableBinTree.MergeParts parts = new MergeableBinTree.MergeParts();
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
        return (BinTreeNodeWithPriorityInNode<Long>) ct.merge(leftTree, rightTree);
    }

    private void someSplit(MergeableBinTree.MergeParts parts, BinTreeNodeCartesianBinTree<Long> tree, Long key) {
        CartesianBinTree ct = new CartesianBinTree();
        ct.split(parts, tree, key);
    }

    public void testAdd1() {
        LongBinTreeNodeWithPriorityInNode[] values = {
                new LongBinTreeNodeWithPriorityInNode(0.8741d, 1L)
                ,new LongBinTreeNodeWithPriorityInNode(0.7317d, 1L)
                ,new LongBinTreeNodeWithPriorityInNode(0.7274d, 1L)
                ,new LongBinTreeNodeWithPriorityInNode(0.7003d, 1L)
        };
        BinTreeCheckPassEventTest thePassEvent = new BinTreeCheckPassEventTest();
        Long ch = 0L, i = 1L;
        CartesianBinTree<LongBinTreeNodeWithPriorityInNode> ct = new CartesianBinTree<>();
        for (LongBinTreeNodeWithPriorityInNode value : values) {
            ct.add(value);
            ch = ct.checkStructure(thePassEvent);
            assertEquals(i.longValue(), ch.longValue());
            i++;
        }
        assertNotNull(ct);
        ch = ct.checkStructure(thePassEvent);
        assertEquals(4L, ch.longValue());
    }

    public void testAdd2() {
        LongBinTreeNodeWithPriorityInNode[] values = {
                new LongBinTreeNodeWithPriorityInNode(0.9360509543078629d, 4392624L)
                ,new LongBinTreeNodeWithPriorityInNode(0.2259664993733157d, 2881184L)
                ,new LongBinTreeNodeWithPriorityInNode(0.7435751784203221d, 9539741L)
                ,new LongBinTreeNodeWithPriorityInNode(0.7741221648952566d, 3081582L)
                ,new LongBinTreeNodeWithPriorityInNode(0.22463054099355506d, 1611473L)
        };
        CartesianBinTree<LongBinTreeNodeWithPriorityInNode> ct = new CartesianBinTree<>();
        for (LongBinTreeNodeWithPriorityInNode value : values) {
            ct.add(value);
        }
        assertNotNull(ct);
        Long ch = ct.checkStructure(new BinTreeCheckPassEventTest());
        assertEquals(5L, ch.longValue());
    }

    public static class BinTreeCheckPassEventTest implements BinTreeCheckPassEvent<LongBinTreeNodeWithPriorityInNode> {

        private String errorMessage;
        private boolean ignor = false;

        public BinTreeCheckPassEventTest() {
        }

        public BinTreeCheckPassEventTest(boolean ignor) {
            this.ignor = ignor;
        }

        @Override
        public void onPass(
                BinTreeIterator<LongBinTreeNodeWithPriorityInNode> leftIterator,
                BinTreeIterator<LongBinTreeNodeWithPriorityInNode> rightIterator,
                BinTreeNodeInterface<LongBinTreeNodeWithPriorityInNode> currentNode,
                BinTreeNodeInterface<LongBinTreeNodeWithPriorityInNode> previousNode) {
            if (!ignor)
                System.out.println(errorMessage
                        + "\r\n Current node: " + currentNode
                        + "\r\n Left node: " + currentNode.getLeft()
                        + "\r\n Right node: " + currentNode.getRight()
                        + "\r\n Previous node: " + previousNode
                        + "\r\n Left iterator: " + leftIterator
                        + "\r\n Right iterator: " + rightIterator);
        }

        @Override
        public void setErrorMessage(String errMsg) {
            errorMessage = errMsg;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }



}
