/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public class Satelite extends Astro{
    
    private RotationInterpolator rotator;
    
    public Satelite( String nombre, float radio, float x, float y, float z, int speed1,int speed2 ){
        super(nombre, radio, x, y, z, speed1,speed2);
    }
    
    @Override
    public void dibujar(){
        
        TransformGroup outer_rot = createRotation(outer_rot_speed);
        TransformGroup position = translate( new Vector3f(this.posX,this.posY,this.posZ) );
        TransformGroup inner_rot = createRotation(inner_rot_speed);
        
        outer_rot.addChild(position);
        position.addChild(inner_rot);
        inner_rot.addChild(this.esfera);
        
        bg.addChild(outer_rot);
    
    }
}
