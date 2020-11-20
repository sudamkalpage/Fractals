import java.util.ArrayList;

public class Mandelbrot extends Fractal { // If the required set is Mandelbrot

	// Setting necessary parameters from command line arguments in Mandelbrot set
	public static boolean setParameters(ArrayList<String> cmd_inputs) {
		if (cmd_inputs.size() == 1) {
		} else if (cmd_inputs.size() == 5) {
			x1 = Double.parseDouble(cmd_inputs.get(1));
			x2 = Double.parseDouble(cmd_inputs.get(2));
			y1 = Double.parseDouble(cmd_inputs.get(3));
			y2 = Double.parseDouble(cmd_inputs.get(4));
		} else if (cmd_inputs.size() == 6) {
			x1 = Double.parseDouble(cmd_inputs.get(1));
			x2 = Double.parseDouble(cmd_inputs.get(2));
			y1 = Double.parseDouble(cmd_inputs.get(3));
			y2 = Double.parseDouble(cmd_inputs.get(4));
			iterations = Integer.parseInt(cmd_inputs.get(5));
		} else {
			// Wrong number of command line input arguments
			return false;
		}
		return true;
	}

	public boolean mandelbrotSet(Point C) { // Function to check whether given C is in the Mandelbrot set
		double z_real = 0;
		double z_imag = 0;
		double znx_real;
		double znx_imag;
		for (int k = 0; k < iterations; k++) { // if ABS(Zn) > 2 not in the set --> ABS(Zn^2) > 4
			if ((z_real * z_real + z_imag * z_imag) > 4) {
				return false;
			}
			znx_real = (z_real * z_real) - (z_imag * z_imag) + (C.getX());
			znx_imag = (2 * z_real * z_imag) + (C.getY());
			z_real = znx_real;
			z_imag = znx_imag;
		}
		return true;
	}

}
