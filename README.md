# MODELS16 Access-Control Evaluation

## Requirements

* Eclipse Modeling Framework (EMF)
* Xtend 2.9.0 | [update-site](http://download.eclipse.org/modeling/tmf/xtext/updates/composite/milestones/)
* IncQuery 1.1.0  | [update-site](http://download.eclipse.org/viatra/incquery/updates/release)
* VIATRA Model Obfuscator | [update-site](http://download.eclipse.org/viatra2/modelobfuscator/updates/integration)
* Yed for visualizing models | [download page](https://www.yworks.com/products/yed/download)

## Import projects from Collaboration Framework [clone url](https://github.com/FTSRG/mondo-collab-framework.git)

* Import [Access Control Language for Rules](https://github.com/FTSRG/mondo-collab-framework/tree/master/plugins/org.mondo.collaboration.security.macl.xtext.rule)
    * Add `src-gen` folder to fix build path error.
    * Run `GenerateMACLRule.mwe2` as "MWE2 Workflow"
* Import [Access Control Language](https://github.com/FTSRG/mondo-collab-framework/tree/master/plugins/org.mondo.collaboration.security.macl.xtext)
    * Add `src-gen` folder to fix build path error.
    * Run `GenerateMondoAccessControlLanguage.mwe2` as "MWE2 Workflow"
* Import [Property-based Locking Language](https://github.com/FTSRG/mondo-collab-framework/tree/master/plugins/org.mondo.collaboration.security.mpbl.xtext)
    * Add `src-gen` folder to fix build path error.
    * Run `GenerateMondoPropertyBasedLocking.mwe2` as "MWE2 Workflow"
* Import [Security Collaboartion Lens Implementation](https://github.com/FTSRG/mondo-collab-framework/tree/master/plugins/org.mondo.collaboration.security.lens)
    * Add `src-gen` folder to fix build path error.
    * Build the project to generate `.java` files from `.xtend` classes.

## Build projects

In the `*.meta` plugin project, open the `*.genmodel` file and generate the model code: in the tree-editor, right click on the root object and select the `Generate Model`.

In the `*.query` plugin project, open the `*.eiq` file and force to build the associated artifacts.

## Generate Models

* In the `*.model` plugin project, the `ModelGenerator.java` file has a `main` method that can be run. 
* Modify the `generate()` method to select the output folder.
* Modify the `MODEL_SIZES` variable to set the models to be generated.

## Generate Rules

* In the `*.rule` plugin project, the `AccessControlRuleGenerator.java` file has a `main` method that can be run. 
* Modify the `generate()` method to select the output folder.
* Modify the `USER_SIZES` variable to set the rules to be generated.

## Execute Evaluation

* In the `*.evaluation` plugin project, the `LensEvaluator.java` file has a `main` method that can be run.

