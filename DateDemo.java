import java.util.Date;
import java.text.*;
class DateDemo{
  public static void main(String[] args){
    Date date = new Date();
   

    System.out.println(date);

    SimpleDateFormat ft = new SimpleDateFormat("EEEEEEE dd-MMMM-yyyy 'at' hh:mm:ss a zzz");
  
    System.out.println(ft.format(date));
  }
}
