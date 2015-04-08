package sgp1;

import GUI.Visualization;
import Model.TheUniverse;
import Model.Camara;
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
        canvas.setSize(600, 600);

        Canvas3D canvas2 = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
        canvas2.setSize(600, 600);

        Camara planta= new Camara(canvas, "planta");
        Camara perspectiva= new Camara(canvas2, "perspectiva");
        //--------------  Añadimos las dos camaras al grafo de escena  -----------------
        vistas.addChild(planta);
        vistas.addChild(perspectiva);
        locale.addBranchGraph(vistas);

        locale.addBranchGraph(new TheUniverse(canvas,canvas2));
        // ---------------------------------------------------------------------------------
        Visualization visualization = new Visualization (false, canvas);
        Visualization visualization2 = new Visualization (false, canvas2);

        visualization.show_visualization(true,0);
        visualization2.show_visualization(true,600);
    }
}
