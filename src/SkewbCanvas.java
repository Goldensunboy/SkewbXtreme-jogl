import com.jogamp.newt.Window;
import com.jogamp.newt.event.MouseAdapter;
import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.awt.AWTMouseAdapter;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;

public class SkewbCanvas implements GLEventListener {
	
	private float view_rotx = 15, view_roty = -20, view_rotz = 0;
	private static final int swapInterval = 1;
	private SkewbPuzzle skewb = new SkewbPuzzle();
	
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		float lightpos[] = {5, 5, 10, 0};

		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightpos, 0);
		gl.glEnable(GL2.GL_CULL_FACE);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_NORMALIZE);
		
		skewb.draw(gl);
		
		MouseAdapter skewbMouse = new SkewbMouseAdapter();
		if (drawable instanceof Window) {
			Window window = (Window) drawable;
			window.addMouseListener(skewbMouse);
		} else if (GLProfile.isAWTAvailable() && drawable instanceof java.awt.Component) {
			java.awt.Component comp = (java.awt.Component) drawable;
			new AWTMouseAdapter(skewbMouse, drawable).addTo(comp);
		}
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		// Special handling for the case where the GLJPanel is translucent
		// and wants to be composited with other Java 2D content
		if (GLProfile.isAWTAvailable() &&
				(drawable instanceof com.jogamp.opengl.awt.GLJPanel) &&
				!((com.jogamp.opengl.awt.GLJPanel) drawable).isOpaque() &&
				((com.jogamp.opengl.awt.GLJPanel) drawable).shouldPreserveColorBufferIfTranslucent()) {
			gl.glClear(GL2.GL_DEPTH_BUFFER_BIT);
		} else {
			gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		}

		// Rotate the scene based on user's mouse drags
		gl.glPushMatrix();
		gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(view_rotz, 0.0f, 0.0f, 1.0f);

		// Place objects and call their display lists
		skewb.call(gl);
		
		gl.glPopMatrix();
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();

		gl.setSwapInterval(swapInterval);

		float h = (float) height / (float) width;

		gl.glMatrixMode(GL2.GL_PROJECTION);

		gl.glLoadIdentity();
		gl.glFrustum(-1.0f, 1.0f, -h, h, 5.0f, 60.0f);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -40.0f);
	}
	
	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}
	
	class SkewbMouseAdapter extends MouseAdapter {
		private int prevMouseX, prevMouseY;
		private boolean leftMouse, rightMouse;
		@Override
		public void mousePressed(MouseEvent e) {
			prevMouseX = e.getX();
			prevMouseY = e.getY();
			leftMouse = e.getButton() == MouseEvent.BUTTON1;
			rightMouse = e.getButton() == MouseEvent.BUTTON3;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			final int x = e.getX();
			final int y = e.getY();
			int width = 0, height = 0;
			Object source = e.getSource();
			if(source instanceof Window) {
				Window window = (Window) source;
				width = window.getSurfaceWidth();
				height = window.getSurfaceHeight();
			} else if(source instanceof GLAutoDrawable) {
				GLAutoDrawable glad = (GLAutoDrawable) source;
				width = glad.getSurfaceWidth();
				height = glad.getSurfaceHeight();
			} else if (GLProfile.isAWTAvailable() && source instanceof java.awt.Component) {
				java.awt.Component comp = (java.awt.Component) source;
				width = comp.getWidth();
				height = comp.getHeight();
			} else {
				throw new RuntimeException("Event source neither Window nor Component: " + source);
			}
			float thetaY = 360.0f * ((float) (x-prevMouseX) / (float) width);
			float thetaX = 360.0f * ((float) (y-prevMouseY) / (float) height);

			prevMouseX = x;
			prevMouseY = y;
			
			if(leftMouse) {
				view_rotx += thetaX;
				view_roty += thetaY;
			} else if(rightMouse) {
				view_rotz -= thetaX;
			}
		}
	}
}
