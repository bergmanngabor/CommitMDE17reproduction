package org.mondo.collaboration.security.lens.evalutation;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
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

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import hu.bme.mit.inf.dslreasoner.visualisation.emf2yed.Model2Yed;
import wt.Module;
import wt.Signal;
import wt.WtFactory;
import wt.WtPackage;

public class LensEvaluator {

	public static final Set<ChangeType> CHANGE_TYPES = ImmutableSet.of(
			ChangeType.AddSignal, 
			ChangeType.DeleteSignal, 
			ChangeType.AddConsumeReference, 
			ChangeType.DeleteConsumeReference);
	
	public enum ChangeType {
		AddSignal, DeleteSignal, AddConsumeReference, DeleteConsumeReference
	}
	
	private final class UniqueIDSchemeFactoryImplementation implements UniqueIDSchemeFactory {
		@Override
		public UniqueIDScheme apply(URI arg0) {
			return new UniqueIDScheme() {
				
				private int id = 0;
				
				@Override
				public Object apply(EObject arg0) {
					return id++;
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
	}

	public static final int[] MODEL_SIZES = {1,2,4,8,16,32,64,128,256};
	public static final int[] USER_SIZES = {1,2,4,8,16,32,64,128,256};

	public final Random rnd = new Random();
	
	public static final String WORKING_DIRECTORY = System.getProperty("user.dir").replace("org.mondo.collaboration.security.lens.evalutation", "");
	
	public static final WtFactory eFactory = WtFactory.eINSTANCE;
	public static final WtPackage ePackage = WtPackage.eINSTANCE;
	
	private int size;
	private int user;
	
	private OnlineCollaborationSession session;
	private Leg leg;
	
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
				evaluator.createLegs();
				
				logHeader();
				evaluator.measureChanges();
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
		}
		ResourceSet policyResourceSet = new ResourceSetImpl();
		{
			policyResourceSet.getResource(queryFileUri, true);
			policyResourceSet.getResource(ruleFileUri, true);
		}
		
		OnlineCollaborationSession session = new OnlineCollaborationSession(
				goldConfinementUri, 
				goldResourceSet, 
				new UniqueIDSchemeFactoryImplementation(), 
				policyResourceSet.getResource(ruleFileUri, true), 
				null, "user_1", "dummy_password");
		
		this.session = session;
	}
	
	public void createLegs() throws Exception {
		{
			String username = "superuser";
			URI frontConfinementUri = URI.createFileURI(WORKING_DIRECTORY + String.format("org.mondo.collaboration.security.model/instances/view-model-size-%d-user-%d-current-%s.xmi", size, user, username));
			leg = session.new Leg(username, new StringObfuscator(username + "seed", username + "salt"), true, new ResourceSetImpl(), frontConfinementUri);
			Resource frontResource = leg.getFrontResourceSet().getResources().get(0);
			CharSequence yed = calculateYed(frontResource);
			save(WORKING_DIRECTORY + String.format("org.mondo.collaboration.security.model/instances/view-model-size-%d-user-%d-current-%s.gml", size, user, username), yed);
			frontResource.save(null);
			System.out.println(String.format("Model saved: %s", frontConfinementUri));
		}
		for (int i = 1; i <= user; i++) {
			String username = String.format("user_%d",i);
			URI frontConfinementUri = URI.createFileURI(WORKING_DIRECTORY + String.format("org.mondo.collaboration.security.model/instances/view-model-size-%d-user-%d-current-%d.xmi", size, user, i));
			Leg leg = session.new Leg(username, new StringObfuscator(username + "seed", username + "salt"), true, new ResourceSetImpl(), frontConfinementUri);
			Resource frontResource = leg.getFrontResourceSet().getResources().get(0);
			CharSequence yed = calculateYed(frontResource);
			save(WORKING_DIRECTORY + String.format("org.mondo.collaboration.security.model/instances/view-model-size-%d-user-%d-current-%d.gml", size, user, i), yed);
			frontResource.save(null);
			System.out.println(String.format("Model saved: %s", frontConfinementUri));
		}
	}
	
	public void measureChanges() throws InterruptedException {
		Module module = selectArbitraryModule();
		for (ChangeType changeType : CHANGE_TYPES) {
			switch (changeType) {
			case AddConsumeReference:
				measureAddConsumeReferenceChange(module);
				break;
			case AddSignal:
				measureAddSignalChange(module);
				break;
			case DeleteConsumeReference:
				measureDeleteConsumeReferenceChange(module);
				break;
			case DeleteSignal:
				measureDeleteSignalChange(module);
				break;
			default:
				break;
			}
		}
		
	}

	private void measureAddConsumeReferenceChange(Module module) throws InterruptedException {
		Signal signal = selectArbitrarySignal();
		
		System.gc();
		System.gc();
		Thread.sleep(1000);
		long start = System.nanoTime();
		module.getConsumes().add(signal);
		leg.trySubmitModification();
		long end = System.nanoTime();
		System.gc();
		System.gc();
		
		logMeasurement(start, end, ChangeType.AddSignal, size, user);
	}
	
	private void measureAddSignalChange(Module module) throws InterruptedException {
		Signal signal = eFactory.createSignal();
		
		System.gc();
		System.gc();
		Thread.sleep(1000);
		long start = System.nanoTime();
		module.getProvides().add(signal);
		leg.trySubmitModification();
		long end = System.nanoTime();
		System.gc();
		System.gc();
		
		logMeasurement(start, end, ChangeType.AddSignal, size, user);
	}
	
	private void measureDeleteConsumeReferenceChange(Module module) throws InterruptedException {
		Signal signal = module.getConsumes().get(0);
		
		System.gc();
		System.gc();
		Thread.sleep(1000);
		long start = System.nanoTime();
		module.getConsumes().remove(signal);
		leg.trySubmitModification();
		long end = System.nanoTime();
		System.gc();
		System.gc();
		
		logMeasurement(start, end, ChangeType.DeleteSignal, size, user);
	}
	
	private void measureDeleteSignalChange(Module module) throws InterruptedException {
		Signal signal = module.getProvides().get(0);
		
		System.gc();
		System.gc();
		Thread.sleep(1000);
		long start = System.nanoTime();
		module.getProvides().remove(signal);
		leg.trySubmitModification();
		long end = System.nanoTime();
		System.gc();
		System.gc();
		
		logMeasurement(start, end, ChangeType.DeleteSignal, size, user);
	}
	
	private Module selectArbitraryModule() {
		List<Module> list = Lists.newArrayList();
		for (TreeIterator<Notifier> iterator = leg.getFrontResourceSet().getAllContents(); iterator.hasNext();) {
			Notifier notifier = iterator.next();
			if (notifier instanceof Module) {
				list.add((Module) notifier);
			}
		}
		return list.get(rnd.nextInt(list.size()));
	}

	private Signal selectArbitrarySignal() {
		List<Signal> list = Lists.newArrayList();
		for (TreeIterator<Notifier> iterator = leg.getFrontResourceSet().getAllContents(); iterator.hasNext();) {
			Notifier notifier = iterator.next();
			if (notifier instanceof Signal) {
				list.add((Signal) notifier);
			}
		}
		return list.get(rnd.nextInt(list.size()));
	}
	
	private CharSequence calculateYed(Resource model) {
		Model2Yed yed = new Model2Yed();
		CharSequence sequence = yed.transform(Lists.newArrayList(model.getAllContents()));
		return sequence;
	}
	
	public static void save(String path, CharSequence sequence) throws Exception {
		try(  PrintWriter out = new PrintWriter( path )  ){
		    out.println( sequence.toString() );
		}
	}
	
	private static void logHeader() {
		System.out.println("ChangeType;ModelSize;UserSize;ExecutionTime(nano)");
	}
	
	private static void logMeasurement(long start, long end, ChangeType type, int size, int user) {
		System.out.println(String.format("%s;%d;%d;%d", type, size, user, end-start));
	}
	
}
