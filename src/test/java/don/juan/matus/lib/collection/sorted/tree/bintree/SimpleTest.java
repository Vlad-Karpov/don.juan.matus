package don.juan.matus.lib.collection.sorted.tree.bintree;

import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.random.RndBinTree;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.red_black.RedBlackTree;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.scapegoat.ScapegoatTree;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.waight.BinTreeW;
import don.juan.matus.lib.collection.sorted.tree.bintree.tst.TreeMapTst;
import junit.framework.TestCase;
import org.apache.commons.collections.list.TreeList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 * Просто тест.
 */
public class SimpleTest extends TestCase {

    private static int rng = 100;

    private Map<byte[], byte[]> cache = new HashMap<byte[], byte[]>();

    public void testQQ() {
        byte[] qq = {1, 2, 3};
        byte[] rr = {4, 5, 6};
        byte[] result = cache.get(qq);
        if (result == null) {
            synchronized (cache) {
                result = cache.get(qq);
                if (result == null) {
                    result = rr;
                    cache.put(qq, rr);
                }
            }
        }
        System.out.println(result);
    }

    public void testBinTree() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Long rnd;
        for (int i = 0; i < 100; i++) {
            rnd = Math.round(Math.random() * 10000L);
            btLng.add(rnd);
        }
        //
        btLng.checkStructure(new BinTreeCheckPassEventTest<>());
        //
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testRndBinTree() {
        RndBinTree<Long> btLng = new RndBinTree<Long>();
        Long rnd;
        for (int i = 0; i < 100; i++) {
            rnd = Math.round(Math.random() * 10000L);
            btLng.add(rnd);
        }
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testTreeMap1() {
        TreeMapTst<Long, Long> tmRndLng = new TreeMapTst<Long, Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 18L, 16L, 13L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            if (i == 15) {
                System.out.println("!");
            }
            tmRndLng.put(rnd[i], rnd[i]);
            /*
            btLng.checkStructure(new AVLBinTree.BinTreePassEvent<AVLBinTree<Long>.BinTreeNodeBalanceFactor<Long>>() {
                @Override
                public void onPass(AVLBinTree<Long>.BinTreeNodeBalanceFactor<Long> theObject) {
                    System.out.println("shit = " + theObject);
                }
            });
            */
        }
        Iterator<Long> it = tmRndLng.descendingKeySet().iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testAVLBinTree1() {
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 13L, 18L, 16L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            if (i == 12) {
                System.out.println("!");
            }
            btLng.add(rnd[i]);
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest<>());
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testBinTreeRemove() {
        ArrayList<Long> ar = new ArrayList<Long>();
        BinTreeBase<Long> btLng = new BinTreeBase<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 13L, 18L, 16L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            if (i == 12) {
                System.out.println("!");
            }
            btLng.add(rnd[i]);
            ar.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator();
        Iterator<Long> arit = ar.iterator();
        Long next;
        while (arit.hasNext()) {
            next = arit.next();
            if (next == 11L) {
                arit.remove();
            }
            System.out.println(" btLng = " + next);
        }
        int i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next == 2) {
                it.remove();
            }
            if (next == 8) {
                it.remove();
            }
            if (next == 19) {
                it.remove();
            }
            if (next == 20) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(9L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(9L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next >= 4L) it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        if (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("remove " + next);
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(1L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(5L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        //btLng.remove(3L);
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        //
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        it.remove();
    }

    public void testAvlBinTreeRemove() {
        ArrayList<Long> ar = new ArrayList<Long>();
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        //SplayTree<Long> btLng = new SplayTree<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 13L, 18L, 16L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            if (i == 15) {
                System.out.println("!");
            }
            btLng.add(rnd[i]);
            ar.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator();
        Iterator<Long> arit = ar.iterator();
        Long next;
        while (arit.hasNext()) {
            next = arit.next();
            if (next == 11L) {
                arit.remove();
            }
            System.out.println(" btLng = " + next);
        }
        int i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next == 2) {
                it.remove();
                it.remove();
                it.remove();
            }
            if (next == 8) {
                it.remove();
            }
            if (next == 19) {
                it.remove();
            }
            if (next == 20) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(9L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(9L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next == 13L) {
                System.out.println("ququ");
            }
            it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next >= 4L) it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        if (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("remove " + next);
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(1L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(5L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        //btLng.remove(3L);
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        //
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        it.remove();
    }

    public void testBinTreeRemoveDesc() {
        ArrayList<Long> ar = new ArrayList<Long>();
        BinTreeBase<Long> btLng = new BinTreeBase<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 13L, 18L, 16L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            if (i == 12) {
                System.out.println("!");
            }
            btLng.add(rnd[i]);
            ar.add(rnd[i]);
        }
        Iterator<Long> it = btLng.descendingIterator();
        Iterator<Long> arit = ar.iterator();
        Long next;
        while (arit.hasNext()) {
            next = arit.next();
            if (next == 11L) {
                arit.remove();
            }
            System.out.println(" btLng = " + next);
        }
        int i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next == 2) {
                it.remove();
            }
            if (next == 8) {
                it.remove();
            }
            if (next == 19) {
                it.remove();
            }
            if (next == 20) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator(9L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator(9L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next <= 13L) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator();
        if (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("remove " + next);
        }
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator(18L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator(14L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        //btLng.remove(3L);
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        //
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator();
        it.remove();
    }

    public void testBaseBinTree1() {
        BinTreeBase<Long> btLng = new BinTreeBase<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 13L, 18L, 16L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            if (i == 12) {
                System.out.println("!");
            }
            btLng.add(rnd[i]);
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest<>());
        BinTreeIterator<Long> it = (BinTreeIterator<Long>) btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("size: " + it.getSize());
        System.out.println("max level: " + it.getMaxLevel());
        System.out.println("tree size: " + btLng.getSize());
        System.out.println("tree max level: " + btLng.getMaxLevel());


    }

    public void testBinTree1() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 18L, 16L, 13L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testTreeList1() {
        TreeList btLng = new TreeList();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 18L, 16L, 13L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testTreeSet1() {
        TreeSet<Long> btLng = new TreeSet<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 18L, 16L, 13L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testAVLBinTree6() {
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest<>());
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testBinTree6() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testAVLBinTree4() {
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L};
        for (int i = 0; i < 10; i++) {
            btLng.add(rnd[i]);
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest<>());
        for (int j = 0; j < 10; j++) {
            Iterator<Long> it = btLng.iterator(rnd[j]);
            int i = 1;
            while (it.hasNext()) {
                System.out.println("i = " + i + " btLng = " + it.next());
                i++;
            }
            System.out.println("----------------------");
        }
        Iterator<Long> it = btLng.iterator(11L);
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
        it = btLng.iterator(-11L);
        i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
    }

    public void testBinTree4() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L};
        for (int i = 0; i < 10; i++) {
            btLng.add(rnd[i]);
        }
        for (int j = 0; j < 10; j++) {
            Iterator<Long> it = btLng.iterator(rnd[j]);
            int i = 1;
            while (it.hasNext()) {
                System.out.println("i = " + i + " btLng = " + it.next());
                i++;
            }
            System.out.println("----------------------");
        }
        Iterator<Long> it = btLng.iterator(11L);
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
        it = btLng.iterator(-11L);
        i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
    }

    public void testAVLBinTree5() {
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        Long[] rnd = new Long[]{2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 8L, 8L, 8L, 8L, 5L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 3L, 3L, 10L, 20L, 20L, 30L, 30L, 40L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest<>());
        Iterator<Long> it = btLng.iterator(15L);
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
    }

    public void testBinTree5() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Long[] rnd = new Long[]{2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 8L, 8L, 8L, 8L, 5L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 3L, 3L, 10L, 20L, 20L, 30L, 30L, 40L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator(15L);
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
    }

    public void testAVLBinTree2() {
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        removeCheckStructure(btLng);
    }

    public void testRedBlackBinTree2() {
        RedBlackTree<Long> btLng = new RedBlackTree<Long>();
        removeCheckStructure(btLng);
    }

    public void testWBinTree2() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        removeCheckStructure(btLng);
    }

    public void testRndBinTree2() {
        RndBinTree<Long> btLng = new RndBinTree<Long>();
        removeCheckStructure(btLng);
    }

    public void removeCheckStructure(BinTreeBase<Long> btLng) {
        Long rnd;
        for (int i = 0; i < 10000; i++) {
            rnd = Math.round(Math.random() * 10000L);
            btLng.add(rnd);
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest<>());
        btLng.passStraight(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                System.out.println("i = " + i + " btLng = " + theObject);
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }

            @Override
            public void onPassCompleted() {

            }
        });
        System.out.println("----------------------");
        btLng.passBack(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                System.out.println("i = " + i + " btLng = " + theObject);
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }

            @Override
            public void onPassCompleted() {

            }
        });
        System.out.println("----------------------");
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            if (i % 2 == 0) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            if (i % 2 == 0) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            if (i % 2 == 0) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest<>());
    }

    public void testBinTreeBase2() {
        BinTreeBase<Long> btLng = new BinTreeBase<Long>();
        addCheckStructure(btLng);
    }

    public void testBinTree2() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        addCheckStructure(btLng);
    }

    public void testScapegoatBinTree2() {
        ScapegoatTree<Long> btLng = new ScapegoatTree<Long>();
        addCheckStructure(btLng);
    }

    public void addCheckStructure(BinTreeBase<Long> btLng) {
        Long rnd;
        for (int i = 0; i < 1000000; i++) {
            rnd = Math.round(Math.random() * 10000L);
            btLng.add(rnd);
        }
        btLng.passStraight(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                //System.out.println("i = " + i + " btLng = " + theObject.getObjectNode());
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }

            @Override
            public void onPassCompleted() {

            }
        });
        System.out.println("----------------------");
        btLng.passBack(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                //System.out.println("i = " + i + " btLng = " + theObject.getObjectNode());
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }

            @Override
            public void onPassCompleted() {

            }
        });

        System.out.println("----------------------");
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            //System.out.println("i = " + i + " btLng = " + it.next());
            it.next();
            i++;
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest<>());
        System.out.println("---------------------------------------------------");
        System.out.println("MaxLevel = " + btLng.getMaxLevel());
        System.out.println("Size = " + btLng.getSize());
        System.out.println("RotateCount = " + btLng.getRotateCount());
        System.out.println("---------------------------------------------------");
        btLng.rebalanceTree();

        final long[] mw = new long[1];

        btLng.passStraight(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;
            long height = 0L;
            long maxheight = 0L;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                //System.out.println("i = " + i + " btLng = " + theObject.getObjectNode());
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                height++;
                if (height > maxheight) maxheight = height;
                return height;
            }

            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                height--;
                return height;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }

            @Override
            public void onPassCompleted() {
                mw[0] = maxheight;
            }
        });

        System.out.println("---------------------------------------------------");
        System.out.println("MaxLevel = " + mw[0]);
        System.out.println("Size = " + btLng.getSize());
        //System.out.println("RotateCount = " + btLng.getRotateCount());
        System.out.println("---------------------------------------------------");
    }

    public void testAVLBinTree3() {
        Calendar cBegin = Calendar.getInstance();
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        btLng.setThreshold((byte) 4);
        Long rnd;
        for (int i = 0; i < 10000; i++) {
            rnd = Math.round(Math.random() * 10000L);
//            System.out.print(", " + rnd);
            btLng.add(rnd);
            if (i % 100000 == 0) {
                System.out.println("i = " + i);
            }
        }
        System.out.println("---------------------------------------------------");
        btLng.checkStructure(new BinTreeCheckPassEventTest<>());
        System.out.println("---------------------------------------------------");
        System.out.println("MaxLevel = " + btLng.getMaxLevel());
        System.out.println("RotateCount = " + btLng.getRotateCount());
        System.out.println("---------------------------------------------------");
        final Iterator<Long> it = btLng.iterator();
        btLng.passStraight(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                if (it.hasNext()) {
                    assertEquals(theObject.getObjectNode(), it.next());
                }
                if (i % 100000 == 0) {
                    System.out.println("i = " + i);
                }
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }

            @Override
            public void onPassCompleted() {

            }
        });
        Calendar cEnd = Calendar.getInstance();
        System.out.println("---------------------------------------------------");
        System.out.println("time = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
    }

    public void testBinTreeEmpty() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Iterator<Long> it1 = btLng.iterator();
        int i = 1;
        while (it1.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it1.next());
            i++;
        }
        Iterator<Long> it2 = btLng.iterator(10L);
        i = 1;
        while (it2.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it2.next());
            i++;
        }
        final Iterator<Long> it = btLng.iterator();
        btLng.passStraight(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                assertEquals(theObject.getObjectNode(), it.next());
                if (i % 100000 == 0) {
                    System.out.println("i = " + i);
                }
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }

            @Override
            public void onPassCompleted() {

            }
        });
        btLng.add(10L);
        Iterator<Long> it3 = btLng.iterator();
        i = 1;
        while (it3.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it3.next());
            i++;
        }
        Iterator<Long> it4 = btLng.iterator(10L);
        i = 1;
        while (it4.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it4.next());
            i++;
        }
        final Iterator<Long> it5 = btLng.iterator();
        btLng.passStraight(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                assertEquals(theObject.getObjectNode(), it5.next());
                if (i % 100000 == 0) {
                    System.out.println("i = " + i);
                }
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }

            @Override
            public void onPassCompleted() {

            }
        });
    }

}
