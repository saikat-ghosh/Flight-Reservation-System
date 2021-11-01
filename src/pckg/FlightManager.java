/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author my
 */
public class FlightManager {                   //holds domestic flight details like src, dest, arr, dep etc
    String src;
    String dest;
    String freq;
    String fl_no;
    String arr;
    String dep;
    String via;
    String dfrom;
    String dtill;
    
    int flag=0;
    
    public void check_booking_history()        //check if selected flight is already booked or not
    {   
        try{
           try (BufferedReader br = new BufferedReader(new FileReader("./booking_history.txt"))) {  //reads booking history file
               String line;
               String s;
               while((line=br.readLine())!=null)                                                    //picks flight one by one
               {
                   StringTokenizer st= new StringTokenizer(line,"\\|");
                   s=st.nextToken();
                   if(s.trim().equalsIgnoreCase(this.fl_no)) {                                      //checks if selected flight alrdy exists in the booking history
                       flag=1;
                       break;                                                                       //if yes, flag is set & exits loop
                   } 
               }
               br.close();                                                                         //close booking hstry file
           }
           if(flag==0)                                                                             //flight is not booked before, send it for date checking
               checkdate();
       }
         catch(Exception e){
               JOptionPane.showMessageDialog(null,e);
             }
    }
    public void checkdate(){                                                                       //checks if selected flight is available on the date of journey or not
              
              SimpleDateFormat f= new SimpleDateFormat("dd MMM yy");                               //converts string into Date type object
              Date date,from,till;                                                                 //System defined java class to hold DATE objects
            
                            
                try{
              
              from= f.parse(this.dfrom);                                                           //parse flight's date of availibility valid from
              till= f.parse(this.dtill);                                                           //parse flight's date of availibility valid to
              
              date=f.parse(screen1.cb2.getSelectedItem().toString()+" "+screen1.cb3.getSelectedItem().toString()+" "+screen1.cb4.getSelectedItem().toString());   //parse user given date of journey
              if(date.compareTo(from)>0 && date.compareTo(till)<0)                                 //compare flight's availibility dates with the date of journey
                 if(this.freq.trim().equalsIgnoreCase("DAILY"))                                    //if yes, then check if the flight operates DAILY or not
                   writeDomFlight();                                                               //if yes, write the flight details to DOMFLIGHT file
                 else                                                                              //if flight is not DAILY, get the weekday of date of journey (Monday, Friday etc)
              {
                 DateFormat df=new SimpleDateFormat("EEEE");                                       //gets the weekday og date of journey
                 String day= df.format(date);                                                      //store the weekday into a string named 'day'
                 StringTokenizer stk= new StringTokenizer(this.freq.trim(),"\\,");                 //divide the frequency field of domestic flight like Monday, Tuesday etc
                 while(stk.hasMoreTokens())
                 {
                     if(day.equalsIgnoreCase(stk.nextToken().trim()))                              //checks tha 'day' string with the weekdays the flight operates
                         writeDomFlight();                                                         //if match found, write flight details in the DOMFLIGHT file
                 }
               }
              
           }
                catch(Exception e){
               JOptionPane.showMessageDialog(null,e);
             }
    }
 public void writeDomFlight()                                                                      //writes flight details in the DOMFLIGHT file
 {  try{
     try (BufferedWriter wr = new BufferedWriter(new FileWriter("./domflight.txt",true))) {        //opens DOMFLIGHT file
         wr.write(this.dest+"|");
         wr.write(this.fl_no+"|");                                                                 //writes only destination, flight no, arrival & departure; others details are not reqired
         wr.write(this.arr+"|");
         wr.write(this.dep+"\n");
     }
          }
             catch(Exception e){
               JOptionPane.showMessageDialog(null,e);
             }
 }
}

