
public class LCS {

	private static int recursiveLCSlength(String x, String y){
		return recursiveLCSlength(x, y, 0, 0);
	}

	private static int recursiveLCSlength(String x, String y, int i, int j) {
		if(i==x.length() || j==y.length()){
			return 0;
		} else {
			int option1 =   recursiveLCSlength(x,y,i+1,j); // skip x[i]
			int option2 =   recursiveLCSlength(x,y,  i,j+1); // skip y[j]
			int option3 = 1+recursiveLCSlength(x,y,i+1,j+1); // keep them
			if(x.charAt(i) == y.charAt(j)){ // we can use option 3
				return max(option1, option2, option3);
			} else {
				return Math.max(option1, option2);
			}
		}
	}
	
	private static int LCSlength(String x, String y){
		int[][] opt = optimizingTable(x, y);
		return opt[0][0];
	}
	
	private static String LCSans(String x, String y){
		int[][] opt = optimizingTable(x, y);
		StringBuilder sb = new StringBuilder();
		int i=0, j=0;
		while(i < x.length() && j < y.length()){
			int option1 =   opt[i+1][j]; // skip x[i]
			int option2 =   opt[i][j+1]; // skip y[j]
			int option3 = 1+opt[i+1][j+1]; // keep them
			if(opt[i][j] == option1){
				i++;
			} else if(opt[i][j] == option2){
				j++;
			} else {
				assert(opt[i][j] == option3 && x.charAt(i)==y.charAt(j));
				sb.append(x.charAt(i));
				i++; j++;
			}
		}
		return sb.toString();
	}

	private static int[][] optimizingTable(String x, String y) {
		int m = x.length();
		int n = y.length();
		int[][] opt = new int[m+1][n+1]; // opt[i][j] equals
										// recursiveLCSlength(x, y, i, j)
		for(int j = 0; j <= n; j++){
			opt[m][j] = 0;
		}
		for(int i = 0; i <= m; i++){
			opt[i][n] = 0;
		}
		for(int i = m-1; i >= 0; i--){
			for(int j = n-1; j >= 0; j--){
				int option1 =   opt[i+1][j]; // skip x[i]
				int option2 =   opt[i][j+1]; // skip y[j]
				int option3 = 1+opt[i+1][j+1]; // keep them
				if(x.charAt(i) == y.charAt(j)){ // we can use option 3
					opt[i][j] =  max(option1, option2, option3);
				} else {
					opt[i][j] =  Math.max(option1, option2);
				}
			}
		}
		return opt;
	}

	private static int max(int option1, int option2, int option3) {
		return Math.max(option1, Math.max(option2, option3));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int len;
		double time;
		String x = "dfgwqtrrtwe";
		String y = "gretwesadas";
		StdOut.println(LCSans(x,y));
		Stopwatch sw = new Stopwatch();
		len = LCSlength(x,y);
		time = sw.elapsedTime();
		StdOut.printf("DP LCS length = %d, time = %f\n", len, time);
		double startTime= sw.elapsedTime();
		len = recursiveLCSlength(x,y);
		time = sw.elapsedTime();
		StdOut.printf("Recursive LCS length = %d, time = %f\n", len, time-startTime);
		
	}

}
