
package Model;

import com.sun.j3d.utils.image.TextureLoader;
import java.util.*;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class Anillo extends Shape3D{
    
    private final BranchGroup bg;
    //private Anillo anillo2;
    private Appearance textura;
    
    public Anillo( float rad1, float rad2, int res1, int res2 ){
        
        this.bg = new BranchGroup();
        this.bg.setPickable(false);
        
        ArrayList<Point3f> vertex = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<Float> texcoord = new ArrayList<>();
        
        Point3f t = new Point3f( rad2,0,0 );
        Vector3f r = new Vector3f( rad1,0,0 );
        
        // Creo las transformaciones necesarias
        
        Transform3D rotar_grande = new Transform3D();
        rotar_grande.rotY( 2*Math.PI / res1 );
        Transform3D rotar_pequeño = new Transform3D();
        rotar_pequeño.rotZ( 2*Math.PI / res2 );
        Transform3D trasladar = new Transform3D();
        trasladar.setTranslation( r );
        
        // Creo la primer tira de puntos circulas (res2)
        
        for( int i = 0; i<res2;i++){

            rotar_pequeño.transform(t);
            Point3f p = new Point3f(t);
            trasladar.transform(p);
            vertex.add( new Point3f(p) );
        }
        
        // Creo con traslaciones el resto de puntos de res1 a partir de los
        // anteriores.
        
        int tam = vertex.size();
        for(int i = 0;i<res1;i++){
            
            for( int j = 0;j<tam;j++ ){
                
                Point3f p = new Point3f( vertex.get((i*tam)+j) );
                rotar_grande.transform(p);
                vertex.add( p );
            
            }
        }
        
        // Generar indices de vertices
        
        for( int i = 0; i<res1;i++){
            
            for( int j = 0; j< res2;j++){

                index.add(( i*res2 ) + j );
                index.add(((( i+1 ) * res2) + j )% (res1*res2)  );
                index.add((( i+1 ) * res2 ) + (( j+1 ) % res2 ));
                index.add(( i * res2 ) + (( j+1 ) % res2 ));

            }
            
        }
        
        // Generar coordenadas de textura:
        
        float startx = 0.0f;
        float starty = 0.0f;
        for( int i = 0; i<res1;i++){
            
            for( int j = 0; j< res2;j++){

                texcoord.add(startx);
                texcoord.add(starty);
                starty += 1.0/res2;
            }
            startx += 1.0/res1;
            starty = 0.0f;
        }
        
        Point3f [] coordenadas = new Point3f[ vertex.size() ];
        int [] indices = new int[ index.size() ];
        // Esto es necesario para pasar un arraylist<int> a int []
        for( int i = 0; i<index.size(); i++){
        
            indices[i] = index.get(i);
        
        }
        
        float [] textura = new float[ texcoord.size() ];
        // Esto es necesario para pasar un arraylist<Float> a float []
        for( int i = 0; i<texcoord.size(); i++){
        
            textura[i] = texcoord.get(i);
        
        }
        
        // Tal y como en el pdf
        IndexedGeometryArray quads = new IndexedQuadArray( vertex.size(), GeometryArray.TEXTURE_COORDINATE_2 | 
                GeometryArray.COORDINATES | GeometryArray.NORMALS ,index.size());
        quads.setTextureCoordinates(0, 0, textura);
        // Los indices para las coordenadas de textura son los mismos que para los puntos del anillo pk estan bien ordenados.
        quads.setTextureCoordinateIndices(0, 0, indices);
        quads.setCoordinates(0, vertex.toArray(coordenadas));
        quads.setCoordinateIndices(0,indices );
        
        this.setGeometry( quads );
        this.setTexture();
        
        this.bg.addChild(this);
        
    }
    
    private void setTexture(){
        this.textura = new Appearance();
        Texture aTexture= new TextureLoader ("imgs/anillo.jpg", null).getTexture();
        
        this.textura.setMaterial( new Material (
            new Color3f (0.2f, 0.2f, 0.2f),   // Color ambiental
            new Color3f (0.2f, 0.2f, 0.2f),   // Color emisivo
            new Color3f (1.0f, 1.00f, 1.00f),   // Color difuso
            new Color3f (0.0f, 0.0f, 0.00f),   // Color especular
            5.0f ) 
        );
        
        TextureAttributes att_t = new TextureAttributes(); 
        att_t.setTextureMode( TextureAttributes.REPLACE );
        
        this.textura.setTextureAttributes(att_t); 
        this.textura.setTexture(aTexture);
        this.setAppearance(textura);
    }
    
    public BranchGroup getBranchGroup(){
    
        return this.bg;
    
    }
    
}
