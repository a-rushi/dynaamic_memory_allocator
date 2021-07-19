Invariants for sanity function: 
1) Parent of sentinel node has to be null
2) for any node n s.t. level(n) = l, level(n.parent) == l-1 && level(n.children) == l+1

Imposing that this.parent.child == this && this.child.parent == this along with the invariants, remove the possibilty of the tree containing a loop, which can be proved using PMI. 

Since duplicates are not added in the tree, sanity check returns false if duplicate is present in the tree. 