/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckg;

/**
 *
 * @author my
 */
public class DisplayManager                  //displays both selected flight details & user given passanger details 
{
    String src;
    String via;
    String dest;
    String dom_fl_no;
    String date_of_journey;
    String phn_no;
    String intl_fl_no;
    String Passenger_name;
    String email_id;
    String booking_nmbr;
    
   public void display()                                 //display details on screen4 GUI
   {
      screen4.jLabel12.setText(booking_nmbr);
      screen4.jLabel13.setText(Passenger_name);
      screen4.jLabel14.setText(date_of_journey);
      screen4.jLabel15.setText(screen1.cb5.getSelectedItem().toString());
      screen4.jLabel16.setText(src);
      screen4.jLabel17.setText(dest);
      screen4.jLabel18.setText(via);
      screen4.jLabel19.setText(dom_fl_no+" ("+src.toUpperCase()+" to "+via.toUpperCase()+" )");
      screen4.jLabel20.setText(intl_fl_no+" ("+via.toUpperCase()+" to "+dest.toUpperCase()+" )");
      screen4.jLabel22.setText(email_id);
    }
   
}
