package spammer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyboardSpammer implements Runnable{

    private boolean running=false;
    private int delay;
    private String inputText;

    public void keyboardSpam_TextInput(String iT, int d){
        delay = d;
        inputText = iT;
        new Thread(this).start();
    }

    public void setRunning(boolean r){
        this.running = r;
    }

    @Override
    public void run() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int[] keys = new int[inputText.length()];
        if (delay < 0) {
            delay =0;
        }
        for (int i = 0; i < keys.length; i++) {
            keys[i] = KeyEvent.getExtendedKeyCodeForChar(inputText.charAt(i));
        }
        try {
            Thread.sleep(3000);
        } catch (Exception ie) {
            ie.printStackTrace();
        }
        while (running) {
            for (int i = 0; i < keys.length; i++) {
                if(inputText.charAt(i)=='/'){
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_7);
                    robot.keyRelease(KeyEvent.VK_7);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
                if(inputText.charAt(i)=='_') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_MINUS);
                    robot.keyRelease(KeyEvent.VK_MINUS);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
                else {
                    robot.keyPress(keys[i]);
                    robot.keyRelease(keys[i]);
                }
            }
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
