package lab7;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Drawer extends JPanel implements MouseMotionListener {

    private Point lastPoint;

    private Point previousPoint;

    Color currentColor;

    BufferedImage image;

    ArrayList<Line> lines = new ArrayList<>();
    public Drawer(){
        super();
        initDrawer();
        this.setDoubleBuffered(true);
    }

    public void setColor(Color color){
        currentColor = color;
    }

    private void initDrawer(){
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (lastPoint == null) {
            return;
        }

        if (previousPoint == null){
            previousPoint = lastPoint;
        }

        lines.add(new Line(previousPoint, lastPoint, this.currentColor));

        if (image != null){
            g.drawImage(image, 0, 0, this);
        }

        for (Line line : lines) {
            g.setColor(line.getColor());
            g.drawLine(line.a.x, line.a.y, line.b.x, line.b.y);
        }
        previousPoint = lastPoint;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.lastPoint = e.getPoint();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.previousPoint = null;
        this.lastPoint = e.getPoint();
    }

    public void saveToFile(File file) throws IOException {
        BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.paint(img.getGraphics());
        ImageIO.write(img, "png", file);
    }

    public void loadImage(File file) throws IOException {
        this.image =  ImageIO.read(file);
        this.lines.clear();
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        this.repaint();
    }
}
