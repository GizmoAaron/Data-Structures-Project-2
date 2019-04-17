public class HTree{
  HNode root;//root of Huff Tree
  HNode head,end;//head and end ptr of sorted linked list
  String bitstring[];//array of 
  
  HTree(CharList list,BitMap map){
    //created a sorted linked list
    createLList(list);
    //send the list into a priority queue
    PriorityQueue q = new PriorityQueue(head, end,list.count);
    //create the Huffman tree
    root = q.heapify();
    //create the bitstrings for each char and save it to the bitmap
    for(int i=0;i<list.count;i++){
      String temp = getBitString(list.array[i]);
      map.add(list.array[i],temp);
    }
    //printHeap();
    //PrintList(root);
  }


  public void createLList(CharList list){
    head = new HNode(' ',-1);
    end = head;
    for(int i=0;i<list.count;i++){
      HNode temp=head;
      if(temp.right==null){
        temp.right = new HNode(list.array[i],list.charCount(i));
        temp.right.left = temp;
        end = temp.right;
      }
      else{
        sortInList(temp,i,list);
      }
    }
   //PrintList(head);
  }
  public void sortInList(HNode itr,int pos,CharList list){
    boolean inserted = false;
    int x = list.charCount(pos);
    while(!inserted && (itr.right!=null)){
      if(itr.right.count>x){
        itr = itr.right;
      }
      else{
        itr.right.left = new HNode(list.array[pos],x);
        itr.right.left.right = itr.right;
        itr.right = itr.right.left;
        itr.right.left = itr;
        inserted = true;
      }
    }
    if(!inserted){
      itr.right = new HNode(list.array[pos],x);
      itr.right.left = itr;
      end = itr.right;
    }
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
    if (n.isLeaf()) {
      System.out.println("^ is leaf.");
      return;
    }
    recursivePrintHeap(n.left, s);
    s[0] += "(" + n + ":" + n.count + ")";
    recursivePrintHeap(n.right, s);
  }
  public String getBitString(char ch){
    String bits[] = {""};
    search(root,ch,bits);
    return bits[0];
  }
  //method to recursively search huff tree and return bitstring
  public boolean search(HNode n,char ch,String[] s){
    if(n.isLeaf()){
      if(n.c.charAt(0)==ch) return true;
      else return false;
    }
    else{
      if(n.left.c.indexOf(ch)>-1){
        s[0] += "0";
        search(n.left,ch,s);
      }
      else if(n.right.c.indexOf(ch)>-1){
        s[0] += "1";
        search(n.right,ch,s);
      }
    }
    return false;
  }

  void PrintList(HNode h){
    String tuple[] = new String[2];
    tuple[0]="dummy-->";
    tuple[1]="dummy<--";
    HNode head = h.right;
    while(head!=null){
      if(head.right!=null){
        tuple[0] = tuple[0] + "|" + head.c + "," + head.count + "|-->";
        tuple[1] = tuple[1] + "|" + head.c + "," + head.count + "|<--";
      }
      else{
        tuple[0] = tuple[0] + "|" + head.c + "," + head.count + "|--> nothing";
        tuple[1] = tuple[1] + "|" + head.c + "," + head.count + "|<--nothing";
      }
      head = head.right;
    }
    System.out.println(tuple[0]);
    System.out.println(tuple[1]);
  }
}