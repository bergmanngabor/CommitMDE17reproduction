package org.mondo.collaboration.security.lens.evalutation;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.incquery.patternlanguage.emf.EMFPatternLanguageStandaloneSetup;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.viatra.modelobfuscator.util.StringObfuscator;
import org.mondo.collaboration.security.lens.bx.online.OnlineCollaborationSession;
import org.mondo.collaboration.security.lens.bx.online.OnlineCollaborationSession.Leg;
import org.mondo.collaboration.security.lens.correspondence.EObjectCorrespondence.UniqueIDScheme;
import org.mondo.collaboration.security.lens.correspondence.EObjectCorrespondence.UniqueIDSchemeFactory;
import org.mondo.collaboration.security.macl.xtext.AccessControlLanguageStandaloneSetup;
import org.mondo.collaboration.security.mpbl.xtext.MondoPropertyBasedLockingStandaloneSetup;

import com.google.common.collect.Lists;

import wt.WtFactory;
import wt.WtPackage;

public class LensEvaluator {

	public static final int[] MODEL_SIZES = {1,2,4,8,16,32,64,128,256};
	public static final int[] USER_SIZES = {1,2,4,8,16,32,64,128,256};
	
	public static final String WORKING_DIRECTORY = System.getProperty("user.dir").replace("org.mondo.collaboration.security.lens.evalutation", "");
	
	public static final WtFactory eFactory = WtFactory.eINSTANCE;
	public static final WtPackage ePackage = WtPackage.eINSTANCE;
	
	private int size;
	private int user;
	private List<Leg> list = Lists.newArrayList();
	
	private OnlineCollaborationSession session;
	
	public LensEvaluator(int size, int user) {
		this.size = size;
		this.user = user;
	}

	public static void main(String[] args) throws Exception {
		
		EMFPatternLanguageStandaloneSetup.doSetup();
		AccessControlLanguageStandaloneSetup.doSetup();
		MondoPropertyBasedLockingStandaloneSetup.doSetup();
		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		for (int size : MODEL_SIZES) {
			for (int user : USER_SIZES) {
				LensEvaluator evaluator = new LensEvaluator(size, user);
				evaluator.initializeLens();	
			}
		}
	}
	
	public void initializeLens() throws IncQueryException {
		URI goldConfinementUri = URI.createFileURI(WORKING_DIRECTORY + String.format("org.mondo.collaboration.security.model/instances/model-%04d.xmi", size));
		URI ruleFileUri = URI.createFileURI(WORKING_DIRECTORY + String.format("org.mondo.collaboration.security.model/instances/rules-%04d.macl", user));
		URI queryFileUri = URI.createFileURI(WORKING_DIRECTORY + "org.mondo.collaboration.security.query/src/org/mondo/collaboration/security/query/queries.eiq");
		ResourceSet goldResourceSet = new ResourceSetImpl();
		{
			goldResourceSet.getResource(goldConfinementUri, true);
			goldResourceSet.getResource(queryFileUri, true);
			goldResourceSet.getResource(ruleFileUri, true);
		}
		
		
		OnlineCollaborationSession session = new OnlineCollaborationSession(
				goldConfinementUri, 
				goldResourceSet, 
				new UniqueIDSchemeFactory() {

					@Override
					public UniqueIDScheme apply(URI arg0) {
						return new UniqueIDScheme() {
							
							@Override
							public Object apply(EObject arg0) {
								return null;
							}
							
							@Override
							public void setUniqueId(EObject target, Object uniqueId) {
								
							}
							
							@Override
							public Object generateUniqueId(EObject input, Set<Object> reserved) {
								return null;
							}
						};
					}
				}, goldResourceSet.getResource(ruleFileUri, true), null, 
				"user_1", "dummy_password");
		
		this.session = session;
	}
	
	public void createLegs() throws InvocationTargetException {
		for (int i = 0; i < user; i++) {
			String username = String.format("user_%d",i);
			Leg leg = session.new Leg(username, new StringObfuscator(username + "seed", username + "salt"));
			list.add(leg);
		}
	}
	
}
