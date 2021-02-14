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

public class GUI {

    private JFrame frame = new JFrame("Spam Test");
    private JLabel l1 = new JLabel("Message");
    private JLabel l2 = new JLabel("Delay");
    private JTextArea f1 = new JTextArea();
    private JSpinner f2 = new JSpinner();
    public JButton bu = new JButton("Spam");
    private JPanel pText = new JPanel();
    private JPanel pDelay = new JPanel();
    private JPanel pKnopf = new JPanel();
    private JComboBox cb = new JComboBox();
    private JPanel pCB = new JPanel();

    private boolean running = false;
    public boolean old = false;

    KeyboardSpammer keyboardSpammer = new KeyboardSpammer();
    MouseSpammer mouseSpammer = new MouseSpammer();

    public GUI(){
        try {
            fenster();
        } catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public static void main(String[] args){
        GUI sp = new GUI();
    }

    public void fenster(){

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

    public void buActionPerformed(ActionEvent evt){
        if(!running){
            f1.setEnabled(false);
            f2.setEnabled(false);
            bu.setText("Spamming in 3 seconds...");
            bu.setEnabled(false);
            running = true;
            if(old){
                mouseSpammer.mouseSpam_LeftClick((int)f2.getValue());
                try{
                    mouseSpammer.getThread().join();
                } catch (Exception e){
                    e.printStackTrace();
                }
                buActionPerformed(null);
            } else {
                keyboardSpammer.setRunning(running);
                keyboardSpammer.keyboardSpam_TextInput(f1.getText(),(int)f2.getValue());
                bu.setEnabled(true);
                bu.setText("Stop");
            }
        } else{
            running = false;
            keyboardSpammer.setRunning(running);
            f1.setEnabled(true);
            f2.setEnabled(true);
            bu.setEnabled(true);
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
