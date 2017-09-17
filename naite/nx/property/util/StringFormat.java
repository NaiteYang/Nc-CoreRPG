package nx.property.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class StringFormat{

	public static List<String> stringListReplace(List<String> list, String regex, String replacement){
		List<String> result = new ArrayList<>();
		for(String str : list){
			result.add(str.replaceAll(regex, replacement));
		}
		return result;
	}

	public static List<String> stringListReplace(List<String> list, String regex, int replacement){
		return stringListReplace(list, regex, String.valueOf(replacement));
	}

	public static List<String> stringListReplace(List<String> list, String regex, double replacement){
		return stringListReplace(list, regex, String.valueOf(replacement));
	}

	public static NumberFormat percentFormat(int fractionDigit){
		NumberFormat percent = NumberFormat.getPercentInstance();
		percent.setMaximumFractionDigits(fractionDigit);
		return percent;
	}

}
