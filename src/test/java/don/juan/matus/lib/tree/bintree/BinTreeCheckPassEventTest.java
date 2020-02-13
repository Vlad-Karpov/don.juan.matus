package don.juan.matus.lib.tree.bintree;

public class BinTreeCheckPassEventTest<T extends Comparable<T>> implements BinTreeCheckPassEvent<T> {

    private String errorMessage;
    private boolean ignor = false;

    public BinTreeCheckPassEventTest() {
    }

    public BinTreeCheckPassEventTest(boolean ignor) {
        this.ignor = ignor;
    }

    @Override
    public void onPass(
            BinTreeIterator<T> leftIterator,
            BinTreeIterator<T> rightIterator,
            BinTreeNodeInterface<T> currentNode,
            BinTreeNodeInterface<T> previousNode) {
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
