/**
 */
package org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Read Levels</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityVocabularyPackage#getReadLevels()
 * @model
 * @generated
 */
public enum ReadLevels implements Enumerator {
	/**
	 * The '<em><b>DENY READ</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DENY_READ_VALUE
	 * @generated
	 * @ordered
	 */
	DENY_READ(0, "DENY_READ", "DENY_READ"),

	/**
	 * The '<em><b>OBFUSCATE READ</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OBFUSCATE_READ_VALUE
	 * @generated
	 * @ordered
	 */
	OBFUSCATE_READ(1, "OBFUSCATE_READ", "OBFUSCATE_READ"),

	/**
	 * The '<em><b>ALLOW READ</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALLOW_READ_VALUE
	 * @generated
	 * @ordered
	 */
	ALLOW_READ(2, "ALLOW_READ", "ALLOW_READ");

	/**
	 * The '<em><b>DENY READ</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DENY READ</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DENY_READ
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DENY_READ_VALUE = 0;

	/**
	 * The '<em><b>OBFUSCATE READ</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OBFUSCATE READ</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OBFUSCATE_READ
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OBFUSCATE_READ_VALUE = 1;

	/**
	 * The '<em><b>ALLOW READ</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALLOW READ</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALLOW_READ
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALLOW_READ_VALUE = 2;

	/**
	 * An array of all the '<em><b>Read Levels</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ReadLevels[] VALUES_ARRAY =
		new ReadLevels[] {
			DENY_READ,
			OBFUSCATE_READ,
			ALLOW_READ,
		};

	/**
	 * A public read-only list of all the '<em><b>Read Levels</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ReadLevels> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Read Levels</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ReadLevels get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReadLevels result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Read Levels</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ReadLevels getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReadLevels result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Read Levels</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ReadLevels get(int value) {
		switch (value) {
			case DENY_READ_VALUE: return DENY_READ;
			case OBFUSCATE_READ_VALUE: return OBFUSCATE_READ;
			case ALLOW_READ_VALUE: return ALLOW_READ;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ReadLevels(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ReadLevels
