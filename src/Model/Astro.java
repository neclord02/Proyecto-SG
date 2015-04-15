package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public abstract class Astro extends BranchGroup{
    private final float posX, posY, posZ;
    private final float radio;
    private Appearance textura;
    private String nombre;
    private final Sphere esfera;
    protected TransformGroup position, inner_rot;
    private RotationInterpolator rotator_int;
    private RotationInterpolator rotator_ext;
    private final int inner_rot_speed;
    private final int outer_rot_speed;
    
    //Metodo para crear la apariencia del astro
    private void setAppeareance(String nombre){
        this.textura = new Appearance();
        Texture aTexture= new TextureLoader ("imgs/" + nombre +".jpg", null).getTexture();
        
        this.textura.setMaterial( new Material (
            new Color3f (0.2f, 0.2f, 0.2f),   // Color ambiental
            new Color3f (0.2f, 0.2f, 0.2f),   // Color emisivo
            new Color3f (1.0f, 1.00f, 1.00f),   // Color difuso
            new Color3f (0.0f, 0.0f, 0.00f),   // Color especular
            5.0f ) 
        );
        
        TextureAttributes att_t = new TextureAttributes(); 
        if( !this.nombre.equals("sol"))
            att_t.setTextureMode( TextureAttributes.MODULATE );
        
        this.textura.setTextureAttributes(att_t); 
        this.textura.setTexture(aTexture);
    }
    
    //Metodo que crea el grafo de escena del astro. Si el astro es el sol,
    //solo tendrá un nodo de rotación sobre si mismo, en el caso de ser otro elemento,
    //presentará en orden descendiente: rotacion_exterio, traslacion, rotacion_interior y el
    //objeto sphere.
    private void setGrafo(){
        if(!this.nombre.equals("sol")){
            TransformGroup outer_rot = createRotation(outer_rot_speed,true);
            position = translate( new Vector3f(this.posX,this.posY,this.posZ) );
            inner_rot = new TransformGroup();
            inner_rot= createRotation(inner_rot_speed, false);
            inner_rot.setCapability(Node.ENABLE_PICK_REPORTING);

            outer_rot.addChild(position);
            position.addChild(inner_rot);
            inner_rot.addChild(this.esfera);
            addChild(outer_rot);
        }
        else{
            TransformGroup taux = createRotation(inner_rot_speed, false);
            taux.setCapability(Node.ENABLE_PICK_REPORTING);
            taux.addChild(this.esfera);
            addChild(taux);
        }
    }
    
    public Astro( String nombre, float radio, float x, float y, float z, int speed1,int speed2 ){    
        this.nombre = nombre;
        
        //Añadimos la textura
        setAppeareance(nombre);
        
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
        
        setGrafo();
    }
    
    //Metodo para crear la traslacion del astro
    private TransformGroup translate(Vector3f vector){       
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(vector);
        TransformGroup transformGroup =new TransformGroup();
        transformGroup.setTransform(transform3D);

        return transformGroup;     
    }
    
    //Metodo que dependiendo del tipoRot creará un nodo de rotacion sobre su eje
    // o una rotacion exterior.
    private TransformGroup createRotation(int rot, boolean tipoRot){
        RotationInterpolator auxInterp;
        TransformGroup transform = new TransformGroup ();
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Transform3D yAxis = new Transform3D ();
        Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0,rot, 0, 0, 0, 0, 0);
        
        auxInterp = new RotationInterpolator (value, transform, yAxis,
            0.0f, (float) Math.PI*2.0f);
        auxInterp.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 1000.0));
        auxInterp.setEnable(true);
        
        if(!tipoRot){
            rotator_int= auxInterp;
            transform.addChild(rotator_int);
        }
        else{
            rotator_ext= auxInterp;
            transform.addChild(rotator_ext);
        }
        
        return transform;
    }
    
    public void addCamara(Camara cam){
        position.addChild(cam);
    }
}
