package Timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {


    public static void main(String[] args) {
        Timer timer = new Timer("test");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("time is runing");
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        },2000 , 1000);


        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("time is cancel");
                new Timer("test").cancel();
            }
        },10000 , 20000);

    }

}
