import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
public class FileIO{
  char c;
  FileReader reader;
  PrintWriter writer;
  BufferedReader buff;
  CharList list;
  String nameOfFile;
  FileIO(String filename){
    nameOfFile = filename;
    list = new CharList();
  }
  void openInput(){
    try{
      reader = new FileReader(nameOfFile);
    }
    catch(IOException e){
      System.out.println("Error Reading File");
    }  
  }
  void openOutput(){
    try{
      writer = new PrintWriter(nameOfFile);
    }
    catch(IOException e){
      System.out.println("Error Reading File");
    }  
  }
  CharList importFileText(){
    int i;
    try{
      while((i=reader.read()) != -1){
        c = (char) i;
        list.add(c);
      } 
    }
    catch(IOException e){
      System.out.println("Error Reading File at FileIO.java Line:25");
    }
    return list;
  }
  void refresh(){
    try{
      reader.close();
    }
    catch(IOException e){
      System.out.println("Error Reading File");
    } 
  }
  String allText(){
    refresh();
    String contents = "";
    String temp;
    try{
      reader = new FileReader(nameOfFile);
      buff = new BufferedReader(reader);
      while((temp = buff.readLine()) != null){
        contents += temp;
      }
    }
    catch(IOException e){
      System.out.println("Error Reading the File.");
    }
    return contents;
  }
  void createChart(BitMap map){
    refresh();
    String temp;
    try{
      reader = new FileReader("EncodingChart.txt");
      buff = new BufferedReader(reader);
      while((temp = buff.readLine()) != null){
        temp = temp.replaceAll("\\s+",",");
        String tokens[] = temp.split(",");
        for(String s:tokens){
          String c = s.substring(0,s.indexOf("-"));
          if(c.equals("pad")){
            map.pad = s.substring((s.indexOf("-")+1));
          }
          else{
            int i = Integer.parseInt(c);
            map.encoding[i] = s.substring((s.indexOf("-")+1));
          }
        }
      }
    }
    catch(IOException e){
      System.out.println("Error Reading the File.");
    }
  }
  public void PrintOutput(String cText,BitMap map){
    //print the characters with their respective huffman encoding
    for(char c: map.keySet()){
      writer.print(c+map.get(c)+">");
      System.out.println(c+map.get(c)+">");
    }
    //add the base64 converted text
    writer.print("#"+cText);
    System.out.println("#"+cText);
    writer.close();
  }
}