package don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.aa;

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

    public void testSplit1() {
        AATree<Character> tree = new AATree<>();
        BinTreeNodeAA<Character> p = new BinTreeNodeAA<>('P', null, null, null);
        BinTreeNodeAA<Character> a = new BinTreeNodeAA<>('A', null, null, null);
        BinTreeNodeAA<Character> r = new BinTreeNodeAA<>('R', null, null, null);
        BinTreeNodeAA<Character> t = new BinTreeNodeAA<>('T', a, p, r);
        r.setParent(t);
        a.setParent(t);
        p.setRight(t);
        BinTreeNodeAA<Character> b = new BinTreeNodeAA<>('B', null, r, null);
        r.setLeft(b);
        BinTreeNodeAA<Character> x = new BinTreeNodeAA<>('X', null, r, null);
        r.setRight(x);
        BinTreeNodeAA<Character> result = tree.split(t);
        Assert.assertEquals('P', (char)result.getParent().getObjectNode());
        Assert.assertEquals('R', (char)result.getParent().getRight().getObjectNode());
        Assert.assertEquals('R', (char)result.getObjectNode());
        Assert.assertEquals('T', (char)result.getLeft().getObjectNode());
        Assert.assertEquals('R', (char)result.getLeft().getParent().getObjectNode());
        Assert.assertEquals('X', (char)result.getRight().getObjectNode());
        Assert.assertEquals('R', (char)result.getRight().getParent().getObjectNode());
        Assert.assertEquals('A', (char)result.getLeft().getLeft().getObjectNode());
        Assert.assertEquals('T', (char)result.getLeft().getLeft().getParent().getObjectNode());
        Assert.assertEquals('B', (char)result.getLeft().getRight().getObjectNode());
        Assert.assertEquals('T', (char)result.getLeft().getRight().getParent().getObjectNode());
    }

    public void testSplit2() {
        AATree<Character> tree = new AATree<>();
        BinTreeNodeAA<Character> p = new BinTreeNodeAA<>('P', null, null, null);
        BinTreeNodeAA<Character> r = new BinTreeNodeAA<>('R', null, null, null);
        BinTreeNodeAA<Character> t = new BinTreeNodeAA<>('T', null, p, r);
        r.setParent(t);
        p.setRight(t);
        BinTreeNodeAA<Character> x = new BinTreeNodeAA<>('X', null, r, null);
        r.setRight(x);
        BinTreeNodeAA<Character> result = tree.split(t);
        Assert.assertEquals('P', (char)result.getParent().getObjectNode());
        Assert.assertEquals('R', (char)result.getParent().getRight().getObjectNode());
        Assert.assertEquals('R', (char)result.getObjectNode());
        Assert.assertEquals('T', (char)result.getLeft().getObjectNode());
        Assert.assertEquals('R', (char)result.getLeft().getParent().getObjectNode());
        Assert.assertEquals('X', (char)result.getRight().getObjectNode());
        Assert.assertEquals('R', (char)result.getRight().getParent().getObjectNode());
    }


}
