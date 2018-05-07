package AVL2;

/**
 *
 * @author João Victor Vilela
 */
public class AVL {
    private Node root;
    
    public AVL() {
        this.root = new Node(true, 0, null);
    }
    
    public Node treeSearch(int key, Node node) {
        if (node.isExternal() == true) {
            return node;
        }
        if (key == node.getKey()) {
            return node;
        } else {
            if (key < node.getKey()) {
                return treeSearch(key, node.getLeft());
            } else {
                if (key > node.getKey()) {
                    return treeSearch(key, node.getRight());
                }
            }
        }
        return null;
    }
    
    private void insertItem(int key) {
        if (root.isExternal() == true) {
            root = new Node(false, key, null);
            return;
        }
        Node search = treeSearch(key, root); // resultado da busca
        if (search.isExternal() == true) { // se a busca resultar em um nó externo, o item será inserido no lugar do nó externo
            if (search.getParent().getLeft() == search) {
                search.getParent().setLeft(new Node(false, key, search.getParent()));
            } else {
                search.getParent().setRight(new Node(false, key, search.getParent()));
            }
        } // caso contrário, o nó já existe...
    }
    
    public void insert(int key) {
        insertItem(key);
    }
    
    public void replaceNode(Node node1, Node node2) { // coloca o node2 no lugar do node1
        if (node1.getParent().getLeft() == node1) { // verifica se node 1 é o filho da esquerda
            node1.getParent().setLeft(node2); 
        } else {
            node1.getParent().setRight(node2);
        }
        node2.setParent(node1.getParent());
    }
    
    public boolean removeElement(int key) {
        Node node = treeSearch(key, root);
        if (!node.isExternal()) { // caso o nó exista na árvore..
            if (node.getLeft().isExternal() && node.getRight().isExternal()) { // caso 01
                node.turnExternal(); // transforma no nó em externo.
                return true;
            }
            if (node.getLeft().isExternal() || node.getRight().isExternal()) { // caso 02
                if (node.getLeft().isExternal()) { // verifica se o nó externo é o da esquerda
                    replaceNode(node, node.getRight());
                } else {
                    replaceNode(node, node.getLeft());
                }
            }
        }
        return false;
    }
    
    public void printTree(Node root) {
        if (root.isExternal()) {
            return;
        }
        System.out.println(root.getKey());
        printTree(root.getLeft());
        printTree(root.getRight());
    }
    
    public Node getRoot() {
        return this.root;
    }
    
}
