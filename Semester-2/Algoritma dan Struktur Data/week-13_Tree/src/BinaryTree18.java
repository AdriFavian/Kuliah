public class BinaryTree18 {
    Node18 root;
    int size;

    public BinaryTree18() {
        root = null;
    }

    boolean isEmpty() {
        return root == null;
    }

    void add(int data) {
        if (isEmpty()) {
            root = new Node18(data);
        } else {
            Node18 current = root;
            while (true) {
                if (data > current.data) {
                    if (current.right != null) {
                        current = current.right;
                    } else {
                        current.right = new Node18(data);
                        break;
                    }
                } else if (data < current.data) {
                    if (current.left != null) {
                        current = current.left;
                    } else {
                        current.left = new Node18(data);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    boolean find(int data) {
        boolean result = false;
        Node18 current = root;
        while (current != null) {
            if (current.data == data) {
                result = true;
                break;  
            } else if (data > current.data) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return result;
    }

    void traversePreOrder(Node18 node) {
        if (node != null) {
            System.out.print(" " + node.data);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    void traversePostOrder(Node18 node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.data);
        }
    }

    void traverseInOrder(Node18 node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.data);
            traverseInOrder(node.right);
        }
    }
    
    Node18 getSuccessor(Node18 del) {
        Node18 successor = del.right;
        Node18 successorParent = del;
        while (successor.left != null) {
            successorParent = successor;
            successor = successor.left; 
        }
        if (successor != del.right) {
            successorParent.left = successor.right;
            successor.right = del.right;
        }
        return successor;
    }

    void delete(int data) {
        if (isEmpty()) {
            System.out.println("Tree is empty!");
            return;
        }
        Node18 parent = root;
        Node18 current = root;
        boolean isLeftChild = false;
        while (current != null) {
            if (current.data == data) {
                break;  
            } else if (data < current.data) {
                parent = current;
                current = current.left;
                isLeftChild = true;
            } else if (data > current.data) {
                parent = current;
                current = current.right;
                isLeftChild = false;
            }
        }
        //deletion
        if (current == null) {
            System.out.println("Couldn't find data!");
            return;
        } else {
            if (current.left == null && current.right == null) {
                if (current == root) {
                    root = null;
                } else {
                    if (isLeftChild) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                }
            } else if (current.left == null) {
                if (current == root) {
                    root = current.right;
                } else {
                    if (isLeftChild) {
                        parent.left = current.right;
                    } else {
                        parent.right = current.right;
                    }
                }
            } else if (current.right == null) {
                if (current == root) {
                    root = current.left;
                } else {
                    if (isLeftChild) {
                        parent.left = current.left;
                    } else {
                        parent.right = current.left;
                    }
                }
            } else {
                Node18 successor = getSuccessor(current);
                if (current == root) {
                    root = successor;
                } else {
                    if (isLeftChild) {
                        parent.left = successor;
                    } else {
                        parent.right = successor;
                    }
                    successor.left = current.left;
                }
            }
        }
    }

    //  menambahkan node dengan cara rekursif
    void addRekursif(int data) {
        root = addRekursif(root, data);
    }

    private Node18 addRekursif(Node18 current, int data) {
        if (current == null) {
            return new Node18(data);
        }

        if (data < current.data) {
            current.left = addRekursif(current.left, data);
        } else if (data > current.data) {
            current.right = addRekursif(current.right, data);
        }
        return current;
    }

    // menemukan nilai minimum dalam tree
    int findMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }
        Node18 current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    // menemukan nilai maximum dalam tree
    int findMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }
        Node18 current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    // menampilkan data yang ada di leaf
    void printLeaf(Node18 node) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                System.out.print(" " + node.data);
            }
            printLeaf(node.left);
            printLeaf(node.right);
        }
    }

    //  menampilkan berapa jumlah leaf yang ada di dalam tree
    int hitungLeaf(Node18 node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return hitungLeaf(node.left) + hitungLeaf(node.right);
    }
}
