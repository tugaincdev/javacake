import processing.core.PApplet;

public class Hello extends PApplet {

    float angle = 0; // rotation angle

    @Override
    public void settings() {
        size(1000, 1000, P3D);
    }

    @Override
    public void setup() {
        background(255);
    }

    @Override
    public void draw() {
        background(255);

        float cx = mouseX;
        float cy = mouseY;

        pushMatrix();            // save current transformation
        translate(cx, cy);      // move origin to mouse
        rotate(angle);          // rotate the whole logo

        // Circles (all relative to new origin)
        noStroke();
        fill(0, 0, 0);
        circle(0, 0, 700);

        fill(250, 250, 250);
        circle(0, 0, 690);

        fill(0, 0, 0);
        circle(0, 0, 500);

        // Original lines, shifted to origin
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

        popMatrix();            // restore original coordinates

        angle += frameCount*0.001;// increase rotation angle for next frame

    }

    public static void main(String[] args) {
        PApplet.main(Hello.class);
    }
}
