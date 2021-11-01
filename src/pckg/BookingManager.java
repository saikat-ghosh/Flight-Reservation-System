/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckg;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author my
 */
public class BookingManager extends DisplayManager             //uses all the data fields of DisplayManager class
{
      
  public void bookFlight()             //write both selected flight details & user given passanger details into a separate file named 'BOOKING_HISTORY'
  {
      try 
      {
          booking_nmbr= shortUUID().toUpperCase();              //call shortUUID method to get an unique Booking ID for each user
          
          try (BufferedWriter wrt = new BufferedWriter(new FileWriter("./booking_history.txt",true))) {    //opens BOOKING_HISTORY file
              wrt.write(this.dom_fl_no+"|");                                                               //write all details
              wrt.write(this.src+"|");
              wrt.write(this.dest+"|");
              wrt.write(this.via+"|");
              wrt.write(this.intl_fl_no+"|");
              wrt.write(this.date_of_journey+"|");
              wrt.write(this.Passenger_name+"|");
              wrt.write(this.email_id+"|");
              wrt.write(this.phn_no+"|");
              wrt.write(this.booking_nmbr+"\n");
          }
       }
             catch(NumberFormatException | IOException e){
               JOptionPane.showMessageDialog(null,e);
             }
  }
  
  public static String shortUUID()                               //generates an unique Booking ID for each user
  {
    UUID uuid = UUID.randomUUID();
    long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
    return Long.toString(l, Character.MAX_RADIX);
   }
  
}
