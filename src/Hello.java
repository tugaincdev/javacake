import processing.core.PApplet;
import java.util.ArrayList;
import processing.core.PImage;
import processing.core.PVector;

public class Hello extends PApplet {
    ArrayList<PVector> posicoes; // Lista para armazenar as posições dos cliques
    PImage img;

    @Override
    public void settings() {
        size(1000, 1000);
    }

    @Override
    public void setup() {
        background(255);
        posicoes = new ArrayList<PVector>();
        img = loadImage("Milky-Zombie-Eye_1_1200x1200-removebg-preview.png");
    }

    @Override
    public void draw() {
        background(255);

        // desenha o logo em cada posição do array
        for (PVector pos : posicoes) {
            pushMatrix();
            translate(pos.x, pos.y);
            float fatorEscala = 0.25f;
            scale(fatorEscala);

            noStroke();
            fill(0, 0, 0);
            circle(0, 0, 700);

            fill(250, 250, 250);
            circle(0, 0, 690);

            fill(0, 0, 0);
            circle(0, 0, 500);

            // Linhas
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

            // Pupila do olho esquerdo
            float olhoEsquerdo_x_local = -100;
            float olhoEsquerdo_y_local = 100;
            float olhoEsquerdo_x_global = pos.x + olhoEsquerdo_x_local * fatorEscala;
            float olhoEsquerdo_y_global = pos.y + olhoEsquerdo_y_local * fatorEscala;
            float angleEsquerdo = atan2(mouseY - olhoEsquerdo_y_global, mouseX - olhoEsquerdo_x_global);
            pushMatrix();
            translate(olhoEsquerdo_x_local, olhoEsquerdo_y_local);
            rotate(angleEsquerdo);
            noStroke();
            fill(255, 0, 0);
            float diamPupilaLocal = 50f;
            circle(diamPupilaLocal / 2, 0, diamPupilaLocal);
            popMatrix();

            // Pupila do olho direito
            float olhoDireito_x_local = 100;
            float olhoDireito_y_local = 100;
            float olhoDireito_x_global = pos.x + olhoDireito_x_local * fatorEscala;
            float olhoDireito_y_global = pos.y + olhoDireito_y_local * fatorEscala;
            float angleDireito = atan2(mouseY - olhoDireito_y_global, mouseX - olhoDireito_x_global);
            pushMatrix();
            translate(olhoDireito_x_local, olhoDireito_y_local);
            rotate(angleDireito);
            noStroke();
            fill(255, 0, 0);
            circle(diamPupilaLocal / 2, 0, diamPupilaLocal);
            popMatrix();

            popMatrix();
        }
    }

    @Override
    public void mousePressed() {
        posicoes.add(new PVector(mouseX, mouseY));
    }

    public static void main(String[] args) {
        PApplet.main(Hello.class);
    }
}
