
package Model;

import javax.media.j3d.TransformGroup;

public class Estrella extends Astro{    
    
    public Estrella(String nombre, float radio, float x, float y, float z, int speed1,int speed2){
        super(nombre, radio, x, y, z, speed1,speed2);
    }
    
    @Override
    public void dibujar(){
        
        TransformGroup taux = createRotation(inner_rot_speed);
        taux.addChild(this.esfera);
        
        bg.addChild(taux);
        
    }
}
