class PriorityQueueNode extends HNode{

  public PriorityQueueNode(HNode left, HNode right) {
    super(left.count+right.count);
    c = left.c+right.c;
    this.left = left;
    this.right = right;
    left.parent = this;
    right.parent = this;

  }
  public PriorityQueueNode(PriorityQueueNode left, HNode right) {
    super(left.count+right.count);
    c = left.c+right.c;
    this.left = left;
    this.right = right;
    left.parent = this;
    right.parent = this;
    right.left = null;
    right.right = null;
    
  }
  public PriorityQueueNode(HNode left, PriorityQueueNode right) {
    super(left.count+right.count);
    c = left.c+right.c;
    this.left = left;
    this.right = right;
    left.parent = this;
    right.parent = this;
    left.left = null;
    left.right = null;
    
  }
  public PriorityQueueNode(PriorityQueueNode nodeL,PriorityQueueNode nodeR){
    super(nodeL.count+nodeR.count);
    System.out.println("PQN constructor called for " +nodeL +" "+nodeR);
    c = nodeL.c+nodeR.c;
    this.left = nodeL;
    this.right = nodeR;
    nodeL.parent = this;
    nodeR.parent = this;
  }
}
