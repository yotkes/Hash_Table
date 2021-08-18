

/**
 *
 * HashTableElement
 *
 * A object that represents an element that will be handled by
 * a specific hash table.
 * Each element contains two fields - a key & a value. 
 *
 * Yotam Hazan 205566870, username: yotamhazan
 * Kfir Grinberg  313434037, username: kfirgrinberg
 *
 */

public class HashTableElement{
	private long key;
	private long value;
	
	public HashTableElement(long key, long value) {
		this.key = key;
		this.value = value;
	}
	
	public long GetKey() { return this.key;}
	
	public long GetValue() { return this.value;}
}