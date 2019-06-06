package don.juan.matus.lib.tree.bintree.mergeabletree.cartesian;

import don.juan.matus.lib.tree.bintree.BinTreeNodeBase;
import don.juan.matus.lib.tree.bintree.mergeabletree.MergeableBaseBinTree;

import java.util.Random;

public class RandomMergeBinTree<T extends Comparable<T>> extends MergeableBaseBinTree<T> {

    private Random rnd;
    private double threshold = 0.5d;

    public RandomMergeBinTree() {
        super();
        rnd = new Random();
        root = new BinTreeNodeBase<>(null, null, null, null);
        mergeStrategy = (left, right) -> rnd.nextDouble() < threshold;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

}
