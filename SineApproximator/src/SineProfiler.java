/**
 * A program to profile sine approximation algorithms that use
 * the Taylor series expansion of the sine function: 
 * sine(x) = x - x^3/3! + x^5/5! - x^7/7! + x^9/9! .....   
 * @author William Duncan, Hayes Chiasson
 * @see SineUtil
 * <pre>
 * Date: 8/25/23
 * Course: csc 3102
 * Project # 0
 * Instructor: Dr. Duncan
 * </pre>
 */

import java.util.Scanner;

public class SineProfiler
{
	public static void main(String[] args) 
	{
        //Code sections/ structures/ comments will be divided by a spaced line
		//Prompt and keyboard/ input detection
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the angle in radians -> "); 
		double angleInput = in.nextDouble();
		
		//Call and use naive/fast-sine methods, and display proper info w/ formatting
		System.out.printf("naive-sine(%.4f) = %.4f\n", angleInput, SineUtil.naiveSine(angleInput, 100));
		System.out.printf("fast-sine(%.4f) = %.4f\n\n", angleInput, SineUtil.fastSine(angleInput, 100));
		
		//Display header/ non-looped segments of table
		System.out.printf(" n\t Naive Time(us)   Fast Time (us)\n");
		System.out.println("==========================================");
		
		//loop through table/ test cases/ format and calculate to microseconds
		for (int i = 1000; i <= 15000; i+= 1000)
		{
			//implementing programming tips tp find naiveSine time, / 1000 to get microseconds
			long naiveStart = System.nanoTime() / 1000;
			SineUtil.naiveSine(angleInput, i);
			long naiveElapsed = (System.nanoTime() / 1000) - naiveStart;
			
			//repeating process for fastSine method
			long fastStart = System.nanoTime() / 1000;
			SineUtil.naiveSine(angleInput, i);
			long fastElapsed = (System.nanoTime() / 1000) - fastStart;
			
			System.out.printf(" %d\t" + naiveElapsed + "\t" + fastElapsed + "\n", i);
		}
		System.out.println("-----------------------------------------");

		in.close();
	}
}
