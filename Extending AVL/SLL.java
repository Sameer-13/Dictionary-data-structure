//**************************  SLL.java  *********************************
//           a generic singly linked list class 

public class SLL<T> {

    private class SLLNode<T> {
      private T info;
      private SLLNode<T> next;
      public SLLNode() {
         this(null,null);
      }
      public SLLNode(T el) {
        this(el,null);
      }
      public SLLNode(T el, SLLNode<T> ptr) {
         info = el; next = ptr;
      }
    } 

    protected SLLNode<T> head, tail;
    public SLL() {
        head = tail = null;
    }
    public boolean isEmpty() {
        return head == null;
    }
    public void addToHead(T el) {
        head = new SLLNode<T>(el,head);
        if (tail == null)
            tail = head;
    }
    public void addToTail(T el) {
        if (!isEmpty()) {
            tail.next = new SLLNode<T>(el);
            tail = tail.next;
        }
        else head = tail = new SLLNode<T>(el);
    }
    public T deleteFromHead() { // delete the head and return its info; 
        if (isEmpty()) 
             return null;
        T el = head.info;
        if (head == tail)       // if only one node on the list;
             head = tail = null;
        else head = head.next;
        return el;
    }
    public T deleteFromTail() { // delete the tail and return its info;
        if (isEmpty()) 
             return null;
        T el = tail.info;
        if (head == tail)       // if only one node in the list;
             head = tail = null;
        else {                  // if more than one node in the list,
             SLLNode<T> tmp;    // find the predecessor of tail;
             for (tmp = head; tmp.next != tail; tmp = tmp.next);
             tail = tmp;        // the predecessor of tail becomes tail;
             tail.next = null;
        }
        return el;
    }
    public void delete(T el) {  // delete the node with an element el;
        if (!isEmpty())
            if (head == tail && el.equals(head.info)) // if only one
                 head = tail = null;       // node on the list;
            else if (el.equals(head.info)) // if more than one node on the list;
                 head = head.next;    // and el is in the head node;
            else {                    // if more than one node in the list
                 SLLNode<T> pred, tmp;// and el is in a nonhead node;
                 for (pred = head, tmp = head.next;  
                      tmp != null && !tmp.info.equals(el); 
                      pred = pred.next, tmp = tmp.next);
                 if (tmp != null) {   // if el was found;
                     pred.next = tmp.next;
                     if (tmp == tail) // if el is in the last node;
                        tail = pred;
                 }
            }
    }
    
    @Override
    public String toString() {
       if(head == null)
          return "";
       String str = "";   
       SLLNode<T> tmp = head;
       while(tmp != null){
         str += tmp.info;
         if(tmp.next != null)
            str += ",";
         tmp = tmp.next;
       }
       return str;             
    }
    
    public boolean contains(T el) {
        if(head == null)
            return false;
        SLLNode<T> tmp = head;
        while(tmp != null){
           if(tmp.info.equals(el))
              return true;
           tmp = tmp.next;
        }
        return false;
    }
    
    public int size(){
        if(head == null)
          return 0;
          
        int count = 0;
        SLLNode<T> p = head;
        while(p != null) {
           count++;
           p = p.next;
        }
           
        return count;
    }
    
    //  Please write the methods of Task02 here:
    public void insertBefore(int index, T newElem) throws EmptyListexception, IndexNotFoundException{
        if(isEmpty())
            throw(new EmptyListexception("list is empty!"));
        if(index < 0 || index > size()-1)
            throw(new IndexNotFoundException("Index not found!"));

        if(index == 0){
            head = new SLLNode<T>(newElem,head);
            if (tail == null)
                tail = head;
        }
        else{
        SLLNode<T> nodeBefore = getNode(index-1);
        SLLNode<T> nodeAfter = nodeBefore.next;
        nodeBefore.next = new SLLNode(newElem, nodeAfter);
        }
    }
    
    // Helper method
    public SLLNode<T> getNode(int index) throws EmptyListexception{

        if(isEmpty())
            throw(new EmptyListexception("list is empty!"));

        SLLNode<T> pointer = head;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
       
        return pointer;

    }

    public T delete(int index) throws EmptyListexception, IndexNotFoundException{
        if(isEmpty())
            throw(new EmptyListexception("list is empty!"));
        if(index < 1 || index > size())
            throw(new IndexNotFoundException("Index not found!"));

        if(index == 0){
            T el = head.info;
            if (head == tail)       // if only one node on the list;
                head = tail = null;
            else head = head.next;
            return el;
        }

        SLLNode<T> thisNode = getNode(index);
        SLLNode<T> nodeBefore = getNode(index- 1);
        SLLNode<T> nodeAfter = getNode(index + 1);
        nodeBefore.next = nodeAfter;

        T deletedInformation = thisNode.info;
        
        return deletedInformation;
    }

    public void insertAfterSecondOccurrence(T e1, T e2) throws EmptyListexception, ElementNotFoundException{
        if(isEmpty())
            throw(new EmptyListexception("list is empty!"));
        if(!isExist(e2))
            throw(new ElementNotFoundException("Element does not exist"));
            SLLNode<T> insertedNode = head;
            int count = 0;
            while (insertedNode != null) {
                if (insertedNode.info.equals(e2))
                    count++;
                if (count == 2)
                    break;
                insertedNode = insertedNode.next;
            }
            if (count != 2)
            throw new RuntimeException("There is no second occurrence of " + e2);
        insertedNode.next = new SLLNode<T>(e1, insertedNode.next);
    }

    // helper method to check if the element exist
    public boolean isExist(T element){
        if(head == null)
            return false;
        SLLNode<T> tmp = head;
        while(tmp != null){
           if(tmp.info.equals(element))
              return true;
           tmp = tmp.next;
        }
        return false;
    }
}


class EmptyListexception extends Exception{
    public EmptyListexception(String massage){
        super(massage);
    }
}

class ElementNotFoundException extends Exception{
    public ElementNotFoundException(String massage){
        super(massage);
    }
}

class IndexNotFoundException extends Exception{
    public IndexNotFoundException(String massage){
        super(massage);
    }
}
