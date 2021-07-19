// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.
public class A2DynamicMem extends A1DynamicMem {

    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    //Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, order them by address. Now think outsid e the scope of the allocation problem and think of handling tiebreaking in blocks, in case key is neither of the two.

    public void Defragment() {
        Dictionary addr;
        if (freeBlk.getClass().getName() == "BSTree") addr = new BSTree();
        else addr = new AVLTree();
        Dictionary fmb = this.freeBlk;
        Dictionary curr = fmb.getFirst();
        while(curr!=null) {
            addr.Insert(curr.address, curr.size, curr.address);
            curr = curr.getNext();
        }
        curr = addr.getFirst();
        if (curr == null) return;
        Dictionary succ = curr.getNext();
        if (succ == null) return;
        while (succ!=null){
            if ((curr.size + curr.key) == succ.key) {
                AVLTree cur = new AVLTree(curr.address, curr.size, curr.size);
                AVLTree suc = new AVLTree(succ.address, succ.size, succ.size);
                fmb.Delete(cur);
                fmb.Delete(suc);
                fmb.Insert(curr.address, cur.size + suc.size, cur.size + suc.size);
                int cadd = curr.address;
                int csize = curr.size;
                int ssize = succ.size;
                addr.Delete(curr);
                addr.Delete(succ);
                curr = addr.Insert(cadd, csize + ssize, cadd);
            } else {
                curr = succ;
            }
            succ = curr.getNext();
        }
        addr = null;
        return;
    }

    public int Allocate(int blockSize) {
        if (blockSize<=0) return -1;
        Dictionary fmb = this.freeBlk;
        Dictionary amb = this.allocBlk;
        Dictionary block = fmb.Find(blockSize,false);
        if (block == null) return -1;
        amb.Insert(block.address, blockSize, block.address);
        int add = block.address;
        int size = block.size;
        int key = block.key;
        fmb.Delete(block);
        if (size > blockSize) {
            fmb.Insert(add + blockSize, size - blockSize, size - blockSize);
        }
        return add;
    }

    public int Free(int startAddr) {
        Dictionary fmb = this.freeBlk;
        Dictionary amb = this.allocBlk;
        Dictionary block = amb.Find(startAddr,true);
        if (block == null) return -1;
        fmb.Insert(block.address,block.size,block.size);
        amb.Delete(block);
        return 0;
    }

}