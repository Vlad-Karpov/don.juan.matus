package don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage;

public class BTreePageIdBase implements BTreePageIdInterface<CharacterArray> {

    CharacterArray characterArray;

    public BTreePageIdBase() {
        characterArray = new CharacterArray();
    }

    public BTreePageIdBase(char[] cid) {
        characterArray = new CharacterArray(cid);
    }

    public BTreePageIdBase(int id) {
        characterArray = new CharacterArray(id);
    }

    @Override
    public CharacterArray getId() {
        return characterArray;
    }

    @Override
    public char[] getIdAsCharArray() {
        return characterArray.getIdAsCharArray();
    }

    @Override
    public int getIdAsInt() {
        return characterArray.getIdAsInt();
    }

    @Override
    public int size() {
        return characterArray.size();
    }

    @Override
    public int compareTo(BTreePageIdInterface<CharacterArray> o) {
        return characterArray.compareTo(o.getId());
    }

    @Override
    public int hashCode() {
        return characterArray.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return characterArray.equals(obj);
    }

    @Override
    public String toString() {
        return characterArray.toString();
    }

}
