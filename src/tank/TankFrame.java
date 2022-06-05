package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    Tank tank = new Tank(200, 200, Dir.DOWN, this);
    Bullet bullet = new Bullet(250, 550, Dir.UP);

    public TankFrame() throws HeadlessException {
        // 创建一个窗口
        Frame frame = new Frame();
        setSize(GAME_WIDTH, GAME_HEIGHT);
        // 拖动的时候不能改变窗口大小
        setResizable(false);
        setTitle("坦克大战");
        setVisible(true);
        addKeyListener(new MyKeyAdapter());
        // 点击可以关闭游戏窗口
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    class MyKeyAdapter extends KeyAdapter {
        boolean bU;
        boolean bD;
        boolean bL;
        boolean bR;
        boolean isMoving;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                default:
                    break;
            }
            setMainDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                default:
                    break;
            }
            setMainDir();
        }

        private void setMainDir() {
            // 这里判断写成了||
            if (!bU && !bD && !bL && !bR) {
                tank.setMoving(false);
                return;
            }
            tank.setMoving(true);
            if (bU) tank.setDir(Dir.UP);
            if (bD) tank.setDir(Dir.DOWN);
            if (bL) tank.setDir(Dir.LEFT);
            if (bR) tank.setDir(Dir.RIGHT);
        }
    }


    @Override
    public void paint(Graphics g) {
        // 坦克自己来决定自己行为
        tank.paintTank(g);
        // 子弹决定自己的行为
        bullet.paintButllet(g);
    }

    Image image = null;

    //消除闪烁现象 首先在内存中把这个图片弄出来，在一次画上，避免刷新导致闪烁
    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gg = image.getGraphics();
        Color color = gg.getColor();
        gg.setColor(Color.black);
        gg.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gg.setColor(color);
        paint(gg);
        g.drawImage(image, 0, 0, null);
    }
}
