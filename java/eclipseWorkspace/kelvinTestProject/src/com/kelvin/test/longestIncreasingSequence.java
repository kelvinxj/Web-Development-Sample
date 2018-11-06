package com.kelvin.test;

public class longestIncreasingSequence {
	public static void main(String[] arg){
		int[] a = new int[]{5,3,4,8,6,1};
		System.out.println("The length of longest increasing sequence is:");
		System.out.println(lis(a));
	}
	
	public static int lis(int[] arr){
		int[] d = new int[arr.length];
		int len = 0;
		for(int i = 0; i<arr.length; i++){
			d[i] = 1;
			for(int j = 0; j<i; j++){
				if(arr[j]<=arr[i] && d[j] + 1 > d[i]){
					d[i] = d[j] + 1;
				}
			}
			if(d[i] > len)
				len = d[i];
		}
		return len;
	}
}