package Model;

import com.sun.j3d.utils.pickfast.PickCanvas;
import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupOnAWTEvent;


public class GenericPick extends Behavior{
    private final WakeupCondition condition = new WakeupOnAWTEvent(MouseEvent.MOUSE_CLICKED );
    private PickCanvas pickCanvas1, pickCanvas2;
    private final Canvas3D canvas, canvas2;
    
    public GenericPick (Canvas3D aCanvas, Canvas3D aCanvas2) {
        canvas = aCanvas;
        canvas2= aCanvas2;
    }
    
    public void setStatus(BranchGroup bg){
        pickCanvas1= new PickCanvas(canvas, bg);
        pickCanvas1.setMode(PickInfo.PICK_GEOMETRY);
        pickCanvas1.setFlags(PickInfo.SCENEGRAPHPATH | 
                            PickInfo.CLOSEST_GEOM_INFO | 
                            PickInfo.CLOSEST_INTERSECTION_POINT);
        
        pickCanvas2= new PickCanvas(canvas2, bg);
        pickCanvas2.setMode(PickInfo.PICK_GEOMETRY);
        pickCanvas2.setFlags(PickInfo.SCENEGRAPHPATH | 
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
        pickCanvas1.setShapeLocation( mouse );
        pickCanvas2.setShapeLocation( mouse );
        
        
        PickInfo pi = pickCanvas1.pickClosest();
        PickInfo pi2 = pickCanvas2.pickClosest();
        
        if(pi!=null){  
            TransformGroup aux= (TransformGroup) pi.getSceneGraphPath().getNode(0);
            RotationInterpolator rot= (RotationInterpolator)aux.getChild(0);

            if(rot.getEnable()){
                rot.setEnable(false);
            }
            else if(!rot.getEnable()){
                rot.setEnable(true);
            }           
        }
        else if(pi2!=null){
            TransformGroup aux2= (TransformGroup) pi2.getSceneGraphPath().getNode(0);
            RotationInterpolator rot2= (RotationInterpolator)aux2.getChild(0);
            
            if(rot2.getEnable()){
                rot2.setEnable(false);
                System.out.println("OFF");
            }
            else if(!rot2.getEnable()){
                rot2.setEnable(true);
                System.out.println("ON");
            }
        }
        
         wakeupOn( condition );
    }
}
