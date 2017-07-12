package org.mondo.collaboration.security.arbiter.startifier

public class GenStratified {
	def static void main(String[] args) {
		val priorities = #[0,2,3]
		println(new GenStratified().gen(priorities))
	}
	
	def CharSequence gen(Iterable<Integer> priorities) '''
package org.mondo.collaboration.security.arbiter

import "http://www.eclipse.org/emf/2002/Ecore"
import "http://mondo.org/collaboration/security/arbiter/vocabulary"

import java org.eclipse.emf.common.util.Enumerator
import java org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.BoundDirection


pattern effectiveJudgement(user: java String,
	asset: EObject, op: SecurityOperation, 
	bound, dir: BoundDirection) 
{
	«FOR prio: priorities SEPARATOR "\n} or {" »
	find effectiveJudgement_at_«prio»(user, asset, op, bound, dir);
	«ENDFOR»
} 


«FOR prio: priorities»
pattern judgement_at_«prio»(user: java String,
	asset: EObject, op: SecurityOperation, 
	bound, dir: BoundDirection) 
{
	find explicitJudgement(user, asset, op, bound, dir, «prio»);
«IF prio != priorities.maxBy[it]»
} or {
	// relaxed judgement
	find relaxedJudgement_at_«prio»(user, asset, op, bound, dir);
«ENDIF»
} or {
	// strong consequence judgement of a (dominant) judgement
	find strongConsequence_at_«prio»(user, asset, op, bound, dir, _domAsset, _domOp, _domBound);
	«FOR domPrio: priorities»«IF domPrio >= prio»
//} or {
//	// weak consequence judgement of a (dominant) judgement
//	find weakConsequence_at_«prio»_of_«domPrio»(user, asset, op, bound, dir, _domAsset, _domOp, _domBound);
	«ENDIF»«ENDFOR»
}
«ENDFOR»

«FOR prio: priorities»«IF prio != priorities.maxBy[it]»
pattern relaxedJudgement_at_«prio»(user: java String,
	asset: EObject, op: SecurityOperation, 
	bound, dir: BoundDirection)
{
	find judgement_at_«prio»(user, asset, op, dominatedBound, dir);
	find domination_of_«prio»(user, asset, op, _dominatedBound, bound);
}
«ENDIF»«ENDFOR»

«FOR prio: priorities»
pattern effectiveJudgement_at_«prio»(user: java String,
	asset: EObject, op: SecurityOperation, 
	bound, dir: BoundDirection) 
{
	find judgement_at_«prio»(user, asset, op, bound, dir);
	«IF prio != priorities.maxBy[it]»
	neg find domination_of_«prio»(user, asset, op, bound, _prevailingBound);
	«ENDIF»
} 
«ENDFOR»

«FOR prio: priorities»«IF prio != priorities.maxBy[it]»
pattern domination_of_«prio»(user: java String,
	asset: EObject, op: SecurityOperation, 
	dominatedBound, prevailingBound) 
{
	«FOR prevailingPrio: priorities.filter[it > prio] SEPARATOR "\n} or {" »
	find domination_by_«prevailingPrio»(user, asset, op, dominatedBound, prevailingBound);
	«ENDFOR»
} 
«ENDIF»«ENDFOR»

«FOR prevailingPrio: priorities»«IF prevailingPrio != priorities.minBy[it]»
pattern domination_by_«prevailingPrio»(user: java String,
	asset: EObject, op: SecurityOperation, 
	dominatedBound, prevailingBound) 
{
	// NOTE: subsumption is included as well
	find effectiveJudgement_at_«prevailingPrio»(user, asset, op, prevailingBound, prevailingDir);
	find permissionOutOfBound(prevailingDir, prevailingBound, dominatedBound);
}  
«ENDIF»«ENDFOR»

pattern readPermissionLevel(level) = {
	level == ReadLevels::ALLOW_READ;
} or {
	level == ReadLevels::OBFUSCATE_READ;
} or {
	level == ReadLevels::DENY_READ;
}

pattern writePermissionLevel(level) = {
	level == WriteLevels::ALLOW_WRITE;
} or {
	level == WriteLevels::BLIND_DELETABLE_WRITE;
} or {
	level == WriteLevels::DENY_WRITE;
}

pattern permissionOutOfBound(prevailingDir: BoundDirection,
	prevailingBound, dominatedBound
) = {
	find readPermissionLevel(prevailingBound);
	find readPermissionLevel(dominatedBound);
	prevailingDir == eval(
		if (prevailingBound.value < dominatedBound.value) 
			BoundDirection::AT_MOST
		else if (prevailingBound.value > dominatedBound.value)
			BoundDirection::AT_LEAST
		else null	
	);
}


«FOR prio: priorities»
pattern strongConsequence_at_«prio»(user: java String,
	depAsset: EObject, depOp: SecurityOperation, depBound, 
	dir: BoundDirection,
	domAsset: EObject, domOp: SecurityOperation, domBound: java Enumerator) 
{
	// type II, read vs write, AT_LEAST
	find effectiveJudgement_at_«prio»(user, domAsset, domOp, domBound, dir);
	depAsset == domAsset; dir == BoundDirection::AT_LEAST;
	domOp == SecurityOperation::WRITE; depOp == SecurityOperation::READ;
	domBound == WriteLevels::ALLOW_WRITE; depBound == ReadLevels::ALLOW_READ; 
} or {
	// type II, read vs write, AT_MOST
	find effectiveJudgement_at_«prio»(user, domAsset, domOp, domBound, dir);
	depAsset == domAsset; dir == BoundDirection::AT_MOST;
	domOp == SecurityOperation::READ; depOp == SecurityOperation::WRITE;
	domBound != ReadLevels::ALLOW_READ; depBound == WriteLevels::BLIND_DELETABLE_WRITE; 
} or {
	// type III, read vs containment, AT_LEAST
	find effectiveJudgement_at_«prio»(user, domAsset, domOp, domBound, dir);
	find contains(depAsset, domAsset);
	dir == BoundDirection::AT_LEAST;
	domOp == SecurityOperation::READ; depOp == SecurityOperation::READ;
	domBound == ReadLevels::OBFUSCATE_READ; depBound == ReadLevels::OBFUSCATE_READ; 
} or {
	// type III, read vs containment, AT_MOST
	find effectiveJudgement_at_«prio»(user, domAsset, domOp, domBound, dir);
	find contains(domAsset, depAsset);
	dir == BoundDirection::AT_MOST;
	domOp == SecurityOperation::READ; depOp == SecurityOperation::READ;
	domBound == ReadLevels::DENY_READ; depBound == ReadLevels::DENY_READ; 
}
«ENDFOR»

//pattern weakConsequence(user: java String,
//	depAsset: EObject, depOp: SecurityOperation, depBound, 
//	dir: BoundDirection, depPrio: java Integer,
//	domAsset: EObject, domOp: SecurityOperation, domBound,
//	domPrio: java Integer) 
//{
//	depPrio == 1;
//	// TODO implement; not needed for example
//}
'''
}