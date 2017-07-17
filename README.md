# Accompanying files for CommitMDE 17

## About

This page contains accompanying files for our CommitMDE 2017 paper _"Towards Efficient Evaluation of Rule-based Permissions
for Fine-grained Access Control in Collaborative Modeling"_. 

Due to the shared case study, this repository itself has been cloned from the [repository](https://github.com/debrecenics/MODELS16) of an earlier paper.

## Install requirements

* Eclipse Modeling Framework (EMF)
* Xtend 2.12.0 | [update-site](http://download.eclipse.org/modeling/tmf/xtext/updates/composite/milestones/)
* Viatra Query 1.6.0  | [update-site](http://download.eclipse.org/viatra/updates/release)

## Build projects

 * Import the `*.meta` and `*.arbiter.vocabulary` plugin projects. 
 * In both projects, open the `*.genmodel` file and generate the model code: in the tree-editor, right click on the root object and select the `Generate Model`.
 * Launch an Eclipse instance that has both of these metamodel plugins loaded, and continue with the following steps. 
 * Import the rest of relevant projects (`*.model`, `*.query`, `*.arbiter.stratifier`, `*.arbiter`) into the workspace of the launched project.

## Generate Instance Models (Optional)

* In the `*.model` plugin project, the `ModelGenerator.java` file has a `main` method that can be run. 
* Modify the `generate()` method to select the output folder.
* Modify the `MODEL_SIZES` variable to set the models to be generated.

## Regenerate Partially Grounded Queries (Optional)

* In the `*.arbiter.stratifier` plugin project, launch the `GenStratified.xtend` file (has a `main` method entry point) as Java Application. 
* Viatra Query code realizing the stratified form of the recursive queries will be generated to the standard output (as seen in the Eclipse Console View). 
* Replace `arbiterMock.vql` in project `*.arbiter` with the generated Viatra Query code.

## Load and Evaluate Query Results

* Open `small-sample-model-0010.xmi` from the `*.model` project, or alternatively one of the smaller generated models, using the Sample Reflective Editor.
* Open the Viatra Query Results View, and load the instance model from the active editor (use the green load button).
* Load the following query files using the Query Results View: `queries.vql` from project `*.query`, then `modelWrapper.vql` and `policyInterpreter.vql` and finally `arbiterMock.vql` from project `*.query`.
