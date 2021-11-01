/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author my
 */
public class DataManager {            
    
   public void readfile(){   
       try{
             BufferedReader br = new BufferedReader(new FileReader("./2015.spicejet.csv"));  //reads Domestic flight list (spicejet file)
               String line;
               String s;
               while((line=br.readLine())!=null)                                             //picks flights one by one
               {
                   StringTokenizer st= new StringTokenizer(line,"\\|");                     // divides its fields like source arrival departure etc
                   s=st.nextToken();
                   if(s.trim().equalsIgnoreCase(screen1.cb1.getSelectedItem().toString()))  //checks if source of flight equals user given source city
                   {
                       FlightManager db= new FlightManager();                               //if yes, then put flight details in fligtmangr object
                       db.src=s;
                       db.dest=st.nextToken();
                       db.freq=st.nextToken();
                       db.fl_no=st.nextToken();
                       db.arr=st.nextToken();
                       db.dep=st.nextToken();
                       db.via=st.nextToken();
                       db.dfrom=st.nextToken();
                       db.dtill=st.nextToken();
                       
                       db.check_booking_history();                                         //check if flight already booked or not
                   }
               }
               br.close();                                                                 //close spicejet file
               //wr.close();
           
       }
      catch(Exception e){
       JOptionPane.showMessageDialog(null,e);
       }
    
   }
   
 }
          
