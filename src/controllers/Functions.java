package controllers;

public class Functions 
{
	public static String getSearchExp(String searchText)
	{
		String[] searchWords = searchText.split(";");
		String searchWordsExp = "[[:<:]]" + searchWords[0] + "[[:>:]]";					
		for(int i = 1; i < searchWords.length; i++)
			searchWordsExp += "|[[:<:]]" + searchWords[i] + "[[:>:]]";
		return searchWordsExp;
	}
}
