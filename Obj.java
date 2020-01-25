import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.*;
import java.io.File;

public class Obj {
    
    public ArrayList<Vector3D> vertices = new ArrayList<>();
    public ArrayList<Vector3D> vectorNormals = new ArrayList<>();
    public ArrayList<Face> faces = new ArrayList<>();
    
    public Obj(String filename) {
        try {
    
            Scanner scan = new Scanner(new File(filename));
            
            Material curMaterial = new Material();
            
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split(" ");
                
                if (data[0].compareTo("mtllib") == 0) {
                    loadMaterialFile(data[1]);
                } else if (data[0].compareTo("v") == 0) {
                    vertices.add(new Vector3D(Double.parseDouble(data[1]),
                            Double.parseDouble(data[2]),
                            Double.parseDouble(data[3])));
                } else if (data[0].compareTo("vn") == 0) {
                    vectorNormals.add(new Vector3D(Double.parseDouble(data[1]),
                            Double.parseDouble(data[2]),
                            Double.parseDouble(data[3])));
                } else if (data[0].compareTo("usemtl") == 0) {
                    curMaterial = findMaterial(data[1]);
                } else if (data[0].compareTo("f") == 0) {
                
                    String[] data0 = data[1].split("/");
                    String[] data1 = data[2].split("/");
                    String[] data2 = data[3].split("/");
                    faces.add(new Face(new Vertex(Integer.parseInt(data0[0])-1,
                            Integer.parseInt(data0[2])-1), new Vertex(Integer.parseInt(data1[0])-1,
                            Integer.parseInt(data1[2])-1), new Vertex(Integer.parseInt(data2[0])-1,
                            Integer.parseInt(data2[2])-1), curMaterial));
                    
                }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadMaterialFile(String filename) {
        try {
        
            Scanner scan = new Scanner(new File(filename));
            
            int curIndex = -1;
            
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split(" ");
                
                Material mat = (curIndex != -1) ? Raytracer.materials.get(curIndex) : null;
                
            
                if (data[0].compareTo("newmtl") == 0) {
                    
                    Raytracer.materials.add(new Material(data[1]));
                    curIndex = Raytracer.materials.size() - 1;
                    
                } else if (data[0].compareTo("Ka") == 0) {
                    mat.ka = new Vector3D(
                            Double.parseDouble(data[1]),
                            Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]));
                } else if (data[0].compareTo("Kd") == 0) {
                    mat.kd = new Vector3D(
                            Double.parseDouble(data[1]),
                            Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]));
                } else if (data[0].compareTo("Ks") == 0) {
                    mat.ks = new Vector3D(
                            Double.parseDouble(data[1]),
                            Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]));
                } else if (data[0].compareTo("Ns") == 0) {
                    mat.alpha = Double.parseDouble(data[1]);
                } else if (data[0].compareTo("Tr") == 0) {
                    mat.tr = new Vector3D(
                            Double.parseDouble(data[1]),
                            Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]));
                } else if (data[0].compareTo("illum") == 0) {
                    
                    int illum = Integer.parseInt(data[1]);
                    
                    if (illum == 2)
                        mat.kr = new Vector3D(0.0, 0.0, 0.0);
                    else if (illum == 3)
                        mat.kr = mat.ks;
                    else if (illum == 6)
                        mat.kr = mat.ks;
    
                    mat.illum = illum;
    
                    //mat.tr = new Vector3D(1.0, 1.0, 1.0).subtract(mat.kr);
                    if (mat.ni == 0.0)
                        mat.ko = new Vector3D(0.0, 0.0, 0.0);
                    else
                        mat.ko = mat.tr;//Raytracer.oneVec.subtract(mat.tr);
                    
                    
                } else if (data[0].compareTo("Ni") == 0) {
                    mat.ni = Double.parseDouble(data[1]);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Material findMaterial(String name) {
        for (Material m : Raytracer.materials) {
            if (m.name.compareTo(name) == 0)
                return m;
        }
        return null;
    }
    
}
