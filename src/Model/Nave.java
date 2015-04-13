package Model;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;

public class Nave extends BranchGroup{
    
    private final String name;
    private Scene modelo;
    Nave(String name, String path){
        this.name = name;
        cargar_objeto(path);
    }
    
    public void cargar_objeto( String path ){
        // Cargar objeto y texturas
        modelo = null; 
        ObjectFile archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE);
        try {
            modelo = archivo.load ( path );
        } catch (FileNotFoundException | ParsingErrorException | IncorrectFormatException e) {
            System.err.println (e);
            System.exit(1);
        }
        translate();
    }
    
    // Probablemente deberiamos hacer una clase "Transformaciones"
    public void translate(){
        /*
        Alpha alphaNave = new Alpha( -1, Alpha.INCREASING_ENABLE, 0,0,6000,0,0,0,0,0 );
        // Nada
        // Reposo
        // Lineal
        // Deceleracion inicial
        // Cuanto tiempo en MAX
        // Mismo hasta 0
                */
        
        Alpha alphaNave = new Alpha( -1, Alpha.INCREASING_ENABLE, 0,0,6000,0,0,0,0,0 );//
        //Alpha alphaNave = new Alpha( -1,10000 );
        TransformGroup target = new TransformGroup();
        Transform3D axisOfRotPos = new Transform3D();
        float[] alphas = {0.0f,0.125f, 0.25f,0.375f, 0.50f,0.625f, 0.75f,0.875f, 1.0f};
        Quat4f[] quats = new Quat4f[9];
        Point3f[] positions = new Point3f[9];

        target.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        for( int i = 0;i<9;i++){
        
            quats[i] = new Quat4f();
        
        }
        quats[0].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(180)));
        quats[1].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(180)));
        quats[2].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(90)));
        quats[3].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(90)));
        quats[4].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(0)));
        quats[5].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(0)));
        quats[6].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(270)));
        quats[7].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(270)));
        quats[8].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(180)));
        
        positions[0]= new Point3f( -20.0f,  0.0f, 20.0f);
        positions[1]= new Point3f( -20.0f, 0.0f, -20.0f);
        positions[2]= new Point3f( -20.0f, 0.0f, -20.0f);
        positions[3]= new Point3f( 20.0f,  0.0f, -20.0f);
        positions[4]= new Point3f( 20.0f,  0.0f, -20.0f);
        positions[5]= new Point3f( 20.0f,  0.0f, 20.0f);
        positions[6]= new Point3f( 20.0f,  0.0f, 20.0f);
        positions[7]= new Point3f( -20.0f,  0.0f, 20.0f);
        positions[8]= positions[0];


        RotPosPathInterpolator rotPosPath = new RotPosPathInterpolator(
                alphaNave, target, axisOfRotPos, alphas, quats, positions);
        rotPosPath.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 1000.0));


        addChild(target);
        addChild(rotPosPath);
        
        Transform3D taux= new Transform3D();
        taux.setScale(2.0);
        TransformGroup t= new TransformGroup(taux);
        t.addChild(modelo.getSceneGroup());
        
        target.addChild(t);
    }    
}
