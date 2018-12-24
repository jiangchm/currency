package com.alog;

import java.util.HashMap;
import java.util.Map;

/*
 * ����һ���������� nums ��һ��Ŀ��ֵ target�������ڸ��������ҳ���ΪĿ��ֵ���� ���� ���������������ǵ������±ꡣ
	����Լ���ÿ������ֻ���Ӧһ���𰸡����ǣ��㲻���ظ��������������ͬ����Ԫ�ء�
 */
public class FindTwoNumberSum {

	public int[] find(int[] nums, int target) {        
		int[] result = new int[2] ;
		Map<Integer,Integer> restore = new HashMap<>() ;
		for(int i = 0 ;i<nums.length;i++){
			restore.put(i, nums[i]);
		}
		int other,otherIndex ;
		for(Map.Entry<Integer, Integer> entry :restore.entrySet()){
			int value = entry.getValue() ;
			int key = entry.getKey() ;
			other = target - value ;
			otherIndex = getIndex(restore,other,key) ;
			if(otherIndex>-1){
				result[0] = key ;
                result[1] = otherIndex ;
                break;
			}
		}
		return result;	
            
    }
    
    private int getIndex(Map<Integer,Integer> restore,int targetVal,int currentIndex){
		if(restore.containsValue(targetVal)){
			for(Map.Entry<Integer, Integer> entry :restore.entrySet()){
				int value = entry.getValue() ;
				int key = entry.getKey() ;
				if(value == targetVal && key!=currentIndex){
					return key ;
				}
			}
		}
		return -1 ;
	}
	
	public static void main(String[] args) {
		FindTwoNumberSum obj = new FindTwoNumberSum();
		int []nums = {2, 7, 11, 15} ;
		int target = 9 ;
		int[] result = obj.find(nums,target) ;
		for(int i : result){
			System.out.println(i);
		}
		System.out.println(result);
	}
}
