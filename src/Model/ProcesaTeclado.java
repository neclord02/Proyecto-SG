package Model;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.View;
import javax.media.j3d.WakeupOnAWTEvent;

public class ProcesaTeclado extends Behavior {
    private Canvas3D canvas;
    private View view;
    private Camara camLuna, camNave;
    private WakeupOnAWTEvent condition;
    private boolean[] activa;
    
    public ProcesaTeclado(Canvas3D canvas, View view, Camara camLuna, Camara camNave){
        this.activa = new boolean[]{true, false, false};
        this.view= view;
        this.camLuna= camLuna;
        this.camNave= camNave;
        this.canvas= canvas;
        this.condition = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
        
    }
    
    @Override
    public void initialize() {
        wakeupOn(condition);
    }

    @Override
    public void processStimulus(Enumeration criterios) {
        WakeupOnAWTEvent unCriterio= (WakeupOnAWTEvent) criterios.nextElement();
        AWTEvent[] eventos= unCriterio.getAWTEvent();
        KeyEvent tecla= (KeyEvent) eventos[0];
        //Procesamos las teclas con carácter
        
        switch(tecla.getKeyChar()){
            case 'l':
                if(activa[0]){
                    //System.out.println(view.numCanvas3Ds());
                    view.removeAllCanvas3Ds();
                    activa[0]=false;
                }
                else if(activa[2]){
                    camNave.eliminarCanvas();
                    activa[2]=false;
                }
                if(!activa[1]){
                    activa[1]= true;
                    camLuna.addCanvas(canvas);
                }
                
                System.out.println("ok-Luna ;)");
                break;
            /*case 'L':
            //case 'L':
                System.out.println("ok");
                break;*/
            case 'p':
                if(activa[1]){
                    camNave.eliminarCanvas();
                    activa[1]=false;
                }
                else if(activa[2]){
                    camNave.eliminarCanvas();
                    activa[2]=false;
                }
                
                if(!activa[0]){
                    activa[0]= true;
                    view.setCanvas3D(canvas, 1);
                }
                
                System.out.println("ok-Perspectiva ;)");
                break;
            case 'n':
                if(activa[0]){
                    view.removeAllCanvas3Ds();
                    activa[0]=false;
                }
                else if(activa[1]){
                    camNave.eliminarCanvas();
                    activa[1]=false;
                }
                if(!activa[2]){
                    activa[2]= true;
                    camNave.addCanvas(canvas);
                }
                
                System.out.println("ok-Nave ;)");
                break;
            default:
                //Tecla sin carácter
                break;
        }
        wakeupOn(condition);
    }
    
}
