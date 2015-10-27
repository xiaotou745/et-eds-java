package com.edaisong.toolscore.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
	public  static <T> String list2str(List<T> list, char c)
    {
		StringBuilder sb = new StringBuilder();
		if(list == null){
			return sb.toString();
		}
		for(T t:list){
			sb.append(t.toString()+c);
		}
		
		if(list.size()>0){
			return StringUtils.trimTrailingCharacter(sb.toString(), c);
		}

        return sb.toString();
    }
	
	public static List<Integer> str2intlist(String s, String regex){
		if(!StringUtils.isEmpty(s) || !StringUtils.hasText(s)){
			return new ArrayList<Integer>();
		}
		String[] split = s.split(regex);
		List<Integer> result = new ArrayList<Integer>();
		for(String item:split){
			result.add(Integer.valueOf(item));
		}
		return result;
	}
}
