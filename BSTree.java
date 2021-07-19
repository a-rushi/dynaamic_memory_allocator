// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.

    public BSTree(){
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }

    public BSTree(int address, int size, int key){
        super(address, size, key);
    }

    public BSTree Insert(int address, int size, int key)
    {
        BSTree curr = this;
        BSTree prev;
        String x = "e";
        while(curr.parent!=null){
            curr = curr.parent;
        }
        prev = curr;
        curr = curr.right;
        while (curr != null){
            prev = curr;
            if (curr.key < key) {
                curr = curr.right;
                x = "r";
            }
            else if (curr.key > key) {
                curr = curr.left;
                x = "l";
            }
            else {
                if (curr.address < address) {
                    curr = curr.right;
                    x = "r";
                }
                else if (curr.address > address) {
                    curr = curr.left;
                    x = "l";
                }
                else {
                    if (curr.size < size) {
                        curr = curr.right;
                        x = "r";
                    }
                    else {
                        curr = curr.left;
                        x = "l";
                    }
                }
            }
        }
        BSTree node = new BSTree(address,size,key);
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
        return node;
    }

    private BSTree getPred(){
        BSTree curr  = this.left;
        while (curr.right!=null){
            curr = curr.right;
        }
        return curr;
    }

    public boolean Delete(Dictionary e)
    {
        BSTree curr = this;
        while (curr.parent!= null){
            curr = curr.parent;
        }
        curr = curr.right;
        if (curr ==  null) return false;
        while (curr != null){
            if (curr.key < e.key) curr = curr.right;
            else if (curr.key > e.key) curr = curr.left;
            else {
                if (curr.address < e.address) curr = curr.right;
                else if (curr.address > e.address) curr = curr.left;
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
            if (curr == curr.parent.left){
                curr.parent.left = null;
            } else curr.parent.right = null;
        } else if (curr.left!=null && curr.right == null){
            if (curr == curr.parent.left){
                curr.parent.left = curr.left;
                curr.left.parent = curr.parent;
            } else {
                curr.parent.right = curr.left;
                curr.left.parent = curr.parent;
            }
       } else if (curr.left == null && curr.right != null){
            if (curr == curr.parent.left){
                curr.parent.left = curr.right;
                curr.right.parent = curr.parent;
            } else {
                curr.parent.right = curr.right;
                curr.right.parent = curr.parent;
            }
        } else{
            Dictionary suc = curr.getNext();
            Delete(suc);
            curr.key = suc.key;
            curr.address = suc.address;
            curr.size = suc.size;
        }
        return true;
    }



    public BSTree Find(int key, boolean exact)
    {
        BSTree curr = this.getFirst();
        while (curr!= null && curr.key < key){
            curr = curr.getNext();
        }
        if (curr == null) return null;
        if (exact && curr.key != key) return null;
        else return curr;
    }

    public BSTree getFirst()
    {
        BSTree curr = this;
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

    public BSTree getNext()
    {
        BSTree cur = this;
        if (this.parent == null) return null;
        if (cur.right != null) {
            cur = cur.right;
            while(cur.left!=null){
                cur = cur.left;
            }
            return cur;
        }
        BSTree child = cur;
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
        BSTree hare = this;
        BSTree tort = this;
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
        BSTree curr = tort;
        if (curr == null) return true;
        else return curr.inorder();
    }
    
    private boolean inorder(){
        BSTree curr = this;
        if (curr.left == null && curr.right == null) return true;
        else if (curr.left != null && curr.right == null){
            if (curr.left.parent != curr) return false;
            if (curr.left.key > curr.key) return false;
            else{
                if (curr.left.address > curr.address) return false;
                else{
                    if (curr.left.size >= curr.size) return false;
                    }
                }
            boolean l = curr.left.inorder();
            return l;
            }
        else if (curr.left == null && curr.right != null){
            if (curr.right.parent != curr) return false;
            if (curr.right.key < curr.key) return false;
            else{
                if (curr.right.address < curr.address) return false;
                else{
                    if (curr.right.size <= curr.size) return false;
                }
            }
            boolean r = curr.right.inorder();
            return r;
        }
        
        else{
            if (curr.left.parent != curr) return false;
            if (curr.left.key > curr.key) return false;
            else{
                if (curr.left.address > curr.address) return false;
                else{
                    if (curr.left.size >= curr.size) return false;
                }
            }
            
            if (curr.right.parent != curr) return false;
            if (curr.right.key < curr.key) return false;
            else{
                if (curr.right.address < curr.address) return false;
                else{
                    if (curr.right.size <= curr.size) return false;
                }
            }
            
            boolean l = curr.left.inorder();
            boolean r = curr.right.inorder();
            return l && r;
        }
    }
}





