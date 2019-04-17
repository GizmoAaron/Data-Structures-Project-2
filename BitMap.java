import java.util.HashMap;

public class BitMap extends HashMap<Character, String>{
  String encoding[];
  String pad;
  BitMap(){
    super();
    encoding = new String[64];
  }
  void add(char c, String s){
    this.put(c,s);
  }
  String convert(String rawText){
    String encodedBits = "";
    for(int i=0;i<rawText.length();i++){
      encodedBits += this.get(rawText.charAt(i));
    }
    return encodedBits;
  }
}