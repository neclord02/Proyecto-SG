/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.pickfast.PickCanvas;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOr;

/**
 *
 * @author Vicente
 */
public class GenericPick extends Behavior{
    //private AppStatus status;
    
    private WakeupOnAWTEvent[] conditionsToListen = {
        new WakeupOnAWTEvent (MouseEvent.MOUSE_PRESSED),
        new WakeupOnAWTEvent (MouseEvent.MOUSE_RELEASED),
        new WakeupOnAWTEvent (MouseEvent.MOUSE_DRAGGED)
    };
    private WakeupCondition condition;
    private PickCanvas pickCanvas;
    private Canvas3D canvas;
    
    public GenericPick (Canvas3D aCanvas) {
        canvas = aCanvas;
        condition = new WakeupOr(conditionsToListen);
       // status = AppStatus.Nothing;
    }
    
    @Override
    public void initialize() {
        wakeupOn(condition);
    }

    @Override
    public void processStimulus(Enumeration enmrtn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
