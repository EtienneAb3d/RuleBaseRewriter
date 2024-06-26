package com.cubaix.rbr;

public class Rule {
	String configLine = null;
	String matcher = null;
	String rewrite = null;
	
	public Rule(String aConfigLine,String aMatcher,String aRewrite) {
		configLine = aConfigLine;
		matcher = aMatcher;
		rewrite = aRewrite;
	}
}
