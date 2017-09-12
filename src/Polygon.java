import java.awt.Color;

import com.jogamp.opengl.GL2;

public abstract class Polygon {
	
	public float x, y, z, rx, ry, rz;
	public Color[] colors;
	
	public Polygon(float x, float y, float z, float rx, float ry, float rz) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
	}
	
	public abstract void draw(GL2 gl);
	
	public static Vertex getNormal(Vertex p, Vertex a, Vertex b) {
		float u1 = a.x - p.x, u2 = a.y - p.y, u3 = a.z - p.z;
		float v1 = b.x - p.x, v2 = b.y - p.y, v3 = b.z - p.z;
		return new Vertex(u2 * v3 - u3 * v2, u3 * v1 - u1 * v3, u1 * v2 - u2 * v1);
	}
	
	public static float[] getColorArr(Color c) {
		float[] f = {
				(float) c.getRed() / 255,
				(float) c.getGreen() / 255,
				(float) c.getBlue() / 255,
				0
		};
		return f;
	}
}
