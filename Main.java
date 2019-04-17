class Main {
  public static void main(String[] args) {
    //create a structure to store characters and frequency
    CharList myList;
    //create a file io object
    FileIO in = new FileIO("input.txt");
    //set file io to get data from file
    in.openInput();
    //load data to list
    myList = in.importFileText();
    //create structure to map characters to their huffman coding equivalent
    BitMap map = new BitMap();
    //create the Huffman tree
    HTree tree = new HTree(myList,map);
    //store the entire contents of the text file
    String text = in.allText();
    //coverts text to bitstring using the map
    text = map.convert(text);
    //save encoding chart to bitmap object
    in.createChart(map);
    //converts bitstring to base64 characters
    String output = base64Conversion(map,text);
    //create file io object to store compressed text
    FileIO out = new FileIO("compressed.txt");
    //set object to store data
    out.openOutput();
    //print to file and system console 
    out.PrintOutput(output,map);
    //Decompress text
    Decompress("compressed.txt");

    
  }
  public static String base64Conversion(BitMap map,String bits){
    String output = "";
    int i = 0;
    int j = 6;
    while(j<=bits.length()){
      //grab a group of 6 bits
      String ss = bits.substring(i,j);
      System.out.println(ss);
      //convert to integer value
      int val = Integer.parseInt(ss,2);
      //get base64 char for said value
      output += map.encoding[val];
      i+=6;
      j+=6;
    }
    String ss = bits.substring(i);
    System.out.println(ss);
    if(ss.equals("")) return output;
    //find the length of the String
    int endBits = ss.length();
    //pad the lack of bits
    int extra = 6 - endBits;
    for(int k=0;k<extra;k++){
      output += map.pad;
    }
    int val = Integer.parseInt(ss,2);
    output += map.encoding[val];
    return output;
  }
  public static String base64Reconstruction(BitMap map, String ctext){//ctext being the converted text
    String bitstring = "";
    boolean padsFound = false;
    //for each char in the text
    for(int i=1;i<ctext.length();i++){
      //convert char to integer value using chart
      String temp = "" + ctext.charAt(i);
      int j = 0;
      boolean isPad = false;
      if(!temp.equals(map.pad)){
        boolean found = false;
        while(!found){
          if(temp.equals(map.encoding[j])){
            found = true;
          }
          else{
            j++;
          }
        }
      }
      else{
        isPad = true;
        padsFound = true;
      }
      String numTobits = "";
      if(!isPad){
        //take the integer value and converts it to bits
        numTobits = Integer.toBinaryString(j);
        //if there were zeros cut off from the front
        if(numTobits.length()<6 && !padsFound){
          int numZ = 6 - numTobits.length();  
          for(int k=0;k<numZ;k++){
            numTobits = "0" + numTobits;
          }    
        }
      }
      //add bits to result string
      bitstring += numTobits;
    }
    return bitstring;
  }
  public static void Decompress(String filename){
    FileIO input = new FileIO(filename);
    input.openInput();
    //grab the contents of the file
    String text = input.allText();
    //parse and seperate the character mappings from the compressed text
    String ary[] = text.split(">");
    String base64Text = "";
    //save the char mappings to a bitmap
    BitMap map = new BitMap();
    for(String s:ary){
      if(s.indexOf("#")==-1){
        map.add(s.charAt(0), s.substring(1));
      }
      else{
        base64Text = s;
      }
    }
    //convert compressed text back to bitstring
    input.createChart(map);
    String bits = base64Reconstruction(map,base64Text);
    //parse bitstring using the map
    CharMap rmap = new CharMap(map);//this is a reverse of the bitmap
    String decompressedText = rmap.reconstruct(bits);
    //save converted string to output file
    FileIO out = new FileIO("output.txt");
    out.openOutput();
    out.writer.print(decompressedText);
    out.writer.close();
  }
}