import java.util.HashMap;
//this object is basically the reverse of the bitmap
//and has the reverse function of the bitmap(convert)
public class CharMap extends HashMap<String, Character>{
  CharMap(BitMap map){
    super();
    for(char i : map.keySet()){
      this.put(map.get(i),i);
    }
  }
  String reconstruct(String encodedBits){
    int i = 0;//beginning of substring
    int j = 1;//end of substring
    String rawText = "";
    while(i < encodedBits.length()){
      String temp = encodedBits.substring(i,j);
      if(this.containsKey(temp)){
        rawText += this.get(temp);
        i = j;
        j = i+1;
      }
      else{
        j++;
      }
    }
    return rawText;
  }
}