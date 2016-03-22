package sorting;

public enum SortType {

	BUBBLE_SORT ("Bubble Sort", 120),
	BUCKET_SORT ("Bucket Sort", 2),
	SELECTION_SORT_SLOW ("Selection Sort (Slow)", 120),
	SELECTION_SORT_FAST ("Selection Sort (Fast)", 120),
	HEAP_SORT ("Heap Sort", 3);
	
	private String name;
	private int fps;
	
	private SortType(String name, int fps) {
		this.name = name;
		this.fps = fps;
	}
	
	public String sortName() {
		return name;
	}
	
	public int getDefaultFPS() {
		return fps;
	}
}
