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

            //pupila do olho esquerdo

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

            //pupila do olho esquerdo

//posicao local do centro do olho esquerdo em relacao ao objeto
            float olhoEsquerdo_x_local = -100;
            float olhoEsquerdo_y_local = 100;

//converte a posicao local do olho para coordenadas globais da tela
// usando a posicao do objeto (pos) e o fator de escala
            float olhoEsquerdo_x_global = pos.x + olhoEsquerdo_x_local * fatorEscala;
            float olhoEsquerdo_y_global = pos.y + olhoEsquerdo_y_local * fatorEscala;

//calcula a direcao do olho em relacao ao mouse
// dxEsquerdo e dyEsquerdo representam o vetor do olho até o mouse
            float dxEsquerdo = mouseX - olhoEsquerdo_x_global;
            float dyEsquerdo = mouseY - olhoEsquerdo_y_global;

//calcula a distamcia entre o centro do olho e o mouse
            float distEsquerdo = dist(mouseX, mouseY, olhoEsquerdo_x_global, olhoEsquerdo_y_global);

//define o limite maxiomo de movimento da pupila dentro do olho
            float distMax = 12f;

//inicializa a posição da pupila como sendo igual ao centro do olho
            float pupilaEsquerda_x_local = olhoEsquerdo_x_local;
            float pupilaEsquerda_y_local = olhoEsquerdo_y_local;

//so move a pupila se houver distancia entre olho e mouse
            if (distEsquerdo > 0) {
                //limitaaa o movimento da pupila ao valor máximo permitido
                float offsetGlobal = min(distEsquerdo, distMax);

                //direçao normalizada do olho para o mouse
                float unit_dx = dxEsquerdo / distEsquerdo;
                float unit_dy = dyEsquerdo / distEsquerdo;

                //aplica o offset limitado na direçao do mouse
                float dxGlobal = unit_dx * offsetGlobal;
                float dyGlobal = unit_dy * offsetGlobal;

                //converte o movimento global de volta para coordenadas locais
                float offsetLocal_x = dxGlobal / fatorEscala;
                float offsetLocal_y = dyGlobal / fatorEscala;

                //atualiza a posicao final da pupila somando o offset ao centro do olho
                pupilaEsquerda_x_local += offsetLocal_x;
                pupilaEsquerda_y_local += offsetLocal_y;
            }
            // Desenha pupila esquerda
            noStroke();
            fill(255,0,0); // Pupila preta
            float diamPupilaLocal = 50f;
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

            // Desenha pupila esquerda
            noStroke();
            fill(255,0,0); // Pupila preta
            float diamPupilaLocal = 50f;
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
