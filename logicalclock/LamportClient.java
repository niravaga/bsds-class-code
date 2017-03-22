/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package logicalclock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Ian Gortan
 */
public class LamportClient implements Runnable {
    
    private MessageBuffer buffer;
    final private int NUM_EVENTS = 10; 
    private int pid;

    private Vector<Integer> clocks = new Vector<>();
    

    public LamportClient(MessageBuffer buf, int pid) {
        this.buffer = buf;
        this.pid = pid;
        clocks.setSize(3);

        clocks.set(0,0);
        clocks.set(1,0);
        clocks.set(2,0);
    }

    public void run() {
        Random random = new Random();
        //start at -1 just so we can index in to events array easily
        int clock = -1;
        clocks.set(pid, -1);
        ArrayList<String> events  = new ArrayList<String> ();         
        
        // create a message object
        Message m = new Message (pid, clocks, "init");
        m.setPid(pid);
        m.setClock(clocks) ;
        
        // add some 'reandomness' to the order at startup
        if ( (pid%2) == 0 ) {
            clock++;
            clocks.set(pid, clocks.get(pid) + 1);
            m.setClock(clocks);
            String event = "[INTERNAL] "  + "PID="  + Integer.toString(pid) + " CLOCK:" + Integer.toString(clock); 
            events.add(event);
        }
        for (int i = 0; i < NUM_EVENTS ; i++ ) {
            // let's generate an event 
            clock++;
            clocks.set(pid, clocks.get(pid) + 1);

            m.setClock(clocks);
            if ( ((i%2) == 0) ) {
                // internal event
                String event = "[INTERNAL] "  + "PID="  + Integer.toString(pid) + " CLOCK:" + vectorToString(clocks);
                events.add(event);
            } else {
                // external event
                String event = "[SEND]     " + "PID="  + Integer.toString(pid) + " CLOCK:" + vectorToString(clocks);
                events.add(event);
                m.setMessageID(event);
                buffer.put(m); // sleep for a random time to induce different orders
                try {
                      Thread.sleep(random.nextInt(2000));
                } catch (InterruptedException e) {}
            
                // get a message and set clock according to Lamport algorithm
                //System.out.println("Getting a message: " + Integer.toString(pid));
                Message recMsg = buffer.get(pid);
                 // chck it wasn't a message we sent (null returned) as we want to ignore those
                 
                if (recMsg != null) {
                    Vector<Integer> recdClocks = recMsg.getClock();

                    clocks.set(pid, clocks.get(pid) + 1);
                    // add the event ...
//                    System.out.println(recdClocks.size());
                    for (int j = 0; j < recdClocks.size(); j++) {
                        if (clocks.get(j) < recdClocks.get(j)) {
                            clocks.set(j, recdClocks.get(j));
                        }
                    }

                    event = "[RECEIVE]  " + "FROM PID="  + Integer.toString(recMsg.getPid()) + " CLOCK IN:" + (vectorToString(recMsg.getClock())) + " LOCAL CLOCK " + vectorToString(clocks);
                    events.add(event);
                }
                
                
            }
           
                
        }
        
        System.out.println("Process " + pid + " complete and ending");
        // your code
        for (String event: events) {
            System.out.println(event);
        }
            
          
     }

     private String vectorToString(Vector<Integer>  list) {
         String result = "";

         for (Integer i : list) {
             result += Integer.toString(i);
             result += ", ";
         }

         return result;
     }
 
}
    

