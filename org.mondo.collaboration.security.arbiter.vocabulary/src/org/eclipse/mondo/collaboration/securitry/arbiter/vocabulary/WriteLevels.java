/**
 */
package org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Write Levels</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.mondo.collaboration.securitry.arbiter.vocabulary.SecurityVocabularyPackage#getWriteLevels()
 * @model
 * @generated
 */
public enum WriteLevels implements Enumerator {
	/**
	 * The '<em><b>DENY WRITE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DENY_WRITE_VALUE
	 * @generated
	 * @ordered
	 */
	DENY_WRITE(0, "DENY_WRITE", "DENY_WRITE"),

	/**
	 * The '<em><b>BLIND DELETABLE WRITE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BLIND_DELETABLE_WRITE_VALUE
	 * @generated
	 * @ordered
	 */
	BLIND_DELETABLE_WRITE(1, "BLIND_DELETABLE_WRITE", "BLIND_DELETABLE_WRITE"),

	/**
	 * The '<em><b>ALLOW WRITE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALLOW_WRITE_VALUE
	 * @generated
	 * @ordered
	 */
	ALLOW_WRITE(2, "ALLOW_WRITE", "ALLOW_WRITE");

	/**
	 * The '<em><b>DENY WRITE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DENY WRITE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DENY_WRITE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DENY_WRITE_VALUE = 0;

	/**
	 * The '<em><b>BLIND DELETABLE WRITE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BLIND DELETABLE WRITE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BLIND_DELETABLE_WRITE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BLIND_DELETABLE_WRITE_VALUE = 1;

	/**
	 * The '<em><b>ALLOW WRITE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALLOW WRITE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALLOW_WRITE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALLOW_WRITE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Write Levels</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final WriteLevels[] VALUES_ARRAY =
		new WriteLevels[] {
			DENY_WRITE,
			BLIND_DELETABLE_WRITE,
			ALLOW_WRITE,
		};

	/**
	 * A public read-only list of all the '<em><b>Write Levels</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<WriteLevels> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Write Levels</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static WriteLevels get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WriteLevels result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Write Levels</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static WriteLevels getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WriteLevels result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Write Levels</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static WriteLevels get(int value) {
		switch (value) {
			case DENY_WRITE_VALUE: return DENY_WRITE;
			case BLIND_DELETABLE_WRITE_VALUE: return BLIND_DELETABLE_WRITE;
			case ALLOW_WRITE_VALUE: return ALLOW_WRITE;
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
	private WriteLevels(int value, String name, String literal) {
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
	
} //WriteLevels
