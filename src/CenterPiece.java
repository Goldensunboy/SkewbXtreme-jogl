import java.awt.Color;

import com.jogamp.opengl.GL2;

public class CenterPiece extends Polygon {
	
	private static final Vertex[] VERT = {
		new Vertex(0, 1, 3),  //  \ Inner corners
		new Vertex(1, 0, 3),  //  |
		new Vertex(0, -1, 3), //  |
		new Vertex(-1, 0, 3), //  /
		new Vertex(1, 2, 3),  //  \ Outer corners
		new Vertex(2, 1, 3),  //  |
		new Vertex(2, -1, 3), //  |
		new Vertex(1, -2, 3), //  |
		new Vertex(-1, -2, 3),//  |
		new Vertex(-2, -1, 3),//  |
		new Vertex(-2, 1, 3), //  |
		new Vertex(-1, 2, 3), //  /
		new Vertex(0, 2, 2),  //  \ Recessed corners
		new Vertex(2, 0, 2),  //  |
		new Vertex(0, -2, 2), //  |
		new Vertex(-2, 0, 2)  //  /
	};
	
	private int location = 0;
	private int orientation = 0;
	
	public void setPosition(int loc, int ori) {
		location = loc;
		orientation = ori;
		float[] rotvec = null;
		switch(loc * 4 + ori) {
		case 0 : {float[] v = {0  , 0  , 0  }; rotvec = v; break;} // F/O
		case 1 : {float[] v = {0  , 0  , 270}; rotvec = v; break;} // F/CW
		case 2 : {float[] v = {0  , 0  , 180}; rotvec = v; break;} // F/180
		case 3 : {float[] v = {0  , 0  , 90 }; rotvec = v; break;} // F/CCW
		case 4 : {float[] v = {270, 0  , 0  }; rotvec = v; break;} // U/O
		case 5 : {float[] v = {270, 0  , 270}; rotvec = v; break;} // U/CW
		case 6 : {float[] v = {270, 0  , 180}; rotvec = v; break;} // U/180
		case 7 : {float[] v = {270, 0  , 90 }; rotvec = v; break;} // U/CCW
		case 8 : {float[] v = {0  , 90 , 0  }; rotvec = v; break;} // R/O
		case 9 : {float[] v = {0  , 90 , 270}; rotvec = v; break;} // R/CW
		case 10: {float[] v = {0  , 90 , 180}; rotvec = v; break;} // R/180
		case 11: {float[] v = {0  , 90 , 90 }; rotvec = v; break;} // R/CCW
		case 12: {float[] v = {90 , 0  , 0  }; rotvec = v; break;} // D/O
		case 13: {float[] v = {90 , 0  , 270}; rotvec = v; break;} // D/CW
		case 14: {float[] v = {90 , 0  , 180}; rotvec = v; break;} // D/180
		case 15: {float[] v = {90 , 0  , 90 }; rotvec = v; break;} // D/CCW
		case 16: {float[] v = {0  , 270, 0  }; rotvec = v; break;} // L/O
		case 17: {float[] v = {0  , 270, 270}; rotvec = v; break;} // L/CW
		case 18: {float[] v = {0  , 270, 180}; rotvec = v; break;} // L/180
		case 19: {float[] v = {0  , 270, 90 }; rotvec = v; break;} // L/CCW
		case 20: {float[] v = {180, 0  , 0  }; rotvec = v; break;} // B/O
		case 21: {float[] v = {180, 0  , 270}; rotvec = v; break;} // B/CW
		case 22: {float[] v = {180, 0  , 180}; rotvec = v; break;} // B/180
		case 23: {float[] v = {180, 0  , 90 }; rotvec = v; break;} // B/CCW
		default:
			System.out.println("Invalid position! (" + loc + ", " + ori + ")");
		}
		rx = rotvec[0];
		ry = rotvec[1];
		rz = rotvec[2];
	}
	
	public CenterPiece(int loc, int ori) {
		super(0, 0, 0, 0, 0, 0);
		colors = new Color[6];
		setPosition(loc, ori);
	}

	@Override
	public void draw(GL2 gl) {
		
		gl.glPushMatrix();
		gl.glRotatef(rx, 1, 0, 0);
		gl.glRotatef(ry, 0, 1, 0);
		gl.glRotatef(rz, 0, 0, 1);
		
		gl.glShadeModel(GL2.GL_FLAT);

		// Center rectangle
		float[] c = getColorArr(colors[0]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		Vertex n = getNormal(VERT[4], VERT[9], VERT[5]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[5].x, VERT[5].y, VERT[5].z);
		gl.glVertex3f(VERT[4].x, VERT[4].y, VERT[4].z);
		gl.glVertex3f(VERT[9].x, VERT[9].y, VERT[9].z);
		gl.glVertex3f(VERT[8].x, VERT[8].y, VERT[8].z);
		gl.glEnd();
		
		// Upper left rectangle
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[3].x, VERT[3].y, VERT[3].z);
		gl.glVertex3f(VERT[0].x, VERT[0].y, VERT[0].z);
		gl.glVertex3f(VERT[11].x, VERT[11].y, VERT[11].z);
		gl.glVertex3f(VERT[10].x, VERT[10].y, VERT[10].z);
		gl.glEnd();
		
		// Lower right rectangle
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[1].x, VERT[1].y, VERT[1].z);
		gl.glVertex3f(VERT[2].x, VERT[2].y, VERT[2].z);
		gl.glVertex3f(VERT[7].x, VERT[7].y, VERT[7].z);
		gl.glVertex3f(VERT[6].x, VERT[6].y, VERT[6].z);
		gl.glEnd();
		
		// UR triangles
		c = getColorArr(colors[1]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		n = getNormal(VERT[0], VERT[12], VERT[11]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex3f(VERT[0].x, VERT[0].y, VERT[0].z);
		gl.glVertex3f(VERT[12].x, VERT[12].y, VERT[12].z);
		gl.glVertex3f(VERT[11].x, VERT[11].y, VERT[11].z);
		gl.glEnd();
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex3f(VERT[1].x, VERT[1].y, VERT[1].z);
		gl.glVertex3f(VERT[6].x, VERT[6].y, VERT[6].z);
		gl.glVertex3f(VERT[13].x, VERT[13].y, VERT[13].z);
		gl.glEnd();
		
		// BR triangles
		c = getColorArr(colors[2]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		n = getNormal(VERT[1], VERT[13], VERT[5]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex3f(VERT[1].x, VERT[1].y, VERT[1].z);
		gl.glVertex3f(VERT[13].x, VERT[13].y, VERT[13].z);
		gl.glVertex3f(VERT[5].x, VERT[5].y, VERT[5].z);
		gl.glEnd();
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex3f(VERT[2].x, VERT[2].y, VERT[2].z);
		gl.glVertex3f(VERT[8].x, VERT[8].y, VERT[8].z);
		gl.glVertex3f(VERT[14].x, VERT[14].y, VERT[14].z);
		gl.glEnd();
		
		// BL triangles
		c = getColorArr(colors[3]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		n = getNormal(VERT[2], VERT[14], VERT[7]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex3f(VERT[2].x, VERT[2].y, VERT[2].z);
		gl.glVertex3f(VERT[14].x, VERT[14].y, VERT[14].z);
		gl.glVertex3f(VERT[7].x, VERT[7].y, VERT[7].z);
		gl.glEnd();
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex3f(VERT[3].x, VERT[3].y, VERT[3].z);
		gl.glVertex3f(VERT[10].x, VERT[10].y, VERT[10].z);
		gl.glVertex3f(VERT[15].x, VERT[15].y, VERT[15].z);
		gl.glEnd();
		
		// UL triangles
		c = getColorArr(colors[4]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		n = getNormal(VERT[3], VERT[15], VERT[9]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex3f(VERT[3].x, VERT[3].y, VERT[3].z);
		gl.glVertex3f(VERT[15].x, VERT[15].y, VERT[15].z);
		gl.glVertex3f(VERT[9].x, VERT[9].y, VERT[9].z);
		gl.glEnd();
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex3f(VERT[0].x, VERT[0].y, VERT[0].z);
		gl.glVertex3f(VERT[4].x, VERT[4].y, VERT[4].z);
		gl.glVertex3f(VERT[12].x, VERT[12].y, VERT[12].z);
		gl.glEnd();
		
		// UR back face
		c = getColorArr(colors[5]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		n = getNormal(VERT[4], VERT[5], VERT[12]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[4].x, VERT[4].y, VERT[4].z);
		gl.glVertex3f(VERT[5].x, VERT[5].y, VERT[5].z);
		gl.glVertex3f(VERT[13].x, VERT[13].y, VERT[13].z);
		gl.glVertex3f(VERT[12].x, VERT[12].y, VERT[12].z);
		gl.glEnd();
		
		// BR back face
		n = getNormal(VERT[6], VERT[7], VERT[13]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[6].x, VERT[6].y, VERT[6].z);
		gl.glVertex3f(VERT[7].x, VERT[7].y, VERT[7].z);
		gl.glVertex3f(VERT[14].x, VERT[14].y, VERT[14].z);
		gl.glVertex3f(VERT[13].x, VERT[13].y, VERT[13].z);
		gl.glEnd();
		
		// BL back face
		n = getNormal(VERT[8], VERT[9], VERT[14]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[8].x, VERT[8].y, VERT[8].z);
		gl.glVertex3f(VERT[9].x, VERT[9].y, VERT[9].z);
		gl.glVertex3f(VERT[15].x, VERT[15].y, VERT[15].z);
		gl.glVertex3f(VERT[14].x, VERT[14].y, VERT[14].z);
		gl.glEnd();
		
		// UL back face
		n = getNormal(VERT[10], VERT[11], VERT[15]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[10].x, VERT[10].y, VERT[10].z);
		gl.glVertex3f(VERT[11].x, VERT[11].y, VERT[11].z);
		gl.glVertex3f(VERT[12].x, VERT[12].y, VERT[12].z);
		gl.glVertex3f(VERT[15].x, VERT[15].y, VERT[15].z);
		gl.glEnd();
		
		gl.glPopMatrix();
	}
}
