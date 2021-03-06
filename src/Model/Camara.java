package Model;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class Camara extends BranchGroup{
    private Transform3D transform3d;
    private TransformGroup transformgroup;
    public View view;
    private ViewPlatform vp;
    public Canvas3D canvas;
    
    public Camara(Canvas3D canvas){
        this.canvas= canvas;
        transform3d= new Transform3D();
        view= new View();
        vp= new ViewPlatform();
        
        transform3d.lookAt(new Point3d(1,600,1), new Point3d(0,0,0), new Vector3d(0,1,0));
        transform3d.invert();
        transformgroup= new TransformGroup(transform3d);
        transformgroup.addChild(vp);
        view.setPhysicalBody(new PhysicalBody());
        view.setPhysicalEnvironment(new PhysicalEnvironment());
        view.setProjectionPolicy( View.PARALLEL_PROJECTION);
        view.setScreenScalePolicy(View.SCALE_EXPLICIT);
        view.setScreenScale(0.0009);//0.0009
        view.setFrontClipDistance(0.1);
        view.setBackClipDistance(600);

        view.addCanvas3D(canvas);
        view.attachViewPlatform(vp);
        
        addChild(transformgroup);
    }
    
    public Camara(String tipo,String modo){
        transform3d= new Transform3D();
        view= new View();
        vp= new ViewPlatform();
        
        switch(tipo){
            case "luna":
                if( modo == "pov" )
                    transform3d.lookAt(new Point3d(0.1,0.1,0.1), new Point3d(-1.75,0,0), new Vector3d(0,1,0));
                else
                    transform3d.lookAt(new Point3d(0,0.1,-10), new Point3d(0,0,1), new Vector3d(0,1,0));
                transform3d.invert();
                transformgroup= new TransformGroup(transform3d);
                transformgroup.addChild(vp);
                view.setPhysicalBody(new PhysicalBody());
                view.setPhysicalEnvironment(new PhysicalEnvironment());
                view.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
                view.setFieldOfView(Math.toRadians(45));
                view.setFrontClipDistance(0.1);
                view.setBackClipDistance(90);

                view.attachViewPlatform(vp);
                break;
            case "nave":
                if( modo == "pov" )
                    transform3d.lookAt(new Point3d(0.1,0.1,0.1), new Point3d(0,0,1), new Vector3d(0,1,0));
                else
                    transform3d.lookAt(new Point3d(4,4,-10), new Point3d(0,0,1), new Vector3d(0,1,0));
                transform3d.invert();
                transformgroup= new TransformGroup(transform3d);
                transformgroup.addChild(vp);
                view.setPhysicalBody(new PhysicalBody());
                view.setPhysicalEnvironment(new PhysicalEnvironment());
                view.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
                view.setFieldOfView(Math.toRadians(45));
                view.setFrontClipDistance(0.1);
                view.setBackClipDistance(90);

                view.attachViewPlatform(vp);
                break;
        }
        this.addChild(transformgroup);
    }
    
    public void eliminarCanvas(){
        view.removeCanvas3D(this.canvas);
    }
    
    public void addCanvas(Canvas3D canvas){
        this.canvas= canvas;
        view.addCanvas3D(this.canvas);
    }
}
