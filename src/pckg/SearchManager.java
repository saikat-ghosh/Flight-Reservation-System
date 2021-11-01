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
import java.io.IOException;
import java.text.*;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author my
 */
public class SearchManager {       //finds international flights according domestic flights selected earlier
    
    String duration;               //'dom' stands for domestic , 'intl' means international
    String dest;
    String intl_freq;
    String dom_fl_no;
    String dom_arr;
    String dom_dep;
    String intl_fl_no;
    String intl_arr;
    String intl_dep;
    
    public void readDomFlight()                         //reads the DOMFLIGHT file
    {
        try{
       BufferedReader b= new BufferedReader(new FileReader("./domflight.txt"));           //opens the DOMFLIGHT file
       String line;
       while((line=b.readLine())!=null)                                                   //pick domestic flights one by one
           { 
           StringTokenizer st= new StringTokenizer(line,"\\|");                           //divides its fields like dest ,arr, dep, flight no.
                   this.dest= st.nextToken().trim();
                   this.dom_fl_no= st.nextToken().trim();
                   this.dom_dep= st.nextToken().trim();
                   this.dom_arr= st.nextToken().trim();
                   searchFlight();                                         //call searchFlight method which searches for corresponding Intl Flights
           }
        }
       catch(Exception e){
       JOptionPane.showMessageDialog(null,e);
       }
    }
    
    
    public void searchFlight()                                    //searches for corresponding Intl Flights
    {
      try
      {
        String s,line;
        Date date;                                                //parse date of journey to its corresponding Date object
          SimpleDateFormat f= new SimpleDateFormat("dd MMM yy");
          date=f.parse(screen1.cb2.getSelectedItem().toString()+" "+screen1.cb3.getSelectedItem().toString()+" "+screen1.cb4.getSelectedItem().toString());
          DateFormat df=new SimpleDateFormat("EEE");
          String day= df.format(date);                           // get the weekday of date of journey
          
       BufferedReader r= new BufferedReader(new FileReader("./2015.silkair.csv"));            //read the Intl flights file ( silkair file )
         while((line=r.readLine())!=null)                                                     //picks Intl flight one by one
           { 
              StringTokenizer str= new StringTokenizer(line,"\\|");                           //divides its fields
                s=str.nextToken();
                 if(s.trim().equalsIgnoreCase(this.dest))                                     //checks if destination of dom flight matches with Intl flight source city
                 {
                   this.intl_freq= str.nextToken().trim();                                    //if yes, get the Intl flight details
                   this.intl_fl_no= str.nextToken().trim();
                   this.intl_dep= str.nextToken().trim();
                   this.intl_arr= str.nextToken().trim();
                  
                   StringTokenizer stk= new StringTokenizer(this.intl_freq,"\\,");            //separates frequency of Intl flights
                   while(stk.hasMoreTokens())
                   {
                     if(day.equalsIgnoreCase(stk.nextToken().trim()))                         //checks weekday of date of journey with Intl flight's running days
                         checktime();                                                         //if match found, select the Intl flight & sends for time checking
                   }                   
                 }
           }
        }
        catch(ParseException | IOException e){
       JOptionPane.showMessageDialog(null,e);
       } 
    }
   
   public void checktime()                                //check time gap between Dom flight & Intl flight
    {   int m,h;
        Date darr,ddep,iarr,idep;
        try
        {
        StringTokenizer st= new StringTokenizer(this.intl_arr,"\\+");   //for calculating Intl arrival time in case of '0610+1' format
        if(st.countTokens()>1)
        {
            h=Integer.parseInt(st.nextToken());
            m=100*Integer.parseInt(st.nextToken());
            String pattern="0000";
           DecimalFormat myFormatter = new DecimalFormat(pattern);
           this.intl_arr= myFormatter.format(h+m);
         }
        SimpleDateFormat f1= new SimpleDateFormat("K:mm a");                       //to parse domestic flight timings
        SimpleDateFormat f2= new SimpleDateFormat("HHmm");                         //to parse Intl flight timings
        darr=f1.parse(this.dom_arr);
        ddep=f1.parse(this.dom_dep);
        iarr=f2.parse(this.intl_arr);
        idep=f2.parse(this.intl_dep);
        
              long diff= idep.getTime()-darr.getTime();                            //calculate time gap between dom flight arrival & Intl flight departure in MILI_SECONDS
              long hr=(diff/(60*1000*60))%24;                                      //converts time gap from MILI_SECONDS to corresponding hour value
              if(hr>=2 && hr<=6)                                                   //checks if time gap in HOURS fall between 2-6 hours range
              {
                  long dur=iarr.getTime()-ddep.getTime();                          //if yes, then calculate the Duration of entire journey
                  long mnt=((dur/(60*1000))%60);
                   if(mnt<0)
                       mnt=60+mnt;
                  long hour=((dur/(60*1000*60))%24);
                   if(hour<0)
                       hour=23+hour;
                  this.duration=hour+"h "+mnt+"m";
                  
                  writeComboFlight();                                              //call writeComboFlight method to write all details
              }
        }
        catch(NumberFormatException | ParseException e){
               JOptionPane.showMessageDialog(null,e);
             }
    }
    
    
   public void writeComboFlight()                             //write both domestic & corresponding Intl flight details to a separate file named COMBOFLIGHT
    { 
        try
        {   String s="AM";                                    //adds AM or PM according to Arrival & Departure timings of Intl flight
            int h,m;
             m=Integer.parseInt(this.intl_arr)%100;
             h=Integer.parseInt(this.intl_arr)/100;
             if(h>12)
             {
                 h=h-12;
                 s="PM";
             }
             
            try (BufferedWriter wrt = new BufferedWriter(new FileWriter("./comboflight.txt",true))) {     //opens COMBOFLIGHT file
                wrt.write(this.dom_fl_no+"|");                                                            //write both flight's details
                wrt.write(this.dom_dep+"|");
                wrt.write(this.dest+"|");
                wrt.write(this.intl_fl_no+"|");
                wrt.write(h+":"+m+" "+s+"|");
                wrt.write(this.duration+"\n");
            }
          }
             catch(NumberFormatException | IOException e){
               JOptionPane.showMessageDialog(null,e);
             }
    }
    
    
    
}
