package math;
/**
 * 3D Vector Object for Vector math
 * @author Zach
 *
 */
public class Vector3D {
	private double x;
	private double y;
	private double z;
	
	/**
	 * Default constructor
	 */
	public Vector3D() {
		x = 0;
		y = 0;
		z = 0;
	}
	/**
	 * Constructor with specified coordinate values
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector3D(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public void setx(double x) {
		this.x=x;
	}
	
	public void sety(double y) {
		this.y = y;
	}
	
	public void setz(double z) {
		this.z = z;
	}
	
	public void setCoords(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public double getx() {
		return x;
	}
	
	public double gety() {
		return y;
	}
	
	public double getz() {
		return z;
	}
	
	// Vector math
	/**
	 * Cross product of this vector and a specified vector
	 * @param vector2 
	 * @return Vector3D Resultant vector of cross product
	 */
	public Vector3D cross(Vector3D vector2) {
		//(a,b,c)×(d,e,f)=(bf−ce,cd−af,ae−bd)
		double d = vector2.getx();
		double e = vector2.gety();
		double f = vector2.getz();
		//Vector3D normal = new Vector3D(y*f-z*e, z*d-x*f, x*e-y*d);
		return new Vector3D(y*f-z*e, z*d-x*f, x*e-y*d);
	}
	
	/**
	 * Scalar division
	 * @param d Operand
	 */
	public void divide(double d) {
		x=x/d;
		y=y/d;
		z=z/d;
	}
	
	/**
	 * Vector subtraction
	 * @param vector2
	 */
	public void subtract(Vector3D vector2) {
		x = x-vector2.getx();
		y = y-vector2.gety();
		z = z-vector2.getz();
	}
	
	/**
	 * Vector addition
	 * @param vector2
	 */
	public void add(Vector3D vector2) {
		x = x+vector2.getx();
		y = y+vector2.gety();
		z = z+vector2.getz();
	}
	
	/**
	 * Get the magnitude of this vector
	 * @return double magnitude
	 */
	public double magnitude() {
		return Math.sqrt(x*x+y*y+z*z);
	}
	
	/**
	 * Get dot product of this vector and a specified vector
	 * @param vector2
	 * @return double dot product
	 */
	public double dotProduct(Vector3D vector2) {
		return x*vector2.getx()+y*vector2.gety()+z*vector2.getz();
	}
	
	/**
	 * get the opposite direction vector
	 */
	public void invert() {
		x = -x;
		y = -y;
		z = -z;
	}
	public void print() {
		System.out.println(x +", "+y+", "+z);
	}
}

