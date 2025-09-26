import processing.core.PApplet;
import java.util.ArrayList;

import processing.core.PImage;
import processing.core.PVector;

public class Hello extends PApplet {
    ArrayList<PVector> positions; // lista para as pocisoes
    PImage img;

    @Override
    public void settings() {
        size(1000, 1000);
    }

    @Override
    public void setup() {
        background(255);
        positions = new ArrayList<PVector>();
        img = loadImage("Milky-Zombie-Eye_1_1200x1200-removebg-preview.png");
    }

    @Override
    public void draw() {
        background(255);

        // desenha o logo em cada posisao do array ykyk
        for (PVector pos : positions) {
            pushMatrix();
            translate(pos.x, pos.y);
            scale(0.25f);


            noStroke();
            fill(0, 0, 0);
            circle(0, 0, 700);

            fill(250, 250, 250);
            circle(0, 0, 690);

            fill(0, 0, 0);
            circle(0, 0, 500);

            // Lines
            stroke(250);
            strokeWeight(50);
            line(-255, -180, -85, 220);
            line(255, -180, 85, 220);
            line(-90, 250, 190, -435);
            line(90, 250, -190, -435);

            stroke(0);
            strokeWeight(20);
            line(48, 35, -48, 35);

            noFill();
            stroke(0);
            strokeWeight(60);
            circle(0, 0, 700);

            stroke(255);
            strokeWeight(120);
            circle(0, 0, 900);

            image(img, -200, 0, 200, 200);
            image(img, 0, 0, 200, 200);


            popMatrix();
        }
    }

    @Override
    public void mousePressed() {
        
        positions.add(new PVector(mouseX, mouseY));
    }

    public static void main(String[] args) {
        PApplet.main(Hello.class);
    }
}
