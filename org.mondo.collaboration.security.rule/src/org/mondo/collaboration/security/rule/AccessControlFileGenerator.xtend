package org.mondo.collaboration.security.rule

class AccessControlFileGenerator {
	def static generate(int numberOfUsers) {
		'''
		«generateUserHeader(numberOfUsers)»
		«generateGroupHeader(numberOfUsers)»
		«generatePolicy(numberOfUsers)»
		'''
	}
	
	def static generateUserHeader(int numberOfUsers) '''
	«FOR user : 1..numberOfUsers»
		user user_«user»
	«ENDFOR»'''
	
	def static generateGroupHeader(int numberOfUsers) '''
	group defaultGroup {
		«FOR user : 1..numberOfUsers SEPARATOR ','»user_«user»«ENDFOR»
	}'''
	
	def static generatePolicy(int numberOfUsers) '''
	policy GeneratedWTPolicy_«numberOfUsers» first-applicable {
		«FOR user : 1..numberOfUsers SEPARATOR ','»
		«generateUserSpecificRules(user)»
		«ENDFOR»
		
		«generateDefaultRule»
	}'''
	
	def static generateUserSpecificRules(int userId) '''
	//Grant RW for user specific control units
	rule permitUserSpecificControlUnit_«userId» permit RW to user_«userId» {
		query "org.mondo.collaboration.security.query.objectControlWithType"
		bind type value "«userId»"
	}
	
	//Grant RW for user specific control units
	rule permitUserRelatedCompositeModules_«userId» permit R to user_«userId» {
		query "org.mondo.collaboration.security.query.objectControlWithType"
		bind type value "«userId»"
	}	
	'''
	
	def static generateDefaultRule() '''
	//Default rule
	rule denyAllModule deny RW to defaultGroup {
		query "org.mondo.collaboration.security.query.objectModule"
	}'''
	
}