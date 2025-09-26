import processing.core.PApplet;
import java.util.ArrayList;

public class nuclear extends PApplet {
    class Particle {
        float x, y, vx, vy;
        int type; // 1=proton, 0=neutron, -1=electron
        Particle orbitTarget; // electron's proton to orbit
        float angle; // electron orbit angle
        float orbitRadius;

        Particle(float x, float y, float vx, float vy, int type) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.type = type;
            if(type == -1){
                this.angle = random(TWO_PI);
                this.orbitRadius = random(50, 100);
            }
        }
    }

    ArrayList<Particle> particles = new ArrayList<>();

    float k = 2000;           // electrostatic constant
    float maxForce = 5;
    float bounce = -0.7f;
    float maxSpeed = 10;

    float nuclearStrength = 8f;
    float nuclearRange = 60f;

    @Override
    public void settings() {
        size(1000, 1000);
    }

    @Override
    public void setup() {
        background(255);
        frameRate(1);

        // create nucleus: protons and neutrons
        int nucleusSize = 6;
        float centerX = width/2f;
        float centerY = height/2f;
        for(int i=0;i<nucleusSize;i++){
            for(int j=0;j<nucleusSize;j++){
                float x = centerX + (i-nucleusSize/2f)*30;
                float y = centerY + (j-nucleusSize/2f)*30;
                if(random(1)<0.5f){
                    particles.add(new Particle(x, y, 0,0,1)); // proton
                } else {
                    particles.add(new Particle(x, y,0,0,0)); // neutron
                }
            }
        }

        // add electrons
        for(int i=0;i<12;i++){
            Particle e = new Particle(random(width), random(height), random(-2,2), random(-2,2), -1);
            // assign nearest proton as orbit target
            e.orbitTarget = nearestProton(e);
            particles.add(e);
        }
    }

    Particle nearestProton(Particle e){
        Particle target = null;
        float minDist = Float.MAX_VALUE;
        for(Particle p: particles){
            if(p.type==1){
                float d = dist(e.x, e.y, p.x, p.y);
                if(d<minDist){
                    minDist = d;
                    target = p;
                }
            }
        }
        return target;
    }

    @Override
    public void draw() {
        background(255);

        // physics
        for(int i=0;i<particles.size();i++){
            Particle p1 = particles.get(i);

            float ax = 0;
            float ay = 0;

            for(int j=0;j<particles.size();j++){
                if(i==j) continue;
                Particle p2 = particles.get(j);

                float dx = p2.x - p1.x;
                float dy = p2.y - p1.y;
                float distSq = dx*dx + dy*dy;
                if(distSq<1) distSq=1;
                float dist = sqrt(distSq);

                // --- nuclear force (protons + neutrons) ---
                if((p1.type==1 || p1.type==0) && (p2.type==1 || p2.type==0)){
                    if(dist<nuclearRange){
                        float nForce = nuclearStrength*(1 - dist/nuclearRange);
                        ax += nForce*(dx/dist);
                        ay += nForce*(dy/dist);
                    }
                }

                // --- Coulomb forces ---
                if(p1.type!=0 && p2.type!=0){
                    float force = k/(distSq);
                    if(p1.type * p2.type >0) force*=-1; // same sign repulsion
                    ax += force*(dx/dist);
                    ay += force*(dy/dist);
                }

                // --- electron repulsion ---
                if(p1.type==-1 && p2.type==-1 && dist<80){
                    float eRepel = 50*(1 - dist/80);
                    ax -= eRepel*(dx/dist);
                    ay -= eRepel*(dy/dist);
                }
            }

            // --- electron orbit update ---
            if(p1.type==-1 && p1.orbitTarget!=null){
                p1.angle += 0.02; // orbit speed
                p1.x = p1.orbitTarget.x + p1.orbitRadius*cos(p1.angle);
                p1.y = p1.orbitTarget.y + p1.orbitRadius*sin(p1.angle);
                p1.vx=0; p1.vy=0; // ignore velocity from forces
            } else {
                p1.vx += ax;
                p1.vy += ay;

                float speed = sqrt(p1.vx*p1.vx + p1.vy*p1.vy);
                if(speed>maxSpeed){
                    p1.vx *= maxSpeed/speed;
                    p1.vy *= maxSpeed/speed;
                }

                p1.x += p1.vx;
                p1.y += p1.vy;
            }

            // boundaries
            if(p1.x>width-10){p1.x=width-10;p1.vx*=bounce;}
            if(p1.x<10){p1.x=10;p1.vx*=bounce;}
            if(p1.y>height-10){p1.y=height-10;p1.vy*=bounce;}
            if(p1.y<10){p1.y=10;p1.vy*=bounce;}

            // draw
            if(p1.type==1){
                stroke(0,0,255); strokeWeight(10);
            } else if(p1.type==-1){
                stroke(255,0,0); strokeWeight(6);
            } else {
                stroke(100); strokeWeight(12);
            }
            point(p1.x, p1.y);
        }
    }

    public void mousePressed(){
        // add electron orbiting nearest proton
        Particle e = new Particle(mouseX, mouseY, 0,0,-1);
        e.orbitTarget = nearestProton(e);
        particles.add(e);
    }

    public static void main(String[] args){
        PApplet.main(nuclear.class);
    }
}
