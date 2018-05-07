package AVL2;

/**
 *
 * @author João Victor Vilela
 */
public class Node {
    private Node left;
    private Node right;
    private Node parent;
    private int key;
    private int fb;
    private int heigthRight;
    private int heigthLeft;
    boolean external;
    
    public Node(boolean external, int key, Node parent) { //criação de um nó
        if (external == true) {
            this.left = null;
            this.right = null;
            this.external = true;
        } else {
            this.left = new Node(true, 0, this);
            this.right = new Node(true, 0, this);
            this.parent = parent;
            this.key = key;
            this.fb = 0; // <- mudar esta parte
            this.external = false;
        }
        this.parent = parent;
    }
    
    public void turnExternal() {
        this.external = true;
        this.left = null;
        this.right = null;
        this.key = 0;
    }
    
    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
    }

    public int getHeigthRight() {
        return heigthRight;
    }

    public void setHeigthRight(int heigthRight) {
        this.heigthRight = heigthRight;
    }

    public int getHeigthLeft() {
        return heigthLeft;
    }

    public void setHeigthLeft(int heigthLeft) {
        this.heigthLeft = heigthLeft;
    }
    
    public boolean isExternal() {
        return external;
    }
    
}
