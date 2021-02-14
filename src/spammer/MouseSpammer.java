package spammer;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MouseSpammer implements Runnable{

    private int clickAmount;
    Thread t;

    public void mouseSpam_LeftClick(int cA){
        clickAmount = cA;
        this.t = new Thread(this);
        t.start();
    }

    public Thread getThread(){
        return t;
    }

    @Override
    public void run() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int amount = clickAmount;
        try {
            Thread.sleep(3000);
        } catch (Exception ie) {
            ie.printStackTrace();
        }

        while (amount>0) {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            amount--;
        }
    }
}
