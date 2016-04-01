# MODELS16 Access-Control Evaluation

## Requirements

* Eclipse Modeling Framework
* Xtend 2.9.0
* IncQuery 1.0.1
* Yed for visualizing models

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
