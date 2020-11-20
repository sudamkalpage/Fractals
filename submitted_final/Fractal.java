
//import necessary libraries 
import java.awt.*; // java abstract window toolkit 
import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

class Fractal extends JPanel { // Fractal inherit from JPanel

	private int width = 800; // Panel width
	private int height = 800; // Panel height
	static double x1 = -1; // left hand side limit of x (real part of complex number)
	static double x2 = 1; // right hand side limit of x (real part of complex number)
	static double y1 = -1; // left hand side limit of y (imaginary part of complex number)
	static double y2 = 1; // right hand side limit of y (imaginary part of complex number)
	static int iterations = 1000; // iterations of dynamic system
	static String set_type = ""; // Mandelbrot set or Julia set

	public Fractal() { // Set the panel width and height
		setPreferredSize(new Dimension(width, height));
	}

	private static void printPoint(Graphics2D frame, Color c, Point p) { // Printing a point with given color
		frame.setColor(c);
		frame.draw(new Line2D.Double(p.getX(), p.getY(), p.getX(), p.getY()));
	}

	private static boolean isValidLimits(double l1, double l2) { //Checking validity of a limit from l1 to l2
		if (l1 >= l2) {
			return false;
		}
		return true;
	}

	public void paintComponent(Graphics g) { // Set the panel width and height
		// Plotting the Mandelbrot set
		if (set_type == "Mandelbrot set") {
			Mandelbrot mandelbrot = new Mandelbrot(); // To retrieve updated values
			// Check whether the limits are valid
			if (!((Fractal.isValidLimits(x1, x2)) && (Fractal.isValidLimits(y1, y2)))) {
				System.out.println("Error: Wrong limits.(first number should be the smaller number)");
				System.exit(0);
			}
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					// Point in the created panel
					Point PanelC = new Point(i, width - j);
					// Respective point in the given range
					Point C = new Point((double) (i * (x2 - x1) / width + x1), (double) (j * (y2 - y1) / height + y1));
					if (mandelbrot.mandelbrotSet(C) == true) { // If C in the Mandelbrot set
						printPoint((Graphics2D) g, Color.BLACK, PanelC);
					}
				}
			}
		}
		// Plotting the Julia set
		else {
			Julia julia = new Julia(); // To retrieve updated values
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					// Point in the created panel
					Point PanelZ = new Point(i, width - j);
					// Respective point in the given range
					Point Z = new Point((double) (i * (x2 - x1) / width + x1),
							(double) (((j) * ((y2 - y1) / height)) + y1));
					if (julia.juliaSet(Z) == true) { // If Z in the Julia set
						printPoint((Graphics2D) g, Color.BLACK, PanelZ);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// Array list to store command line inputs
		ArrayList<String> cmd_inputs = new ArrayList<String>();

		for (int inc = 0; inc < args.length; inc++) {
			cmd_inputs.add(args[inc]);
		}

		if (args[0].equals("Mandelbrot")) { // Mandelbrot set
			if (Mandelbrot.setParameters(cmd_inputs) == true) {
				set_type = "Mandelbrot set";
			} else { // Error handling
				System.out.println("Error: number of arguements with Mandelbrot is wrong (can be only 0,4,5)");
				System.exit(0);
			}

		} else if (args[0].equals("Julia")) { // Julia set
			if (Julia.setParameters(cmd_inputs) == true) {
				set_type = "Julia Set";
			} else { // Error handling
				System.out.println("Error: number of arguements with Julia is wrong (can be only 0,2)");
				System.exit(0);
			}

		} else { // Error handling
			System.out.println("Error: Wrong syntax in the command line");
			System.exit(0);
		}

		// create a frame
		JFrame frame = new JFrame(set_type);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set the content of the frame as one of this panel
		frame.setContentPane(new Fractal());

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}