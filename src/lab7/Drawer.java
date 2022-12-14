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

    BufferedImage imgTemp;
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

        if (image == null){
            return;
        }

        g.drawImage(image, 0, 0, this);

        previousPoint = lastPoint;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.lastPoint = e.getPoint();
        if (previousPoint == null){
            previousPoint = lastPoint;
        }
        lines.add(new Line(previousPoint, lastPoint, this.currentColor));



        if (image == null){
            image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            this.paint(image.getGraphics());
        }

        Graphics bufferGraphics = image.getGraphics();

        for (Line line : lines) {
            bufferGraphics.setColor(line.getColor());
            bufferGraphics.drawLine(line.a.x, line.a.y, line.b.x, line.b.y);
        }

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
        this.imgTemp =  ImageIO.read(file);
        this.lines.clear();

        image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.paint(image.getGraphics());

        this.image.getGraphics().drawImage(imgTemp, 0, 0, this);

        lastPoint = null;
        previousPoint = null;

        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

        this.repaint();
    }
}
