

/**
 *
 * OAHashTable
 *
 * An OAHashTable is an object that represents 
 * a hash table which is maintained by the 
 * open addressing method.
 *
 * Yotam Hazan 205566870, username: yotamhazan
 * Kfir Grinberg  313434037, username: kfirgrinberg
 *
 */


public abstract class OAHashTable implements IHashTable {
	
	private HashTableElement [] table;
	private boolean [] isDeleted;
	protected ModHash mh;
	protected int m;
	
	public OAHashTable(int m) {
		this.m = m;
		this.table = new HashTableElement[m];
		this.isDeleted = new boolean [m];
	}
	
	
	/**
	 * public HashTableElement Find(long key)
	 * 
	 * @param key - a unique key to search for a HashTableElement in the table
	 * @return the HashTableElement if found in the table (with the same key), or null otherwise
	 */	
	@Override
	public HashTableElement Find(long key) {
		for (int i = 0; i < this.m; i++)
		{
		  if (this.table[Hash(key,i)] == null && this.isDeleted[Hash(key,i)] == false) // key does not exist in the table
			  return null;
		  
		  if (this.table[Hash(key,i)].GetKey() == key) // the element with the same key was found in the table
			  return new HashTableElement(key, this.table[Hash(key,i)].GetValue());
			
		}
		return null;
	}
	
	/**
	 * public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException
	 * 
	 * @param hte - a HashTableElement to insert in the table (if possible)
	 * @throws KeyAlreadyExistsException - throws this if an entry of the same key already exists in the table.
	 * @throws TableIsFullException - throws this if the probing sequence did not find a place for hte.
	 */		
	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {
		long key = hte.GetKey();
		int potential = this.m+1; // indicates the potential index of insertion (a deleted cell)
		int i = 0;
		int index = 0;
		
		while(i < this.m)
	      {
			index = Hash(key, i);
			HashTableElement checkElement = this.table[index];
			
			if(checkElement == null && this.isDeleted[index] == false) // an empty cell - no need to continue the search sequence
				break;
	    	 
	    	if(checkElement == null && this.isDeleted[index] == true && potential > i) // an optional cell for insertion - needs to continue the search sequence
	    	   potential = i;
	    	
	    	
		    if(checkElement != null)
		    {
		   	    if(checkElement.GetKey() == key) // there is already an element with the same key in the table
		    	   throw new KeyAlreadyExistsException(hte);
		    }
	    	
	    	i++;	    
	      }
		
		if(potential < i) // there is an optional index in the search sequence (a deleted cell)
			{
			 this.table[Hash(key, potential)] = hte;
			 this.isDeleted[Hash(key, potential)] = false;
			}
		
		else
		{
			if(i < this.m) // there is an optional index in the search sequence (a non-deleted cell)
				this.table[index] = hte;
			
			else // search sequence is over without finding  potential cell for insertion - table is full
				throw new TableIsFullException(hte);
		}		
	}
	
	/**
	 * public void Delete(long key) throws KeyDoesntExistException
	 * 
	 * @param key - the key of the HashTableElement to be deleted
	 * @throws KeyDoesntExistException - throws this if an entry of the searched key doesn't exist in the table.
	 */		
	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		int i = 0;
		
		while(i < this.m)
		{
		  int index = Hash(key, i);
		  
		  if(this.table[index] == null && this.isDeleted[index] == false) // key does not exist in the table
			  throw new KeyDoesntExistException(key);
		  
		  if(this.table[index] != null)
			 if(this.table[index].GetKey() == key) // there is a match between the key and the hash function
			 {
			   this.table[index] = null;
			   this.isDeleted[index] = true;
			   break;
		     }		  
			 
		  i++;
		}
		
		if(i == this.m)  // search sequence is finished
			throw new KeyDoesntExistException(key);
		
	}
	
	/**
	 * 
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);
}
