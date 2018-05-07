package eda.AVL;

/**
 *
 * @author victor
 */
public class Avl {
    NodeAvl root;
    public Avl(NodeAvl root) {
        this.root = root;
    }
    
    public Avl(int key) {
        this.root = new NodeAvl(key);
    }
    
    public Avl() {
        this.root = null;
    }
    
    public NodeAvl search(int key) {
        return search(key, root);
    }
    
    private NodeAvl search(int key, NodeAvl node) {
        if (node == null) {
            return null;
        }
        if (key == node.getKey()) {
            return node;
        }
        if (key > node.getKey()) {
            return search(key, node.getRight());
        } else {
            return search(key, node.getLeft());
        }
    }
    
    public void inserirKey(int key) {
        inserirNode(new NodeAvl(key));
    }
    
    public boolean inserirNode(NodeAvl node) {
        if (search(node.getKey()) != null) {
            System.out.println("nó já existe..");
            return false; // Nó já existe
        }
        ///////
        System.out.println("inserido..");
        ///////
        return inserir(node, root);
    }
    
    private boolean inserir(NodeAvl node, NodeAvl place) { // place = local onde será inserido
        if (place == null) {
            return false;
        }
        if (node.getKey() > place.getKey()) { // Adiciona à direita de place
            if (place.getRight() == null) {
                place.setRight(node); // Nó inserido na árvore...
                place.getRight().setFather(place);
                //balancear(place); // Verifica balanceamento do nó pai
            } else {
                return inserir(node, place.getRight());
            }
        } else { // Adiciona à esquerda de place
            if (place.getLeft() == null) {
                place.setLeft(node); // Nó inserido na árvore...
                place.getLeft().setFather(place);
                //balancear(place); // Verifica balanceamento do nó pai
            } else {
                return inserir(node, place.getLeft());
            }
        }
        return true;
    }
    
    public void balancear(NodeAvl node) {
        setFb(node); // atualiza fator de balanceamento do nó
        int fb = node.getFb();
        if (fb == -2) { // verifica se está desbalanceado
            if (height(node.getLeft().getLeft()) >= height(node.getLeft().getRight())) {
                node = rotationRight(node);
            } else {
                node = doubleRotationLeftRight(node);
            }
        } else if (fb == 2) {
            if (height(node.getRight().getRight()) >= height(node.getRight().getLeft())) {
                node = rotationLeft(node);
            } else {
                node = doubleRotationRightLeft(node);
            }
        }
        
        if (node.getFather() != null) {
            balancear(node.getFather());
        } else {
            this.root = node;
        }
    }
    
    public NodeAvl rotationLeft(NodeAvl node) {
        NodeAvl right = node.getRight();
        right.setFather(node.getFather());
        node.setRight(right.getLeft());
        if (node.getRight() != null) {
            node.getRight().setFather(node);
        }
        right.setLeft(node);
        node.setFather(right);
        if (right.getFather() != null) {
            if (right.getFather().getRight() == node) {
                right.getFather().setRight(right);
            } else if (right.getFather().getLeft() == node) {
                right.getFather().setLeft(right);
            }
        }
        
        setFb(node);
        setFb(right);
        
        return right;
    }
    
    public NodeAvl rotationRight(NodeAvl node) {
        NodeAvl left = node.getLeft();
        left.setFather(node.getFather());
        node.setLeft(left.getRight());
        if (node.getLeft() != null) {
            node.getLeft().setFather(node);
        }
        
        left.setRight(node);
        node.setFather(left);
        
        if (left.getFather() != null) {
            if (left.getFather().getRight() == node) {
                left.getFather().setRight(left);
            } else if (left.getFather().getLeft() == node) {
                left.getFather().setLeft(left);
            }
        }
        
        setFb(node);
        setFb(left);
        
        return left;
    }
    
    public NodeAvl doubleRotationLeftRight(NodeAvl node) {
        node.setLeft(rotationLeft(node.getLeft()));
        return rotationRight(node);
    }
    
    public NodeAvl doubleRotationRightLeft(NodeAvl node) {
        node.setRight(rotationRight(node.getRight()));
        return rotationLeft(node);
    }
    
    public void printTree() {
        printNodes(root);
    }
    
    private void printNodes(NodeAvl node) {
        if (node == null) {
            return;
        }
        System.out.println(node.getKey());
        printNodes(node.getLeft());
        printNodes(node.getRight());
    }
    
    private int height(NodeAvl node) {
        if (node == null) {
            return -1;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return 0;
        } else if (node.getLeft() == null) {
            return 1 + height(node.getRight());
        } else if (node.getRight() == null) {
            return 1 + height(node.getLeft());
        } else {
            return 1 + Math.max(height(node.getRight()), height(node.getLeft()));
        }
    }
    
    
    private void setFb(NodeAvl node) {
        node.setFb(height(node.getRight()) - height(node.getLeft()));
    }
    
    public NodeAvl sucessor(NodeAvl q) {
        if (q.getRight() != null) {
            NodeAvl r = q.getRight();
                while (r.getLeft() != null) {
                    r = r.getLeft(); // pega o ultimo a esquerda da direita de q
                }
            return r;
        } else {
            NodeAvl p = q.getFather();
                while (p != null && q == p.getRight()) {
                    q = p;
                    p = q.getFather();
            }
            return p;
        }
    }
    
    public void removerKey(int key) {
        NodeAvl node = search(key);
        if (node != null) {
            removerNode(node);
        }
    }
    
    public void removerNode(NodeAvl node) {
        NodeAvl oldNode;

        if (node.getLeft() == null || node.getRight() == null) {
            if (node.getFather() == null) { // Verifica se é a raiz e tem no máximo um filho
                    if (node.getLeft() == null) {
                        this.root = node.getRight();
                    } else {
                        this.root = node.getLeft();
                    }
                    node = null;
                    return;
            }
            oldNode = node;

        } else { // Caso o nó tenha dois nós filhos
            oldNode = sucessor(node); // Verifica quem será o nó que substituirá o nó antigo e substitui
            node.setKey(oldNode.getKey()); // Substitui a chave
        }

        NodeAvl newNode;
        if (oldNode.getLeft() != null) { // Verifica qual dos filhos de oldNode não é nulo.
            newNode = oldNode.getLeft(); 
        } else {
            newNode = oldNode.getRight();
        }

        if (newNode != null) { // Verifica se oldNode tem pelo menos um filho. 
            newNode.setFather(oldNode.getFather());
        }

        if (oldNode.getFather() == null) {
            this.root = newNode;
        } else {
            if (oldNode == oldNode.getFather().getLeft()) { // Verifica se o oldNode é o nó da esquerda
                oldNode.getFather().setLeft(newNode); // Substitui o nó da esquerda pelo newNode
            } else {
                oldNode.getFather().setRight(newNode); // Caso contrário substitui o nó da direita...
            }
            balancear(oldNode.getFather()); // Balancea o nó pai se necessário
        }
        oldNode = null;
    }
    
    
    
}
