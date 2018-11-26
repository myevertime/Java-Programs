public class Sorter
{

    public Sorter()
    {
    }

    public int[] ascending(int[] input)
    {
        int[] lst = input;
        int length = lst.length;
	      for (int i = 0; i < length; i++) {
	    	  for (int j = 0; j < length; j++){
	    		  if (lst[i] < lst[j]) {
	    			  int n = lst[i];
	            lst[i] = lst[j];
              lst[j] = n;
            }
          }
        }
        return lst;
    }

    public int[] descending(int[] input)
    {
        int[] lst = input;
		    int length = lst.length;
	      for (int i = 0; i < length; i++) {
	    	  for (int j = 0; j < length; j++){
	    		  if (lst[i] > lst[j]) {
	    			  int n = lst[i];
	            lst[i] = lst[j];
              lst[j] = n;
            }
          }
        }
        return lst;
    }
}
