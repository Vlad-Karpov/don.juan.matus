package don.juan.matus.lib.tree.bintree.rotatetree.splay;

import don.juan.matus.lib.tree.bintree.BinTreeInterface;
import don.juan.matus.lib.tree.bintree.BinTreeNodeInterface;
import junit.framework.TestCase;

import java.util.Arrays;

public class SplayTreeTest extends TestCase {

    public void testRemove() {
        SplayTree<Long> btLng = new SplayTree<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 13L, 18L, 16L, 20L, 17L, 19L, 11L};
        btLng.addAll(Arrays.asList(rnd));
        BinTreeNodeInterface<Long> item = btLng.getRoot().getLeft();
        assertEquals("Must bu 11!", 11L, item.getObjectNode().longValue());
        assertEquals("Must bu 10!", 10L, item.getLeft().getObjectNode().longValue());
        assertEquals("Must bu 19!", 19L, item.getRight().getObjectNode().longValue());
        btLng.removeNode(false, BinTreeInterface.seekLoop(2L, btLng.getRoot()), null);
        btLng.removeNode(false, BinTreeInterface.seekLoop(3L, btLng.getRoot()), null);
        btLng.removeNode(false, BinTreeInterface.seekLoop(8L, btLng.getRoot()), null);
        item = btLng.getRoot().getLeft();
        assertEquals("Must bu 7!", 7L, item.getObjectNode().longValue());
        assertEquals("Must bu 1!", 1L, item.getLeft().getObjectNode().longValue());
        assertEquals("Must bu 11!", 11L, item.getRight().getObjectNode().longValue());
        btLng.removeNode(false, BinTreeInterface.seekLoop(13L, btLng.getRoot()), null);
        btLng.removeNode(false, BinTreeInterface.seekLoop(9L, btLng.getRoot()), null);
        btLng.removeNode(false, BinTreeInterface.seekLoop(18L, btLng.getRoot()), null);
        item = btLng.getRoot().getLeft();
        assertEquals("Must bu 17!", 17L, item.getObjectNode().longValue());
        assertEquals("Must bu 16!", 16L, item.getLeft().getObjectNode().longValue());
        assertEquals("Must bu 19!", 19L, item.getRight().getObjectNode().longValue());
        btLng.removeNode(false, BinTreeInterface.seekLoop(11L, btLng.getRoot()), null);
        item = btLng.getRoot().getLeft();
        assertEquals("Must bu 10!", 10L, item.getObjectNode().longValue());
        assertEquals("Must bu 7!", 7L, item.getLeft().getObjectNode().longValue());
        assertEquals("Must bu 16!", 16L, item.getRight().getObjectNode().longValue());
        btLng.removeNode(false, BinTreeInterface.seekLoop(10L, btLng.getRoot()), null);
        item = btLng.getRoot().getLeft();
        assertEquals("Must bu 7!", 7L, item.getObjectNode().longValue());
        assertEquals("Must bu 1!", 1L, item.getLeft().getObjectNode().longValue());
        assertEquals("Must bu 16!", 16L, item.getRight().getObjectNode().longValue());
        btLng.removeNode(false, BinTreeInterface.seekLoop(12L, btLng.getRoot()), null);
        assertEquals("Must bu 7!", 7L, item.getObjectNode().longValue());
        assertEquals("Must bu 1!", 1L, item.getLeft().getObjectNode().longValue());
        assertEquals("Must bu 16!", 16L, item.getRight().getObjectNode().longValue());
        assertEquals("Must bu 15!", 15L, item.getRight().getLeft().getObjectNode().longValue());
        assertEquals("Must bu 17!", 17L, item.getRight().getRight().getObjectNode().longValue());
        btLng.removeNode(false, BinTreeInterface.seekLoop(20L, btLng.getRoot()), null);
        item = btLng.getRoot().getLeft();
        assertEquals("Must bu 16!", 16L, item.getObjectNode().longValue());
        assertEquals("Must bu 7!", 7L, item.getLeft().getObjectNode().longValue());
        assertEquals("Must bu 19!", 19L, item.getRight().getObjectNode().longValue());
    }

}
