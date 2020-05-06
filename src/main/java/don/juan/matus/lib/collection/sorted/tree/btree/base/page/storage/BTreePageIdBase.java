package don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage;

public class BTreePageIdBase implements BTreePageIdInterface<CharacterArray> {

    CharacterArray characterArray;

    public BTreePageIdBase() {
        characterArray = new CharacterArray();
    }

    public BTreePageIdBase(char[] cid) {
        setIdAsCharArray(cid);
    }

    public BTreePageIdBase(int id) {
        setIdAsInt(id);
    }

    @Override
    public CharacterArray getId() {
        return characterArray;
    }

    @Override
    public void setId(CharacterArray id) {
        characterArray = id;
    }

    @Override
    public char[] getIdAsCharArray() {
        return characterArray.getIdAsCharArray();
    }

    @Override
    public void setIdAsCharArray(char[] id) {
        characterArray = new CharacterArray(id);
    }

    @Override
    public int getIdAsInt() {
        return characterArray.getIdAsInt();
    }

    @Override
    public void setIdAsInt(int id) {
        characterArray = new CharacterArray(id);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BTreePageIdBase that = (BTreePageIdBase) o;
        return characterArray.equals(that.characterArray);
    }

    @Override
    public int hashCode() {
        return characterArray.hashCode();
    }

    @Override
    public String toString() {
        return characterArray.toString();
    }

}
