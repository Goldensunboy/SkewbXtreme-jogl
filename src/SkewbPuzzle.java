import java.awt.Color;

import com.jogamp.opengl.GL2;

public class SkewbPuzzle {
	
	private Polygon[] pieces = {
		new CenterPiece(0, 0),
		new CenterPiece(1, 0),
		new CenterPiece(2, 0),
		new CenterPiece(3, 0),
		new CenterPiece(4, 0),
		new CenterPiece(5, 0),
		new CornerPiece(0, 0),
		new CornerPiece(1, 0),
		new CornerPiece(2, 0),
		new CornerPiece(3, 0),
		new CornerPiece(4, 0),
		new CornerPiece(5, 0),
		new CornerPiece(6, 0),
		new CornerPiece(7, 0)
	};
	
	private final Color[] colors = {
		new Color(240, 240, 240), // WHITE
		new Color(0  , 150, 0  ), // GREEN
		new Color(240, 0  , 0  ), // RED
		new Color(70 , 70 , 255), // BLUE
		new Color(120, 160, 180), // PURPLE
		new Color(240, 240, 0  ), // YELLOW
		new Color(160, 255, 220), // TEAL
		new Color(255, 140, 160), // PINK
		new Color(220, 190, 255), // VIOLET
		new Color(170, 255, 140), // YELLOWISH GREEN
		new Color(20 , 20 , 20 )  // BLACK
	};
	
	private int[] GLpieces = new int[pieces.length];
	
	public SkewbPuzzle() {
		pieces[0].colors[0] = colors[0];
		pieces[0].colors[1] = colors[6];
		pieces[0].colors[2] = colors[9];
		pieces[0].colors[3] = colors[8];
		pieces[0].colors[4] = colors[7];
		pieces[0].colors[5] = colors[10];
		
		pieces[1].colors[0] = colors[1];
		pieces[1].colors[1] = colors[8];
		pieces[1].colors[2] = colors[6];
		pieces[1].colors[3] = colors[7];
		pieces[1].colors[4] = colors[9];
		pieces[1].colors[5] = colors[10];
		
		pieces[2].colors[0] = colors[2];
		pieces[2].colors[1] = colors[8];
		pieces[2].colors[2] = colors[7];
		pieces[2].colors[3] = colors[9];
		pieces[2].colors[4] = colors[6];
		pieces[2].colors[5] = colors[10];
		
		pieces[3].colors[0] = colors[3];
		pieces[3].colors[1] = colors[9];
		pieces[3].colors[2] = colors[7];
		pieces[3].colors[3] = colors[6];
		pieces[3].colors[4] = colors[8];
		pieces[3].colors[5] = colors[10];
		
		pieces[4].colors[0] = colors[4];
		pieces[4].colors[1] = colors[7];
		pieces[4].colors[2] = colors[8];
		pieces[4].colors[3] = colors[6];
		pieces[4].colors[4] = colors[9];
		pieces[4].colors[5] = colors[10];
		
		pieces[5].colors[0] = colors[5];
		pieces[5].colors[1] = colors[7];
		pieces[5].colors[2] = colors[8];
		pieces[5].colors[3] = colors[9];
		pieces[5].colors[4] = colors[6];
		pieces[5].colors[5] = colors[10];
		
		pieces[6].colors[0] = colors[0];
		pieces[6].colors[1] = colors[1];
		pieces[6].colors[2] = colors[2];
		pieces[6].colors[3] = colors[7];
		pieces[6].colors[4] = colors[8];
		pieces[6].colors[5] = colors[9];
		pieces[6].colors[6] = colors[10];
		
		pieces[7].colors[0] = colors[0];
		pieces[7].colors[1] = colors[4];
		pieces[7].colors[2] = colors[1];
		pieces[7].colors[3] = colors[8];
		pieces[7].colors[4] = colors[9];
		pieces[7].colors[5] = colors[6];
		pieces[7].colors[6] = colors[10];
		
		pieces[8].colors[0] = colors[0];
		pieces[8].colors[1] = colors[3];
		pieces[8].colors[2] = colors[4];
		pieces[8].colors[3] = colors[9];
		pieces[8].colors[4] = colors[6];
		pieces[8].colors[5] = colors[7];
		pieces[8].colors[6] = colors[10];
		
		pieces[9].colors[0] = colors[0];
		pieces[9].colors[1] = colors[2];
		pieces[9].colors[2] = colors[3];
		pieces[9].colors[3] = colors[6];
		pieces[9].colors[4] = colors[7];
		pieces[9].colors[5] = colors[8];
		pieces[9].colors[6] = colors[10];
		
		pieces[10].colors[0] = colors[1];
		pieces[10].colors[1] = colors[5];
		pieces[10].colors[2] = colors[2];
		pieces[10].colors[3] = colors[9];
		pieces[10].colors[4] = colors[7];
		pieces[10].colors[5] = colors[6];
		pieces[10].colors[6] = colors[10];
		
		pieces[11].colors[0] = colors[4];
		pieces[11].colors[1] = colors[5];
		pieces[11].colors[2] = colors[1];
		pieces[11].colors[3] = colors[6];
		pieces[11].colors[4] = colors[8];
		pieces[11].colors[5] = colors[7];
		pieces[11].colors[6] = colors[10];
		
		pieces[12].colors[0] = colors[3];
		pieces[12].colors[1] = colors[5];
		pieces[12].colors[2] = colors[4];
		pieces[12].colors[3] = colors[7];
		pieces[12].colors[4] = colors[9];
		pieces[12].colors[5] = colors[8];
		pieces[12].colors[6] = colors[10];
		
		pieces[13].colors[0] = colors[2];
		pieces[13].colors[1] = colors[5];
		pieces[13].colors[2] = colors[3];
		pieces[13].colors[3] = colors[8];
		pieces[13].colors[4] = colors[6];
		pieces[13].colors[5] = colors[9];
		pieces[13].colors[6] = colors[10];
	}
	
	public void draw(GL2 gl) {
		for(int i = 0; i < pieces.length; ++i) {
			GLpieces[i] = gl.glGenLists(1);
			gl.glNewList(GLpieces[i], GL2.GL_COMPILE);
			pieces[i].draw(gl);
			gl.glEndList();
		}
	}
	
	public void call(GL2 gl) {
		for(int i = 0; i < pieces.length; ++i) {
			gl.glPushMatrix();
			gl.glCallList(GLpieces[i]);
			gl.glPopMatrix();
		}
	}
}
