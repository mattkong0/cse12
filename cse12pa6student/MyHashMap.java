import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyHashMap<K, V> implements DefaultMap<K, V> {
	public static final double DEFAULT_LOAD_FACTOR = 0.75;
	public static final int DEFAULT_INITIAL_CAPACITY = 16;
	public static final String ILLEGAL_ARG_CAPACITY = "Initial Capacity must be non-negative";
	public static final String ILLEGAL_ARG_LOAD_FACTOR = "Load Factor must be positive";
	public static final String ILLEGAL_ARG_NULL_KEY = "Keys must be non-null";
	
	private double loadFactor;
	private int capacity;
	private int size;

	// Use this instance variable for Separate Chaining conflict resolution
	private List<HashMapEntry<K, V>>[] buckets;  
	
	// Use this instance variable for Linear Probing
	private HashMapEntry<K, V>[] entries; 	

	public MyHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	/**
	 * 
	 * @param initialCapacity the initial capacity of this MyHashMap
	 * @param loadFactor the load factor for rehashing this MyHashMap
	 * @throws IllegalArgumentException if initialCapacity is negative or loadFactor not
	 * positive
	 */
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity, double loadFactor) throws IllegalArgumentException {
		// TODO Finish initializing instance fields
		if (loadFactor < 0 || loadFactor == 0) {
			throw new IllegalArgumentException(ILLEGAL_ARG_LOAD_FACTOR);
		}
		if (initialCapacity < 0) {
			throw new IllegalArgumentException(ILLEGAL_ARG_CAPACITY);
		}
		this.capacity = initialCapacity;
		this.loadFactor = loadFactor;
		this.size = 0;

		// if you use Separate Chaining
		buckets = (List<HashMapEntry<K, V>>[]) new List<?>[capacity];

		// if you use Linear Probing
		entries = (HashMapEntry<K, V>[]) new HashMapEntry<?, ?>[initialCapacity];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		// can also use key.hashCode() assuming key is not null
		int keyHash = Objects.hashCode(key);
		keyHash = Math.abs(keyHash);
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		int index = keyHash % this.buckets.length;
		if (this.buckets[index] == null) {
			this.buckets[index] = new ArrayList<>();
		}
		for (int i = 0; i < this.buckets[index].size(); i += 1) {
			HashMapEntry entry = (MyHashMap.HashMapEntry) this.buckets[index].get(i);
			if (!this.buckets[index].get(i).getKey().equals(key)) {
				this.buckets[index].add(entry);
				return true;
			}
			else if (this.buckets[index].get(i).getValue() == null) {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		int keyHash = Objects.hashCode(key);
		keyHash = Math.abs(keyHash);
		int index = keyHash % this.buckets.length;
		if (this.buckets[index] == null) {
			return false;
		}
		for (int i = 0; i < this.buckets[index].size(); i += 1) {
			if (this.buckets[index].get(i).getKey().equals(key)) {
				this.buckets[index].get(i).setValue(newValue);
				this.size += 1;
				return true;
			}
		}
 		return false;
	}

	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		int keyHash = Objects.hashCode(key);
		keyHash = Math.abs(keyHash);
		int index = keyHash % this.buckets.length;
		if (this.buckets[index] == null) {
			return false;
		}
		for (int i = 0; i < this.buckets[index].size(); i++) {
			if (this.buckets[index].get(i).getKey().equals(key)) {
				this.buckets[index].remove(key);
				this.size -= 1;
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		else if (loadFactor() > this.loadFactor) {
			expandCapacity();
		}
		int keyHash = Objects.hashCode(key);
		keyHash = Math.abs(keyHash);
		int index = keyHash % this.buckets.length;
		if (this.buckets[index] == null) {
			this.buckets[index] = new ArrayList<HashMapEntry<K, V>>();
			this.buckets[index].add(new HashMapEntry(key, value));
		}
		else {
			for (HashMapEntry entry: this.buckets[index]) {
				if (entry.key.equals(key)) {
					if (entry.value == null) {
						throw new IllegalArgumentException("Null Value");
					}
					entry.setValue(value);
					return;
				}
			}
			this.buckets[index].add(new HashMapEntry(key, value));
		}
		this.size += 1;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public V get(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		int keyHash = Objects.hashCode(key);
		keyHash = Math.abs(keyHash);
		int index = keyHash % this.buckets.length;
		if (this.buckets[index] == null) {
			return null;
		}
		else {
			for (HashMapEntry entry: this.buckets[index]) {
				if (entry.key.equals(key)) {
					if (entry.getValue() == null) {
						return null;
					}
					return (V) entry.getValue();
				}
			}
			return null;
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (this.size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		int keyHash = Objects.hashCode(key);
		keyHash = Math.abs(keyHash);
		int index = keyHash % this.buckets.length;
		if (this.buckets[index] == null) {
			return false;
		}
		else if (this.buckets[index] != null) {
			for (int i = 0; i < this.buckets[index].size(); i++) {
				if (this.buckets[index].get(i).getKey().equals(key)) {
					return true;
				}
			}
		}
 		return false;
	}

	@Override
	public List<K> keys() {
		// TODO Auto-generated method stub
		List<K> keys = new ArrayList<K>();
		if (this.size == 0) {
			List<K> empty = new ArrayList<K>();
			return empty;
		}
		for (int i = 0; i < this.capacity; i += 1) {
			if (this.buckets[i] != null) {
				for (int j = 0; j < this.buckets[i].size(); j += 1) {
					K currKey = this.buckets[i].get(j).getKey(); 
					keys.add(currKey);
				}
			}
		}
		return keys;
	}
	
	private static class HashMapEntry<K, V> implements DefaultMap.Entry<K, V> {
		
		K key;
		V value;
		
		private HashMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
		
		@Override
		public void setValue(V value) {
			this.value = value;
		}
	}
	
	// helper method for rehashing
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void expandCapacity() {
		List<MyHashMap.HashMapEntry<K, V>>[] newEntries = (List<MyHashMap.HashMapEntry<K, V>>[])
										                  (new List[this.buckets.length * 2]);
		List<MyHashMap.HashMapEntry<K, V>>[] oldEntries = (List<MyHashMap.HashMapEntry<K, V>>[]) 
														  this.buckets;
		this.buckets = (List<MyHashMap.HashMapEntry<K, V>>[]) newEntries;
		this.size = 0;
		for (int i = 0; i < oldEntries.length; i++) {
			if (oldEntries[i] == null) {
				continue;
			}
			for (HashMapEntry e: oldEntries[i]) {
				this.set((K)e.key, (V)e.value);
			}
		}
	}
	
	// helper method to get loadFactor for set(K key, V value)
	public double loadFactor() {
		return (double) (this.size / this.buckets.length);
	}
}
