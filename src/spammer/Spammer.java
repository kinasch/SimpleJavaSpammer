package spammer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Component.LEFT_ALIGNMENT;

/**
 *
 * @author kinasch
 * @version 1.0 *
 */

public class Spammer implements Runnable {

    private JFrame frame = new JFrame("Spam Test");
    private JLabel l1 = new JLabel("Message");
    private JLabel l2 = new JLabel("Delay");
    private JTextArea f1 = new JTextArea();
    private JSpinner f2 = new JSpinner();
    private JButton bu = new JButton("Spam");
    private JPanel pText = new JPanel();
    private JPanel pDelay = new JPanel();
    private JPanel pKnopf = new JPanel();
    private JComboBox cb = new JComboBox();
    private JPanel pCB = new JPanel();

    private boolean running = false;
    private boolean old = false;

    public Spammer(){
        try {
            fenster();
        } catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public static void main(String[] args){
        Spammer sp = new Spammer();
    }

    public void fenster(){
        //WIP, weil ich keine Ahnung habe ngl

        f1.setPreferredSize(new Dimension(100,60));
        f1.setAlignmentX(LEFT_ALIGNMENT);
        f1.setBorder(BorderFactory.createLineBorder(Color.black));
        f2.setPreferredSize(new Dimension(100,20));

        bu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buActionPerformed(e);
            }
        });

        String[] sa = {"TextSpammer", "MouseClickSpammer"};
        cb.addItem(sa[0]);
        cb.addItem(sa[1]);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbActionPerformed(e);
            }
        });

        pCB.add(cb);

        pText.add(l1);
        pText.add(f1);

        pDelay.add(l2);
        pDelay.add(f2);

        pKnopf.add(bu);

        frame.getContentPane().add(pCB);
        frame.getContentPane().add(pText);
        frame.getContentPane().add(pDelay);
        frame.getContentPane().add(pKnopf);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));

        frame.setSize(300,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void run(){
        if(old) {
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int interval = (int) f2.getValue();
            try {
                Thread.sleep(5000);
            } catch (Exception ie) {
                ie.printStackTrace();
            }
            bu.setEnabled(true);
            bu.setText("Stop");
            while (interval>0) {
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
                interval--;
            }
            buActionPerformed(null);
        } else if(!old){
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int[] keys = new int[f1.getText().length()];
            if ((int) f2.getValue() < 0) {
                f2.setValue(0);
            }
            int interval = (int) f2.getValue();
            for (int i = 0; i < keys.length; i++) {
                keys[i] = KeyEvent.getExtendedKeyCodeForChar(f1.getText().charAt(i));
            }
            try {
                Thread.sleep(3000);
            } catch (Exception ie) {
                ie.printStackTrace();
            }
            bu.setEnabled(true);
            bu.setText("Stop");
            int counter = 0;
            while (running) {
                for (int i = 0; i < keys.length; i++) {
                    if(f1.getText().charAt(i)=='/'){
                        robot.keyPress(KeyEvent.VK_SHIFT);
                        robot.keyPress(KeyEvent.VK_7);
                        robot.keyRelease(KeyEvent.VK_7);
                        robot.keyRelease(KeyEvent.VK_SHIFT);
                    }
                    if(f1.getText().charAt(i)=='_') {
                        robot.keyPress(KeyEvent.VK_SHIFT);
                        robot.keyPress(KeyEvent.VK_MINUS);
                        robot.keyRelease(KeyEvent.VK_MINUS);
                        robot.keyRelease(KeyEvent.VK_SHIFT);
                    }
                    else {
                        robot.keyPress(keys[i]);
                        //robot.keyRelease(keys[i]);
                    }
                }
                try {
                    Thread.sleep(interval);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                counter++;
            }
        }
    }

    public void buActionPerformed(ActionEvent evt){
        if(!running){
            f1.setEnabled(false);
            f2.setEnabled(false);
            bu.setText("Spamming in 3 seconds...");
            bu.setEnabled(false);
            running = true;
            new Thread(this).start();
        } else{
            running = false;
            f1.setEnabled(true);
            f2.setEnabled(true);
            bu.setText("Spam");
        }
    }

    public void cbActionPerformed(ActionEvent evt){
        String name = (String)cb.getSelectedItem();
        if(name.equals("MouseClickSpammer")){
            old = true;
            f1.setVisible(false);
            l1.setVisible(false);
            l2.setText("Repeats: ");
        } else if(name.equals("TextSpammer")){
            old = false;
            f1.setVisible(true);
            l1.setVisible(true);
        }
    }
}
