/**
 */
package org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SecurityVocabularyFactoryImpl extends EFactoryImpl implements SecurityVocabularyFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SecurityVocabularyFactory init() {
		try {
			SecurityVocabularyFactory theSecurityVocabularyFactory = (SecurityVocabularyFactory)EPackage.Registry.INSTANCE.getEFactory(SecurityVocabularyPackage.eNS_URI);
			if (theSecurityVocabularyFactory != null) {
				return theSecurityVocabularyFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SecurityVocabularyFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityVocabularyFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case SecurityVocabularyPackage.READ_LEVELS:
				return createReadLevelsFromString(eDataType, initialValue);
			case SecurityVocabularyPackage.WRITE_LEVELS:
				return createWriteLevelsFromString(eDataType, initialValue);
			case SecurityVocabularyPackage.BOUND_DIRECTION:
				return createBoundDirectionFromString(eDataType, initialValue);
			case SecurityVocabularyPackage.SECURITY_OPERATION:
				return createSecurityOperationFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case SecurityVocabularyPackage.READ_LEVELS:
				return convertReadLevelsToString(eDataType, instanceValue);
			case SecurityVocabularyPackage.WRITE_LEVELS:
				return convertWriteLevelsToString(eDataType, instanceValue);
			case SecurityVocabularyPackage.BOUND_DIRECTION:
				return convertBoundDirectionToString(eDataType, instanceValue);
			case SecurityVocabularyPackage.SECURITY_OPERATION:
				return convertSecurityOperationToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReadLevels createReadLevelsFromString(EDataType eDataType, String initialValue) {
		ReadLevels result = ReadLevels.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertReadLevelsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WriteLevels createWriteLevelsFromString(EDataType eDataType, String initialValue) {
		WriteLevels result = WriteLevels.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWriteLevelsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BoundDirection createBoundDirectionFromString(EDataType eDataType, String initialValue) {
		BoundDirection result = BoundDirection.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBoundDirectionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityOperation createSecurityOperationFromString(EDataType eDataType, String initialValue) {
		SecurityOperation result = SecurityOperation.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSecurityOperationToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityVocabularyPackage getSecurityVocabularyPackage() {
		return (SecurityVocabularyPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SecurityVocabularyPackage getPackage() {
		return SecurityVocabularyPackage.eINSTANCE;
	}

} //SecurityVocabularyFactoryImpl
