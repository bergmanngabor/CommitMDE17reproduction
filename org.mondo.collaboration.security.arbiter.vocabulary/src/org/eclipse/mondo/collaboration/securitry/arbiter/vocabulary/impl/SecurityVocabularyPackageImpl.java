/**
 */
package org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.impl;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.BoundDirection;
import org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.ReadLevels;
import org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityOperation;
import org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityVocabularyFactory;
import org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityVocabularyPackage;
import org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.WriteLevels;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SecurityVocabularyPackageImpl extends EPackageImpl implements SecurityVocabularyPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum readLevelsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum writeLevelsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum boundDirectionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum securityOperationEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityVocabularyPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SecurityVocabularyPackageImpl() {
		super(eNS_URI, SecurityVocabularyFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link SecurityVocabularyPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SecurityVocabularyPackage init() {
		if (isInited) return (SecurityVocabularyPackage)EPackage.Registry.INSTANCE.getEPackage(SecurityVocabularyPackage.eNS_URI);

		// Obtain or create and register package
		SecurityVocabularyPackageImpl theSecurityVocabularyPackage = (SecurityVocabularyPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SecurityVocabularyPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SecurityVocabularyPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theSecurityVocabularyPackage.createPackageContents();

		// Initialize created meta-data
		theSecurityVocabularyPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSecurityVocabularyPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SecurityVocabularyPackage.eNS_URI, theSecurityVocabularyPackage);
		return theSecurityVocabularyPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getReadLevels() {
		return readLevelsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getWriteLevels() {
		return writeLevelsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBoundDirection() {
		return boundDirectionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSecurityOperation() {
		return securityOperationEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityVocabularyFactory getSecurityVocabularyFactory() {
		return (SecurityVocabularyFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create enums
		readLevelsEEnum = createEEnum(READ_LEVELS);
		writeLevelsEEnum = createEEnum(WRITE_LEVELS);
		boundDirectionEEnum = createEEnum(BOUND_DIRECTION);
		securityOperationEEnum = createEEnum(SECURITY_OPERATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Initialize enums and add enum literals
		initEEnum(readLevelsEEnum, ReadLevels.class, "ReadLevels");
		addEEnumLiteral(readLevelsEEnum, ReadLevels.DENY_READ);
		addEEnumLiteral(readLevelsEEnum, ReadLevels.OBFUSCATE_READ);
		addEEnumLiteral(readLevelsEEnum, ReadLevels.ALLOW_READ);

		initEEnum(writeLevelsEEnum, WriteLevels.class, "WriteLevels");
		addEEnumLiteral(writeLevelsEEnum, WriteLevels.DENY_WRITE);
		addEEnumLiteral(writeLevelsEEnum, WriteLevels.BLIND_DELETABLE_WRITE);
		addEEnumLiteral(writeLevelsEEnum, WriteLevels.ALLOW_WRITE);

		initEEnum(boundDirectionEEnum, BoundDirection.class, "BoundDirection");
		addEEnumLiteral(boundDirectionEEnum, BoundDirection.AT_LEAST);
		addEEnumLiteral(boundDirectionEEnum, BoundDirection.AT_MOST);

		initEEnum(securityOperationEEnum, SecurityOperation.class, "SecurityOperation");
		addEEnumLiteral(securityOperationEEnum, SecurityOperation.READ);
		addEEnumLiteral(securityOperationEEnum, SecurityOperation.WRITE);

		// Create resource
		createResource(eNS_URI);
	}

} //SecurityVocabularyPackageImpl
