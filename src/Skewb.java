import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

public class Skewb {
	public static void main(String[] args) {

		java.awt.Frame frame = new java.awt.Frame("Skewb");
		frame.setSize(1500, 1500);
		frame.setLayout(new java.awt.BorderLayout());

		final Animator animator = new Animator();
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						animator.stop();
						System.exit(0);
					}
				}).start();
			}
		});

		GLCanvas canvas = new GLCanvas();
		animator.add(canvas);

		canvas.addGLEventListener(new SkewbCanvas());

		frame.add(canvas, java.awt.BorderLayout.CENTER);
		frame.validate();

		frame.setVisible(true);
		animator.start();
	}
}
