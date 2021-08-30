package render;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import math.Equation;
import math.Vector3D;

public class Animator {
	// screen variables
	private JTextPane screen; // visualization screen
	private int width = 40; // height/width of the screen in num of characters
	private char[][] screen_arr = new char [width][width]; // array of characters to display onto screen
	private double[][] dist_arr = new double[width][width]; // for conflict-cases where a point would map to the same area on the screen, we choose the closest point.
	private double[][][] value_arr = new double[width][width][4]; // for each point on the screen, save the values of the equation to calculate the shading later on.
	// camera-related variables
	private double camera_dist; // distance from camera to the screen
	private double object_dist = 12; // distance of center of object from the screen (in z direction)
	
	// timer-related variables
	private Timer timer; // for iteration through frames
	private int sleep_duration; // duration between frames in milliseconds
	private double t_increment; // value to increment t by
	private double t = 0; // current time value
	private int frames;
	
	// rendering-related variables
	private double increment; // value to increment parameters by (determined by sampling res.)
	private double sampling_resolution = 120;
	
	// equation-related variables
	private int numParams;
	
	// light-related variables
	private double light_x = 4;
	private double light_y = 1;
	private double light_z = 4;
	private double light_intensity = 1; // strength of light source - from 0 to 1, where 1 is the strongest intensity
	private double ambient_illumination = 0; // ambient illumination (AKA base illumination level), from 0 to 1
	
	private boolean on=false;
	
	private static char[] shader_arr = "^\":;=+*#$@".toCharArray(); // levels of shading from 1 (lowest) to 10 (brightest)
	// @$#*+=;:. .:;=+*#$@
	public Animator(JTextPane textArea, double seconds, int frames, int params) {
		screen = textArea;
		this.frames = frames;
		// set the camera distance using the maximum value of the function in the x or y dimension
		camera_dist = (double)width*object_dist*3/(8*Equation.getMaxValue(params));
		increment = (double)1/sampling_resolution;
		sleep_duration = (int)(seconds/frames)*1000;
		t_increment = (double)1/frames;
		numParams = params;
		timer = new Timer(sleep_duration, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				renderFrame();
			}
	    });
		
		// instantiate the array
		for (int i =0;i<width;i++) 
			for (int j=0;j<width;j++) {
				screen_arr[i][j] = ' ';
				dist_arr[i][j] = Integer.MAX_VALUE;
				value_arr[i][j][0] = -1;
				value_arr[i][j][1] = -1;
				value_arr[i][j][2] = -1;
				value_arr[i][j][3] = -1;
			}
	}
	
	private void renderFrame() {
		// parameters u,v, and t
		double u = 0;
		double v = 0;
		double w = 0;
		if (numParams == 2) {
			for (u = 0; u<=1; u+=increment) {
				for (v = 0; v<=1; v+=increment) {
					Vector3D vector = Equation.f(u, v, t);
					project(vector,u,v,t);
				}
			}
		} else {
			for (u = 0; u<=1; u+=increment) {
				for (v = 0; v<=1; v+=increment) {
					for (w = 0; w<=1; w+=increment) {
						Vector3D vector = Equation.f(u, v, w, t);
						//project(vector.getx(),vector.gety(),vector.getz(),u,v,t);
						project(vector,u,v,w,t);
					}
				}
			}
		}
        display();
        t+=t_increment;
        if (t>=1) {t=0;}	
	}
	
	private void project(Vector3D s, double u, double v, double t) {
		//double dist = camera_dist+object_dist+z;
		double x = s.getx();
		double y = s.gety();
		double z = s.getz();
		// get the coordinates for the location on the screen
		int x_proj = (int) (width/2+camera_dist*x/(object_dist+z));
		int y_proj = (int) (width/2-camera_dist*y/(object_dist+z));
		// Phong illumination model; only using ambient and diffuse intensity
		//double light_intensity = ambient_illumination + 
		//System.out.println(x_proj + ", " + y_proj);
		if (0 <= y_proj && y_proj < width && 0 <= x_proj && x_proj < width) {
			if (z<dist_arr[y_proj][x_proj]) { // if new point is closer than a previously rendered point
				//point_arr[y_proj][x_proj] = shader_arr[getShading(s,u,v,w,t)];
				value_arr[y_proj][x_proj][0] = u;
				value_arr[y_proj][x_proj][1] = v;
				value_arr[y_proj][x_proj][3] = t;
				dist_arr[y_proj][x_proj] = z;
			}
		}
		
	}
	
	private void project(Vector3D s, double u, double v, double w, double t) {
		//double dist = camera_dist+object_dist+z;
		double x = s.getx();
		double y = s.gety();
		double z = s.getz();
		// get the coordinates for the location on the screen
		int x_proj = (int) (width/2+camera_dist*x/(object_dist+z));
		int y_proj = (int) (width/2-camera_dist*y/(object_dist+z));
		// Phong illumination model; only using ambient and diffuse intensity
		//double light_intensity = ambient_illumination + 
		//System.out.println(x_proj + ", " + y_proj);
		if (0 <= y_proj && y_proj < width && 0 <= x_proj && x_proj < width) {
			if (z<dist_arr[y_proj][x_proj]) { // if new point is closer than a previously rendered point
				//point_arr[y_proj][x_proj] = shader_arr[getShading(s,u,v,w,t)];
				value_arr[y_proj][x_proj][0] = u;
				value_arr[y_proj][x_proj][1] = v;
				value_arr[y_proj][x_proj][2] = w;
				value_arr[y_proj][x_proj][3] = t;
				dist_arr[y_proj][x_proj] = z;
			}
		}
		
	}
	
	private void display() {
		screen.setText(null);
		int[] shade = new int[2];
		for (int i =0;i<width;i++) {
			for (int j=0;j<width;j++) {
				//System.out.print(point_arr[i][j]);
				if (value_arr[i][j][0] != -1) {
					double u = value_arr[i][j][0];
					double v = value_arr[i][j][1];
					double w = value_arr[i][j][2];
					double t = value_arr[i][j][3];
					shade = shade(u,v,w,t);
					screen_arr[i][j] = shader_arr[shade[0]];
					int rgb = shade[1];
					append(Character.toString(screen_arr[i][j])+Character.toString(screen_arr[i][j]), new Color(rgb, rgb, rgb));
				} else
					append("  ", Color.BLACK);
				//screen.append(Character.toString(screen_arr[i][j])+Character.toString(screen_arr[i][j]));
				screen_arr[i][j] = ' '; // wipe array after displaying
				dist_arr[i][j] = Integer.MAX_VALUE; // reset to maximum value
				value_arr[i][j][0] = -1;
				value_arr[i][j][1] = -1;
				value_arr[i][j][2] = -1;
				value_arr[i][j][3] = -1;
			}
			append("\n",Color.BLACK);
			// screen.append("\n");
		//System.out.println();
		}
	}
	
	private void append(String str, Color c) {
	     StyledDocument document = (StyledDocument) screen.getDocument();
	     Style style = screen.addStyle("New Style", null);
	     StyleConstants.setForeground(style, c);
	     try {
			document.insertString(document.getLength(), str, style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	
	public int[] shade(double u, double v, double w, double t) {
		Vector3D s,n,l = new Vector3D(light_x,light_y,light_z+object_dist);
		if (w == -1) { // if w = -1 , then there are only 2 parameters
			s = Equation.f(u, v, t);
			n = Equation.normal(u, v, t);
		} else { // there are three parameters
			s = Equation.f(u, v, w, t);
			n = Equation.normal(u, v, w, t);
		}
			l.subtract(s); // get light vector
			l.divide(l.magnitude()); // normalize
			double light_angle = n.dotProduct(l); // from 0 to 1
			if (light_angle >= 0) {
				double intensity = ambient_illumination+light_intensity*light_angle;
				if (intensity > 1) intensity = 1;
				return new int[] {(int)(intensity*9+0.5),(int)(intensity*255+0.5)}; // convert to range 0 to 10 rounded
			} else {
				return new int[]{0,0}; // if negative, then light is hitting at >90 angle and thus should be rendered black
			}
	}
	
	
	public void setTime(double seconds, int frames) {
		sleep_duration = (int)(seconds/frames)*1000;
		t_increment = 1/frames;
		timer = new Timer(sleep_duration, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				renderFrame();
			}
	    });
	}
	
	public void start() {
		System.out.println("Animating");
		screen.setText(null);
		timer.start();
	}
	
	public void stop() {
		System.out.println("Animation stopped at frame "+ (int)(t*frames));
		timer.stop();
		//t=0;
	}
	
	public void toggle() {
		if (on) {
			stop();
			on=false;
		} else {
			start();
			on=true;
		}
		
	}
	
	public void setSamplingResolution(double sampling_res) {
		this.sampling_resolution = sampling_res;
		this.increment = 1/sampling_resolution;
	}
	
	public void setCameraDist(double dist) {
		camera_dist = dist;
	}
	
	public void setObjectDist(double dist) {
		object_dist = dist;
	}
}
