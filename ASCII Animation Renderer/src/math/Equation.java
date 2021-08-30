package math;

/**
 * Logic to solve a specified equation
 * @author Zach
 *
 */
public class Equation {
	/**
	 * Solve an equation with two parameters and time value
	 * @param u parameter u with domain 0 to 1
	 * @param v parameter v with domain 0 to 1
	 * @param t parameter t with domain 0 to 1
	 * @return
	 */
	public static Vector3D f(double u, double v, double t) {
		// extra pre-computation input
		double x1,x2,y1,y2,z1,z2,x,y,z;
		//pre-computation
		double cosU = Math.cos(2*Math.PI*u);
		double cosV = Math.cos(2*Math.PI*v);
		double sinU = Math.sin(2*Math.PI*u);
		double sinV = Math.sin(2*Math.PI*v);
		
		
		// torus 
		
		double r1 = 2;
		double r2 = 5;
		
		x1=(r2 + r1*cosU)*cosV;
		y1=(r2 + r1*cosU)*sinV;
		z1= r1*sinU; 
		
		/*
		// Cylinder
		x1=3*sinU;
		y1=3*cosU;
		z1=-3+v*(6);
		*/
		// plane
		/*
		x1=3-3*u;
		y1=8-8*v;
		z1=3*u+8*v;
		*/
		

		// animation
		double dur = t;
		double cosT = Math.cos(2*Math.PI*dur);
		double sinT = Math.sin(2*Math.PI*dur);
		
		
		x = x1*Math.cos(2*Math.PI*dur)+z1*Math.sin(2*Math.PI*dur);
		y = y1;
		z = x1*-Math.sin(2*Math.PI*dur)+z1*Math.cos(2*Math.PI*dur);
		
		//x = x1*cosT*cosT + y1*cosT*sinT + z1*-sinT;
		//y = x1*(sinT*sinT*cosT - cosT*sinT) + y1*(sinT*sinT*sinT + cosT*cosT) + z1*(sinT*cosT);
		//z = x1*(cosT*sinT*cosT + sinT*sinT) + y1*(cosT*sinT*sinT - sinT*cosT) + z1*(cosT*cosT);
		Vector3D vector = new Vector3D(x,y,z);
		return vector;
	}
	
	/**
	 * Solve an equation with three parameters and time value
	 * @param u
	 * @param v
	 * @param w
	 * @param t
	 * @return
	 */
	public static Vector3D f(double u, double v, double w, double t) {
		double x,y,z,x1,y1,z1;	
		// cube
		
		x1=-3+u*6;
		y1=-3+v*6; 
		z1=-3+w*6;
		
		
		// half sphere
		/*
		double cosU = Math.cos(-Math.PI/2+Math.PI*u/2);
		double sinU = Math.sin(-Math.PI/2+Math.PI*u/2);
		double cosV = Math.cos(-Math.PI+2*Math.PI*v);
		double sinV = Math.sin(-Math.PI+2*Math.PI*v);
		x1=w*3*cosU*sinV;
		y1=w*3*sinU;
		z1=w*3*cosU*cosV;
		*/
		
		// hershey kiss
		/*
		x1=(1+Math.sin(1.5*Math.PI*u))*w*(1-u)*1.5*Math.sin(2*Math.PI*v);
		y1=(5*u)*w;
		z1=(1+Math.sin(1.5*Math.PI*u))*w*(1-u)*1.5*Math.cos(2*Math.PI*v);
		*/
		// animation
		double dur = t;
		double cosT = Math.cos(2*Math.PI*dur);
		double sinT = Math.sin(2*Math.PI*dur);
		
		/*
		x = x1*Math.cos(2*Math.PI*dur)+z1*Math.sin(2*Math.PI*dur);
		y = y1;
		z = x1*-Math.sin(2*Math.PI*dur)+z1*Math.cos(2*Math.PI*dur);
		*/
		x = x1*cosT*cosT + y1*cosT*sinT + z1*-sinT;
		y = x1*(sinT*sinT*cosT - cosT*sinT) + y1*(sinT*sinT*sinT + cosT*cosT) + z1*(sinT*cosT);
		z = x1*(cosT*sinT*cosT + sinT*sinT) + y1*(cosT*sinT*sinT - sinT*cosT) + z1*(cosT*cosT);
		Vector3D vector = new Vector3D(x,y,z);
		return vector;
	}
	
	/**
	 * Get the normal of the function at a given point specified by parameters u, v, and t
	 * @param u
	 * @param v
	 * @param t
	 * @return
	 */
	public static Vector3D normal(double u, double v, double t) { // get the unit normal vector at a point
		double h = 1e-9;
		Vector3D partial_der_u = f(u+h,v,t);
		Vector3D partial_der_v = f(u,v+h,t);
		Vector3D base = f(u,v,t);
		
		partial_der_u.subtract(base);
		partial_der_u.divide(h);
		
		partial_der_v.subtract(base);
		partial_der_v.divide(h);
		
		Vector3D normal = partial_der_u.cross(partial_der_v);

		if (normal.magnitude() == 0) {
			return normal;
		}
		normal.divide(normal.magnitude()); // normalize to make a unit vector
		return normal;
	}
	
	/**
	 * Get the normal of a 3 parameter equation (u,v,w)
	 * @param u
	 * @param v
	 * @param w
	 * @param t
	 * @return
	 */
	public static Vector3D normal(double u, double v, double w, double t) { // get the unit normal vector at a point
		double h = 1e-9;
		
		Vector3D partial_der_1 = new Vector3D(0,0,0);
		Vector3D partial_der_2 = new Vector3D(0,0,0);
		

		
		if (w <= 0 || w >= 1) {
			//if (w<= 0) {
				partial_der_1 = f(u+h,v,w,t);
				partial_der_2 = f(u,v+h,w,t);
			//} else {
				//partial_der_1 = f(u,v+h,w,t);
				//partial_der_2 = f(u+h,v,w,t);
			//}
		} else if (v <= 0 || v >= 1) {
			//if (v <= 0) {
				//partial_der_1 = f(u+h,v,w,t);
				//partial_der_2 = f(u,v,w+h,t);
				partial_der_1 = f(u,v,w+h,t);
				partial_der_2 = f(u+h,v,w,t);
			//} else {
				//partial_der_1 = f(u+h,v,w,t);
				//partial_der_2 = f(u,v,w+h,t);
				//partial_der_1 = f(u,v,w+h,t);
				//partial_der_2 = f(u+h,v,w,t);
			//}
		} else if (u <= 0 || u > 1) {
			//if (u <= 0) {
				partial_der_1 = f(u,v+h,w,t);
				partial_der_2 = f(u,v,w+h,t);
			//} else {
				//partial_der_1 = f(u,v,w+h,t);
				//partial_der_2 = f(u,v+h,w,t);
			//}
		} else {
			partial_der_1 = f(u+h,v,w,t);
			partial_der_2 = f(u,v+h,w,t);
		}
	
		
		Vector3D base = f(u,v,w,t);
		
		partial_der_1.subtract(base);
		partial_der_1.divide(h);
		
		partial_der_2.subtract(base);
		partial_der_2.divide(h);
		
		Vector3D normal = partial_der_1.cross(partial_der_2);
		
		//normal.print(); // debugging

		if (normal.magnitude() == 0) {
			return normal;
		}
		normal.divide(normal.magnitude()); // normalize to make a unit vector
		return normal;
	}
	
	/**
	 * get the maximum value in x or y direction (return the larger)
	 * 	Used to set the camera distance
	 * @param numParams number of parameters (2 or 3)
	 * @return
	 */
	public static double getMaxValue(int numParams) {
		double increment = (double)1/60;
		double max_x = 0, max_y = 0;
		switch(numParams) {
		case 2: // calculation for 2 parameterfunction
			for (double t = 0; t <= 1; t+=increment) {
				for (double v = 0; v <= 1; v+=increment) {
					for (double u = 0; u <= 1; u+=increment) {
						Vector3D point = f(u,v,t);
						double x = Math.abs(point.getx());
						double y = Math.abs(point.gety());
						if (x>max_x) max_x = x;
						if (y>max_y) max_y = y;
					}
				}
			}
			break;
		case 3: // calculation for 3 parameter function
			for (double t = 0; t <= 1; t+=increment) {
				for (double w = 0; w <= 1; w+=increment) {
					for (double v = 0; v <= 1; v+=increment) {
						for (double u = 0; u <= 1; u+= increment) {
							Vector3D point = f(u,v,w,t);
							double x = Math.abs(point.getx());
							double y = Math.abs(point.gety());
							if (x>max_x) max_x = x;
							if (y>max_y) max_y = y;
						}
					}
				}
			}
			break;
		}
		return Math.max(max_x,max_y);
		
	}
}
