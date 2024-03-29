/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        // Your code goes here
        CharData addchar = new CharData(chr);
        Node newNode = new Node(addchar, first);
        this.first = newNode; //take the new node of chardata element and make it first
        size++; //because we add one

    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
        // Your code goes here
        if (size == 0)
        {   
            String output = "()";
            return output;
        }
        String resultS = "(";
        Node current = first;
        while ( current != null)
        {
            resultS += current.toString() + " ";
            current = current.next;
        }
        resultS = resultS.substring(0, resultS.length()-1); //remove the last unnecessary " ";
        return resultS + ")";
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        // Your code goes here
        int index = 0;
        Node currect = first;
        while (currect != null)
        {
            if(currect.cp.chr == chr) //check if they have the same char
            {
                return index;
            }
            currect = currect.next;
            index++;
        }
        return -1;
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
        // Your code goes here
        if (first == null) //so the list is empty so we add the char at the begining
        {
            addFirst(chr);
            return;
        }
        Node currect = first;
        if (indexOf(chr) == -1) //the char is not exist in the list so we need to add
        {
            addFirst(chr);
            return;
        }
        if(indexOf(chr) != -1) //the char is exist so we need to add 1 to the char count in "CharData"
        {
            for( int i =0; i< indexOf(chr); i++) //move until we get to the char we want
            {
                currect=currect.next;
            }
            currect.cp.count++; //add one to the count char in "CharData"
        }
    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        // Your code goes here
        if (indexOf(chr) == -1)
        {
            return false;
        }
        Node prev = null;
        Node currect = first;
        while(currect != null && currect.cp.chr != chr)
        {
            prev = currect;
            currect= currect.next;
        }
        if (prev == null)
        {
            return false;
        }
        prev.next = currect.next; //so we remove the currect element
        size--;
        return true;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        // Your code goes here
        if ( index >= size || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        Node currect = first;
        for ( int i = 0; i <index ; i++)
        {
            currect= currect.next;
        }
        return currect.cp;
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first;
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }
}
