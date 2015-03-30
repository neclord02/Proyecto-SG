package Model;

import java.util.ArrayList;
import java.util.List;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public class Planeta extends Astro{

    private final List<Satelite> satelites;
    private final List<Anillo> anillos;
    
    public Planeta( String nombre, float radio, float x, float y, float z, int speed1,int speed2 ){
        super(nombre, radio, x, y, z, speed1,speed2);
        this.satelites = new ArrayList<>();
        this.anillos = new ArrayList<>();
    }
    
    public void addSatelite( Satelite satelite ){
        
        this.satelites.add(satelite);
        
    }
    
    public void addAnillo( Anillo ring){
    
        this.anillos.add(ring);
        
    }
    
    @Override
    public void dibujar(){
        
        TransformGroup outer_rot = createRotationExt(outer_rot_speed);
        TransformGroup position = translate( new Vector3f(this.posX,this.posY,this.posZ) );
        TransformGroup inner_rot = createRotationInt(inner_rot_speed);
        
        outer_rot.addChild(position);
        position.addChild(inner_rot);
        inner_rot.addChild(this.esfera);
        
        for( Anillo i: anillos ){ 
            position.addChild( i.getBranchGroup());    
        }
        
        for( Satelite i: satelites){ 
            i.dibujar();
            position.addChild(i);    
        }
        
        bg.addChild(outer_rot);  
    }
}
