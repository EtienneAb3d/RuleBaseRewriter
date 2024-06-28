package com.cubaix.rbr;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Vector;

public class RuleBaseRewriter {
	static final boolean _DEBUG = true;
	
	String configPath = null;
	Vector<Rule> rules = new Vector<Rule>();
	
	public RuleBaseRewriter(String aConfigPath) throws Exception {
		configPath = aConfigPath;
		loadRules();
	}

	void loadRules() throws Exception {
		BufferedReader aBR = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(configPath)
				,"UTF8"));
		String aLine = null;
		while((aLine = aBR.readLine()) != null) {
			aLine = aLine.trim();
			if(aLine.isEmpty() || aLine.startsWith("#")) {
				//Ignore empty lines or comments
				continue;
			}
			String[] aToks = aLine.split("\t");
			if(aToks.length < 2) {
				throw new Exception("Bad config line: "+aLine);
			}
			rules.add(new Rule(aLine,aToks[0].replaceAll("(^[|]|[|]$)", "")
					,aToks[1].replaceAll("(^[|]|[|]$)", "")
					));
		}
		aBR.close();
	}
	
	public String process(String aText) throws Exception {
		String aNewText = aText;
		String aProcessed = aText;
		HashSet<String> aHistory = new HashSet<String>();
		while(true) {
			aNewText = aProcessed;
			for(Rule aR : rules) {
				if(_DEBUG) {
					System.out.println("RULE: "+aR.configLine);
				}
				aProcessed = aR.process(aProcessed); 
				if(_DEBUG) {
					System.out.println("TEXT: "+aProcessed + "\n");
				}
			}
			if(aNewText.equals(aProcessed)) {
				//Ok, no more change
				return aProcessed;
			}
			if(aHistory.contains(aProcessed)) {
				throw new Exception("Infinite loop detected on : \""+aProcessed+ "\"");
			}
			if(aProcessed.length() > aText.length() * 100) {
				throw new Exception("Text very long, possible infinite loop detected on : \""+aProcessed+ "\"");
			}
			aHistory.add(aProcessed);
		}
	}
	
	public static void main(String[] args) {
		try {
			String aConfigPath = "numbers-fr-FR.tsv";
			String aText =
					"[0, 1, 5, 10, 11, 15, 20, 21, 30, 35, 50, 51, 68, 70, 75, 99, 100, 101, 105, 111, 123, 168, 171, 175, 199, 200, 201, 555, 999, 1000, 1001, 1111, 1199, 1234, 1999, 2000, 2001, 2020, 2021, 2345, 9999, 10000, 11111, 12345, 123456, 654321, 999999]";
			if(args.length != 2) {
				System.out.println("Usage: RuleBaseRewriter config.tsv text");
				System.out.println("Example: numbers-fr-FR.tsv \""
						+ aText
						+ "\"");
			}
			else {
				aConfigPath = args[0];
				aText = args[1];
			}
			String aOutput = new RuleBaseRewriter(aConfigPath)
					.process(aText);
			System.out.println("Output: "+aOutput);
		} catch (Exception e) {
			System.err.println("Unkown error: ");
			e.printStackTrace(System.err);
			System.exit(-1);
		}
		System.exit(0);
	}

}
