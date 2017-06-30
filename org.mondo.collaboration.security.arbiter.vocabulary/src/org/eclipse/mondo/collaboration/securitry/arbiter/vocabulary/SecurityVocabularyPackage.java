/**
 */
package org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityVocabularyFactory
 * @model kind="package"
 * @generated
 */
public interface SecurityVocabularyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "vocabulary";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://mondo.org/collaboration/security/arbiter/vocabulary";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "secVocab";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SecurityVocabularyPackage eINSTANCE = org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.impl.SecurityVocabularyPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.ReadLevels <em>Read Levels</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.ReadLevels
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.impl.SecurityVocabularyPackageImpl#getReadLevels()
	 * @generated
	 */
	int READ_LEVELS = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.WriteLevels <em>Write Levels</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.WriteLevels
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.impl.SecurityVocabularyPackageImpl#getWriteLevels()
	 * @generated
	 */
	int WRITE_LEVELS = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.BoundDirection <em>Bound Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.BoundDirection
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.impl.SecurityVocabularyPackageImpl#getBoundDirection()
	 * @generated
	 */
	int BOUND_DIRECTION = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityOperation <em>Security Operation</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityOperation
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.impl.SecurityVocabularyPackageImpl#getSecurityOperation()
	 * @generated
	 */
	int SECURITY_OPERATION = 3;


	/**
	 * Returns the meta object for enum '{@link org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.ReadLevels <em>Read Levels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Read Levels</em>'.
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.ReadLevels
	 * @generated
	 */
	EEnum getReadLevels();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.WriteLevels <em>Write Levels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Write Levels</em>'.
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.WriteLevels
	 * @generated
	 */
	EEnum getWriteLevels();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.BoundDirection <em>Bound Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Bound Direction</em>'.
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.BoundDirection
	 * @generated
	 */
	EEnum getBoundDirection();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityOperation <em>Security Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Security Operation</em>'.
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityOperation
	 * @generated
	 */
	EEnum getSecurityOperation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SecurityVocabularyFactory getSecurityVocabularyFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.ReadLevels <em>Read Levels</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.ReadLevels
		 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.impl.SecurityVocabularyPackageImpl#getReadLevels()
		 * @generated
		 */
		EEnum READ_LEVELS = eINSTANCE.getReadLevels();

		/**
		 * The meta object literal for the '{@link org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.WriteLevels <em>Write Levels</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.WriteLevels
		 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.impl.SecurityVocabularyPackageImpl#getWriteLevels()
		 * @generated
		 */
		EEnum WRITE_LEVELS = eINSTANCE.getWriteLevels();

		/**
		 * The meta object literal for the '{@link org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.BoundDirection <em>Bound Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.BoundDirection
		 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.impl.SecurityVocabularyPackageImpl#getBoundDirection()
		 * @generated
		 */
		EEnum BOUND_DIRECTION = eINSTANCE.getBoundDirection();

		/**
		 * The meta object literal for the '{@link org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityOperation <em>Security Operation</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityOperation
		 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.impl.SecurityVocabularyPackageImpl#getSecurityOperation()
		 * @generated
		 */
		EEnum SECURITY_OPERATION = eINSTANCE.getSecurityOperation();

	}

} //SecurityVocabularyPackage
