

/**
 *
 * ModHash
 *
 * An object which declares a hash function from an
 * universal family of hash functions.
 *
 * Yotam Hazan 205566870, username: yotamhazan
 * Kfir Grinberg  313434037, username: kfirgrinberg
 *
 */

import java.util.Random;

public class ModHash{
	
	private static int m;
	private static long p;
	private long [] params;
	
	public ModHash(int m, long p)
	{
		ModHash.m = m;
		ModHash.p = p;
		this.params = new long[2];		
	}
	
	/**
	 * public static ModHash GetFunc(int m, long p)
	 * 
	 * @param m - the table's size
	 * @param p - a prime number
	 * @return  a ModHash object with new values (long type) a and b.
	 */
	public static ModHash GetFunc(int m, long p){
	
		Random rnd = new Random();
		long a = rnd.nextLong() % (p-1); // a long type integer from range 1-(p-1)
		a = (a + p-1) % (p-1) +1;
		
		long b = rnd.nextLong() % p; // a long type integer from range 0-(p-1)
		b = (b + p) % p;
		
		ModHash mh = new ModHash(m, p);
		mh.params[0] = a;
		mh.params[1] = b;
		
		return mh;
	}
	
	/**
	 * public int Hash(long key)
	 * 
	 * @param x - the key that will be calculated by the hash function
	 * @return the index in the table (based on hash calculation)
	 */
	public int Hash(long key) {
		
		int index = (int)(((this.params[0]*key + this.params[1])% ModHash.p) % ModHash.m);
		
		return index;
	}

}
