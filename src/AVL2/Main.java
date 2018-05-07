/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AVL2;

/**
 *
 * @author victor
 */
public class Main {
    public static void main(String[] args) {
        AVL tree = new AVL();
        tree.insert(10);
        tree.insert(20);
        tree.insert(3);
        tree.insert(25);
        tree.insert(25);
        tree.insert(25);
        tree.insert(25);
        tree.removeElement(20);
        tree.printTree(tree.getRoot());
    }
    
}
