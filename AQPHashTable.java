

/**
 *
 * AQPHashTable
 *
 * An implementation of a hash table based on an alternative quadratic 
 * form of probing.
 *
 * Yotam Hazan 205566870, username: yotamhazan
 * Kfir Grinberg  313434037, username: kfirgrinberg
 *
 */

public class AQPHashTable extends OAHashTable {

	public AQPHashTable(int m, long p) {
		super(m);
		this.mh = ModHash.GetFunc(m, p);
	}
	
	
	/**
	 * public int Hash(long x, int i)
	 * 
	 * @param x - the key that will be calculated by the hash function
	 * @param i - the specific probing
	 * @return the optional index in the table (based on hash calculation)
	 * 
	 */
	@Override
	public int Hash(long x, int i) {

		int index = (int)((this.mh.Hash(x) + (Math.pow(-1, i)*Math.pow(i, 2))) % this.m);
		index = (index + this.m) % this.m;
		
		return index;
	}
}
