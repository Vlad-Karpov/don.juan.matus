package don.juan.matus.lib.collection.tree.bintree;

import don.juan.matus.lib.collection.tree.bintree.mergeabletree.cartesian.CartesianBinTree;
import don.juan.matus.lib.collection.tree.bintree.mergeabletree.cartesian.RandomMergeBinTree;
import don.juan.matus.lib.collection.tree.bintree.rotatetree.aa.AATree;
import don.juan.matus.lib.collection.tree.bintree.rotatetree.avl.AVLBinTree;
import don.juan.matus.lib.collection.tree.bintree.rotatetree.random.RndBinTree;
import don.juan.matus.lib.collection.tree.bintree.rotatetree.red_black.RedBlackTree;
import don.juan.matus.lib.collection.tree.bintree.rotatetree.scapegoat.RandomRotateBinTree;
import don.juan.matus.lib.collection.tree.bintree.rotatetree.scapegoat.ScapegoatTree;
import don.juan.matus.lib.collection.tree.bintree.rotatetree.splay.SplayTree;
import don.juan.matus.lib.collection.tree.bintree.rotatetree.waight.BinTreeW;
import don.juan.matus.lib.collection.tree.bintree.tst.TreeMapTst;
import junit.framework.TestCase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class CmpTest extends TestCase {

    private static final int maxRandomDataFile = 10000000;

    private static List<Long> rList;

    static {
        Calendar cBegin = Calendar.getInstance();
        rList = readRendomDataFile();
        Calendar cEnd = Calendar.getInstance();
        System.out.println("time 1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        System.out.println("");
    }

    private static List<Long> readRendomDataFile() {
        List<Long> result = new ArrayList<>();
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new FileInputStream("src/test/resources/RandomDataFile.dat"));
            result = (List<Long>) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void createRandomDataFile() throws IOException {
        TreeMapTst<Long, Long> tmRndLng = new TreeMapTst<Long, Long>();
        List<Long> rList = new ArrayList<Long>();
        Long rnd;
        while (tmRndLng.size() < maxRandomDataFile) {
            rnd = Math.round(Math.random() * maxRandomDataFile);
            if (tmRndLng.put(rnd, rnd) == null) rList.add(rnd);
        }
        tmRndLng = null;
        System.gc();
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/test/resources/RandomDataFile.dat"));
        os.writeObject(rList);
        os.flush();
        os.close();
        System.gc();
    }

    public void testBinTreeBinTreeBase() {
        addSeekRemove("BinTreeBase", rList, new BinTreeBase<>());
    }

    public void testBinTreeTreeMapTst() {
        addSeekRemove(rList, new TreeMapTst<>());
    }

    public void testBinTreeBinTreeW() {
        addSeekRemove("BinTreeW", rList, new BinTreeW<>());
    }

    public void testBinTreeRedBlackTree() {
        addSeekRemove("RedBlackTree", rList, new RedBlackTree<>());
    }

    public void testBinTreeAVLBinTree() {
        addSeekRemove("AVLBinTree", rList, new AVLBinTree<>());
    }

    public void testBinTreeAATree() {
        addSeekRemove("AATree", rList, new AATree<>());
    }

    public void testBinTreeSplayTree() {
        addSeekRemove("SplayTree", rList, new SplayTree<>());
    }

    public void testBinTreeScapegoatTree() {
        addSeekRemove("ScapegoatTree", rList, new ScapegoatTree<>());
    }

    public void testBinTreeCartesianBinTree() {
        addSeekRemove("CartesianBinTree", rList, new CartesianBinTree<>());
    }

    public void testBinTreeRandomMergeBinTree() {
        addSeekRemove("RandomMergeBinTree", rList, new RandomMergeBinTree<>());
    }

    public void testBinTreeRndBinTree() {
        addSeekRemove("RndBinTree", rList, new RndBinTree<>());
    }

    public void testBinTreeRandomRotateBinTree() {
        addSeekRemove("RandomRotateBinTree", rList, new RandomRotateBinTree<>());
    }

    private <T extends Comparable<T>> void addSeekRemove(List<T> rList, TreeMapTst<T, T> tree) {
        Calendar cBegin;
        Calendar cEnd;
        T rnd;
        int j;
        Iterator<T> it;
        System.out.println("TreeMap");
        cBegin = Calendar.getInstance();
        for (int i = 0; i < maxRandomDataFile; i++) {
            rnd = rList.get(i);
            tree.put(rnd, rnd);
        }
        cEnd = Calendar.getInstance();
        System.out.print("time 2.1 (add) = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        System.out.println(", maxLevel = " + tree.maxLevel + ", RotateCount = " + tree.rotateCount);
        //
        cBegin = Calendar.getInstance();
        it = tree.descendingKeySet().iterator();
        j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.1 (iterate) = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + tree.size());
        System.out.println("time 4.1 (check) = " + 0 + " " + tree.size());
        //
        tree.rotateCount = 0L;
        tree.maxLevel = 0L;
        cBegin = Calendar.getInstance();
        for (int i = 0; i < maxRandomDataFile; i += 100) {
            for (int l = 0; l < 10; l++) {
                for (int k = 0; k < 99; k++) {
                    if (i + k < maxRandomDataFile) {
                        rnd = rList.get(i + k);
                        if (!tree.containsKey(rnd)) {
                            System.out.println(String.format("%s not found!", rnd));
                        }
                    }
                }
            }
        }
        cEnd = Calendar.getInstance();
        System.out.print("time 5.1 (seek)  = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        System.out.println(", maxLevel = " + tree.maxLevel + ", RotateCount = " + tree.rotateCount);
        //
        tree.rotateCount = 0L;
        tree.maxLevel = 0L;
        cBegin = Calendar.getInstance();
        it = tree.descendingKeySet().iterator();
        j = 0;
        int r = 0;
        while (it.hasNext()) {
            if ((j & 1) == 1) {
                it.remove();
                r++;
            }
            it.next();
            j++;
        }
        cEnd = Calendar.getInstance();
        System.out.print("time 6.1 (remove) = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + r + ") " + tree.size() + ", - " + (r + tree.size()));
        System.out.println(", maxLevel = " + tree.maxLevel + ", RotateCount = " + tree.rotateCount);
        //
        System.out.println("time 7.1 (check) = " + 0 + " " + tree.size());
        System.out.println("<=========================================================================================>");
        System.gc();
    }

    private <T extends Comparable<T>> void addSeekRemove(String nameTree, List<T> rList, BinTreeBase<T> tree) {
        Calendar cBegin;
        Calendar cEnd;
        T rnd;
        BinTreeBase.TreeProps tp;
        int j;
        Iterator<T> it;
        //
        System.out.println(nameTree);
        cBegin = Calendar.getInstance();
        for (int i = 0; i < maxRandomDataFile; i++) {
            rnd = rList.get(i);
            tree.add(rnd);
        }
        cEnd = Calendar.getInstance();
        System.out.print("time 2.1 (add) = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        tp = tree.treePassage();
        System.out.println(", maxLevel = " + tp.heght + ", RotateCount = " + tree.getRotateCount());
        //
        cBegin = Calendar.getInstance();
        it = tree.iterator();
        j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.1 (iterate) = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + tree.getSize());
        //
        cBegin = Calendar.getInstance();
        tree.checkStructure(new BinTreeCheckPassEventTest<>());
        cEnd = Calendar.getInstance();
        System.out.println("time 4.1 (check) = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " " + tree.getSize());
        //
        tree.setRotateCount(0L);
        cBegin = Calendar.getInstance();
        for (int i = 0; i < maxRandomDataFile; i += 100) {
            for (int l = 0; l < 10; l++) {
                for (int k = 0; k < 99; k++) {
                    if (i + k < maxRandomDataFile) {
                        rnd = rList.get(i + k);
                        if (!tree.seek(rnd, null)) {
                            System.out.println(String.format("%s not found!", rnd));
                        }
                    }
                }
            }
        }
        cEnd = Calendar.getInstance();
        System.out.print("time 5.1 (seek)  = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        tp = tree.treePassage();
        System.out.println(", maxLevel = " + tp.heght + ", RotateCount = " + tree.getRotateCount());
        //
        tree.setRotateCount(0L);
        cBegin = Calendar.getInstance();
        it = tree.iterator();
        j = 0;
        int r = 0;
        while (it.hasNext()) {
            if ((j & 1) == 1) {
                it.remove();
                r++;
            }
            it.next();
            j++;
        }
        cEnd = Calendar.getInstance();
        System.out.print("time 6.1 (remove) = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + r + ") " + tree.getSize() + ", - " + (r + tree.getSize()));
        tp = tree.treePassage();
        System.out.println(", maxLevel = " + tp.heght + ", RotateCount = " + tree.getRotateCount());
        //
        if (tp.heght > (Math.log(maxRandomDataFile)/Math.log(2) * 10000)) {
            System.out.print("rebalance");
            tree.rebalanceTree();
            tp = tree.treePassage();
            System.out.println(", maxLevel = " + tp.heght);
        }
        //
        cBegin = Calendar.getInstance();
        tree.checkStructure(new BinTreeCheckPassEventTest<>());
        cEnd = Calendar.getInstance();
        System.out.println("time 7.1 (check) = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " " + tree.getSize());
        //
        System.out.println("<=========================================================================================>");
        System.gc();
    }


}
