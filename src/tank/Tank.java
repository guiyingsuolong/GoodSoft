package tank;

import java.awt.*;

// 这个类用来制造坦克
public class Tank {
    // 位置
    private int x, y;

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    // 移动方向
    private Dir dir;
    // 移动速度
    private static final int SPEED = 10;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    // 坦克如何运动应该由坦克自己来决定
    public void paintTank(Graphics g) {
        g.fillRect(x, y, 50, 50);
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
    }
}
