package Model;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.WakeupOnAWTEvent;

public class ProcesaTeclado extends Behavior {
    private Canvas3D canvas,canvas2;
    private WakeupOnAWTEvent condition;
    
    public ProcesaTeclado( Canvas3D aCanvas, Canvas3D aCanvas2 ){
        
        canvas = aCanvas;
        canvas2= aCanvas2;
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
                
                System.out.println("ok");
                break;
            case 'L':
            //case 'L':
                System.out.println("ok");
                break;
            case 'p':
                
                break;
            case 'n':
                
                break;
            default:
                //Tecla sin carácter
                break;
        }
        wakeupOn(condition);
    }
    
}
