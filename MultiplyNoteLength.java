import java.util.*;
import java.io.*;
import javax.swing.*;

public class MultiplyNoteLength {
   public static void main(String[]args) {
      ArrayList<String> ustFile = new ArrayList<String>();
      ImageIcon utauIco = new ImageIcon("utaulogo.png");
      boolean error = false;
      
      String fileName = JOptionPane.showInputDialog(null,"Enter UST name","UST Note Length Multiplier",JOptionPane.PLAIN_MESSAGE);
      String inFile = fileName + ".ust";
      String outFile = fileName + "-edited.ust";
      
      try {         
         Scanner reader = new Scanner(new File(inFile));
         while (reader.hasNext()) {
            ustFile.add(reader.nextLine());
         }
      } catch (Exception ex) {
         error = true;
         JOptionPane.showMessageDialog(null, "Could not read UST.", "UST Note Length Multiplier Error", JOptionPane.ERROR_MESSAGE);
      }
      
      if (!error) {
         String factorStr = JOptionPane.showInputDialog(null,"Enter multiplication factor","UST Note Length Multiplier",JOptionPane.PLAIN_MESSAGE);
         double factor = Double.parseDouble(factorStr);
         
         int tempoChoice = JOptionPane.showConfirmDialog(null,"Multiply the tempo too?", "UST Note Length Multiplier", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,utauIco);
      
         for (int i = 0; i < ustFile.size(); i++) {
            String line = ustFile.get(i);
            if (tempoChoice == 0 && line.length() > 6 && line.substring(0,6).equals("Tempo=")){
               double tempo = Double.parseDouble(line.substring(6));
               tempo *= factor;
               ustFile.set(i, "Tempo=" + tempo);
            }
            if (line.length() > 7 && line.substring(0,7).equals("Length=")){
               int length = Integer.parseInt(line.substring(7));
               length *= factor;
               ustFile.set(i, "Length=" + length);
            }
            if (line.length() > 9 && line.substring(0,9).equals("Duration=")){
               int duration = Integer.parseInt(line.substring(9));
               duration *= factor;
               ustFile.set(i, "Duration=" + duration);
            }
            if (line.length() > 6 && line.substring(0,6).equals("Delta=")){
               int delta = Integer.parseInt(line.substring(6));
               delta *= factor;
               ustFile.set(i, "Delta=" + delta);
            }
         }
         
         try {
            PrintWriter writer = new PrintWriter(new File(outFile));
            
            for (String line : ustFile) {
               writer.println(line);
            }
            
            writer.close();
            JOptionPane.showMessageDialog(null, "Done.", "UST Note Length Multiplier", JOptionPane.INFORMATION_MESSAGE,utauIco);
         } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Could not write UST.", "UST Note Length Multiplier Error", JOptionPane.ERROR_MESSAGE);
         }
      }
   }
}