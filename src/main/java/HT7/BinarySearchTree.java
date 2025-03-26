package HT7;

// BinarySearchTree.java
public class BinarySearchTree<E extends Comparable<E>> {
    // Clase interna Node
    private static class Node<E extends Comparable<E>> {
        E value;
        Node<E> left;
        Node<E> right;

        public Node(E value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node<E> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree<E> insert(E value) {
        Node<E> newNode = new Node<>(value);
        if (this.root == null) {
            this.root = newNode;
            return this;
        } else {
            Node<E> current = this.root;
            while (true) {
                if (value.compareTo(current.value) == 0) {
                    return this;
                }
                if (value.compareTo(current.value) < 0) {
                    if (current.left == null) {
                        current.left = newNode;
                        return this;
                    } else {
                        current = current.left;
                    }
                } else if (value.compareTo(current.value) > 0) {
                    if (current.right == null) {
                        current.right = newNode;
                        return this;
                    } else {
                        current = current.right;
                    }
                }
            }
        }
    }

    public E find(String sku) {
        if (this.root == null) {
            return null;
        }

        Node<E> current = this.root;
        while (current != null) {
            Product p = (Product) current.value;
            int compare = sku.compareTo(p.getSku());
            if (compare == 0) {
                return current.value;
            } else if (compare < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    public void inOrderAscending() {
        inOrderAscendingRec(root);
    }

    private void inOrderAscendingRec(Node<E> root) {
        if (root != null) {
            inOrderAscendingRec(root.left);
            System.out.println(root.value);
            inOrderAscendingRec(root.right);
        }
    }

    public void inOrderDescending() {
        inOrderDescendingRec(root);
    }

    private void inOrderDescendingRec(Node<E> root) {
        if (root != null) {
            inOrderDescendingRec(root.right);
            System.out.println(root.value);
            inOrderDescendingRec(root.left);
        }
    }
}