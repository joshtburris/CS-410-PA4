import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Intersection {
    
    public Vector3D normal = null;
    public Material material = null;
    public Vector3D point = null;
    double distance = 0.0;
    
    public Intersection(Vector3D normal, Material material, Vector3D point, double distance) {
        this.normal = normal;
        this.material = material;
        this.point = point;
        this.distance = distance;
    }
    
    public Ray refractExit(Vector3D W, Vector3D point, double ni_in, double ni_out) {
    
        Vector3D C = this.point.subtract(this.normal);
        Vector3D tmp = point.subtract(C).normalize();
        Vector3D T1 = refractTRay(W, tmp, ni_out, ni_in);
        
        if (Raytracer.sum(T1) == 0.0) {
            return null;
        }
        
        Vector3D exit = point.add( T1.scalarMultiply(C.subtract(point).dotProduct(T1)*2) );
        Vector3D Nin = C.subtract(exit).normalize();
        
        Vector3D T2 = refractTRay(T1.scalarMultiply(-1), Nin, ni_in, ni_out);
        
        return new Ray(exit, T2);
    }
    
    public Vector3D refractTRay(Vector3D W, Vector3D N, double ni0, double ni1) {
        
        double ni = ni0 / ni1;
        double a = -ni;
        double wn = W.dotProduct(N);
        double radsq = Math.pow(ni, 2) * (Math.pow(wn, 2) - 1) + 1;
        
        Vector3D T = new Vector3D(0.0, 0.0, 0.0);
        if (radsq >= 0.0) {
            double b = (ni * wn) - Math.sqrt(radsq);
            T = W.scalarMultiply(a).add(N.scalarMultiply(b));
        }
        
        return T;
    }
}
