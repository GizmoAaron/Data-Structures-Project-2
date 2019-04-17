public class HNode{
  String c;
  int count;
  HNode left, right, parent;
  public HNode(){
    c = "";
    count = 0;
  }
  public HNode(int size){
    c = "";
    count = size;
  }
  public HNode(char aChar, int theCount) {
    c = "" + aChar;
    count = theCount;
  }

  public HNode(HNode left, HNode right) {
    System.out.println(left.toString() + right.toString());
    c = left.c + right.c;
    count = left.count + right.count;
    this.left = left;
    left.parent = this;
    this.right = right;
    right.parent = this;
  }
  public void setLeaf(){
    left = null;
    right = null;
  }

  public String toString() {
    return c + ":" + count;
  }
  public boolean isLeaf() {
    return (left == null && right == null);
  }
  public boolean greaterThan(HNode node){
    return this.count > node.count;
  }
}