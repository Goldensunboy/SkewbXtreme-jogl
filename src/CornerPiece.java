import java.awt.Color;

import com.jogamp.opengl.GL2;

public class CornerPiece extends Polygon {
	
	private static final Vertex[] VERT = {
		new Vertex(2, 3, 3), //  \ Front pentagonal face
		new Vertex(3, 3, 3), //  |
		new Vertex(3, 2, 3), //  |
		new Vertex(2, 1, 3), //  |
		new Vertex(1, 2, 3), //  /
		new Vertex(1, 3, 2), //  \ Top pentagonal face
		new Vertex(2, 3, 1), //  | (with 0 and 1)
		new Vertex(3, 3, 2), //  /
		new Vertex(3, 2, 1), //  \ Right pentagonal face
		new Vertex(3, 1, 2), //  / (with 1, 2 and 7)
		new Vertex(0, 2, 2), //  Left diamond (with 0, 4 and 5)
		new Vertex(2, 2, 0), //  Back diamond (with 6, 7 and 8)
		new Vertex(2, 0, 2)  //  Lower diamond (with 2, 3 and 9)
	};
	
	private int location = 0;
	private int orientation = 0;
	
	public void setPosition(int loc, int ori) {
		location = loc;
		orientation = ori;
		float[] rotvec = null;
		switch(loc * 3 + ori) {
		case 0 : {float[] v = {0  , 0  , 0  }; rotvec = v; break;} // FUR/O
		case 1 : {float[] v = {270, 0  , 270}; rotvec = v; break;} // FUR/CW
		case 2 : {float[] v = {0  , 90 , 90 }; rotvec = v; break;} // FUR/CCW
		case 3 : {float[] v = {0  , 0  , 90 }; rotvec = v; break;} // FUL/O
		case 4 : {float[] v = {0  , 270, 0  }; rotvec = v; break;} // FUL/CW
		case 5 : {float[] v = {90 , 180, 0  }; rotvec = v; break;} // FUL/CCW
		case 6 : {float[] v = {0  , 0  , 180}; rotvec = v; break;} // FDL/O
		case 7 : {float[] v = {90 , 0  , 90 }; rotvec = v; break;} // FDL/CW
		case 8 : {float[] v = {0  , 270, 270}; rotvec = v; break;} // FDL/CCW
		case 9 : {float[] v = {0  , 0  , 270}; rotvec = v; break;} // FDR/O
		case 10: {float[] v = {0  , 90 , 180}; rotvec = v; break;} // FDR/CW
		case 11: {float[] v = {90 , 0  , 0  }; rotvec = v; break;} // FDR/CCW
		case 12: {float[] v = {270, 0  , 0  }; rotvec = v; break;} // BUR/O
		case 13: {float[] v = {0  , 180, 90 }; rotvec = v; break;} // BUR/CW
		case 14: {float[] v = {0  , 90 , 0  }; rotvec = v; break;} // BUR/CCW
		case 15: {float[] v = {270, 270, 0  }; rotvec = v; break;} // BUL/O
		case 16: {float[] v = {0  , 180, 0  }; rotvec = v; break;} // BUL/CW
		case 17: {float[] v = {270, 0  , 90 }; rotvec = v; break;} // BUL/CCW
		case 18: {float[] v = {270, 180, 0  }; rotvec = v; break;} // BDL/O
		case 19: {float[] v = {0  , 180, 270}; rotvec = v; break;} // BDL/CW
		case 20: {float[] v = {0  , 270, 180}; rotvec = v; break;} // BDL/CCW
		case 21: {float[] v = {270, 90 , 0  }; rotvec = v; break;} // BDR/O
		case 22: {float[] v = {180, 0  , 0  }; rotvec = v; break;} // BDR/CW
		case 23: {float[] v = {90 , 0  , 270}; rotvec = v; break;} // BDR/CCW
		default:
			System.out.println("Invalid position! (" + loc + ", " + ori + ")");
		}
		rx = rotvec[0];
		ry = rotvec[1];
		rz = rotvec[2];
	}
	
	public CornerPiece(int loc, int ori) {
		super(1, 1, 1, 0, 0, 0);
		colors = new Color[7];
		setPosition(loc, ori);
	}

	@Override
	public void draw(GL2 gl) {
		
		gl.glPushMatrix();
		gl.glRotatef(rx, 1, 0, 0);
		gl.glRotatef(ry, 0, 1, 0);
		gl.glRotatef(rz, 0, 0, 1);
		gl.glTranslatef(x, y, z);
		
		gl.glShadeModel(GL2.GL_FLAT);
		
		// Front face
		float[] c = getColorArr(colors[0]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		Vertex n = getNormal(VERT[1], VERT[0], VERT[2]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_POLYGON);
		gl.glVertex3f(VERT[4].x, VERT[4].y, VERT[4].z);
		gl.glVertex3f(VERT[3].x, VERT[3].y, VERT[3].z);
		gl.glVertex3f(VERT[2].x, VERT[2].y, VERT[2].z);
		gl.glVertex3f(VERT[1].x, VERT[1].y, VERT[1].z);
		gl.glVertex3f(VERT[0].x, VERT[0].y, VERT[0].z);
		gl.glEnd();
		
		// Top face
		c = getColorArr(colors[1]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		n = getNormal(VERT[1], VERT[7], VERT[0]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_POLYGON);
		gl.glVertex3f(VERT[7].x, VERT[7].y, VERT[7].z);
		gl.glVertex3f(VERT[6].x, VERT[6].y, VERT[6].z);
		gl.glVertex3f(VERT[5].x, VERT[5].y, VERT[5].z);
		gl.glVertex3f(VERT[0].x, VERT[0].y, VERT[0].z);
		gl.glVertex3f(VERT[1].x, VERT[1].y, VERT[1].z);
		gl.glEnd();
		
		// Right face
		c = getColorArr(colors[2]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		n = getNormal(VERT[1], VERT[2], VERT[7]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_POLYGON);
		gl.glVertex3f(VERT[9].x, VERT[9].y, VERT[9].z);
		gl.glVertex3f(VERT[8].x, VERT[8].y, VERT[8].z);
		gl.glVertex3f(VERT[7].x, VERT[7].y, VERT[7].z);
		gl.glVertex3f(VERT[1].x, VERT[1].y, VERT[1].z);
		gl.glVertex3f(VERT[2].x, VERT[2].y, VERT[2].z);
		gl.glEnd();
		
		// Left diamond
		c = getColorArr(colors[3]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		n = getNormal(VERT[0], VERT[5], VERT[4]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[0].x, VERT[0].y, VERT[0].z);
		gl.glVertex3f(VERT[5].x, VERT[5].y, VERT[5].z);
		gl.glVertex3f(VERT[10].x, VERT[10].y, VERT[10].z);
		gl.glVertex3f(VERT[4].x, VERT[4].y, VERT[4].z);
		gl.glEnd();
		
		// Back diamond
		c = getColorArr(colors[4]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		n = getNormal(VERT[7], VERT[8], VERT[6]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[6].x, VERT[6].y, VERT[6].z);
		gl.glVertex3f(VERT[7].x, VERT[7].y, VERT[7].z);
		gl.glVertex3f(VERT[8].x, VERT[8].y, VERT[8].z);
		gl.glVertex3f(VERT[11].x, VERT[11].y, VERT[11].z);
		gl.glEnd();
		
		// Lower diamond
		c = getColorArr(colors[5]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		n = getNormal(VERT[2], VERT[3], VERT[9]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[2].x, VERT[2].y, VERT[2].z);
		gl.glVertex3f(VERT[3].x, VERT[3].y, VERT[3].z);
		gl.glVertex3f(VERT[12].x, VERT[12].y, VERT[12].z);
		gl.glVertex3f(VERT[9].x, VERT[9].y, VERT[9].z);
		gl.glEnd();
		
		// Front inner face
		c = getColorArr(colors[6]);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, c, 0);
		n = getNormal(VERT[3], VERT[4], VERT[12]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[3].x, VERT[3].y, VERT[3].z);
		gl.glVertex3f(VERT[4].x, VERT[4].y, VERT[4].z);
		gl.glVertex3f(VERT[10].x, VERT[10].y, VERT[10].z);
		gl.glVertex3f(VERT[12].x, VERT[12].y, VERT[12].z);
		gl.glEnd();
		
		// Upper inner face
		n = getNormal(VERT[5], VERT[6], VERT[10]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[5].x, VERT[5].y, VERT[5].z);
		gl.glVertex3f(VERT[6].x, VERT[6].y, VERT[6].z);
		gl.glVertex3f(VERT[11].x, VERT[11].y, VERT[11].z);
		gl.glVertex3f(VERT[10].x, VERT[10].y, VERT[10].z);
		gl.glEnd();
		
		// Right inner face
		n = getNormal(VERT[8], VERT[9], VERT[11]);
		gl.glNormal3f(n.x, n.y, n.z);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(VERT[8].x, VERT[8].y, VERT[8].z);
		gl.glVertex3f(VERT[9].x, VERT[9].y, VERT[9].z);
		gl.glVertex3f(VERT[12].x, VERT[12].y, VERT[12].z);
		gl.glVertex3f(VERT[11].x, VERT[11].y, VERT[11].z);
		gl.glEnd();
		
		gl.glPopMatrix();
	}
}
