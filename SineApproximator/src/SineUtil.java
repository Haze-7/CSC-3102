/**
 * Provides implementations of Sine Approximation Algorithms and auxiliary
 * methods
 * 
 * @author William Duncan, Hayes Chiasson
 * 
 *         <pre>
 * Date: 8/25/23
 * Course: csc 3102
 * Project # 0
 * Instructor: Dr. Duncan
 *         </pre>
 */

public class SineUtil {
	/**
	 * Computes the factorial of the specified number
	 * 
	 * @param n the number whose factorial is to be determined
	 * @return n!
	 * @throw IllegalArgumentException when n < 0
	 */
	private static double factorial(int n) {
		if (n < 0) 
		{
			throw new IllegalArgumentException("Input must be an integer greater than 0");
		}
		else
		{
		double num = 1;
		
		for (int i = 2; i <= n; i++) 
		{
			num = num * i;
		}
		return num;
		}
	}

	/**
	 * Computes the specified power
	 * 
	 * @param x the base of the power
	 * @param n the exponent of the power
	 * @return x^n
	 * @throw IllegalArgumentException when x = 0 and n <= 0
	 */
	private static double pow(double x, int n) {
		double result = 1;
		if (x == 0 && n <= 0)
		{
			throw new IllegalArgumentException("Base cannot equal 0 and exponent cannot be less than or equal to 0");
		}
			for (int i = 0; i < n; i++)
			{
				result = result * x;
			}
			if (n < 0)
			{
				return 1 / result;
			}
			else
			{
		return result;
			}
	}

	/**
	 * Computes the sine of an angle using the Taylor Series approximation of the
	 * sine function and naive exponentiation
	 * 
	 * @param x angle in radians
	 * @param n number of terms
	 * @return sine(x) = x - x^3/3! + x^5/5! - x^7/7! .....
	 * @throw IllegalArgumentException when n <= 0
	 */
	public static double naiveSine(double x, int n) {
		
		if (n <= 0) 
		{
			throw new IllegalArgumentException("indeterminate");
		} 
		else 
		{
			double approx = 0;
			
			for (int i = 1; i <= n; i++) 
			{
				if (i % 2 == 0) 
				{
					approx = approx - (pow(x, 2 * i - 1) / factorial(2 * i - 1));
				} 
				else 
				{
					approx = approx + (pow(x, 2 * i - 1) / factorial(2 * i - 1));
				}
			}
			return approx;
		}
	}

	/**
	 * Computes the sine of an angle using the Taylor Series approximation of the
	 * sine function and fast exponentiation
	 * 
	 * @param x angle in radians
	 * @param n number of terms
	 * @return sine(x) = x - x^3/3! + x^5/5! - x^7/7! .....
	 * @throw IllegalArgumentException when n <= 0
	 */
	public static double fastSine(double x, int n) {
		// Implement this method
		if (n <= 0) 
		{
			throw new IllegalArgumentException("indeterminate");
		} 
		else 
		{
			double approx = x;
			double denom = 3;
			double factor = -1;
			double base = x;
			
			for (int i = 2; i <= n; i++) 
			{
				base = base * (x / denom) * (x / (denom - 1));
				denom = denom + 2;
				approx = approx + (factor * base);
				factor = factor * -1;
			}
			return approx;
		}

	}
}