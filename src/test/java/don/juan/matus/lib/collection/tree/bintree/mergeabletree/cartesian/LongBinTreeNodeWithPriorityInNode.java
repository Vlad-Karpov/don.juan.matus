package don.juan.matus.lib.collection.tree.bintree.mergeabletree.cartesian;

import org.jetbrains.annotations.NotNull;

public class LongBinTreeNodeWithPriorityInNode implements PriorityCartesianBinTreeNode, Comparable<LongBinTreeNodeWithPriorityInNode>  {

    double priority = 0.0d;
    Long value = 0L;

    public LongBinTreeNodeWithPriorityInNode(double priority, Long value) {
        this.priority = priority;
        this.value = value;
    }

    @Override
    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public int compareTo(@NotNull LongBinTreeNodeWithPriorityInNode o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
