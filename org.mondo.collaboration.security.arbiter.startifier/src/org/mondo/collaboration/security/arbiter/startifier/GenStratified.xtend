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

«FOR prio: priorities»
pattern judgement_at_«prio»(user: java String,
	asset: EObject, op: SecurityOperation, 
	bound: java Enumerator, dir: BoundDirection) 
{
	find explicitJudgement(user, asset, op, bound, dir, «prio»);
} or {
	// relaxed judgement
	find domination_of_«prio»(user, asset, op, _dominatedBound, dir, bound);
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

«FOR prio: priorities»
pattern dominantJudgement_at_«prio»(user: java String,
	asset: EObject, op: SecurityOperation, 
	bound: java Enumerator, dir: BoundDirection) 
{
	find judgement_at_«prio»(user, asset, op, bound, dir);
	neg find domination_of_«prio»(user, asset, op, bound, dir, _prevailingBound);
} 
«ENDFOR»

«FOR prio: priorities»«IF prio != priorities.maxBy[it]»
pattern domination_of_«prio»(user: java String,
	asset: EObject, op: SecurityOperation, 
	dominatedBound: java Enumerator, dominatedDir: BoundDirection,
	prevailingBound: java Enumerator) 
{
	«FOR prevailingPrio: priorities»«IF prevailingPrio > prio»
	find domination_of_«prio»_by_«prevailingPrio»(user, asset, op, dominatedBound, dominatedDir, prevailingBound);
	«ENDIF»«ENDFOR»
} 
«ENDIF»«ENDFOR»

«FOR prio: priorities»«FOR prevailingPrio: priorities»«IF prevailingPrio > prio»
pattern domination_of_«prio»_by_«prevailingPrio»(user: java String,
	asset: EObject, op: SecurityOperation, 
	dominatedBound: java Enumerator, dominatedDir: BoundDirection,
	prevailingBound: java Enumerator) 
{
	// NOTE: subsumption is included as well
	find judgement_at_«prio»(user, asset, op, dominatedBound, dominatedDir); 
	find dominantJudgement_at_«prevailingPrio»(user, asset, op, prevailingBound, prevailingDir);
	check (
		// TODO equal priority, but dominating direction
		prevailingDir == BoundDirection::AT_MOST && prevailingBound.value < dominatedBound.value ||
		prevailingDir == BoundDirection::AT_LEAST && prevailingBound.value > dominatedBound.value
		// && (dominatedDir != prevailingDir) - omitting this ensures that subsumption is included as well
	);	
}  
«ENDIF»«ENDFOR»«ENDFOR»

«FOR prio: priorities»
pattern strongConsequence_at_«prio»(user: java String,
	depAsset: EObject, depOp: SecurityOperation, depBound: java Enumerator, 
	dir: BoundDirection,
	domAsset: EObject, domOp: SecurityOperation, domBound: java Enumerator) 
{
	// type II, read vs write, AT_LEAST
	find dominantJudgement_at_«prio»(user, domAsset, domOp, domBound, dir);
	depAsset == domAsset; dir == BoundDirection::AT_LEAST;
	domOp == SecurityOperation::WRITE; depOp == SecurityOperation::READ;
	domBound == WriteLevels::ALLOW_WRITE; depBound == ReadLevels::ALLOW_READ; 
} or {
	// type II, read vs write, AT_MOST
	find dominantJudgement_at_«prio»(user, domAsset, domOp, domBound, dir);
	depAsset == domAsset; dir == BoundDirection::AT_MOST;
	domOp == SecurityOperation::READ; depOp == SecurityOperation::WRITE;
	domBound != ReadLevels::ALLOW_READ; depBound == WriteLevels::BLIND_DELETABLE_WRITE; 
} or {
	// type III, read vs containment, AT_LEAST
	find dominantJudgement_at_«prio»(user, domAsset, domOp, domBound, dir);
	find contains(depAsset, domAsset);
	dir == BoundDirection::AT_LEAST;
	domOp == SecurityOperation::READ; depOp == SecurityOperation::READ;
	domBound == ReadLevels::OBFUSCATE_READ; depBound == ReadLevels::OBFUSCATE_READ; 
} or {
	// type III, read vs containment, AT_MOST
	find dominantJudgement_at_«prio»(user, domAsset, domOp, domBound, dir);
	find contains(domAsset, depAsset);
	dir == BoundDirection::AT_MOST;
	domOp == SecurityOperation::READ; depOp == SecurityOperation::READ;
	domBound == ReadLevels::DENY_READ; depBound == ReadLevels::DENY_READ; 
}
«ENDFOR»

//pattern weakConsequence(user: java String,
//	depAsset: EObject, depOp: SecurityOperation, depBound: java Enumerator, 
//	dir: BoundDirection, depPrio: java Integer,
//	domAsset: EObject, domOp: SecurityOperation, domBound: java Enumerator,
//	domPrio: java Integer) 
//{
//	depPrio == 1;
//	// TODO implement; not needed for example
//}
'''
}