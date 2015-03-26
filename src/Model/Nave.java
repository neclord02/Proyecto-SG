package Model;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PositionPathInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class Nave {
    
    private String name;
    private BranchGroup bg;
    
    Nave(String name, String path){
        this.bg = new BranchGroup();
        this.name = name;
        cargar_objeto(path);
    }
    
    public void cargar_objeto( String path ){
        // Cargar objeto y texturas
        Scene modelo = null; 
        ObjectFile archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE);
        try {
            modelo = archivo.load ( path );
        } catch (FileNotFoundException e) {
            System.err.println (e);
            System.exit(1);
        } catch (ParsingErrorException e) {
            System.err.println (e);
            System.exit(1);
        } catch (IncorrectFormatException e) {
            System.err.println (e);
            System.exit(1);
        }
        // Cargar traslacion inicial
        TransformGroup posicion = translate(  );
        posicion.addChild ( modelo.getSceneGroup() );
        // Añadir el nodo
        bg.addChild(posicion);
    }
    
    // Probablemente deberiamos hacer una clase "Transformaciones"
    public TransformGroup translate(){
        
        Transform3D transform3D = new Transform3D();
        TransformGroup transformGroup =new TransformGroup();
        transformGroup.setTransform(transform3D);
        transformGroup.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
        transformGroup.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
        
        Alpha alphaNave = new Alpha( -1, Alpha.INCREASING_ENABLE, 0,0,6000,0,0,0,0,0 );
        // Nada
        // Reposo
        // Lineal
        // Deceleracion inicial
        // Cuanto tiempo en MAX
        // Mismo hasta 0
        float [] alphas = { 0.0f,0.33f,0.66f,1.0f };
        Point3f [] positions = { new Point3f(-20.0f,0.0f,20.0f),new Point3f(20.0f,00.0f,20.0f),new Point3f(0.0f,20.0f,20.0f),new Point3f(-20.0f,0.0f,20.0f) };
        
        
        PositionPathInterpolator path = new PositionPathInterpolator( alphaNave,transformGroup,transform3D,alphas,positions );
        transformGroup.addChild(path);
        path.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 1000.0));
        path.setEnable(true);
        
        return transformGroup;
    }
    
    public BranchGroup getBranchGroup(){
        return bg;
    }
    
}
