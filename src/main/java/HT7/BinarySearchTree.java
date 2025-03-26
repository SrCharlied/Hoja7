package HT7;

/**
 * A generic Binary Search Tree (BST) implementation that stores elements in a sorted order.
 * The tree is ordered based on the natural ordering of the elements, as defined by their
 * {@link Comparable} implementation.
 * <p>
 * This implementation is based on the concepts described in the article
 * "https://medium.com/swlh/binary-search-tree-ca33c003da96"
 * Binary Search Tree" by The Startup
 *
 * @param <E> the type of elements stored in the tree, must implement {@link Comparable}
 */
public class BinarySearchTree<E extends Comparable<E>> {
    private static class Node<E extends Comparable<E>> {
        E value;
        Node<E> left;
        Node<E> right;

        Node(E value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node<E> root;

    /**
     * Constructs an empty Binary Search Tree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Inserts a new element into the tree. If the element already exists (based on its
     * comparison with existing elements), the tree remains unchanged.
     *
     * @param value the element to insert
     * @return this tree, for method chaining
     */
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

    /**
     * Searches for a product in the tree by its SKU.
     *
     * @param sku the SKU to search for
     * @return the product with the specified SKU, or null if not found
     */
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

    /**
     * Prints the elements of the tree in ascending order (in-order traversal).
     */
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

    /**
     * Prints the elements of the tree in descending order (reverse in-order traversal).
     */
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