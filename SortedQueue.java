//this is a sorted queue
public class SortedQueue {

    private QueueNode front;
    private QueueNode back;
    
    static class QueueNode {
        
        private OS.Process process;
        private QueueNode next;

        public QueueNode (OS.Process p) {
            process = p;
            next = null;
        }

        public OS.Process getProcess() {
            return process;
        }

        public void setProcess(OS.Process process) {
            this.process = process;
        }

        public QueueNode getNext() {
            return next;
        }

        public void setNext(QueueNode next) {
            this.next = next;
        }
    }
    
    public SortedQueue() {
        front = null;
        back = null;
    }

    public void EnQueue(OS.Process p) {
        QueueNode temp = new QueueNode(p); //new node
        if (front == null) {
            front = temp;
            back = front;
        } else {
            QueueNode curr = front;
            while (curr!=null) {
                //if new node is == current node
                while (curr != null && curr.getProcess().getExecutionTime() == p.getExecutionTime()) {
                    //new.AT < curr.AT & curr == front -- insert at front
                    if (p.getArrivalTime() < curr.getProcess().getArrivalTime() && front == curr) {
                        temp.next = front;
                        front = temp;
                        return; 
                    }
                    //new.AT < curr.AT & curr != front -- insert at front
                    if (p.getArrivalTime() < curr.getProcess().getArrivalTime() && front != curr) {
                        QueueNode prev = front;
                        while (prev.next != curr) {
                            prev = prev.next;
                        }
                        temp.next = prev.next;
                        prev.next = temp;
                        return;
                    }
                    //new.AT > curr.AT and back == curr -- insert at back
                    if (p.getArrivalTime() > curr.getProcess().getArrivalTime() && back == curr) {
                        curr.next = temp;
                        back = temp;
                        return;
                    }
                    //new.AT > curr.AT && p.AT < curr.next.AT && curr.next.RT == p.RT -- insert in between
                    if (p.getArrivalTime() > curr.getProcess().getArrivalTime() && p.getArrivalTime() < curr.next.getProcess().getArrivalTime() && curr.next.getProcess().getExecutionTime() == p.getExecutionTime()) {
                        temp.next = curr.next;
                        curr.next = temp;
                        return;
                    }
                    //new.AT > curr.AT && curr.next.RT != p.RT
                    if (p.getArrivalTime() > curr.getProcess().getArrivalTime() && curr.next.getProcess().getExecutionTime() != p.getExecutionTime()) {
                        temp.next = curr.next;
                        curr.next = temp;
                        return;
                    }
                    curr = curr.next;
                }
                // if new node RT < curr RT and curr is at front -- insert at front
                if (p.getExecutionTime() < curr.getProcess().getExecutionTime() && front == curr) {
                    temp.next = front;
                    front = temp;
                    break;
                    
                }
                // if new node RT is > than curr RT and curr is at back  -- insert at back
                if (p.getExecutionTime() > curr.getProcess().getExecutionTime() && back == curr) {
                    curr.next = temp;
                    back = temp;
                    break;
                }
                // if new node RT > curr RT && new node < curr.next RT the move forward  -- insert in between
                if (p.getExecutionTime() > curr.getProcess().getExecutionTime() && p.getExecutionTime() < curr.next.getProcess().getExecutionTime()) {
                    temp.next = curr.next;
                    curr.next = temp;
                    break;
                }
                curr = curr.next;
            }
        }
    }
    /*
    1- at the begining
        i- if RT is equal and AT is lower than curr place it at front
        ii-if RT is equal and AT is higher than curr place it at behind */
    //front->|1|->|2|->|3|<-back
    public OS.Process Dequeue() {
        QueueNode temp = front;
        front = front.next;
        temp.next = null;
        return temp.process;
    }

    public boolean isEmpty() {
        if (front == null && back == null) {
            return true;
        } else {
            return false;
        }
    }

    public OS.Process getProcessBack() {
        if (back == null) {
            return null;
        }
        return back.process;
    }

    public OS.Process getProcessFront() {
        if (front == null) {
            return null;
        }
        return front.process;
    }

    public QueueNode getFront() {
        return front;
    }

    public void setFront(QueueNode front) {
        this.front = front;
    }

    public QueueNode getBack() {
        return back;
    }

    public void setBack(QueueNode back) {
        this.back = back;
    }

    public void printQueue() {
        QueueNode temp = front;
        while (temp!=null) {
            System.out.println(temp.getProcess().getId() + " arrives at " + temp.getProcess().getArrivalTime() + " with exec time " + temp.getProcess().getExecutionTime());
            temp = temp.next;
        }
    }

    
}
