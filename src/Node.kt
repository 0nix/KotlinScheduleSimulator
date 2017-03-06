/**
 * Created by 0nix on 3/5/2017.
 */
package Simulator;
data class Node (val arrival:Int, val priority:Int, var time:Int, var exit:Int, var next:Node? = null)