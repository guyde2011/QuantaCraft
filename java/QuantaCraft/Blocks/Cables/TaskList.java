package QuantaCraft.Blocks.Cables;

import java.util.ArrayList;
import java.util.List;

import QuantaCraft.main.Sides;

public class TaskList<A> {
	private List<A> tasks = new ArrayList<A>(); 
	private List<Sides> sides = new ArrayList<Sides>();
	
	public TaskList(){
	}
	
	public void add(Sides side , A task){
		tasks.add(task);
		sides.add(side);
	}
	
	public Sides getSide(int a){
		return sides.get(a);
	}
	
	public A getInfo(int a){
		return tasks.get(a);
	}
	
	public void empty(){
		tasks = new ArrayList<A>(); 
		sides = new ArrayList<Sides>();
	}
	
	public int size(){
		return tasks.size();
	}
}
