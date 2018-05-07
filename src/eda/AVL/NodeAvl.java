
package eda.AVL;

/**
 *
 * @author victor
 */
public class NodeAvl {
    private NodeAvl left;
    private NodeAvl right;
    private NodeAvl father;
    private int key;
    private int fb;
    private int heigthRight;
    private int heigthLeft;
    public NodeAvl(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.father = null;
        this.fb = 0;
        this.heigthLeft = 0;
        this.heigthRight = 0;
    }
    public void setLeft(NodeAvl left) {
        this.left = left;
    }
    public void setRight(NodeAvl right) {
        this.right = right;
    }
    public void setFather(NodeAvl father) {
        this.father = father;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public void setFb(int fb) {
        this.fb = fb;
    }
    public NodeAvl getLeft() {
        return this.left;
    }
    public NodeAvl getFather() {
        return this.father;
    }
    public NodeAvl getRight() {
        return this.right;
    }
    public int getKey() {
        return this.key;
    }
    public int getFb() {
        return this.fb;
    }
    
    public boolean isEmpty() {
        if (left == null && right == null) {
            return true;
        }
        return false;
    } 
    
}
