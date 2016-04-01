package org.mondo.collaboration.security.rule;

import java.io.PrintWriter;

public class AccessControlRuleGenerator {

	public static final int[] USER_SIZES = {1,2,4,8,16,32,64,128,256};
	
	
	public static void main(String[] args) throws Exception {
		AccessControlRuleGenerator generator = new AccessControlRuleGenerator();
		generator.generate();
	}


	private void generate() throws Exception {
		for (int size : USER_SIZES) {
			CharSequence model = AccessControlFileGenerator.generate(size);
			save("C:\\Eclipse\\Articles\\workspace\\org.mondo.collaboration.security.model\\instances\\rules-"+size+".macl", model);
		}
	}

	public static void save(String path, CharSequence sequence) throws Exception {
		try(  PrintWriter out = new PrintWriter( path )  ){
		    out.println( sequence.toString() );
		}
	}
}
