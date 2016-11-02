/** Generic Resizable priority queue using Min Heap
 *  Time Complexity for insertion : O(log N)
 *  Time Complexity for deletion : O(log N)
 *  Time Complexity for extracting element with highest priority : O(1)
 *  List Of All Methods which can be used:
 *  ===  == === ======= ===== === == ====
 *  insert(val,priority); : This method will insert a value in queue according to given priority
 *  delete() : This method will delete and return element with highest priority
 *  getHighestPriority(): will return element having maximum priority
 *  size() : This method will return current size of the queue
 *  capacity() : This method will return current capacity of the queue
 *  resize(expand_size) : This method will expand the current capacity of queue
 *  @author: Arpan Pathak **/
import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
class PriorityQueue<T>
{
	private class Node<T>{
		T val; int priority;
		Node(T val,int priority) { this.val=val; this.priority=priority; }
	}
	private Node<T>[] que;
	private int MAX_SIZE,current_size=-1;
	
	public PriorityQueue(int MAX_SIZE) { this.MAX_SIZE=MAX_SIZE; que=(Node<T>[])Array.newInstance(Node.class, MAX_SIZE); }
	// if no initial size is mentioned then default size 10 is created... 
	public PriorityQueue() { this.MAX_SIZE=10; que=(Node<T>[])Array.newInstance(Node.class, MAX_SIZE); }
	
	// insert in priority queue.. if queue is full then resize
	public void insert(T val,int priority){
		if(current_size==MAX_SIZE-1)
			resize(MAX_SIZE*2);
		que[++current_size]=new Node<T>(val,priority);
		heapUp(current_size);
	}
	public void resize(int expand_size){
		MAX_SIZE=expand_size;
		Node<T>[] temp=(Node<T>[])Array.newInstance(Node.class, expand_size);;
		for(int i=0;i<=current_size;i++) temp[i]=que[i];
		que=temp;
	}
	public int size() { return current_size+1; }
	public int capacity() { return MAX_SIZE; }
	public T getHighestPriority() throws Exception {
		if(current_size==-1) throw new Exception("Empty Queue Exception");
		return que[0].val;
	}
	@Override
	public String toString(){
		String result="";
		for(int i=0;i<=current_size;i++) result+="("+que[i].val+",priority="+que[i].priority+")\n";
		return result;
	}
	//Delete element which has highest priority
	public T delete() throws Exception{
		if(current_size==-1) throw new Exception("Empty Queue Exception");
		T item=que[0].val;
		int i=0,j=0;
		swap(0,current_size--);
		while(2*i+1<=current_size)
		{
			j=2*i+1;
			if(( (j+1<=current_size) && que[i].priority<que[j].priority && que[i].priority<que[j+1].priority) || que[i].priority<que[j].priority ) break;// No need to heapify if max heap property is satisfied
			if((j+1<=current_size) && que[j+1].priority<que[j].priority) j++;// extracting children with max value
			swap(i,j);
			i=j;
		}
		return item;
	}
	// All private methods....
	private void swap(int i,int j){ Node<T> temp=que[i]; que[i]=que[j]; que[j]=temp; }	
	private void heapUp(int index){
		while((index>0) && (que[(index-1)/2].priority>que[index].priority) )
		{
			swap(index,(index-1)/2);
			index=(index-1)/2;
		}
	}
}
public class PriorityQueueExample {
	public static void main(String[] args) throws Exception {
		PriorityQueue<String> q=new PriorityQueue<>();
		// q.delete(); // if you uncomment this line then it will throw Empty Queue Exception =D
		for(int i=1;i<=10;i++)
			q.insert("Job"+i, 11-i);
		System.out.println(q.delete());
		System.out.println(q);
	}
}
