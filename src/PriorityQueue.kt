/**
 * Created by 0nix on 3/5/2017.
 */
package Simulator;
class PriorityQueue() {
    var head:Node? = null
    public fun append(n:Node){
        //IF QUEUE IS EMPTY
        if(head == null){
            head = n
            return
        }
        //AT THIS POINT, HEAD IS NOT NULL
        // IF N IS BIGGER PRIORITY THAN HEAD
        if(head!!.priority > n.priority){
            n.next = head
            head = n
            return
        }
        // IF N IS TO BE INSERTED IN THE MIDDLE OF THE QUEUE
        var p:Node? = head
        while(p!!.next != null){
            if(p.next!!.priority > n.priority){
                n.next = p.next
                p.next = n
                return
            }
            else p = p.next
        }
        //SEEING AS NO OTHER TASK OF LOWER PRIORITY EXISTS
        //INSERT AT THE END
        p.next = n
        return
    }
    public fun pop():Node? {
        val out = head;
        head = head?.next
        return out
    }
    public fun rr(){
        if(head != null && head?.next != null){
            if(head!!.priority == head!!.next!!.priority){
                val x:Node? = head!!.next!!.next
                head!!.next!!.next = head
                head = head!!.next!!
                head!!.next!!.next = x

            }
        }
    }

}