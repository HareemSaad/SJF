public class Queue {

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
    
    public Queue() {
        front = null;
        back = null;
    }

    public void EnQueue(OS.Process p) {
        if (front == null && back == null) {
            front = new QueueNode(p);
            back = front;
        } else {
            back.next = new QueueNode(p);
            back = back.next;
        }
    }

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
        while (front!=null) {
            System.out.println(temp.getProcess().getId());
            if (temp.next == null) {
                break;
            }
        }
    }
}
