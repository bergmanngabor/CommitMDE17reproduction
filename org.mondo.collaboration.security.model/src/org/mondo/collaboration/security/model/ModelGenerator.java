package org.mondo.collaboration.security.model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Lists;

import hu.bme.mit.inf.dslreasoner.visualisation.emf2yed.Model2Yed;
import wt.Composite;
import wt.Control;
import wt.Cycle;
import wt.Signal;
import wt.WtFactory;
import wt.WtPackage;

public class ModelGenerator {

	public static final int[] MODEL_SIZES = {1,2,4,8,16,32,64,128,256};
	public static final WtFactory eFactory = WtFactory.eINSTANCE;
	public static final WtPackage ePackage = WtPackage.eINSTANCE;
	
	
	public static void main(String[] args) throws Exception {
		ModelGenerator generator = new ModelGenerator();
		generator.generate();
	}

	private void generate() throws Exception {
		for (int size : MODEL_SIZES) {
			Composite model = generate(size);
			CharSequence yed = calculateYed(model);
			save("C:\\Eclipse\\Articles\\workspace\\org.mondo.collaboration.security.model\\instances\\model-"+size+".gml", yed);
		}
	}

	private Composite generate(int size) {
		Composite rootComposite = eFactory.createComposite();
		{
			rootComposite.setProtectedIP(false);
			rootComposite.setVendor("");
		}
		for(int id = 0; id < size; id++) {
			Composite pyramidRoot = createPyramid(id);	
			rootComposite.getSubmodules().add(pyramidRoot);
		}
		return rootComposite;
	}

	private Composite createPyramid(int id) {
		Composite pyramidRoot = eFactory.createComposite();
		{
			pyramidRoot.setProtectedIP(id % 2 == 1 ? true : false);
			pyramidRoot.setVendor(String.valueOf(id));
		}
		
		Composite left = eFactory.createComposite();
		{
			left.setProtectedIP(id % 2 == 1 ? true : false);
			left.setVendor(String.valueOf(id));
		}
		Composite right = eFactory.createComposite();
		{
			right.setProtectedIP(id % 2 == 1 ? true : false);
			right.setVendor(String.valueOf(id));
		}
		pyramidRoot.getSubmodules().add(left);
		pyramidRoot.getSubmodules().add(right);
		
		createController(id, left);
		createController(id, left);
		createController(id, right);
		createController(id, right);
		
		return pyramidRoot;
	}

	private void createController(int id, Composite parent) {
		Control ctrl = eFactory.createControl();
		{
			parent.getSubmodules().add(ctrl);
			ctrl.setCycle(getRandomCycleEnum());
			ctrl.setType(id);
			
			createInput(parent, ctrl);
			createOutput(parent, ctrl);
		}
	}
	
	private void createInput(Composite parent, Control ctrl) {
		Signal parentSignalInput = eFactory.createSignal();
		Signal ctrlSignalInput = eFactory.createSignal();
		parent.getProvides().add(parentSignalInput);
		ctrl.getProvides().add(ctrlSignalInput);
		ctrl.getReads().add(parentSignalInput);		
	}

	private void createOutput(Composite parent, Control ctrl) {
		Signal parentSignalOutput = eFactory.createSignal();
		Signal ctrlSignalOutput = eFactory.createSignal();
		parent.getProvides().add(parentSignalOutput);
		ctrl.getProvides().add(ctrlSignalOutput);
		parent.getReads().add(ctrlSignalOutput);		
	}

	private Cycle getRandomCycleEnum() {
		List<Cycle> list = Lists.newArrayList(Cycle.VALUES);
		Collections.shuffle(list);
		return list.get(0);
	}
	
	private CharSequence calculateYed(Composite model) {
		Model2Yed yed = new Model2Yed();
		ArrayList<EObject> objects = Lists.newArrayList(model.eAllContents());
		objects.add(model);
		CharSequence sequence = yed.transform(objects);
		return sequence;
	}
	
	public static void save(String path, CharSequence sequence) throws Exception {
		try(  PrintWriter out = new PrintWriter( path )  ){
		    out.println( sequence.toString() );
		}
	}
}
