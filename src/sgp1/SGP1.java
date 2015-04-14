package sgp1;

import GUI.Visualization;
import Model.Camara;
import GUI.ControlCamaras;
import Model.TheUniverse;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.View;

public class SGP1 {

    public static void main(String[] args) {
        // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
        Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
        canvas.setSize(600, 600);

        Canvas3D canvas2 = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
        canvas2.setSize(600, 600);

        Camara camLuna= new Camara("luna");
        Camara camNave= new Camara("nave");
        View view = null;
        TheUniverse universe = new TheUniverse (canvas, canvas2,  camLuna, camNave, view);
 
        ControlCamaras control= new ControlCamaras(universe, camLuna, camNave);
        
        Visualization visualization = new Visualization (false, canvas);
        Visualization visualization2 = new Visualization (false, canvas2);

        visualization.show_visualization(true,0);
        visualization2.show_visualization(true,600);
    }
}
