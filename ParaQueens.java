import static java.lang.Math.*;//####[2]####
import java.util.concurrent.atomic.AtomicInteger;//####[3]####
//####[3]####
//-- ParaTask related imports//####[3]####
import pt.runtime.*;//####[3]####
import java.util.concurrent.ExecutionException;//####[3]####
import java.util.concurrent.locks.*;//####[3]####
import java.lang.reflect.*;//####[3]####
import pt.runtime.GuiThread;//####[3]####
import java.util.concurrent.BlockingQueue;//####[3]####
import java.util.ArrayList;//####[3]####
import java.util.List;//####[3]####
//####[3]####
public class ParaQueens {//####[5]####
    static{ParaTask.init();}//####[5]####
    /*  ParaTask helper method to access private/protected slots *///####[5]####
    public void __pt__accessPrivateSlot(Method m, Object instance, TaskID arg, Object interResult ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {//####[5]####
        if (m.getParameterTypes().length == 0)//####[5]####
            m.invoke(instance);//####[5]####
        else if ((m.getParameterTypes().length == 1))//####[5]####
            m.invoke(instance, arg);//####[5]####
        else //####[5]####
            m.invoke(instance, arg, interResult);//####[5]####
    }//####[5]####
//####[7]####
    public static void main(String[] args) {//####[7]####
        long n = 9;//####[8]####
        long boundary = (long) pow(n, n);//####[9]####
        long numThreads = 2;//####[11]####
        long chunk = boundary / numThreads;//####[12]####
        long remainder = boundary % numThreads;//####[13]####
        AtomicInteger counter = new AtomicInteger(0);//####[14]####
        TaskIDGroup g = new TaskIDGroup((int) numThreads);//####[16]####
        long startTime = System.currentTimeMillis();//####[17]####
        for (int i = 0; i < numThreads; i++) //####[20]####
        {//####[20]####
            g.add(checkArrangementsInPara(i * chunk, ((i + 1) * chunk - 1), n, counter));//####[21]####
        }//####[22]####
        if (remainder != 0) //####[24]####
        {//####[24]####
            g.add(checkArrangementsInPara((boundary - remainder), (boundary - 1), n, counter));//####[25]####
        }//####[26]####
        try {//####[28]####
            g.waitTillFinished();//####[29]####
        } catch (Exception e) {//####[30]####
            e.printStackTrace();//####[31]####
        }//####[32]####
        long totalTime = System.currentTimeMillis() - startTime;//####[35]####
        System.out.println("\nTime: " + totalTime + " milliseconds");//####[36]####
        System.out.println("Number of solutions for N = " + n + ": " + counter.get());//####[37]####
    }//####[39]####
//####[42]####
    public static void checkArrangement(String s, AtomicInteger counter, long n) {//####[42]####
        for (int i = 0; i < s.length(); i++) //####[44]####
        {//####[44]####
            char c = s.charAt(i);//####[45]####
            for (int j = 0; j < s.length(); j++) //####[46]####
            {//####[46]####
                if ((j != i) && ((c == s.charAt(j)) || (abs(i - j) == abs(Character.getNumericValue(c) - Character.getNumericValue(s.charAt(j)))))) //####[47]####
                {//####[48]####
                    return;//####[50]####
                }//####[51]####
            }//####[52]####
        }//####[53]####
        if (s.length() == (int) n || (s.length() == (int) (n - 1) && s.indexOf('0') == -1)) //####[55]####
        {//####[55]####
            System.out.println("Thread " + Thread.currentThread().getId() + " found solution: " + s);//####[56]####
            counter.incrementAndGet();//####[57]####
        }//####[58]####
    }//####[60]####
//####[64]####
    private static volatile Method __pt__checkArrangementsInPara_long_long_long_AtomicInteger_method = null;//####[64]####
    private synchronized static void __pt__checkArrangementsInPara_long_long_long_AtomicInteger_ensureMethodVarSet() {//####[64]####
        if (__pt__checkArrangementsInPara_long_long_long_AtomicInteger_method == null) {//####[64]####
            try {//####[64]####
                __pt__checkArrangementsInPara_long_long_long_AtomicInteger_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__checkArrangementsInPara", new Class[] {//####[64]####
                    long.class, long.class, long.class, AtomicInteger.class//####[64]####
                });//####[64]####
            } catch (Exception e) {//####[64]####
                e.printStackTrace();//####[64]####
            }//####[64]####
        }//####[64]####
    }//####[64]####
    public static TaskID<Void> checkArrangementsInPara(Object chunkStart, Object chunkStop, Object n, Object counter) {//####[64]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[64]####
        return checkArrangementsInPara(chunkStart, chunkStop, n, counter, new TaskInfo());//####[64]####
    }//####[64]####
    public static TaskID<Void> checkArrangementsInPara(Object chunkStart, Object chunkStop, Object n, Object counter, TaskInfo taskinfo) {//####[64]####
        // ensure Method variable is set//####[64]####
        if (__pt__checkArrangementsInPara_long_long_long_AtomicInteger_method == null) {//####[64]####
            __pt__checkArrangementsInPara_long_long_long_AtomicInteger_ensureMethodVarSet();//####[64]####
        }//####[64]####
        List<Integer> __pt__taskIdIndexList = new ArrayList<Integer>();//####[64]####
        List<Integer> __pt__queueIndexList = new ArrayList<Integer>();//####[64]####
        if (chunkStart instanceof BlockingQueue) {//####[64]####
            __pt__queueIndexList.add(0);//####[64]####
        }//####[64]####
        if (chunkStart instanceof TaskID) {//####[64]####
            taskinfo.addDependsOn((TaskID)chunkStart);//####[64]####
            __pt__taskIdIndexList.add(0);//####[64]####
        }//####[64]####
        if (chunkStop instanceof BlockingQueue) {//####[64]####
            __pt__queueIndexList.add(1);//####[64]####
        }//####[64]####
        if (chunkStop instanceof TaskID) {//####[64]####
            taskinfo.addDependsOn((TaskID)chunkStop);//####[64]####
            __pt__taskIdIndexList.add(1);//####[64]####
        }//####[64]####
        if (n instanceof BlockingQueue) {//####[64]####
            __pt__queueIndexList.add(2);//####[64]####
        }//####[64]####
        if (n instanceof TaskID) {//####[64]####
            taskinfo.addDependsOn((TaskID)n);//####[64]####
            __pt__taskIdIndexList.add(2);//####[64]####
        }//####[64]####
        if (counter instanceof BlockingQueue) {//####[64]####
            __pt__queueIndexList.add(3);//####[64]####
        }//####[64]####
        if (counter instanceof TaskID) {//####[64]####
            taskinfo.addDependsOn((TaskID)counter);//####[64]####
            __pt__taskIdIndexList.add(3);//####[64]####
        }//####[64]####
        int[] __pt__queueIndexArray = new int[__pt__queueIndexList.size()];//####[64]####
        for (int __pt__i = 0; __pt__i < __pt__queueIndexArray.length; __pt__i++) {//####[64]####
            __pt__queueIndexArray[__pt__i] = __pt__queueIndexList.get(__pt__i);//####[64]####
        }//####[64]####
        taskinfo.setQueueArgIndexes(__pt__queueIndexArray);//####[64]####
        if (__pt__queueIndexArray.length > 0) {//####[64]####
            taskinfo.setIsPipeline(true);//####[64]####
        }//####[64]####
        int[] __pt__taskIdIndexArray = new int[__pt__taskIdIndexList.size()];//####[64]####
        for (int __pt__i = 0; __pt__i < __pt__taskIdIndexArray.length; __pt__i++) {//####[64]####
            __pt__taskIdIndexArray[__pt__i] = __pt__taskIdIndexList.get(__pt__i);//####[64]####
        }//####[64]####
        taskinfo.setTaskIdArgIndexes(__pt__taskIdIndexArray);//####[64]####
        taskinfo.setParameters(chunkStart, chunkStop, n, counter);//####[64]####
        taskinfo.setMethod(__pt__checkArrangementsInPara_long_long_long_AtomicInteger_method);//####[64]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[64]####
    }//####[64]####
    public static void __pt__checkArrangementsInPara(long chunkStart, long chunkStop, long n, AtomicInteger counter) {//####[64]####
        for (long i = chunkStart; i < chunkStop; i++) //####[66]####
        {//####[66]####
            String temp = Long.toString(i, (int) n);//####[67]####
            if (temp.length() != (int) n) //####[68]####
            {//####[68]####
                while (temp.length() != (int) n) //####[69]####
                {//####[69]####
                    temp = "0" + temp;//####[70]####
                }//####[71]####
            }//####[72]####
            checkArrangement(temp, counter, n);//####[74]####
        }//####[75]####
        long currentThreadID = Thread.currentThread().getId();//####[83]####
        System.out.println("-- Thread " + currentThreadID + " finished (" + chunkStart + " - " + chunkStop + ")");//####[84]####
    }//####[87]####
//####[87]####
}//####[87]####
