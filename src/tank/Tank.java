package tank;

import java.awt.*;

// 这个类用来制造坦克
public class Tank {
    // 位置
    private int x, y;
    private boolean isMoving;
    // 移动方向
    private Dir dir;
    private TankFrame frame;
    // 移动速度
    private static final int SPEED = 5;

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public Tank(int x, int y, Dir dir, TankFrame frame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.frame = frame;
    }


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
        Color color = g.getColor();
        g.setColor(Color.blue);
        g.fillRect(x, y, 50, 50);
        g.setColor(color);
        if (isMoving) {
            move();
        }
    }

    private void move() {
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

    // 能够发射子弹
    public void fire() {
        // 子弹在这里被创建，但是只有在tankframe才能被绘制，so 需要持有它的引用
        Bullet bullet = new Bullet(x, y, dir);
        frame.bullet = bullet;
    }
}
