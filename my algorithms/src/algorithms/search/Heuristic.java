package algorithms.search;

public interface Heuristic<T> {
	
	public double calc(State<T> init , State<T> goal);

}
