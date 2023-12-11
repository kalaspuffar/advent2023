package org.ea.day10;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Position {

    public static int NORTH = 0;
    public static int SOUTH = 1;
    public static int EAST = 2;
    public static int WEST = 3;

    private int x;
    private int y;
    private int direction;
    private long count;

    private boolean canMove = true;

    private GeneralPath gp = new GeneralPath(GeneralPath.WIND_EVEN_ODD);

    public Position(int x, int y) {
        this.x = x;
        this.y = y;

        gp.moveTo((x + 1) * 10, (y + 1) * 10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long getCount() {
        return count;
    }

    public void move(int direction) {
        count++;
        this.direction = direction;
        if (direction == NORTH) {
            y++;
        }
        if (direction == SOUTH) {
            y--;
        }
        if (direction == WEST) {
            x--;
        }
        if (direction == EAST) {
            x++;
        }
        gp.lineTo((x + 1) * 10, (y + 1) * 10);
    }

    public int getDirection() {
        return direction;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
        gp.closePath();
    }

    public boolean isCanMove() {
        return canMove;
    }

    public int getArea(boolean[] visited) throws IOException {
        int MAP_SIZE = 140;

        BufferedImage bi = new BufferedImage(2000, 2000, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = (Graphics2D) bi.getGraphics();
        g.setBackground(Color.WHITE);
        g.setColor(Color.WHITE);
        g.fillRect(0,0, 2000, 2000);
        g.setColor(Color.BLACK);
        g.draw(gp);

        int area = 0;
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                if (!visited[y * MAP_SIZE + x]) {

                    if (gp.contains((x + 1) * 10 + 1, (y + 1) * 10 + 1, 8, 8)) {
                        area += 1;
                        g.fillRect((x + 1) * 10 + 1, (y + 1) * 10 + 1, 8, 8);
                    }
                }
            }
        }

        // 235 low
        // 310 low
        // 416 high

        g.dispose();
        ImageIO.write(bi, "PNG", new File("img.png"));
        return area;
    }
}
