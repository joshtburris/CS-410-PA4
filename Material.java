import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Material {
    
    public Vector3D ka, kd, ks,
            kr, // Controls the attenuation for reflection.
            ko, // Opacity of the material.
        tr = new Vector3D(1.0, 1.0, 1.0); // The amount of light passing through an object.
    public double alpha = 0.0, ni = 0;
    public int illum = 0;
    public String name = "";
    
    public Material() { }
    
    public Material(Vector3D ka, Vector3D kd, Vector3D ks, Vector3D kr, Vector3D ko,
            double alpha, double ni) {
        this.ka = ka;
        this.kd = kd;
        this.ks = ks;
        this.kr = kr;
        this.ko = ko;
        this.alpha = alpha;
        this.ni = ni;
    }
    
    public Material(Vector3D ka, Vector3D kd, Vector3D ks, Vector3D kr, Vector3D ko,
            double alpha, double ni, String name) {
        this.ka = ka;
        this.kd = kd;
        this.ks = ks;
        this.kr = kr;
        this.ko = ko;
        this.alpha = alpha;
        this.ni = ni;
        this.name = name;
    }
    
    public Material(String name) {
        this.name = name;
    }

}
