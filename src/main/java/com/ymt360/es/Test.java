package com.ymt360.es;

import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

public class Test {

	public static void test1(){
		JestClientUtil jcu = new JestClientUtil("log.ymt360.com",9200);
		System.out.println(jcu.getEsclient());
		String query = "{\n" +
	            "    \"query\": {\n" +
	            "        \"filtered\" : {\n" +
	            "        }\n" +
	            "    }\n" +
	            "}";

	Search search = new Search.Builder(query)
	                // multiple index or types can be added.
	                .addIndex("im-*").build();

	try {
		SearchResult result = jcu.getEsclient().execute(search);
		String s = result.getJsonString();
		System.out.println(s);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static void test2(){
		
	}
	public static void main(String[] args) {
		test1();
	}
}
