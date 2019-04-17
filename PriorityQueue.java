import javax.lang.model.util.ElementScanner6;

class PriorityQueue {
  HNode root;
  int size;
  HNode Head,End;
  HNode ary[];

  public PriorityQueue(HNode head,HNode end,int s) {
    root = null;
    size = s;
    this.Head = head;
    this.End = end;
    ary = new HNode[size];
  }
  public HNode heapify(){
    HNode temp = Head.right;
    int i=0;
    while(temp!=null){
      ary[i] = temp;
      i++;
      temp = temp.right;
    }
    i--;
    for(int j=0;j<i+1;j++)  ary[j].setLeaf();
    while(i>=1){
      //pop the smallest 2 nodes
      HNode x = ary[i];
      ary[i] = null;
      i--;
      HNode y = ary[i];
      ary[i] = null;
      i--;
      //combine
      HNode newEnd = combine(x,y);
      //sort array
      sort(ary,i,newEnd);
      i++;
      //System.out.println("size of ary = " + (i+1));
      //System.out.println("size of root = " + ary[0].count);
    }
    return ary[0];
  }
  public void print(HNode a[]){
    int i=0;
    while(i<16){
      System.out.print(a[i]);
      i++;
    }
    System.out.println();
  }
  public PriorityQueueNode combine(HNode m,HNode n) {
    //where n is the smallest node, and m is the 2nd smallest
    //System.out.println("|"+m.toString()+" "+n.toString()+"|");
    PriorityQueueNode huffNode = new PriorityQueueNode(m,n);
    return huffNode;
  }
  public void sort(HNode a[],int i,HNode newNode){
    if(i<0){
      a[0] = newNode;
      return;
    }
    boolean found = false;
    while(!found){
      int x = i+1;
      HNode prev = a[i];
      HNode empty = a[x];
      if(i==0){
        a[x] = prev;
        a[i] = newNode;
        found = true;
      }
      else if(!newNode.greaterThan(prev)){
        empty = newNode;
        a[x] = empty;
        found = true;
      }
      else{
        a[x] = prev;
        i--;
      }
    }
  }
  public void sort(HNode hN,HNode eN){
    //hN head node, eN end node
    //iterate back to front and sort the new node into the sorted list
    HNode itr = eN.left;
    //if(hN==itr) return;
    if(eN.greaterThan(hN.right)){
      swapToFront(hN.right,eN);
      return;
    }
    while(itr.parent!=hN && eN.greaterThan(itr)){
      itr = itr.left;
    }
    swap(itr,eN);
  }
  // helper function: Sorts b between a > priority node and a < node
  private void swap(HNode N, HNode newN) {
    System.out.println("Swapping "+N+" with "+newN);
    newN.right = N.right;
    N.right.left = newN;
    newN.left.right = null;
    newN.left = N;
    N.right = newN;
  }
  // also a helper fucnction but not utilized.
  private void swapToFront(HNode oldH,HNode newH){
    newH.left.right = null;
    newH.left = null;
    newH.right = oldH;
    oldH.left = newH;
    oldH.parent.right = newH;
  }
  // helper function: print the entire heap inorder for easier debugging
  public void printHeap() {
    String[] s = { "Heap(inorder)=" };
    recursivePrintHeap(root, s);
    System.out.println(s[0]); // array is passed by reference, string is not
  }

  private void recursivePrintHeap(HNode n, String[] s) {
    // prints the heap in order
    System.out.println(n);
    if (n.isLeaf()) return;
    recursivePrintHeap(n.left, s);
    s[0] += "(" + n + ":" + n.count + ")";
    recursivePrintHeap(n.right, s);
  }
}