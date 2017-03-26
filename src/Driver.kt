/**
 * Created by 0nix on 3/5/2017.
 */
package Simulator;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
class Driver {
    fun getProcFile():File? {
        val chooser = JFileChooser()
        chooser.dialogTitle = "Choose the Process Text File"
        chooser.fileFilter = FileNameExtensionFilter("Text File","txt")
        val choice = chooser.showOpenDialog(null)
        if(choice != JFileChooser.APPROVE_OPTION) return null
        return chooser.getSelectedFile()
    }
    fun procArrayBuilder(f:File):Array<Node?>?{
        val bufferedReader = f.bufferedReader()
        var line = bufferedReader.readLine()
        //AT THIS POINT, LINE SHOULD CONTAIN NUMBER OF PROCESSES
        if(!matchRegex(line,"\\d+")) {
            println("No available number of processes. Exiting Now.")
            return null
        }
        val numProcs = line.toInt()
        println("Scanning for "+numProcs+" processes.")
        var procMem:Array<Node?> = kotlin.arrayOfNulls(numProcs)
        line = bufferedReader.readLine()
        var ix:Int = 0
        while ( line != null) {
            if(!matchRegex(line,"\\d+ \\d+ \\d+")) {
                println("Match for process readout unsuccessful. Exiting Now.")
                return null
            }
            //LINE CONTAINS ARRIVAL PRIORITY CPUTIME
            val processArgs:List<String> = line.split(" ")
            procMem[ix] = Node(processArgs[0].toInt(),processArgs[1].toInt(),processArgs[2].toInt(),0,null)
            ix++
            line = bufferedReader.readLine()
        }
        return procMem
    }
    fun matchRegex(subject:String,pattern:String):Boolean {
        return subject.matches(Regex(pattern));
    }
    fun main(){
        println("-- Process Simulator Start --")
        val procFile = getProcFile()
        if(procFile == null) {
            println("No file selected. Exiting now.")
            return
        }
        val pmem:Array<Node?>? = procArrayBuilder(procFile)
        var ix = 1

        for( p in pmem!!){
            System.out.printf("Inserted Process: %d [Arrival: %d] [Priority: %d] [Time: %d]\n",ix,p?.arrival,p?.priority,p?.time)
            ix++
        }
        var sim = Simulator(pmem)
        sim.start()
    }
}