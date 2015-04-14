package Model;

import javax.media.j3d.*;

class TheScene extends BranchGroup {
  private final BranchGroup figure;
  private RotationInterpolator rotator;
  private Nave nave;
  private Canvas3D canvas;
  private Camara camLuna, camNave;
  private View view;
  
  TheScene (View view, Canvas3D canvas, Camara camLuna, Camara camNave) { 
    this.view= view;
    this.camLuna= camLuna;
    this.camNave= camNave;
    this.canvas= canvas;
    figure = createScene ();
    this.addChild(figure);
  }

  private BranchGroup createScene () {

      /* ----- PLANETAS ---- */ 
      
      //// SOL
    Astro sol= new Estrella("sol", 27.0f, 0,0,0,100000,0);
      //// TIERRA
    Astro tierra= new Planeta("tierra", 1.5f, 50,0,0,10000,30000);
    
    Astro luna = new Satelite("luna",0.5f,7,0,0,5000,5000);
    luna.addCamara(camLuna);
    ((Planeta)tierra).addSatelite((Satelite)luna);
    
       //// VENUS
    Astro venus= new Planeta("venus", 1.2f, 40,0,0,7000,7000);
    
       //// MERCURIO
    Astro mercurio= new Planeta("mercurio", 0.5f, 30,0,0,7000,3000);
    
       //// MARTE
    Astro phobos = new Satelite("phobos",0.4f,7,0,0,8000,8000);
    Astro deimos = new Satelite("deimos",0.4f,12,0,0,5000,5000);
    
    Astro marte= new Planeta("marte", 1.3f, 70,0,0,9000,11000);
    
    ((Planeta)marte).addSatelite((Satelite)phobos);
    ((Planeta)marte).addSatelite((Satelite)deimos);
    
    
       //// JUPITER
    Astro io = new Satelite("io",0.53f,16,0,0,10000,5000);
    Astro europa = new Satelite("europa",0.48f,20,0,0,10000,6000);
    Astro ganimedes = new Satelite("ganimedes",0.6f,25,0,0,10000,7000);
    Astro calisto = new Satelite("calisto",0.57f,30,0,0,10000,8000);
    
    Astro jupiter= new Planeta("jupiter", 9.0f, 100,0,0,12000,23000);
    
    ((Planeta)jupiter).addSatelite((Satelite)io);
    ((Planeta)jupiter).addSatelite((Satelite)europa);
    ((Planeta)jupiter).addSatelite((Satelite)ganimedes);
    ((Planeta)jupiter).addSatelite((Satelite)calisto);
    
       //// SATURNO
    
    Astro saturno= new Planeta("saturno", 9.0f, 150,0,0,15000,10000);
    
    /* Anillos */
    
    Anillo anillo = new Anillo(33.0f,1.0f,50,40);
    Anillo anillo2 = new Anillo(27.0f,1.0f,50,40);
    Anillo anillo3 = new Anillo(21.0f,1.0f,50,40);
    
    ((Planeta)saturno).addAnillo(anillo);
    ((Planeta)saturno).addAnillo(anillo2);
    ((Planeta)saturno).addAnillo(anillo3);
    
    /* fin anillos */
    
       //// URANO
    Astro titania = new Satelite("titania",0.53f,17,0,0,8000,8000);
    Astro oberon = new Satelite("oberon",0.48f,20,0,0,8000,9000);
    Astro umbriel = new Satelite("umbriel",0.6f,12,0,0,8000,7000);
    Astro ariel = new Satelite("ariel",0.57f,8,0,0,8000,6000);
    Astro miranda = new Satelite("miranda",0.57f,5,0,0,8000,5000);
    
    Astro urano= new Planeta("urano", 4.5f, 190,0,0,6000,16000);
    ((Planeta)urano).addSatelite((Satelite)titania);
    ((Planeta)urano).addSatelite((Satelite)oberon);
    ((Planeta)urano).addSatelite((Satelite)umbriel);
    ((Planeta)urano).addSatelite((Satelite)ariel);
    ((Planeta)urano).addSatelite((Satelite)miranda);
    
       //// NEPTUNO
    Astro triton = new Satelite("triton",0.57f,9,0,0,8000,8000);
    
    Astro neptuno= new Planeta("neptuno", 4.5f, 220,0,0,14000,9000);
    ((Planeta)neptuno).addSatelite((Satelite)triton); 
    
    // Se crea la rama desde la que cuelga todo
    BranchGroup bg = new BranchGroup ();
    // Se le dan permisos para poder intercambiar las figuras
    bg.setCapability(Group.ALLOW_CHILDREN_EXTEND);
    bg.setCapability(Group.ALLOW_CHILDREN_WRITE);
    // Y le ponemos una figura
    bg.addChild(sol);
    bg.addChild(mercurio);
    bg.addChild(venus);
    bg.addChild(tierra);
    bg.addChild(marte);
    bg.addChild(jupiter);
    bg.addChild(saturno);
    bg.addChild(urano);
    bg.addChild(neptuno);
    /* ----- FIN PLANETAS ---- */ 
    
    /* ----- NAVES ---- */
    // Nave 1
    Nave tie_fighter = new Nave("tie_fighter","models\\E-TIE-I\\E-TIE-I.obj" );
            /// TEST ESCALE DE LA NAVE
    Transform3D tg = new Transform3D();
    tg.setScale( 2.0f );

    TransformGroup tie_aux = new TransformGroup();
    tie_aux.setTransform( tg );
    
    tie_fighter.addCamara(camNave);
    tie_aux.addChild(tie_fighter);
    bg.addChild( tie_aux );
    
    /* ----- FIN NAVES ---- */
    
    return bg;
  }
  
  public Camara getCamaraLuna(){
      return camLuna;
  }
   public Camara getCamaraNave(){
      return camNave;
  }
  
}
