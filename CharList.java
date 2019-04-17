import java.util.HashMap;

public class CharList extends HashMap<Character,Integer> {
  char array[];//array of characters that were recorded from the input file
  int count;//count of how many character were recorded
  CharList(){
    super();
    array = new char[256];
    count = 0;
  }
  boolean charExists(char c){
    return this.containsKey(c);
  }
  void add(char c){
    if(charExists(c)){
      this.put(c,this.get(c)+1);
    }
    else{
      this.put(c,1);
      array[count] = c;
      count++;
    }
  }
  //takes int position of char in array and returns the frequency,from the Hashmap of that char.
  int charCount(int pos){
    return this.get(array[pos]);
  }

}