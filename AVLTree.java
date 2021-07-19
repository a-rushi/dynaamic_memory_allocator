// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {

    private AVLTree left, right;     // Children.
    private AVLTree parent;          // Parent pointer.
    private int height;  // The height of the subtree

    public AVLTree() {
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.

    }

    public AVLTree(int address, int size, int key) {
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions.
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.

    private void updateHt(AVLTree node){
        AVLTree curr = node;
        if (curr.parent == null) return;
        if (curr.left!=null && curr.right!=null){
            if (curr.left.height >= curr.right.height){
                curr.height = curr.left.height + 1;
            }
            else {
                curr.height = curr.right.height + 1;
            }
        } else if (curr.left ==null  && curr.right!=null){
            curr.height = curr.right.height  +1;
        } else if (curr.left != null  && curr.right == null){
            curr.height = curr.left.height  +1;
        } else {
            curr.height = 0;
        }
        Balance(curr);
        updateHt(curr.parent);
    }

    private void leftRotate(AVLTree curr){
        AVLTree z = curr;
        AVLTree y = z.right;

        if (z == z.parent.left){
            {
                z.parent.left = y;
                y.parent = z.parent;
            }
        } else {
            z.parent.right = y;
            y.parent = z.parent;
        }
        z.right = y.left;
        if (y.left != null) {
            y.left.parent = z;
        }

        z.parent = y;
        y.left = z;

        if (z.left!=null && z.right!=null){
            if (z.left.height >= z.right.height){
                z.height = z.left.height + 1;
            }
            else {
                z.height = z.right.height + 1;
            }
        } else if (z.left ==null  && z.right!=null){
            z.height = z.right.height  +1;
        } else if (z.left != null  && z.right == null){
            z.height = z.left.height  +1;
        } else {
            z.height = 0;
        }

        if (y.left!=null && y.right!=null){
            if (y.left.height >= y.right.height){
                y.height = y.left.height + 1;
            }
            else {
                y.height = y.right.height + 1;
            }
        } else if (y.left ==null  && y.right!=null){
            y.height = y.right.height  +1;
        } else if (y.left != null  && y.right == null){
            y.height = y.left.height  +1;
        } else {
            y.height = 0;
        }
    }

    private void rightRotate(AVLTree curr){
        AVLTree z = curr;
        AVLTree y = z.left;

        if (z == z.parent.left){
            {
                z.parent.left = y;
                y.parent = z.parent;
            }
        } else {
            z.parent.right = y;
            y.parent = z.parent;
        }
        z.left = y.right;
        if (y.right != null) {
            y.right.parent = z;
        }
        z.parent = y;
        y.right = z;

        if (z.left!=null && z.right!=null){
            if (z.left.height >= z.right.height){
                z.height = z.left.height + 1;
            }
            else {
                z.height = z.right.height + 1;
            }
        } else if (z.left ==null  && z.right!=null){
            z.height = z.right.height  +1;
        } else if (z.left != null  && z.right == null){
            z.height = z.left.height  +1;
        } else {
            z.height = 0;
        }

        if (y.left!=null && y.right!=null){
            if (y.left.height >= y.right.height){
                y.height = y.left.height + 1;
            }
            else {
                y.height = y.right.height + 1;
            }
        } else if (y.left ==null  && y.right!=null){
            y.height = y.right.height  +1;
        } else if (y.left != null  && y.right == null){
            y.height = y.left.height  +1;
        } else {
            y.height = 0;
        }
    }

    private void rightLeftRotate(AVLTree curr){
        AVLTree z = curr;
        AVLTree y = z.right;

        rightRotate(y);
        leftRotate(z);
    }

    private void leftRightRotate(AVLTree curr){
        AVLTree z = curr;
        AVLTree y = z.left;

        leftRotate(y);
        rightRotate(z);
    }

    private void Balance(AVLTree node){
        AVLTree curr = node;
        if (curr.parent == null) return;
        if (curr.left==null && curr.right == null){
        } else if (curr.left == null && curr.right != null){
            AVLTree child = curr.right;
                if (child.left != null) {
                    rightLeftRotate(curr);
                }
                else if (child.right != null) {
                    leftRotate(curr);
                }
        } else if (curr.left != null && curr.right == null){
            AVLTree child = curr.left;
            if (child.left != null ){
                rightRotate(curr);
            }
            else if (child.right != null){
                leftRightRotate(curr);
            }
        } else{
            if ((curr.left.height - curr.right.height) > 1){
                AVLTree child = curr.left;
                if (child.left.height >= child.right.height){
                    rightRotate(curr);
                }
                else if (child.left.height < child.right.height){
                    leftRightRotate(curr);
                }
            } else if ((curr.left.height - curr.right.height) < -1){
                AVLTree child = curr.right;
                if (child.right.height >= child.left.height){
                    leftRotate(curr);
                }
                else if (child.right.height < child.left.height){
                    rightLeftRotate(curr);
                }
            }
        }
    }

    public AVLTree Insert(int address, int size, int key)
    {
        AVLTree curr = this;
        AVLTree prev;
        String x = "e";
        while(curr.parent!=null){
            curr = curr.parent;
        }
        prev = curr;
        curr = curr.right;
        while (curr != null) {
            prev = curr;
            if (curr.key < key) {
                curr = curr.right;
                x = "r";
            } else if (curr.key > key) {
                curr = curr.left;
                x = "l";
            } else {
                if (curr.address < address) {
                    curr = curr.right;
                    x = "r";
                } else if (curr.address > address) {
                    curr = curr.left;
                    x = "l";
                } else {
                    if (curr.size < size) {
                        curr = curr.right;
                        x = "r";
                    } else if (curr.size > size){
                        curr = curr.left;
                        x = "l";
                    }
                    else{
                        return curr;
                    }
                }
            }
        }
        AVLTree node = new AVLTree(address,size,key);
        if (prev.parent == null){
            prev.right = node;
            node.parent = prev;
        } else{
            if (x.equals("l")) {
                prev.left = node;
            } else{
                prev.right = node;
            }
            node.parent = prev;
        }
        updateHt(node);
        return node;
    }

    public boolean Delete(Dictionary e)
    {
        AVLTree curr = this;
        while (curr.parent!= null){
            curr = curr.parent;
        }
        curr = curr.right;
        if (curr ==  null) return false;
        while (curr != null){
            if (curr.key < e.key) {
                curr = curr.right;
            }
            else if (curr.key > e.key) {
                curr = curr.left;
            }
            else {
                if (curr.address < e.address) {
                    curr = curr.right;
                }
                else if (curr.address > e.address) {
                    curr = curr.left;
                }
                else {
                    if (curr.size < e.size) curr = curr.right;
                    else if (curr.size > e.size) curr = curr.left;
                    else {
                        break;
                    }
                }
            }
        }
        if (curr== null) return false;

        if (curr.left==null && curr.right== null){
            AVLTree par = curr.parent;
            if (curr == curr.parent.left){
                curr.parent.left = null;
            } else curr.parent.right = null;
            curr.parent= null;
            updateHt(par);
        } else if (curr.left!=null && curr.right == null){
            AVLTree par = curr.parent;
            if (curr == curr.parent.left){
                curr.parent.left = curr.left;
                curr.left.parent = curr.parent;
            } else {
                curr.parent.right = curr.left;
                curr.left.parent = curr.parent;
            }
            curr.parent= null;
            curr.left = null;
            updateHt(par);
        } else if (curr.left == null && curr.right != null){
            AVLTree par = curr.parent;
            if (curr == curr.parent.left){
                curr.parent.left = curr.right;
                curr.right.parent = curr.parent;
            } else {
                curr.parent.right = curr.right;
                curr.right.parent = curr.parent;
            }
            curr.parent= null;
            curr.right = null;
            updateHt(par);
        } else{
            Dictionary suc = curr.getNext();
            Delete(suc);
            curr.key = suc.key;
            curr.address = suc.address;
            curr.size = suc.size;
        }
        return true;
    }

    public AVLTree Find(int key, boolean exact)
    {
        AVLTree curr = this.getFirst();
        while (curr!= null && curr.key < key){
            curr = curr.getNext();
        }
        if (curr == null) return null;
        if (exact && curr.key != key) return null;
        else return curr;
    }

    public AVLTree getFirst()
    {
        AVLTree curr = this;
        while (curr.parent!= null){
            curr = curr.parent;
        }
        curr = curr.right;
        if (curr == null) return null;
        while(curr.left!=null){
            curr = curr.left;
        }
        return curr;
    }

    public AVLTree getNext()
    {
        AVLTree cur = this;
        if (this.parent == null) return null;
        if (cur.right != null) {
            cur = cur.right;
            while(cur.left!=null){
                cur = cur.left;
            }
            return cur;
        }
        AVLTree child = cur;
        cur = cur.parent;
        while (cur!=null){
            if (cur.right == child) {
                child = cur;
                cur = cur.parent;
            }
            else break;
        }
        if (cur==null) return null;
        else return cur;
    }

    public boolean sanity()
    {
        AVLTree hare = this;
        AVLTree tort = this;
        while(hare!=null){
            tort= tort.parent;
            if (hare.parent == null)  hare = null;
            else hare=hare.parent.parent;

            if (hare == tort) break;
        }
        if (hare == tort && tort != null) return false;
        if (this.parent == null) {
            tort = this.right;
            if (tort!= null && this != tort.parent) return false;
        }
        else{
            while (tort.parent!=null){
                tort = tort.parent;
            }
            if (tort.right!= null && tort!= tort.right.parent) return false;
            tort = tort.right;
        }
        AVLTree curr = tort;
        if (curr == null) return true;
        else return curr.inorder();
    }

    private boolean inorder(){
        AVLTree curr = this;
        if (curr.left == null && curr.right == null) {
            if (curr.height!=0) return false;
            else return true;
        }
        else if (curr.left != null && curr.right == null){
            if (curr.left.parent != curr) return false;
            if (curr.height != 1) return false;
            if (curr.height != curr.left.height + 1) return false;
            if (curr.left.key > curr.key) {
                if (curr.left.address > curr.address) {
                    if (curr.left.size > curr.size) return false;
                }
            }
            boolean l = curr.left.inorder();
            return l;
        }
        else if (curr.left == null && curr.right != null){
            if (curr.right.parent != curr) return false;
            if (curr.height != 1) return false;
            if (curr.height != curr.right.height + 1) return false;
            if (curr.right.key < curr.key) {
                if (curr.right.address < curr.address) {
                    if (curr.right.size < curr.size) return false;
                }
            }
            boolean r = curr.right.inorder();
            return r;
        }

        else{
            if (curr.left.parent != curr) return false;
            if (curr.left.key > curr.key) {
                if (curr.left.address > curr.address){
                    if (curr.left.size > curr.size) return false;
                }
            }

            if (curr.right.parent != curr) return false;
            if (curr.right.key < curr.key) {
                if (curr.right.address < curr.address) {
                    if (curr.right.size < curr.size) return false;
                }
            }

            if (curr.left.height - curr.right.height > 1 || curr.left.height - curr.right.height < -1 ) return false;
            if (curr.left.height >=curr.right.height){
                if (curr.height!= curr.left.height+1) return false;
            }
            else{
                if (curr.height!= curr.right.height+1) return false;
            }
            boolean l = curr.left.inorder();
            boolean r = curr.right.inorder();
            return l && r;
        }
    }
}


