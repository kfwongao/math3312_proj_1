/**
 * MATH3312 Project 1 Java version
 * @author WONG, Kin Fat Huanqiang
 *
 */
public class Project1 {
	public static double a = -2;
	public static double b = 2;
	public static double TOL = 1e-10;
	public static int MAX_ITERATIONS_ALLOWED = 100;
	
	/**
	 * 
	 * @param x input
	 * @return f(x) = -x^3 - cos(x) at x
	 */
	public static double f(double x) {
		return (-x*x*x - Math.cos(x));
	}
	
	/**
	 * 
	 * @param x input
	 * @return first derivative of f(x) at x
	 */
	public static double g(double x) {
		return (-3*x*x + Math.sin(x));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Problem: Use computer programs to find the root of f (x) = −x^3 − cos(x)\n"
				+ "==========================================================================");
		System.out.println("1. Use Bisection Method to find a root of f (x) accurate to within 1e−10 on [−2, 2]\n"
				+ "  , and show how many iterations are used.\n");
		System.out.println("2. Use Bisection Method to find p4. \n"
				+ "  Consider p4 as an initial guess then use Newton’s Method to find a root of f(x) \n"
				+ "  such that |pn − pn−1| ≤ 1e−10, and show how many iterations are used in total.\n");
		
	//==========================Problem 1 begin==========================
		System.out.println("Problem 1 inputs:  a = -2, b = 2, TOL = 1e-10, MAX_ITERATIONS_ALLOWED = 100");
		System.out.println("\nBisection Method: Output in each iteration");
		System.out.println("iteration# |    a_n     |    b_n     |    p_n     |    f(a_n)    |    f(mid)    |     Error    |");
		//Bisection Method begin
		//initialization
		int i = 1;
		double FA = f(a);
		double mid = 0;
		double FM = 0;
		double root = 0;
		double p4 = 0;
		String s ="";
		//Bisection Method algorithm
		while(i <= MAX_ITERATIONS_ALLOWED) {
			FA = f(a);
			mid = (a+b)/2;
			FM = f(mid);
			//print the output in each step
			s = String.format("  iter # %2d|%9f...|%9f...|%9f...|%14.4e|%14.4e|%14.4e|", i, a, b, mid, FA, FM, (b-a)/2);
			System.out.println(s);
			if(FM == 0 || ((b-a)/2 < TOL)) {
				root = mid;
				break;
			}
			
			if(i == 4)
				p4 = mid; //store p4 for Problem 2
			
			i++;
			
			if (FA*FM > 0)
				a = mid;
			else
				b = mid;
		}
		int iter_problem_1 = i;
		System.out.println("\nFinal Output of Problem 1:\n" 
				+ iter_problem_1 + " iterations are used in this method.\n"
				+ "root = " + root);
	
		
	//==========================Problem 2 begin==========================
		System.out.println("\n\nProblem 2 input:  p4 = " + p4);
		//f'(p_n) = g(p_n)
		double p0 = p4;
		if(g(p0) == 0)
			System.err.println("Error f'(p0) is already 0");
		double p = p0 - f(p0)/g(p0);

		System.out.println("iteration #|   p_(n+1)  |    p_n     |    f(p_n)    |    f'(p_n)   |    Error     |");
		System.out.printf("  iter # %2d|%9f...|%9f...|%14.4e|%14.4e|%14.4e|%n", 0, p, p0, f(p0), g(p0), Math.abs(p - p0));
		//Newton's method begin
		i = 1;  //reset counter
		while((g(p) != 0) && Math.abs(p - p0) > TOL && i < MAX_ITERATIONS_ALLOWED) {
			p0 = p;
			if(g(p0) == 0)
				System.err.println("Error f'(p) is already 0");
			
			p = p0 - f(p0)/g(p0);
			System.out.printf("  iter # %2d|%9f...|%9f...|%14.4e|%14.4e|%14.4e|%n", i, p, p0, f(p0), g(p0), Math.abs(p - p0));
			i++;
		}
		
		int iter_problem_2 = i-1;
		System.out.println("\nFinal Output of Problem 2:\n" +"root = " + p + "\n (" + iter_problem_2 
						+ ") iterations used in Newton's method + (4) iterations used to find p4");
		System.out.println("Total iterations used in Problem 2 is " + (iter_problem_2 + 4));
		i = 1;  //reset counter
		
		//check which method in the problem is faster
		if(iter_problem_2 - iter_problem_1 < 0)
			System.out.println("\nNewton's method converges faster");
		else
			System.out.println("\nNewton's method converges no faster than Bisection method in this time");
	}

}
