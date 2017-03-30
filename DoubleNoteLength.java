import java.util.*;
import java.io.*;
import javax.swing.*;

public class DoubleNoteLength {
   public static void main(String[]args) {
      ArrayList<String> ustFile = new ArrayList<String>();
      ImageIcon utauIco = new ImageIcon("utaulogo.png");
      boolean error = false;
      
      String fileName = JOptionPane.showInputDialog(null,"Enter UST name","UST Note Doubler",JOptionPane.PLAIN_MESSAGE);
      String inFile = fileName + ".ust";
      String outFile = fileName + "-doubled.ust";
      
      try {         
         Scanner reader = new Scanner(new File(inFile));
         while (reader.hasNext()) {
            ustFile.add(reader.next());
         }
      } catch (Exception ex) {
         error = true;
         JOptionPane.showMessageDialog(null, "Could not read UST.", "UST Note Doubler Error", JOptionPane.ERROR_MESSAGE);
      }
      
      int tempoChoice = 1;
      if (!error) {
         tempoChoice = JOptionPane.showConfirmDialog(null,"Double the tempo too?", "UST Note Doubler", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,utauIco);
      
         for (int i = 0; i < ustFile.size(); i++) {
            String line = ustFile.get(i);
            if (tempoChoice == 0 && line.length() > 6 && line.substring(0,6).equals("Tempo=")){
               double tempo = Double.parseDouble(line.substring(6));
               tempo *= 2;
               ustFile.set(i, "Tempo=" + tempo);
            }
            if (line.length() > 7 && line.substring(0,7).equals("Length=")){
               int length = Integer.parseInt(line.substring(7));
               length *= 2;
               ustFile.set(i, "Length=" + length);
            }
            
         }
         
         try {
            PrintWriter writer = new PrintWriter(new File(outFile));
            
            for (String line : ustFile) {
               writer.println(line);
            }
            
            writer.close();
            JOptionPane.showMessageDialog(null, "Done.", "UST Note Doubler", JOptionPane.INFORMATION_MESSAGE,utauIco);
         } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Could not write UST.", "UST Note Doubler Error", JOptionPane.ERROR_MESSAGE);
         }
      }
   }
}