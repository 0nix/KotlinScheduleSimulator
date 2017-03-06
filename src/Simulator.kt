/**
 * Created by 0nix on 3/5/2017.
 */
package Simulator;
import java.util.Timer
import java.util.TimerTask


class Simulator {
    val QUANTA = 2
    public var mainTicker:Timer? = null
    public var acc = 0
    inner class SimulatorTick:TimerTask(){
        public var tick:Int = 0
        public var ix:Int = 0
        public var q:Int = QUANTA
        var pdone:Int = 0
        public override fun run(){
            //CHECK IF WE HAVE EXHAUSTED ALL PROCESSES
            //ARRIVAL TICK
            println("tick: "+tick)
            if(numProcs == pdone) {
                var avg = (acc.toFloat() / numProcs.toFloat())
                System.out.printf("Average Turnaround was %.2f ", avg)
                println("Please press any key to continue...")
                readLine();
                mainTicker!!.cancel()
            }

            if(ix < numProcs && ProcList!![ix]!!.arrival == tick){
                queue.append(ProcList!![ix]!!)
                System.out.printf("In Process: [Arrival: %d] [Priority: %d] [Time: %d]\n",ProcList!![ix]!!.arrival,ProcList!![ix]!!.priority,ProcList!![ix]!!.time)
                ix++
            }
            //PROCESSOR TICK
            if(queue.head != null){
                (queue.head)!!.time--
                System.out.printf("Proc Process: [Arrival: %d] [Priority: %d] [Time: %d]\n",
                        (queue.head)!!.arrival,(queue.head)!!.priority,(queue.head)!!.time)

                if(queue.head!!.time == 0){
                    var out:Node = queue.pop()!!
                    out.exit = tick
                    System.out.printf("Out Process: [Arrival: %d] [Priority: %d] [Time: %d] [Out: %d] [Turnaround: %d]\n"
                            ,out.arrival,out.priority,out.time, out.exit,(tick - out.arrival))
                    acc += (tick - out.arrival)
                    pdone++
                    q = QUANTA
                }
            }
            q--
            //QUEUE TICK
            if(q <= 0){
                queue.rr()
                q = QUANTA
            }

            //DONE WITH THINGS
            tick++

        }
    }
    var ProcList:Array<Node?>? = null
    var numProcs:Int = 0
    var queue:PriorityQueue = PriorityQueue()
    constructor(pl:Array<Node?>?){
        ProcList = pl!!
        numProcs = pl!!.size
        mainTicker = Timer()
    }
    public fun start(){
        mainTicker!!.schedule(SimulatorTick(), 500,500)
    }
}