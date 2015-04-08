package Model;

import com.sun.j3d.utils.pickfast.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOr;

/**
 *
 * @author Vicente
 */
public class GenericPick extends Behavior{
    private WakeupCondition condition = new WakeupOnAWTEvent(MouseEvent.MOUSE_CLICKED );
    private PickCanvas pickCanvas;
    private Canvas3D canvas;
    
    public GenericPick (Canvas3D aCanvas) {
        canvas = aCanvas;
        //condition = new WakeupOnAWTEvent( MouseEvent.MOUSE_CLICKED );
       // status = AppStatus.Nothing;
    }
    
    public void setStatus(BranchGroup bg){
        pickCanvas= new PickCanvas(canvas, bg);
        pickCanvas.setMode(PickInfo.PICK_GEOMETRY);
        pickCanvas.setFlags(PickInfo.SCENEGRAPHPATH | 
                            PickInfo.CLOSEST_GEOM_INFO | 
                            PickInfo.CLOSEST_INTERSECTION_POINT);
    }
    
    @Override
    public void initialize() {
        wakeupOn(condition);
    }

    @Override
    public void processStimulus(Enumeration cond) {
        WakeupOnAWTEvent c = (WakeupOnAWTEvent) cond.nextElement();
        AWTEvent[] e = c.getAWTEvent();
        MouseEvent mouse = (MouseEvent) e[0];
        pickCanvas.setShapeLocation( mouse );
        
        PickInfo pi = pickCanvas.pickClosest();
        
       // if(pi.getNode().)
        TransformGroup aux= (TransformGroup) pi.getSceneGraphPath().getNode(0);
        //TransformGroup aux2= (TransformGroup)aux.getChild(0);
        RotationInterpolator rot= (RotationInterpolator)aux.getChild(0);
        
        if(rot.getEnable()){
            rot.setEnable(false);
            System.out.println("OFF");
        }
        else{
            rot.setEnable(true);
            System.out.println("ON");
        }
        
        wakeupOn( condition );
    }
}
