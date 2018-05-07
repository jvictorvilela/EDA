/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eda.AVL;

/**
 *
 * @author victor
 */
public class Main {
    public static void main(String[] args) {
        Avl arvore = new Avl(11);
        arvore.inserirKey(10);
        arvore.inserirKey(15);
        arvore.inserirKey(5);
        arvore.inserirKey(6);
        arvore.inserirKey(7);
        arvore.inserirKey(22);
        arvore.inserirKey(1);
        arvore.printTree();
    }
}
