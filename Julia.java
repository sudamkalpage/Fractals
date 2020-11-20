import java.util.ArrayList;

public class Julia extends Mandelbrot { // If the required set is Julia

	// Default values of C
	static double c_real = -0.4;
	static double c_imag = 0.6;

	// Setting the necessary parameters from command line arguments when Julia set
	public static boolean setParameters(ArrayList<String> cmd_inputs) {
		if (cmd_inputs.size() == 1) {
		} else if (cmd_inputs.size() == 3) {
			c_real = Double.parseDouble(cmd_inputs.get(1));
			c_imag = Double.parseDouble(cmd_inputs.get(2));
		} else if (cmd_inputs.size() == 4) {
			c_real = Double.parseDouble(cmd_inputs.get(1));
			c_imag = Double.parseDouble(cmd_inputs.get(2));
			iterations = Integer.parseInt(cmd_inputs.get(3));
		} else {
			// Wrong number of command line input arguments
			return false;
		}
		return true;
	}

	public boolean juliaSet(Point Z) { // Function to check whether given Z is in the Julia set
		double z_real = Z.getX();
		double z_imag = Z.getY();
		double znx_real;
		double znx_imag;
		for (int k = 0; k < iterations; k++) {
			if (((z_real * z_real) + (z_imag * z_imag)) > 4) { // if ABS(Zn) > 2 not in the set --> ABS(Zn^2) > 4
				return false;
			}
			znx_real = (z_real * z_real) - (z_imag * z_imag) + (c_real);
			znx_imag = (2 * z_real * z_imag) + (c_imag);
			z_real = znx_real;
			z_imag = znx_imag;
		}
		return true;
	}

}
