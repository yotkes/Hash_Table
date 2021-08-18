

/**
 *
 * QPHashTable
 *
 * An implementation of a hash table based on a quadratic 
 * form of probing.
 *
 * Yotam Hazan 205566870, username: yotamhazan
 * Kfir Grinberg  313434037, username: kfirgrinberg
 *
 */

public class QPHashTable extends OAHashTable {

	public QPHashTable(int m, long p) {
		super(m);
		this.mh = ModHash.GetFunc(m,p);
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
		
		int index = (int)((this.mh.Hash(x) + Math.pow(i, 2)) % this.m) ;
		index = (index + this.m) % this.m;
		
		return index;
	}
	
}