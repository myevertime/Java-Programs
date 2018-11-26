public class Cuckoo<K, V>
{

    public KVPair<K, V>[] table1;
    public KVPair<K, V>[] table2;

    private HashFunction<K> hash1;
    private HashFunction<K> hash2;
    private AllocationRule alloc;

    private double threshold;

    private final int initial_length;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Cuckoo(
        HashFunction<K> h1,
        HashFunction<K> h2,
        AllocationRule alloc,
        double threshold,
        int initial_length
    )
    {
        this.initial_length = initial_length;

        table1 = (KVPair<K, V>[])new KVPair[initial_length];
        table2 = (KVPair<K, V>[])new KVPair[initial_length];

        hash1 = h1; hash2 = h2;
        this.alloc = alloc;

        this.threshold = threshold;
    }

    public void insert(K key, V value)
    {
        insert(key, value, table1);
        double load_factor = (double)size() / (2 * length());
        if (load_factor >= threshold) {
            resize();
        }
    }

    public void remove(K key)
    {
       for (int i = 0; i < table1.length; i++) {
          if (table1[i] != null){
             if (table1[i].key.equals(key)){
                 table1[i] = null;
             }
          }
          if (table2[i] != null){
             if (table2[i].key.equals(key)) {
                 table2[i] = null;
             }
          }
        }
    }

    public V get(K key) throws ItemNotFoundException
    {
        boolean found = false;
        V value = null;
        for (int i = 0; i < table1.length; i++) {
          if (table1[i] != null){
        	   if (table1[i].key.equals(key)){
        		     found = true;
        		     value = table1[i].value;
        	   }
          }
          if (table2[i] != null){
        	   if (table2[i].key.equals(key)) {
        		     found = true;
        		     value = table2[i].value;
        	   }
          }
        }
    	if (found) return value;
    	else throw new ItemNotFoundException();
    }

    public void clear()
    {
        int len = table1.length;
    	  table1 = (KVPair<K, V>[])new KVPair[len];
        table2 = (KVPair<K, V>[])new KVPair[len];
    }

    public int size()
    {
        int count = 0;
        for (int i = 0; i < table1.length; i++) {
        	if (table1[i] != null) count++;
        	if (table2[i] != null) count++;
        }
    	  return count;
    }

    public int length()
    {
        return table1.length;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }
    public void insert(K insert_key, V insert_value, KVPair[] table) {
      if (table == table1) {
        int index = hash1.hash(insert_key, table1.length);
        if (table1[index] == null) {
          table1[index] = new KVPair(insert_key, insert_value);
        }

        else {
          if (insert_key.equals(table1[index].key)){
            table1[index].value = insert_value;
          }
          else{
            K new_key = table1[index].key;
            V new_value = table1[index].value;
            table1[index] = new KVPair(insert_key, insert_value);
            insert(new_key, new_value, table2);
          }
        }
      }
      else {
        int index = hash2.hash(insert_key, table2.length);
        if (table2[index] == null) {
          table2[index] = new KVPair(insert_key, insert_value);
        }

        else {
          K new_key = table2[index].key;
          V new_value = table2[index].value;
          table2[index] = new KVPair(insert_key, insert_value);
          insert(new_key, new_value, table1);
        }
      }
    }

    public void resize() {
      double load_factor = (double)size() / (2 * length());
      if (load_factor < threshold) {
          return;
      }
    	int length = alloc.expandFrom(length());
		  KVPair<K, V>[] table3 = (KVPair<K, V>[])new KVPair[length];
		  KVPair<K, V>[] table4 = (KVPair<K, V>[])new KVPair[length];
		  System.arraycopy(table1, 0, table3, 0, length());
      System.arraycopy(table2, 0, table4, 0, length());
		  table1 = table3;
		  table2 = table4;
      resize();
    }
}
