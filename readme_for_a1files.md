Invariants of sanity:

1) tail.next == null
2) head.prev == null
3) this.next.prev == this;
4) this.prev.next == this;
5) if curr = head
   repeat until curr == null {cur = head.next} : this look should stop after a while
6) head == (-1,-1,-1)
7) tail == (-1,-1,-1)
