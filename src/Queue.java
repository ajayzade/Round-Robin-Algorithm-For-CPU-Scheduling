public class Queue {
    String[] readyQueue = new String[100];
    int front = 0;
    int rear = 0;
    String s;
    int c = 0;

    public void enqueue(String s){
        readyQueue[rear++] = s;
    }

    public String dequeue(){
        s = readyQueue[front];
        for(int i = 0; i < rear - 1; i++){
            readyQueue[i] = readyQueue[i + 1];
        }
        rear--;
        return s;
    }

    public boolean isEmpty(){
        for(int j = front; j <= rear; j++){
            c = c + 1;
        }
        if(c == 0) {
            return false;
        }
        else{
            c = 0;
            return true;
        }
    }
}
