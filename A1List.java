// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node

    public A1List(int address, int size, int key) {
        super(address, size, key);
    }

    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel

        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List newNode = new A1List(address,size,key);
        newNode.next = this.next;
        this.next.prev = newNode;
        this.next = newNode;
        newNode.prev = this;
        return newNode;
    }

    public boolean Delete(Dictionary d) {
        A1List fwd;
        A1List bwd;
        fwd = bwd = this;
        if (d == null) return false;
        while ((fwd.next != null) && (fwd.key != d.key || fwd.address != d.address || fwd.size != d.size)) {
            fwd = fwd.next;
        }
        if (fwd.key == d.key && fwd.address == d.address && fwd.size == d.size) {
            fwd.prev.next = fwd.next;
            fwd.next.prev = fwd.prev;
            return true;

        } else {
            while ((bwd.prev != null) && (bwd.key != d.key || bwd.address != d.address || bwd.size != d.size)) {
                bwd = bwd.prev;
            }

            if (bwd.key == d.key && bwd.address == d.address && bwd.size == d.size) {
                bwd.prev.next = bwd.next;
                bwd.next.prev = bwd.prev;
                return true;
            }
            return false;
        }
    }

    public A1List Find(int k, boolean exact)
    {
        A1List node;
        A1List fwd;
        node = fwd = this.getFirst();
        if (node == null) return null;
        while(fwd.next != null){
            if (fwd.key >= k){
                node = fwd;
            }
            if (exact && node.key == k){
                return node;
            } else if(!exact && node.key >= k){
                return node;
            }
            fwd = fwd.next;
        }
        return null;
    }

    public A1List getFirst()
    {
        A1List curr = this;
        while (curr.key != -1 || curr.prev != null ){
            curr = curr.prev;
        }
        if (curr.next.key == -1 && curr.next.next == null){
            return null;
        }
        return curr.next;
    }

    public A1List getNext()
    {
        if (this.next.key == -1 && this.next.next == null){
            return null;
        }
        return this.next;
    }

    public boolean sanity() {
        A1List hare;
        A1List tort;
        hare = tort = this;

        while (hare != null && tort != null) {
            if (tort.prev != null) {
                if (tort.prev.next != tort) return false;
            } else {
                if (tort.key != -1 || tort.size != -1 || tort.address != -1) return false;
            }
            if (tort.next != null) {
                if (tort.next.prev != tort) return false;
            } else {
                if (tort.key != -1 || tort.size != -1 || tort.address != -1) return false;
            }

            tort = tort.next;
            if (hare.next == null) hare = null;
            else {
                hare = hare.next.next;
            }

            if (hare == tort) break;
        }

        if (hare == tort && tort != null) return false;
        else {
            while (tort != null) {
                if (tort.prev != null) {
                    if (tort.prev.next != tort) return false;
                } else {
                    if (tort.key != -1 || tort.size != -1 || tort.address != -1) return false;
                }
                if (tort.next != null) {
                    if (tort.next.prev != tort) return false;
                } else {
                    if (tort.key != -1 || tort.size != -1 || tort.address != -1) return false;
                }

                tort = tort.next;
            }
        }

        hare = tort = this;

        while (hare != null && tort != null) {
            if (tort.prev != null) {
                if (tort.prev.next != tort) return false;
            } else {
                if (tort.key != -1 || tort.size != -1 || tort.address != -1) return false;
            }
            if (tort.next != null) {
                if (tort.next.prev != tort) return false;
            } else {
                if (tort.key != -1 || tort.size != -1 || tort.address != -1) return false;
            }

            tort = tort.prev;
            if (hare.prev == null) hare = null;
            else {
                hare = hare.prev.prev;
            }

            if (hare == tort) break;
        }

        if (hare == tort && tort != null) return false;
        else {
            while (tort != null) {
                if (tort.prev != null) {
                    if (tort.prev.next != tort) return false;
                } else {
                    if (tort.key != -1 || tort.size != -1 || tort.address != -1) return false;
                }
                if (tort.next != null) {
                    if (tort.next.prev != tort) return false;
                } else {
                    if (tort.key != -1 || tort.size != -1 || tort.address != -1) return false;
                }

                tort = tort.prev;
            }
        }
        return true;
    }
}

