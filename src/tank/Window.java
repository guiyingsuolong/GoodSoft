package tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {
    public static void main(String[] args) throws InterruptedException {
        // beizhu
        TankFrame tankFrame = new TankFrame();
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
