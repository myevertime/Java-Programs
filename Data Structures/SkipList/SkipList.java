import java.lang.Comparable;

public class SkipList<K extends Comparable<K>, V>
{
    private Node head;
    private Node tail;

    private int size;

    private CoinToss coin;

    public int height;

    public SkipList(
        CoinToss coin
    )
    {
    	  Node node1, node2;
        node1 = new Node(null, null); //left
        node2 = new Node(null, null); //right
        node1.right = node2;
        node2.left = node1;
        size = 0;
        height = 1;
        head = node1;
        tail = node2;
        this.coin = coin;
    }

    public void insert(K key, V value)
    {
    	Node node = find(key);

    	if (key.equals(node.key)) {
    		node.value = value;
    		return;
    	}
    	Node new_node = new Node(key, value);
    	new_node.left = node;
    	new_node.right = node.right;
    	node.right.left = new_node;
    	node.right = new_node;
    	int i = 0;
      while(coin.toss() != false) {
        i += 1;
      }
      for (int j = 0; j < i; j++) {
        if (i >= height) {
          addList();
        }
        while (node.up == null) {
        	node = node.left;
        }
        node = node.up;

        Node new_node2 = new Node(key, value);
        new_node2.left = node;
        new_node2.right = node.right;
        new_node2.down = new_node;
        node.right.left = new_node2;
        node.right = new_node2;
        new_node.up = new_node2;

        new_node = new_node2;
        }
      size += 1;
    }

    public void remove(K key)
    {
        Node node = find(key);
        if (key.equals(node.key)) {
          node.right.left = node.left;
          node.left.right = node.right;
          while(node.up != null){
            node = node.up;
            node.right.left = node.left;
            node.left.right = node.right;
          }
        }
        size -= 1;
    }

    public V get(K key) throws ItemNotFoundException
    {
      Node node = find(key);
    	if (key.equals(node.key)) {
    		return (V) node.value;
    	}
    	else throw new ItemNotFoundException();
    }

    public KVPair<K, V>[] items()
    {
      KVPair<K, V>[] table = (KVPair<K, V>[])new KVPair[size()];
    	if (size() == 0) {
    		return table;
    	}
    	else {
    		Node pointer = head;
    		while (pointer.down != null) {
    			pointer = pointer.down;
    		}
    		int i = 0;
    		while (pointer.right.key != null) {
    			table[i] = new KVPair(pointer.right.key, pointer.right.value);
    			pointer = pointer.right;
          i += 1;
    		}
        return table;
    	}

    }

    public void clear()
    {
        Node node1, node2;
        node1 = new Node(null, null);
        node2 = new Node(null, null);
        node1.right = node2;
        node2.left = node1;
        size = 0;
        height = 0;
        head = node1;
        tail = node2;
    }

    public int size()
    {
       return size;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public Node find(K key) {
    	Node node = head;
    	while(true) {
    		while((node.right.key != null) && (((K)node.right.key).compareTo(key)) <= 0) {
    			node = node.right;
    		}
    		if (node.down != null) {
    			node = node.down;
    		}
    		else {
    			break;
    		}
    	}
    	return node;
    }
    public void addList() {
    	Node node1, node2;
    	node1 = new Node(null, null);
    	node2 = new Node(null, null);

    	node1.right = node2;
    	node1.down = head;
    	node2.left = node1;
    	node2.down = tail;
    	head.up = node1;
    	tail.up = node2;

    	head = node1;
    	tail = node2;

    	height += 1;
    }
}

class Node<K,V>{
  public K key;
  public V value;

  public Node<K,V> up;
  public Node<K,V> down;
  public Node<K,V> left;
  public Node<K,V> right;

  Node(K key, V value){
    this.key = key;
    this.value = value;
  }
}
