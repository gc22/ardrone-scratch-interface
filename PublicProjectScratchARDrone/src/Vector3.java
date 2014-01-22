
public class Vector3{
	private double[] e;
	
	public Vector3(){
		e = new double[]{0,0,0};
	}
	
	public Vector3(double x, double y, double z){
		e = new double[]{x,y,z};
	}
	
	public double dot(Vector3 b){
        double scaler = 0.0;
        for( int i=0; i < 3; i++) {
            scaler += this.get(i)*b.get(i);
        }
        return scaler;
	}
	
    public static double dot(Vector3 a, Vector3 b) {
        double scaler = 0.0;
        for( int i=0; i < 3; i++) {
            scaler += a.get(i)*b.get(i);
        }
        return scaler;
    }
    
    private double get(int i){
    	return e[i];
    }
    
    public double getX(){
    	return e[0];
    }
    
    public double getY(){
    	return e[1];
    }
    
    public double getZ(){
    	return e[2];
    }
}