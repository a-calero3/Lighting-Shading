/**/
package java_3d;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.swing.*;
import java.awt.*;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author CALERO
 */
public class JAVA_3D extends JPanel {

    public JAVA_3D(){
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3d = new Canvas3D(config);
        
        setLayout(new BorderLayout());
        add(canvas3d);
        
        SimpleUniverse universo = new SimpleUniverse(canvas3d);
        universo.getViewingPlatform().setNominalViewingTransform();
        
        BranchGroup escena = crearGrafoEscena();
        universo.addBranchGraph(escena);
    }
    
    public BranchGroup crearGrafoEscena(){
        BranchGroup objetoRaiz = new BranchGroup();
        
        Appearance apariencia = new Appearance();
        
        Material material = new Material();
        
        material.setAmbientColor(new Color3f(Color.DARK_GRAY));
        material.setDiffuseColor(new Color3f(Color.RED));
        material.setSpecularColor(new Color3f(Color.WHITE));
        material.setShininess(20.f);
        apariencia.setMaterial(material);
        
        Cylinder cilindro = new Cylinder(0.2f, 0.5f, Cylinder.GENERATE_NORMALS, apariencia);
        //Sphere esfera = new Sphere(0.5f, Sphere.GENERATE_NORMALS, apariencia);
        
        //Luz ambiental
        Color3f colorAmbiente = new Color3f(Color.DARK_GRAY);
        AmbientLight luzAmbiente = new AmbientLight(colorAmbiente);
        luzAmbiente.setInfluencingBounds(new BoundingSphere(new Point3d(0, 0, 0), 100));
        
        //Agreganos la luz direccional
        Color3f colorLuz = new Color3f(Color.WHITE);
        Vector3f dirLuz = new Vector3f(-1.0f, -1.0f, -1.0f);
        DirectionalLight luz = new DirectionalLight(colorLuz, dirLuz);
        luz.setInfluencingBounds(new BoundingSphere(new Point3d(0, 0, 0), 100));
        
        objetoRaiz.addChild(cilindro);
        //objetoRaiz.addChild(esfera);
        objetoRaiz.addChild(luzAmbiente);
        objetoRaiz.addChild(luz);
        
        return objetoRaiz;
    }
    
    public static void main(String[] args) {
        
        System.setProperty("sun.awt.noeraseBackground", "true");
        JFrame ventana = new JFrame("Luces Java 3D");
        JAVA_3D panel = new JAVA_3D();
        ventana.add(panel);
        ventana.setSize(700, 700);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
    }
    
}
