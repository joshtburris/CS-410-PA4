import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Face {
    
    public Vertex v1, v2, v3;
    public Material material;
    private Vector3D norm0, norm1, norm2;
    
    public Face(Vertex v1, Vertex v2, Vertex v3, Material material) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.material = material;
    }
    
    /*public boolean sharesVertex(int v) {
        return v == v1.v || v == v2.v || v == v3.v;
    }
    
    public boolean sharesVertex(Face f) {
        return sharesVertex(f.v1.v) || sharesVertex(f.v2.v) || sharesVertex(f.v2.v);
    }
    
    public boolean sharesEdge(Face f) {
        return (    sharesVertex(f.v1.v) && sharesVertex(f.v2.v))
                || (sharesVertex(f.v2.v) && sharesVertex(f.v3.v))
                || (sharesVertex(f.v3.v) && sharesVertex(f.v1.v));
    }
    
    public double angle(Face f, Obj obj) {
        Vector3D u = getNormal(this, obj);
        Vector3D v = getNormal(f, obj);
        return Math.acos( u.dotProduct(v) / (magnitude(u)*magnitude(v)) );
    }
    
    public Vector3D getNormal(Face f, Obj obj) {
        Vector3D vert = obj.vertices.get(f.v1.v);
        Point3D vertex0 = new Point3D(vert.getX(), vert.getY(), vert.getZ());
        vert = obj.vertices.get(f.v2.v);
        Point3D vertex1 = new Point3D(vert.getX(), vert.getY(), vert.getZ());
        vert = obj.vertices.get(f.v3.v);
        Point3D vertex2 = new Point3D(vert.getX(), vert.getY(), vert.getZ());
    
        Vector3D edge1 = new Vector3D(vertex1.x - vertex0.x,
                vertex1.y - vertex0.y,
                vertex1.z - vertex0.z);
        Vector3D edge2 = new Vector3D(vertex2.x - vertex0.x,
                vertex2.y - vertex0.y,
                vertex2.z - vertex0.z);
        
        return edge1.crossProduct(edge2);
    }
    
    private double magnitude(Vector3D u) {
        return Math.sqrt( Math.pow(u.getX(), 2) +  Math.pow(u.getY(), 2)
                + Math.pow(u.getZ(), 2));
    }
    
    public void addNormal(Face f) {
        if (f.sharesVertex(v1.v)) {
            norm0 = norm0.add(f.v1.vn);
        }
    }*/
    
}
