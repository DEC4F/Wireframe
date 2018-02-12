# Wireframe 1.0
Created by Stanley Tian on Nov 22, 2017

### Introduction:
This is the design document of an implementation of Wireframe, which includes the architecture, class design, encapsulation, error propagation design, class dependency graph and pseudocode.

Architecture and Class Design description:
The main class Wireframe is the base class that serves as a canvas to this application. Everything created on top of this canvas is WireframeEntity, which comes in two types: Group and Element. Group is a group of elements clustered together, making mass moving easier, while Element is the atom of this application – it can no longer be divided. There are three types of Element – TextualElement, ShapeElement and InteractableElement, classified based on the specific functionality of each type. At last, each type of Element has an array of different subclasses, which are what’s truly being created on the Wireframe by user.

### Encapsulation:
All the classes that are user-facing are declared as public, such as those subclasses of the three types of Element plus Group, while others, such as WireframeEntity, Element and the three types of Elements themselves, those of which has no need to be seen by others, is declared as package protected. Most of their methods are declared as package-protected as well for the purpose of inheritance and encapsulation.

### Error Propagation:
It was intended that Wireframe class will serve as a barricade between user input and the rest of the program. However, I wasn’t about to implement a functional Java GUI before the time, so the constructor of each subclasses is actually vulnerable to bad input. Yet, their methods are equipped with lots of assertion and validation method calling, so it should be OK as long as Constructor is not abused.

### Graph and Pseudocode:
	See in this folder.
