package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public abstract class Astro extends BranchGroup{
    protected float posX, posY, posZ;
    protected float radio;
    protected BranchGroup bg;
    protected Appearance textura;
    protected String nombre;
    protected Sphere esfera;
    private RotationInterpolator rotator_int;
    private RotationInterpolator rotator_ext;
    protected int inner_rot_speed;
    protected int outer_rot_speed;
    
    public Astro( String nombre, float radio, float x, float y, float z, int speed1,int speed2 ){
        
        this.nombre = nombre;
        BranchGroup bg= new BranchGroup();
        bg.setCapability(BranchGroup.ALLOW_DETACH);
        this.textura = new Appearance();
        
        Texture aTexture;
        aTexture= new TextureLoader ("imgs/" + nombre +".jpg", null).getTexture();
        
        textura.setMaterial( new Material (
        new Color3f (0.2f, 0.2f, 0.2f),   // Color ambiental
        new Color3f (0.2f, 0.2f, 0.2f),   // Color emisivo
        new Color3f (1.0f, 1.00f, 1.00f),   // Color difuso
        new Color3f (0.0f, 0.0f, 0.00f),   // Color especular
        5.0f ) );
        
        TextureAttributes att_t = new TextureAttributes();
        
        if( nombre != "sol")
            att_t.setTextureMode( TextureAttributes.MODULATE );
        
        textura.setTextureAttributes(att_t);
        
        this.textura.setTexture(aTexture);
        
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.inner_rot_speed = speed1;
        this.outer_rot_speed = speed2;
        this.nombre= nombre;
        this.radio= radio;
        
        esfera= new Sphere (radio, 
        Primitive.GENERATE_NORMALS | 
        Primitive.GENERATE_TEXTURE_COORDS |
        Primitive.ENABLE_APPEARANCE_MODIFY, 64, 
        this.textura);
        
        this.addChild(bg);
    }
    
    public TransformGroup translate(Vector3f vector){
        
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(vector);
        TransformGroup transformGroup =new TransformGroup();
        transformGroup.setTransform(transform3D);

        return transformGroup;
        
    }
    
    public TransformGroup createRotationInt (int rot) {
        TransformGroup transform = new TransformGroup ();
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Transform3D yAxis = new Transform3D ();
        Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
                rot, 0, 0, 0, 0, 0);
        rotator_int = new RotationInterpolator (value, transform, yAxis,
            0.0f, (float) Math.PI*2.0f);
        rotator_int.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 1000.0));
        rotator_int.setEnable(true);
        transform.addChild( rotator_int );
        return transform;
    }
    
    public TransformGroup createRotationExt (int rot) {
        TransformGroup transform = new TransformGroup ();
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Transform3D yAxis = new Transform3D ();
        Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
                rot, 0, 0, 0, 0, 0);
        rotator_ext = new RotationInterpolator (value, transform, yAxis,
            0.0f, (float) Math.PI*2.0f);
        rotator_ext.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 1000.0));
        rotator_ext.setEnable(true);
        transform.addChild(rotator_ext);
        return transform;
    }
    
    void setRotationOnOff (boolean onOff) {
        rotator_int.setEnable(false);
    }
    
    public abstract void dibujar();
    
}
