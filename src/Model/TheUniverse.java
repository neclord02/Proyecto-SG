
package Model;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class TheUniverse extends BranchGroup{
  // Atributos de relación
  private final TheBackground background;
  private final TheLights lights;
  private final TheScene scene;
  private final Camara camara;
  private View view;
  private final Canvas3D canvas;

  // ******* Constructor
  
  public TheUniverse (Canvas3D canvas, Canvas3D canvas2, Camara camLuna, Camara camNave) {
    this.canvas= canvas;
    this.view= new View();
    // Todo cuelga de un nodo raiz
    BranchGroup root = new BranchGroup();
    
    // Se crea y se añade el fondo
    background = new TheBackground ();
    root.addChild(background);

    // Se crean las luces y se añaden
    lights = new TheLights ();
    root.addChild(lights);
    
    // Se crea el universo. La parte de la vista
    SimpleUniverse universe = createUniverse (canvas);
    
    //Añadimos la cámara en planta
    camara= new Camara(canvas2);
    universe.getLocale().addBranchGraph(camara);
    
    // Se crea y se añade la escena al universo
    scene = new TheScene (camLuna, camNave); 
    scene.setPickable(true);
    root.addChild(scene);
    
    //Procesamiento de Pick
    GenericPick pick= new GenericPick(canvas, canvas2);
    pick.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 1000.0));
    pick.setStatus(scene);
    scene.addChild(pick);
    
    // Se optimiza la escena y se cuelga del universo
    root.compile();
    universe.addBranchGraph(root);
  }
  
  // ******* Private
  
  private SimpleUniverse createUniverse (Canvas3D canvas) {
        //Creamos el viewingPlatform
        ViewingPlatform viewingPlatform = new ViewingPlatform();
    
        // La transformación de vista, dónde se está, a dónde se mira, Vup
        TransformGroup viewTransformGroup = viewingPlatform.getViewPlatformTransform();
        Transform3D viewTransform3D = new Transform3D();
        viewTransform3D.lookAt (new Point3d(120,60,120), new Point3d(0,0,0), new Vector3d(0,1,0));
        viewTransform3D.invert();
        viewTransformGroup.setTransform (viewTransform3D);

        // El comportamiento, para mover la camara con el raton
        OrbitBehavior orbit = new OrbitBehavior(canvas, OrbitBehavior.REVERSE_ALL);
        orbit.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 1000.0f));
        orbit.setZoomFactor (50.0f);
        viewingPlatform.setViewPlatformBehavior(orbit);
        
        // Se establece el angulo de vision a 45 grados y el plano de recorte trasero
        Viewer viewer = new Viewer (canvas);
        view = viewer.getView();
        view.setFieldOfView(Math.toRadians(45));
        view.setBackClipDistance(150.0);
        
    
        // Se construye y devuelve el Universo con los parametros definidos
        return new SimpleUniverse (viewingPlatform, viewer);
  }
  
  // ******* Public
  
  public View getView(){
      return view;
  }
  
  public Canvas3D getCanvas(){
      return canvas;
  }
  
  public void closeApp (int code) {
    System.exit (code);
  } 
}
