import sun.rmi.runtime.Log;

import java.util.Scanner;

public class Processes {
    static int p,quantum;
    public static void main(String[] args){
        int i, time = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Round Robin Algorithm for CPU Scheduling\n");
        System.out.println("Enter the number of processes in CPU");
        p = sc.nextInt();

        int[] bt = new int[p];
        int[] ct = new int[p];
        String[] pro = new String[] {
            "P1", "P2", "P3", "P4"
        };
        int[] tat = new int[p];
        int[] wt = new int[p];
        int[] at = new int[p];
        int[] temp = new int[p];

        //Get Burst time for processes
        System.out.println("Enter burst time for all processes");
        for(i = 0; i < p ; i++){
            bt[i] = sc.nextInt();
        }

        //Get Arrival time for processes
        System.out.println("Enter Arrival time for all processes");
        for(i = 0; i < p ; i++){
            at[i] = sc.nextInt();
        }

        //Get Quantam time for each processes
        System.out.println("Enter Quantam or Time of processes allowed by CPU");
        quantum = sc.nextInt();

        System.out.println();

        //Show the input data
        System.out.println("Input data for CPU is : \n");
        System.out.print("Process \t A.T \t B.T \n");
        for(i = 0; i < p ; i++){
            System.out.print(pro[i] + "\t\t\t" + at[i] + "\t\t  " + bt[i] + "\n");
        }
        System.out.println();

        Queue q = new Queue();

        for(i = 0; i < p ; i++){
            q.enqueue(pro[i]);
            //Copying Burst time into another array
            temp[i] = bt[i];
        }

        String str;

        //Round Robins Algorithm :
        i = 0;
        while(i < p){
            if(bt[i] > 0){
                str = q.dequeue();
                bt[i] = bt[i] - quantum;
                System.out.print(str + " | ");
                if(bt[i] > 0){
                    time = time + quantum;
                    q.enqueue(str);
                }else{
                    time = time + (quantum + bt[i]);
                    ct[i] = time;
                    tat[i] = ct[i] - at[i];
                    wt[i] = tat[i] - temp[i];
                }
            }
            i++;
            if(i == p && q.isEmpty()){
                i = 0;
            }
            if(q.rear == 0){
                break;
            }
        }

        float awt = 0,atat = 0;
        int sum1 = 0,sum2 = 0;

        //Calculating the awt and atat from data
        for(i = 0; i < p; i++){
            sum1 = wt[i] + sum1;
            sum2 = tat[i] + sum2;
        }

        awt = (float) (sum1 / p);
        atat = (float) (sum2 / p);

        //Displaying Complete Output Data
        System.out.print("\n\nProcesses \t AT \t BT \t CT \t TAT \t WT \n");
        System.out.println();
        for(i = 0; i < p; i++){
            System.out.print(pro[i] + "\t\t\t" + at[i] + "\t\t" + temp[i] + "\t\t" + ct[i] + "\t\t" + tat[i] + "\t\t" + wt[i] + "\n");
        }
        System.out.println();

        System.out.println("Average Waiting Time : " + awt);
        System.out.println("Average Turning Arrival Time : " + atat);
        System.out.println();

    }
}
