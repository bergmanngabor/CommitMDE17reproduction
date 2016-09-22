package org.mondo.collaboration.security.rule;

import java.io.PrintWriter;

public class AccessControlRuleGenerator {

	public static final int[] MODEL_SIZES = {13, 25, 38, 50, 63, 75, 88, 100};
	public static final int[] USER_SIZES = {2,5,10,20,30,40,50,60,70,80,90,100};

	public static void main(String[] args) throws Exception {
		AccessControlRuleGenerator generator = new AccessControlRuleGenerator();
		generator.generate();
	}


	private void generate() throws Exception {
		for (int size : USER_SIZES) {
			CharSequence model = AccessControlFileGenerator.generate(size);
			save(
					String.format("../org.mondo.collaboration.security.model/instances/rules-%04d.macl", size), 
					model);
		}
	}

	public static void save(String path, CharSequence sequence) throws Exception {
		try(  PrintWriter out = new PrintWriter( path )  ){
		    out.println( sequence.toString() );
		}
	}
}
