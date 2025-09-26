import processing.core.PApplet;
import java.util.ArrayList;
import processing.core.PImage;
import processing.core.PVector;

public class Hello extends PApplet {
    ArrayList<PVector> posicoes; //listatat armazenar as posições dos cliques
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

            //pupila do olho esquerdo
            float olhoEsquerdo_x_local = -100;
            float olhoEsquerdo_y_local = 100;
            float olhoEsquerdo_x_global = pos.x + olhoEsquerdo_x_local * fatorEscala;
            float olhoEsquerdo_y_global = pos.y + olhoEsquerdo_y_local * fatorEscala;
            float dxEsquerdo = mouseX - olhoEsquerdo_x_global;
            float dyEsquerdo = mouseY - olhoEsquerdo_y_global;
            float distEsquerdo = dist(mouseX, mouseY, olhoEsquerdo_x_global, olhoEsquerdo_y_global);
            float distMax = 12f;
            float pupilaEsquerda_x_local = olhoEsquerdo_x_local;
            float pupilaEsquerda_y_local = olhoEsquerdo_y_local;
            if (distEsquerdo > 0) {
                float offsetGlobal = min(distEsquerdo, distMax);
                float unit_dx = dxEsquerdo / distEsquerdo;
                float unit_dy = dyEsquerdo / distEsquerdo;
                float dxGlobal = unit_dx * offsetGlobal;
                float dyGlobal = unit_dy * offsetGlobal;
                float offsetLocal_x = dxGlobal / fatorEscala;
                float offsetLocal_y = dyGlobal / fatorEscala;
                pupilaEsquerda_x_local += offsetLocal_x;
                pupilaEsquerda_y_local += offsetLocal_y;
            }
            // pupila esquerda
            noStroke();
            fill(255,0,0); // Pupila veremelha do benfica
            float diamPupilaLocal = 40f;
            circle(pupilaEsquerda_x_local, pupilaEsquerda_y_local, diamPupilaLocal);

            // Pupila do olho direito
            float olhoDireito_x_local = 100;
            float olhoDireito_y_local = 100;
            float olhoDireito_x_global = pos.x + olhoDireito_x_local * fatorEscala;
            float olhoDireito_y_global = pos.y + olhoDireito_y_local * fatorEscala;
            float dxDireito = mouseX - olhoDireito_x_global;
            float dyDireito = mouseY - olhoDireito_y_global;
            float distDireito = dist(mouseX, mouseY, olhoDireito_x_global, olhoDireito_y_global);
            float pupilaDireita_x_local = olhoDireito_x_local;
            float pupilaDireita_y_local = olhoDireito_y_local;
            if (distDireito > 0) {
                float offsetGlobal = min(distDireito, distMax);
                float unit_dx = dxDireito / distDireito;
                float unit_dy = dyDireito / distDireito;
                float dxGlobal = unit_dx * offsetGlobal;
                float dyGlobal = unit_dy * offsetGlobal;
                float offsetLocal_x = dxGlobal / fatorEscala;
                float offsetLocal_y = dyGlobal / fatorEscala;
                pupilaDireita_x_local += offsetLocal_x;
                pupilaDireita_y_local += offsetLocal_y;
            }
            //dsenha pupila direita
            noStroke();
            fill(255,0,0);
            circle(pupilaDireita_x_local, pupilaDireita_y_local, diamPupilaLocal);

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
