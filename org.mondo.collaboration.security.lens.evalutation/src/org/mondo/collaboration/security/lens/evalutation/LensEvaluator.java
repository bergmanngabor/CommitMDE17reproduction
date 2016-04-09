package org.mondo.collaboration.security.lens.evalutation;

import java.util.Map;
import java.util.Map.Entry;
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
import com.google.common.collect.Maps;

import wt.Module;
import wt.Signal;
import wt.WtFactory;
import wt.WtPackage;

public class LensEvaluator {

	public static final Set<ChangeType> CHANGE_TYPES = ImmutableSet.of(
			ChangeType.Complex,
			ChangeType.AddSignal, 
			ChangeType.DeleteSignal, 
			ChangeType.AddConsumeReference, 
			ChangeType.DeleteConsumeReference);
	
	public enum ChangeType {
		Complex, AddSignal, DeleteSignal, AddConsumeReference, DeleteConsumeReference
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

	public static final int[] MODEL_SIZES = {13, 25, 38, 50, 63, 75, 88, 100};//{1,2,4,8,16,32,64,128,256};//
	public static final int[] USER_SIZES = {10,20,30,40,50,60,70,80,90,100};//,128,256};

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

	public void dispose() {
		while(!session.getLegs().isEmpty())
			session.getLegs().iterator().next().dispose();
	}
	
	public static void main(String[] args) throws Exception {
		
		EMFPatternLanguageStandaloneSetup.doSetup();
		AccessControlLanguageStandaloneSetup.doSetup();
		MondoPropertyBasedLockingStandaloneSetup.doSetup();
		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		logHeader();
		for (int size : MODEL_SIZES) {
			for (int user : USER_SIZES) {
					if(size * 4 <= user) continue;
					
					System.gc();
					System.gc();
					System.gc();
					
					printMemoryUsage();
					
					LensEvaluator evaluator = new LensEvaluator(size, user);
					evaluator.initializeLens();
					evaluator.createLegs();
					evaluator.measureChanges();
			}
		}
	}

	public void initializeLens() throws IncQueryException {
		URI goldConfinementUri = URI.createFileURI(WORKING_DIRECTORY + String.format("org.mondo.collaboration.security.model/instances/model-%04d-%04d.xmi", size, user));
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
		}
		for (int i = 1; i <= user; i++) {
			String username = String.format("user_%d",i);
			URI frontConfinementUri = URI.createFileURI(WORKING_DIRECTORY + String.format("org.mondo.collaboration.security.model/instances/view-model-size-%d-user-%d-current-%d.xmi", size, user, i));
			printMemoryUsage();
			session.new Leg(username, new StringObfuscator(username + "seed", username + "salt"), true, new ResourceSetImpl(), frontConfinementUri);
		}
	}
	
	public void measureChanges() throws InterruptedException {
		measureComplexChange();
//		Module module = selectArbitraryModule();
//		for (ChangeType changeType : CHANGE_TYPES) {
//			switch (changeType) {
//			case AddConsumeReference:
//				measureAddConsumeReferenceChange(module);
//				break;
//			case AddSignal:
//				measureAddSignalChange(module);
//				break;
//			case DeleteConsumeReference:
//				measureDeleteConsumeReferenceChange(module);
//				break;
//			case DeleteSignal:
//				measureDeleteSignalChange(module);
//				break;
//			default:
//				break;
//			}
//		}
		
	}

	private void measureComplexChange() throws InterruptedException {
		Map<Module, Signal> allConsumedSignal = getAllConsumedSignal();
		
		for (Entry<Module,Signal> entry : allConsumedSignal.entrySet()) {
			Module consumer = entry.getKey();
			Signal signal = entry.getValue();
			Module provider = (Module) signal.eContainer(); 
			
			System.gc();
			System.gc();
			Thread.sleep(1000);
			long start = System.nanoTime();
			consumer.getProvides().add(signal);
			provider.getConsumes().add(signal);
			leg.trySubmitModification();
			long end = System.nanoTime();
			System.gc();
			System.gc();

			logMeasurement(start, end, ChangeType.DeleteConsumeReference, size, user, session.getLegs().size());
		}
	}
	
	private Map<Module,Signal> getAllConsumedSignal() {
		Map<Module,Signal> ret = Maps.newHashMap();
		for (TreeIterator<Notifier> iterator = leg.getFrontResourceSet().getAllContents(); iterator.hasNext();) {
			Notifier notifier = iterator.next();
			if(notifier instanceof Module) {
				Module module = (Module) notifier;
				for (Signal signal : module.getConsumes()) {
					ret.put(module, signal);
				}
				
			}
		}
		return ret;
	}
	
	private static void printMemoryUsage() {
//		Runtime runtime = Runtime.getRuntime();
//
//		NumberFormat format = NumberFormat.getInstance();
//
//		StringBuilder sb = new StringBuilder();
//		long maxMemory = runtime.maxMemory();
//		long allocatedMemory = runtime.totalMemory();
//		long freeMemory = runtime.freeMemory();
//
//		sb.append("free memory: " + format.format(freeMemory / 1024) + "\n");
//		sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "\n");
//		sb.append("max memory: " + format.format(maxMemory / 1024) + "\n");
//		sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "\n");
//		System.out.println(sb.toString());
	}
	
//	private void measureAddConsumeReferenceChange(Module module) throws InterruptedException {
//		Signal signal = selectArbitrarySignal();
//		
//		System.gc();
//		System.gc();
//		Thread.sleep(1000);
//		long start = System.nanoTime();
//		module.getConsumes().add(signal);
//		leg.trySubmitModification();
//		long end = System.nanoTime();
//		System.gc();
//		System.gc();
//		
//		logMeasurement(start, end, ChangeType.AddConsumeReference, size, user, session.getLegs().size());
//	}
//	
//	private void measureAddSignalChange(Module module) throws InterruptedException {
//		Signal signal = eFactory.createSignal();
//		
//		System.gc();
//		System.gc();
//		Thread.sleep(1000);
//		long start = System.nanoTime();
//		module.getProvides().add(signal);
//		leg.trySubmitModification();
//		long end = System.nanoTime();
//		System.gc();
//		System.gc();
//		
//		logMeasurement(start, end, ChangeType.AddSignal, size, user, session.getLegs().size());
//	}
//	
//	private void measureDeleteConsumeReferenceChange(Module module) throws InterruptedException {
//		Signal signal = module.getConsumes().get(0);
//		
//		System.gc();
//		System.gc();
//		Thread.sleep(1000);
//		long start = System.nanoTime();
//		module.getConsumes().remove(signal);
//		leg.trySubmitModification();
//		long end = System.nanoTime();
//		System.gc();
//		System.gc();
//		
//		logMeasurement(start, end, ChangeType.DeleteConsumeReference, size, user, session.getLegs().size());
//	}
//	
//	private void measureDeleteSignalChange(Module module) throws InterruptedException {
//		Signal signal = module.getProvides().get(0);
//		
//		System.gc();
//		System.gc();
//		Thread.sleep(1000);
//		long start = System.nanoTime();
//		module.getProvides().remove(signal);
//		leg.trySubmitModification();
//		long end = System.nanoTime();
//		System.gc();
//		System.gc();
//		
//		logMeasurement(start, end, ChangeType.DeleteSignal, size, user, session.getLegs().size());
//	}
	
//	private Module selectArbitraryModule() {
//		List<Module> list = Lists.newArrayList();
//		for (TreeIterator<Notifier> iterator = leg.getFrontResourceSet().getAllContents(); iterator.hasNext();) {
//			Notifier notifier = iterator.next();
//			if (notifier instanceof Module) {
//				list.add((Module) notifier);
//			}
//		}
//		return list.get(rnd.nextInt(list.size()));
//	}
//
//	private Signal selectArbitrarySignal() {
//		List<Signal> list = Lists.newArrayList();
//		for (TreeIterator<Notifier> iterator = leg.getFrontResourceSet().getAllContents(); iterator.hasNext();) {
//			Notifier notifier = iterator.next();
//			if (notifier instanceof Signal) {
//				list.add((Signal) notifier);
//			}
//		}
//		return list.get(rnd.nextInt(list.size()));
//	}
	
//	private CharSequence calculateYed(Resource model) {
//		Model2Yed yed = new Model2Yed();
//		CharSequence sequence = yed.transform(Lists.newArrayList(model.getAllContents()));
//		return sequence;
//	}
//	
//	public static void save(String path, CharSequence sequence) throws Exception {
//		try(  PrintWriter out = new PrintWriter( path )  ){
//		    out.println( sequence.toString() );
//		}
//	}
	
	private static void logHeader() {
		System.out.println("ChangeType;ModelSize;UserSize;NumberOfLegs;ExecutionTime(nano)");
	}
	
	private static void logMeasurement(long start, long end, ChangeType type, int size, int user, int legCount) {
		System.out.println(String.format("%s;%d;%d;%d;%d", type, size, user, legCount, end-start));
	}
	
}
