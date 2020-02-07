package don.juan.matus.lib.tree.bintree.rotatetree.aa;

import junit.framework.TestCase;
import org.junit.Assert;

public class AATreeTest extends TestCase {

    public void testSkew1() {
        AATree<Character> tree = new AATree<>();
        BinTreeNodeAA<Character> a = new BinTreeNodeAA<>('A', null, null, null);
        BinTreeNodeAA<Character> b = new BinTreeNodeAA<>('B', null, null, null);
        BinTreeNodeAA<Character> l = new BinTreeNodeAA<>('L', a, null, b);
        a.setParent(l);
        b.setParent(l);
        BinTreeNodeAA<Character> r = new BinTreeNodeAA<>('R', null, null, null);
        BinTreeNodeAA<Character> t = new BinTreeNodeAA<>('T', l, null, r);
        r.setParent(t);
        BinTreeNodeAA<Character> p = new BinTreeNodeAA<>('P', null, null, t);
        t.setParent(p);
        BinTreeNodeAA<Character> result = tree.skew(t);
        Assert.assertEquals('L', (char)result.getObjectNode());
        Assert.assertEquals('P', (char)result.getParent().getObjectNode());
        BinTreeNodeAA<Character> l1 = (BinTreeNodeAA<Character>) p.getRight();
        Assert.assertEquals('L', (char)l1.getObjectNode());
        Assert.assertEquals('P', (char)l1.getParent().getObjectNode());
        BinTreeNodeAA<Character> a1 = (BinTreeNodeAA<Character>) l1.getLeft();
        Assert.assertEquals('A', (char)a1.getObjectNode());
        Assert.assertEquals('L', (char)a1.getParent().getObjectNode());
        BinTreeNodeAA<Character> t1 = (BinTreeNodeAA<Character>) l1.getRight();
        Assert.assertEquals('T', (char)t1.getObjectNode());
        Assert.assertEquals('L', (char)t1.getParent().getObjectNode());
        BinTreeNodeAA<Character> b1 = (BinTreeNodeAA<Character>) t1.getLeft();
        Assert.assertEquals('B', (char)b1.getObjectNode());
        Assert.assertEquals('T', (char)b1.getParent().getObjectNode());
        BinTreeNodeAA<Character> r1 = (BinTreeNodeAA<Character>) t1.getRight();
        Assert.assertEquals('R', (char)r1.getObjectNode());
        Assert.assertEquals('T', (char)r1.getParent().getObjectNode());
    }

    public void testSkew2() {
        AATree<Character> tree = new AATree<>();
        BinTreeNodeAA<Character> l = new BinTreeNodeAA<>('L', null, null, null);
        BinTreeNodeAA<Character> t = new BinTreeNodeAA<>('T', l, null, null);
        BinTreeNodeAA<Character> p = new BinTreeNodeAA<>('P', null, null, t);
        t.setParent(p);
        BinTreeNodeAA<Character> result = tree.skew(t);
        Assert.assertEquals('L', (char)result.getObjectNode());
        Assert.assertEquals('P', (char)result.getParent().getObjectNode());
        BinTreeNodeAA<Character> l1 = (BinTreeNodeAA<Character>) p.getRight();
        Assert.assertEquals('L', (char)l1.getObjectNode());
        Assert.assertEquals('P', (char)l1.getParent().getObjectNode());
        BinTreeNodeAA<Character> a1 = (BinTreeNodeAA<Character>) l1.getLeft();
        Assert.assertNull(a1);
        BinTreeNodeAA<Character> t1 = (BinTreeNodeAA<Character>) l1.getRight();
        Assert.assertEquals('T', (char)t1.getObjectNode());
        Assert.assertEquals('L', (char)t1.getParent().getObjectNode());
        BinTreeNodeAA<Character> b1 = (BinTreeNodeAA<Character>) t1.getLeft();
        Assert.assertNull(b1);
        BinTreeNodeAA<Character> r1 = (BinTreeNodeAA<Character>) t1.getRight();
        Assert.assertNull(r1);
    }


}
