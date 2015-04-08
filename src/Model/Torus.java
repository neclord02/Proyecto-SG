package Model;

import java.util.ArrayList;
import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedGeometryArray;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class Torus extends Shape3D{
    
    public Torus(float rad1, float rad2, int res1, int res2, Appearance app){
    
        ArrayList<Point3f> vertex = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();
        
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
        
        Point3f [] coordenadas = new Point3f[ vertex.size() ];
        int [] indices = new int[ index.size() ];
        // Esto es necesario para pasar un arraylist<int> a int []
        for( int i = 0; i<index.size(); i++){
        
            indices[i] = index.get(i);
        
        }
        // Tal y como en el pdf
        IndexedGeometryArray quads = new IndexedQuadArray( vertex.size(),GeometryArray.COORDINATES ,index.size());
        quads.setCoordinates(0, vertex.toArray(coordenadas));
        quads.setCoordinateIndices(0,indices );
        
        this.setGeometry( quads );
        this.setAppearance(app);
        
    }
}
