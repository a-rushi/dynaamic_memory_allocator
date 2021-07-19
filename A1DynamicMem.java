// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {

    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        if (blockSize<=0) return -1;
        Dictionary fmb = this.freeBlk;
        Dictionary amb = this.allocBlk;
        Dictionary block = fmb.Find(blockSize,false);
        if (block == null) return -1;
        amb.Insert(block.address, blockSize, block.address);
        fmb.Delete(block);
        if (block.key > blockSize) {
            fmb.Insert(block.address + blockSize, block.size - blockSize, block.size - blockSize);
        }
        return block.address;
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