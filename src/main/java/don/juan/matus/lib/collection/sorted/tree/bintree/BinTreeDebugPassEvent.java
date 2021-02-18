package don.juan.matus.lib.collection.sorted.tree.bintree;

public class BinTreeDebugPassEvent<T extends Comparable<T>> implements BinTreeCheckPassEvent<T> {

    @Override
    public void onPass(BinTreeIterator<T> leftIterator, BinTreeIterator<T> rightIterator, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {

    }

    @Override
    public void setErrorMessage(String errMsg) {

    }

    @Override
    public String getErrorMessage() {
        return null;
    }

}
