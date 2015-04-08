package sgp1;

import GUI.Visualization;
import Model.TheUniverse;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class SGP1 {

    public static void main(String[] args) {
        VirtualUniverse universe= new VirtualUniverse();
        Locale locale = new Locale(universe);
        BranchGroup vistas= new BranchGroup();
        // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
    Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    // Se le da el tamaño deseado
    canvas.setSize(600, 600);
    
    Canvas3D canvas2 = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    // Se le da el tamaño deseado
    canvas2.setSize(600, 600);
    
    // Se crea el Universo con dicho Canvas3D
    //TheUniverse universe = new TheUniverse (canvas, true);
    //TheUniverse universe2 = new TheUniverse (canvas2, false);
    
    
    
    
    //---------------CAMARAS---------------
    Transform3D transformPlanta= new Transform3D();
    transformPlanta.lookAt(new Point3d(1,600,1), new Point3d(0,0,0), new Vector3d(0,1,0));
    transformPlanta.invert();

    TransformGroup tgPlanta= new TransformGroup(transformPlanta);

    ViewPlatform vpPlanta= new ViewPlatform();
    tgPlanta.addChild(vpPlanta);

    //definimos la VISTA PARALELA

    View viewPlanta= new View();
    viewPlanta.setPhysicalBody(new PhysicalBody());
    viewPlanta.setPhysicalEnvironment(new PhysicalEnvironment());

    viewPlanta.setProjectionPolicy( View.PARALLEL_PROJECTION);
    viewPlanta.setScreenScalePolicy(View.SCALE_EXPLICIT);
    viewPlanta.setScreenScale(0.0009);//0.0009
    viewPlanta.setFrontClipDistance(0.1);
    viewPlanta.setBackClipDistance(600);

    //Conectamos el canvas3D el view y el ViewPlatform
    viewPlanta.addCanvas3D(canvas);
    viewPlanta.attachViewPlatform(vpPlanta);


    //definimos la VISTA PERSPECTIVA
    Transform3D transformPersp= new Transform3D();
    transformPersp.lookAt(new Point3d(120,60,120), new Point3d(0,0,0), new Vector3d(0,1,0)); //new Point3d(120,60,120)
    transformPersp.invert();

    TransformGroup tgPersp= new TransformGroup(transformPersp);

    ViewPlatform vpPersp= new ViewPlatform();
    tgPersp.addChild(vpPersp);

    //Definimos las caracteristicas de la vista en perspectiva

    View viewPersp= new View();
    viewPersp.setPhysicalBody(new PhysicalBody());
    viewPersp.setPhysicalEnvironment(new PhysicalEnvironment());

    viewPersp.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
    viewPersp.setFieldOfView(Math.toRadians(45));
    viewPersp.setFrontClipDistance(0.1);
    viewPersp.setBackClipDistance(90);
    
    viewPersp.addCanvas3D(canvas2);
    viewPersp.attachViewPlatform(vpPersp);
    // -------------------------------------------------------------------
    
    
    //--------------  Añadimos las dos camaras al grafo de escena  -----------------
    vistas.addChild(tgPersp);
    vistas.addChild(tgPlanta);
    locale.addBranchGraph(vistas);
    
    locale.addBranchGraph(new TheUniverse(canvas,false));
    // ---------------------------------------------------------------------------------
    Visualization visualization = new Visualization (false, canvas);
    Visualization visualization2 = new Visualization (false, canvas2);
    
    visualization.show_visualization(true,0);
    visualization2.show_visualization(true,600);
    }
}
