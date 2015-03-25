package Model;

import javax.media.j3d.*;
import javax.vecmath.*;

class TheLights extends BranchGroup {
  
  TheLights () {
    
    Color3f white = new Color3f (1.0f, 1.0f, 1.0f);
    Light aLight = new PointLight( white , new Point3f(0.0f,0.0f,0.0f), new Point3f(1.0f,0.0f,0.0f));
    aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 1000.0));
    aLight.setCapability(Light.ALLOW_STATE_WRITE);
    aLight.setEnable(true);
    this.addChild(aLight);
    
  }
  
  /// Este método es llamado desde la fachada
  void setOnOff (int lightIndex, boolean onOff) {
   /* if (lightIndex > 0 && lightIndex < this.numChildren()) {
      ((Light) this.getChild (lightIndex)).setEnable(onOff);
    }  */ 
  }
  
}
