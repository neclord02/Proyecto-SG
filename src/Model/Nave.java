package Model;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public class Nave {
    
    private Vector3f vector;
    private String name;
    private BranchGroup bg;
    
    Nave(String name, String path,Vector3f v){
        this.bg = new BranchGroup();
        this.name = name;
        this.vector = v;
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
        TransformGroup posicion = translate( vector );
        posicion.addChild ( modelo.getSceneGroup() );
        // Añadir el nodo
        bg.addChild(posicion);
    }
    
    // Probablemente deberiamos hacer una clase "Transformaciones"
    public TransformGroup translate(Vector3f vector){
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(vector);
        TransformGroup transformGroup =new TransformGroup();
        transformGroup.setTransform(transform3D);
        return transformGroup;        
    }
    
    public BranchGroup getBranchGroup(){
        return bg;
    }
    
}
