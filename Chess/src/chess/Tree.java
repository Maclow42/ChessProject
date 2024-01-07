package chess;

import java.util.ArrayList;

public class Tree {
	
	public Object value;
	public ArrayList<Object> children;
	
	Tree(Object value){
		this.value = value;
		this.children = new ArrayList<Object>();
	}
	
	Tree addChild(Object childValue) {
		Tree newChild = new Tree(childValue);
		this.children.add(newChild);
		return newChild;
	}
	
	int nbChildren() {
		return this.children.size();
	}
	
	boolean removeChild(Tree toRemove) {
		return this.children.remove(toRemove);
	}
	
	Tree removeChildWithValue(Object value) {
		for(int i = 0; i < this.nbChildren(); i++) {
			if(((Tree) this.children.get(i)).value == value) {
				return (Tree) this.children.remove(i);
			}
		}
		return null;
	}
}
