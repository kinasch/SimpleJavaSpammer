import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spammer.GUI;

import static org.junit.jupiter.api.Assertions.*;

class GUITest {
    GUI testGUI;
    @BeforeEach
    void setUp() {
        testGUI = new GUI();
    }

    @Test
    void mouseSpamming() {
        //This test should only check whether the GUI restrictions and elements work while the mouse spamming is done.
        testGUI.old = true;
        testGUI.buActionPerformed(null);
        assertEquals(testGUI.bu.isEnabled(),true);
    }
    @Test
    void keyboardSpamming() {
        //This test should only check whether the GUI restrictions and elements work while the keyboard spamming is done.
        testGUI.old = false;
        testGUI.buActionPerformed(null);
        assertEquals(testGUI.bu.getText(),"Stop");
    }

    @AfterEach
    void cleanUp(){
        testGUI.buActionPerformed(null);
    }
}
