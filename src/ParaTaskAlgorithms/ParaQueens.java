package ParaTaskAlgorithms;//####[1]####
//####[1]####
import static java.lang.Math.*;//####[3]####
import java.util.concurrent.atomic.AtomicInteger;//####[4]####
//####[4]####
//-- ParaTask related imports//####[4]####
import pt.runtime.*;//####[4]####
import java.util.concurrent.ExecutionException;//####[4]####
import java.util.concurrent.locks.*;//####[4]####
import java.lang.reflect.*;//####[4]####
import pt.runtime.GuiThread;//####[4]####
import java.util.concurrent.BlockingQueue;//####[4]####
import java.util.ArrayList;//####[4]####
import java.util.List;//####[4]####
//####[4]####
public class ParaQueens {//####[6]####
    static{ParaTask.init();}//####[6]####
    /*  ParaTask helper method to access private/protected slots *///####[6]####
    public void __pt__accessPrivateSlot(Method m, Object instance, TaskID arg, Object interResult ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {//####[6]####
        if (m.getParameterTypes().length == 0)//####[6]####
            m.invoke(instance);//####[6]####
        else if ((m.getParameterTypes().length == 1))//####[6]####
            m.invoke(instance, arg);//####[6]####
        else //####[6]####
            m.invoke(instance, arg, interResult);//####[6]####
    }//####[6]####
//####[8]####
    public static void main(String[] args) {//####[8]####
        long n = 9;//####[9]####
        long boundary = (long) pow(n, n);//####[10]####
        long numThreads = 2;//####[12]####
        long chunk = boundary / numThreads;//####[13]####
        long remainder = boundary % numThreads;//####[14]####
        AtomicInteger counter = new AtomicInteger(0);//####[15]####
        TaskIDGroup g = new TaskIDGroup((int) numThreads);//####[17]####
        long startTime = System.currentTimeMillis();//####[18]####
        for (int i = 0; i < numThreads; i++) //####[21]####
        {//####[21]####
            g.add(checkArrangementsInPara(i * chunk, ((i + 1) * chunk - 1), n, counter));//####[22]####
        }//####[23]####
        if (remainder != 0) //####[25]####
        {//####[25]####
            g.add(checkArrangementsInPara((boundary - remainder), (boundary - 1), n, counter));//####[26]####
        }//####[27]####
        try {//####[29]####
            g.waitTillFinished();//####[30]####
        } catch (Exception e) {//####[31]####
            e.printStackTrace();//####[32]####
        }//####[33]####
        long totalTime = System.currentTimeMillis() - startTime;//####[36]####
        System.out.println("\nTime: " + totalTime + " milliseconds");//####[37]####
        System.out.println("Number of solutions for N = " + n + ": " + counter.get());//####[38]####
    }//####[40]####
//####[43]####
    public static void checkArrangement(String s, AtomicInteger counter, long n) {//####[43]####
        for (int i = 0; i < s.length(); i++) //####[45]####
        {//####[45]####
            char c = s.charAt(i);//####[46]####
            for (int j = 0; j < s.length(); j++) //####[47]####
            {//####[47]####
                if ((j != i) && ((c == s.charAt(j)) || (abs(i - j) == abs(Character.getNumericValue(c) - Character.getNumericValue(s.charAt(j)))))) //####[48]####
                {//####[49]####
                    return;//####[51]####
                }//####[52]####
            }//####[53]####
        }//####[54]####
        if (s.length() == (int) n || (s.length() == (int) (n - 1) && s.indexOf('0') == -1)) //####[56]####
        {//####[56]####
            System.out.println("Thread " + Thread.currentThread().getId() + " found solution: " + s);//####[57]####
            counter.incrementAndGet();//####[58]####
        }//####[59]####
    }//####[61]####
//####[65]####
    private static volatile Method __pt__checkArrangementsInPara_long_long_long_AtomicInteger_method = null;//####[65]####
    private synchronized static void __pt__checkArrangementsInPara_long_long_long_AtomicInteger_ensureMethodVarSet() {//####[65]####
        if (__pt__checkArrangementsInPara_long_long_long_AtomicInteger_method == null) {//####[65]####
            try {//####[65]####
                __pt__checkArrangementsInPara_long_long_long_AtomicInteger_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__checkArrangementsInPara", new Class[] {//####[65]####
                    long.class, long.class, long.class, AtomicInteger.class//####[65]####
                });//####[65]####
            } catch (Exception e) {//####[65]####
                e.printStackTrace();//####[65]####
            }//####[65]####
        }//####[65]####
    }//####[65]####
    public static TaskID<Void> checkArrangementsInPara(Object chunkStart, Object chunkStop, Object n, Object counter) {//####[65]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[65]####
        return checkArrangementsInPara(chunkStart, chunkStop, n, counter, new TaskInfo());//####[65]####
    }//####[65]####
    public static TaskID<Void> checkArrangementsInPara(Object chunkStart, Object chunkStop, Object n, Object counter, TaskInfo taskinfo) {//####[65]####
        // ensure Method variable is set//####[65]####
        if (__pt__checkArrangementsInPara_long_long_long_AtomicInteger_method == null) {//####[65]####
            __pt__checkArrangementsInPara_long_long_long_AtomicInteger_ensureMethodVarSet();//####[65]####
        }//####[65]####
        List<Integer> __pt__taskIdIndexList = new ArrayList<Integer>();//####[65]####
        List<Integer> __pt__queueIndexList = new ArrayList<Integer>();//####[65]####
        if (chunkStart instanceof BlockingQueue) {//####[65]####
            __pt__queueIndexList.add(0);//####[65]####
        }//####[65]####
        if (chunkStart instanceof TaskID) {//####[65]####
            taskinfo.addDependsOn((TaskID)chunkStart);//####[65]####
            __pt__taskIdIndexList.add(0);//####[65]####
        }//####[65]####
        if (chunkStop instanceof BlockingQueue) {//####[65]####
            __pt__queueIndexList.add(1);//####[65]####
        }//####[65]####
        if (chunkStop instanceof TaskID) {//####[65]####
            taskinfo.addDependsOn((TaskID)chunkStop);//####[65]####
            __pt__taskIdIndexList.add(1);//####[65]####
        }//####[65]####
        if (n instanceof BlockingQueue) {//####[65]####
            __pt__queueIndexList.add(2);//####[65]####
        }//####[65]####
        if (n instanceof TaskID) {//####[65]####
            taskinfo.addDependsOn((TaskID)n);//####[65]####
            __pt__taskIdIndexList.add(2);//####[65]####
        }//####[65]####
        if (counter instanceof BlockingQueue) {//####[65]####
            __pt__queueIndexList.add(3);//####[65]####
        }//####[65]####
        if (counter instanceof TaskID) {//####[65]####
            taskinfo.addDependsOn((TaskID)counter);//####[65]####
            __pt__taskIdIndexList.add(3);//####[65]####
        }//####[65]####
        int[] __pt__queueIndexArray = new int[__pt__queueIndexList.size()];//####[65]####
        for (int __pt__i = 0; __pt__i < __pt__queueIndexArray.length; __pt__i++) {//####[65]####
            __pt__queueIndexArray[__pt__i] = __pt__queueIndexList.get(__pt__i);//####[65]####
        }//####[65]####
        taskinfo.setQueueArgIndexes(__pt__queueIndexArray);//####[65]####
        if (__pt__queueIndexArray.length > 0) {//####[65]####
            taskinfo.setIsPipeline(true);//####[65]####
        }//####[65]####
        int[] __pt__taskIdIndexArray = new int[__pt__taskIdIndexList.size()];//####[65]####
        for (int __pt__i = 0; __pt__i < __pt__taskIdIndexArray.length; __pt__i++) {//####[65]####
            __pt__taskIdIndexArray[__pt__i] = __pt__taskIdIndexList.get(__pt__i);//####[65]####
        }//####[65]####
        taskinfo.setTaskIdArgIndexes(__pt__taskIdIndexArray);//####[65]####
        taskinfo.setParameters(chunkStart, chunkStop, n, counter);//####[65]####
        taskinfo.setMethod(__pt__checkArrangementsInPara_long_long_long_AtomicInteger_method);//####[65]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[65]####
    }//####[65]####
    public static void __pt__checkArrangementsInPara(long chunkStart, long chunkStop, long n, AtomicInteger counter) {//####[65]####
        for (long i = chunkStart; i < chunkStop; i++) //####[67]####
        {//####[67]####
            String temp = Long.toString(i, (int) n);//####[68]####
            if (temp.length() != (int) n) //####[69]####
            {//####[69]####
                while (temp.length() != (int) n) //####[70]####
                {//####[70]####
                    temp = "0" + temp;//####[71]####
                }//####[72]####
            }//####[73]####
            checkArrangement(temp, counter, n);//####[75]####
        }//####[76]####
        long currentThreadID = Thread.currentThread().getId();//####[84]####
        System.out.println("-- Thread " + currentThreadID + " finished (" + chunkStart + " - " + chunkStop + ")");//####[85]####
    }//####[88]####
//####[88]####
}//####[88]####
