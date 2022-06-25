import java.util.*;
public class SquidGame {
	public static int naiveCost = Integer.MAX_VALUE; 
	public static String naiveAns = "";
	
	public static String fields = "";
	static Hashtable<Integer, String> costToAnswer = new Hashtable<Integer, String>();
	public static String efficientAns = "";
	
	public static String naive(int k, int[] l) {
		
		naiveHelper(l, l.length, k, 0, 0, 0,"");
		String s = naiveAns;
		s = s.replaceAll(" ", ",");
		s = s.replaceAll(",;",";");
		s = s.substring(0,(s.length())-1);
		naiveCost = Integer.MAX_VALUE; 
		naiveAns = "";
		return s;
		
	}
	
	public static void naiveHelper(int l[], int len, int k,int index, int sum, int maxSum,String currAns)
	{
		// K=1 is the base Case
		if (k == 1) {
			
			maxSum = Math.max(maxSum, sum);
			sum = 0;
			
//			currAns+=";";
			for (int i = index; i < len; i++) {
				sum += l[i]; 
				currAns+=l[i]+" ";
			}

			maxSum = Math.max(maxSum, sum);
			// the answer is stored in ans
			
			
			if(naiveCost != maxSum && Math.min(naiveCost, maxSum)==maxSum){
				naiveCost = maxSum;
				naiveAns = naiveCost+";"+currAns;
			}


			return;
		}
		sum = 0;

		for (int i = index; i < len; i++) {
			sum += l[i]; //
			currAns+= l[i]+" ";

			
			System.out.println(currAns);
			maxSum = Math.max(maxSum, sum);
			// calling function again
			naiveHelper(l, len, k - 1, i + 1, sum, maxSum,currAns+";");
	
		}
	}

	public static String efficient(int k, int[] l) {
		String result = "";
		String fields = "";
		int sum = 0;
		if (k == 1) {
			for (int i = 0; i < l.length; i++) {
				sum += l[i];
				if (i == l.length - 1) {
					fields += l[i] + "";
				} else
					fields += l[i] + ",";
			}
			result = sum + ";" + fields;
		} else if (k == l.length) {
			int max = 0;
			for (int i = 0; i < l.length; i++) {
				if (l[i] > max)
					max = l[i];
				if (i == l.length - 1)
					result += l[i] + "";
				else
					result += l[i] + ";";
			}
			result = max + ";" + result;
		} else {
			result = efficientHelper(l, l.length, k);
			result = result.substring(0, result.length() - 1);
			efficientAns = "";
			costToAnswer.clear();
		}
		return result;
	}
	
	static String efficientHelper(int array[], int n, int k) {
		int start = 1;
		for (int i = 0; i < n; ++i) {
			if (array[i] > start)
				start = array[i]; 
		}
		int end = 0;

		for (int i = 0; i < n; i++) {
			end += array[i]; 
		}

		
		int answer = 0;
		while (start <= end) {
			fields = "";
			int mid = (start + end) / 2;

	
			if (check(mid, array, n, k)) {
				answer = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		efficientAns = costToAnswer.get(answer);
		return answer + ";" + efficientAns;
	}
	
	static boolean check(int mid, int array[], int n, int K) {

		int count = 0;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			if (array[i] > mid) {
				return false;
			}
			sum += array[i];
			if (sum > mid) {
				count++;
				sum = array[i];
				
				fields = fields.substring(0, fields.length() - 1);
				fields += ";" + array[i] + ",";
			} else {
				fields += array[i] + ",";
			}
			costToAnswer.put(mid, fields);
		}
		count++;

		// Check condition
		if (count <= K)
			return true;
		return false;
	}

//	https://www.geeksforgeeks.org/split-the-given-array-into-k-sub-arrays-such-that-maximum-sum-of-all-sub-arrays-is-minimum/

	
	public static void main(String[] args) {
	
	}



	
	
	

}
