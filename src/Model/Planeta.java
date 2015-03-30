package Model;

public class Planeta extends Astro{
    
    public Planeta( String nombre, float radio, float x, float y, float z, int speed1,int speed2 ){
        super(nombre, radio, x, y, z, speed1,speed2);
    }
    
    public void addSatelite( Satelite satelite ){
        position.addChild(satelite);
    }
    
    public void addAnillo( Anillo ring){
        position.addChild(ring.getBranchGroup());  
    }
}
