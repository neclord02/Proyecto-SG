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
        // Cargar traslacion inicial
        //TransformGroup posicion = translate(  );
        //posicion.addChild ( modelo.getSceneGroup() );
        // Añadir el nodo
        //addChild(posicion);
        translate();
    }
    
    // Probablemente deberiamos hacer una clase "Transformaciones"
    public void translate(){
        /*
        Transform3D transform3D = new Transform3D();
        transform3D.set(new Vector3f(0.0f, 0.0f,0.0f));
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
        Quat4f [] rotations= new Quat4f[4];
        for(int i=0; i<4; i++){
            rotations[i]= new Quat4f();
            rotations[i].set(new AxisAngle4f(0.0f, 0.0f, 1.0f, (float)Math.toRadians(90)));
        }
        
        RotPosPathInterpolator path = new RotPosPathInterpolator( alphaNave,transformGroup,transform3D,alphas,rotations, positions );
        transformGroup.addChild(path);
        path.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 1000.0));
        path.setEnable(true);
        
        return transformGroup;*/
        //BranchGroup objRoot = new BranchGroup();

        Alpha alphaNave = new Alpha( -1, Alpha.INCREASING_ENABLE, 0,0,6000,0,0,0,0,0 );//
        //Alpha alphaNave = new Alpha( -1,10000 );
        TransformGroup target = new TransformGroup();
        Transform3D axisOfRotPos = new Transform3D();
        float[] alphas = {0.0f, 0.25f, 0.50f, 0.75f, 1.0f};
        Quat4f[] quats = new Quat4f[5];
        Point3f[] positions = new Point3f[5];

        target.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        for( int i = 0;i<5;i++){
        
            quats[i] = new Quat4f();
        
        }
        quats[0].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(270)));
        quats[1].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(180)));
        quats[2].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(90)));
        quats[3].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(0)));
        quats[4].set( new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(270)));
        
        positions[0]= new Point3f( -20.0f,  0.0f, 20.0f);
        positions[1]= new Point3f( -20.0f, 0.0f, -20.0f);
        positions[2]= new Point3f( 20.0f,  0.0f, -20.0f);
        positions[3]= new Point3f( 20.0f,  0.0f, 20.0f);
        positions[4]= positions[0];


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
