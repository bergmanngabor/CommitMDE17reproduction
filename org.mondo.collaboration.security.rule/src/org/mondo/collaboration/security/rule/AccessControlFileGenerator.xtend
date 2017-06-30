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
	user superuser
	«FOR user : 1..numberOfUsers»
		user user_«user»
	«ENDFOR»
	'''
	
	def static generateGroupHeader(int numberOfUsers) '''
	group restrictedGroup {
		«FOR user : 1..numberOfUsers SEPARATOR ','»user_«user»«ENDFOR»
	}'''
	
	def static generatePolicy(int numberOfUsers) '''
	policy GeneratedWTPolicy_«numberOfUsers» first-applicable {
		«FOR user : 1..numberOfUsers SEPARATOR '''
		'''»
		«generateUserSpecificRules(user)»
		«ENDFOR»
		
		«generateDefaultRules»
	}'''
	
	def static generateUserSpecificRules(int userId) '''
	// Rules specific to user «userId»
	
		//Grant RW for user specific control units
		rule permitUserSpecificControlUnit_«userId» permit RW to user_«userId» {
			query "org.mondo.collaboration.security.query.objectControlWithType"
			bind type value "«userId»"
		}
		//Grant R for user specific composite hierarchy
		rule permitUserRelatedCompositeModules_«userId» permit R to user_«userId» {
			query "org.mondo.collaboration.security.query.objectCompositeWithType"
			bind type value "«userId»"
		}	
	
	'''
	
	def static generateDefaultRules() '''
	// IP protected modules
	rule denyProtectedSignalRead deny RW to restrictedGroup {
		query "org.mondo.collaboration.security.query.protectedModuleReadsSignal"
	}		
	rule denyProtectedVendor deny RW to restrictedGroup {
		query "org.mondo.collaboration.security.query.protectedModuleVendor"
	}		
	// Default rule
	rule denyAllModule deny RW to restrictedGroup {
		query "org.mondo.collaboration.security.query.objectModule"
	}
	
	rule allowEveryhing permit RW to superuser, restrictedGroup {
		query "org.mondo.collaboration.security.query.objectAll"
	}'''
	
}