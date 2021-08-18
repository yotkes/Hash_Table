

/**
 *
 * DoubleHashTable
 *
 * An implementation of a hash table based on probing by 2 different
 * hash functions (from the same universal family of functions).
 *
 * Yotam Hazan 205566870, username: yotamhazan
 * Kfir Grinberg  313434037, username: kfirgrinberg
 *
 */

public class DoubleHashTable extends OAHashTable {
	
	private ModHash mh2;
	
	public DoubleHashTable(int m, long p) {
		super(m);
		this.mh = ModHash.GetFunc(m,p); //creates a function from the universal family
		this.mh2 = ModHash.GetFunc(m-1,p); //creates a function from the universal family that gets values in range: 0 to m-1
	}
	
	/**
	 * public int Hash(long x, int i)
	 * 
	 * @param x - the key that will be calculated by the hash function
	 * @param i - the specific probing
	 * @return the index in the table (based on hash calculation)
	 */
	@Override
	public int Hash(long x, int i) {

		int index = (int)((this.mh.Hash(x) + i*(this.mh2.Hash(x)+1)) % this.m); // there is an addition of 1 to mh2 so that the function's value will be in range 1 to m
		index = (index + this.m) % this.m;
		
		return index;
	}
	
}
