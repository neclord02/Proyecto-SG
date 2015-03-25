package sgp1;

import GUI.Visualization;
import Model.TheUniverse;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;

public class SGP1 {

    public static void main(String[] args) {
        // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
    Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    // Se le da el tamaño deseado
    canvas.setSize(600, 600);
    
    // Se crea el Universo con dicho Canvas3D
    TheUniverse universe = new TheUniverse (canvas);
    
    Visualization visualization = new Visualization (false, canvas);
    visualization.show_visualization(true,0);
    
    /*Canvas3D canvas2 = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    // Se le da el tamaño deseado
    canvas2.setSize(600, 600);
    TheUniverse universe2 = new TheUniverse (canvas2);
    Visualization visualization2 = new Visualization (false, canvas2);
    visualization2.show_visualization(true,600);
    */
    }
}
