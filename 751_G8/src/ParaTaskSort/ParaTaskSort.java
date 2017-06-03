package ParaTaskSort;//####[1]####
//####[1]####
import pt.runtime.TaskIDGroup;//####[3]####
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
public class ParaTaskSort {//####[5]####
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
    private int arraySize;//####[7]####
//####[8]####
    private int minimum;//####[8]####
//####[9]####
    private int maximum;//####[9]####
//####[11]####
    private int[] array;//####[11]####
//####[12]####
    private int[] unsortedArray;//####[12]####
//####[13]####
    private int[] temp;//####[13]####
//####[15]####
    public ParaTaskSort(int size, int min, int max) {//####[15]####
        arraySize = size;//####[16]####
        minimum = min;//####[17]####
        maximum = max;//####[18]####
        array = new int[arraySize];//####[20]####
        unsortedArray = new int[arraySize];//####[21]####
        temp = new int[arraySize];//####[22]####
        for (int i = 0; i < arraySize; i++) //####[24]####
        {//####[24]####
            array[i] = minimum + (int) (Math.random() * maximum);//####[25]####
            unsortedArray[i] = array[i];//####[26]####
        }//####[27]####
    }//####[28]####
//####[30]####
    private void printArray() {//####[30]####
        for (int i = 0; i < arraySize; i++) //####[31]####
        {//####[31]####
            if (i > 0) //####[32]####
            {//####[32]####
                System.out.print(", ");//####[33]####
            }//####[34]####
            System.out.print(array[i]);//####[35]####
        }//####[36]####
        System.out.println();//####[37]####
    }//####[38]####
//####[40]####
    public void unsortArray() {//####[40]####
        for (int i = 0; i < arraySize; i++) //####[41]####
        {//####[41]####
            array[i] = unsortedArray[i];//####[42]####
        }//####[43]####
    }//####[44]####
//####[46]####
    public void sequentialSort() {//####[46]####
        long startTime = System.currentTimeMillis();//####[47]####
        sequentialMergeSort(0, arraySize - 1);//####[48]####
        long endTime = System.currentTimeMillis();//####[49]####
        System.out.println("Sequential sort time: " + (endTime - startTime));//####[51]####
        printArray();//####[52]####
    }//####[53]####
//####[55]####
    public void parallelSort() {//####[55]####
        long startTime = System.currentTimeMillis();//####[56]####
        TaskID id = paraTaskMergeSort(0, arraySize - 1);//####[57]####
        try {//####[58]####
            id.waitTillFinished();//####[59]####
        } catch (Exception e) {//####[60]####
            System.out.println("Fucked Up Sequential Sort");//####[60]####
        }//####[60]####
        long endTime = System.currentTimeMillis();//####[61]####
        System.out.println("Parallel sort time: " + (endTime - startTime));//####[64]####
        printArray();//####[65]####
    }//####[66]####
//####[68]####
    private void sequentialMergeSort(int left, int right) {//####[68]####
        if (left < right) //####[70]####
        {//####[70]####
            int middle = left + (right - left) / 2;//####[71]####
            sequentialMergeSort(left, middle);//####[72]####
            sequentialMergeSort(middle + 1, right);//####[73]####
            merge(left, middle, right);//####[74]####
        }//####[75]####
    }//####[77]####
//####[79]####
    private static volatile Method __pt__paraTaskMergeSort_int_int_method = null;//####[79]####
    private synchronized static void __pt__paraTaskMergeSort_int_int_ensureMethodVarSet() {//####[79]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[79]####
            try {//####[79]####
                __pt__paraTaskMergeSort_int_int_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__paraTaskMergeSort", new Class[] {//####[79]####
                    int.class, int.class//####[79]####
                });//####[79]####
            } catch (Exception e) {//####[79]####
                e.printStackTrace();//####[79]####
            }//####[79]####
        }//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(int left, int right) {//####[79]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[79]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(int left, int right, TaskInfo taskinfo) {//####[79]####
        // ensure Method variable is set//####[79]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[79]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[79]####
        }//####[79]####
        taskinfo.setParameters(left, right);//####[79]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[79]####
        taskinfo.setInstance(this);//####[79]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(TaskID<Integer> left, int right) {//####[79]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[79]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(TaskID<Integer> left, int right, TaskInfo taskinfo) {//####[79]####
        // ensure Method variable is set//####[79]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[79]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[79]####
        }//####[79]####
        taskinfo.setTaskIdArgIndexes(0);//####[79]####
        taskinfo.addDependsOn(left);//####[79]####
        taskinfo.setParameters(left, right);//####[79]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[79]####
        taskinfo.setInstance(this);//####[79]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(BlockingQueue<Integer> left, int right) {//####[79]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[79]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(BlockingQueue<Integer> left, int right, TaskInfo taskinfo) {//####[79]####
        // ensure Method variable is set//####[79]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[79]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[79]####
        }//####[79]####
        taskinfo.setQueueArgIndexes(0);//####[79]####
        taskinfo.setIsPipeline(true);//####[79]####
        taskinfo.setParameters(left, right);//####[79]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[79]####
        taskinfo.setInstance(this);//####[79]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(int left, TaskID<Integer> right) {//####[79]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[79]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(int left, TaskID<Integer> right, TaskInfo taskinfo) {//####[79]####
        // ensure Method variable is set//####[79]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[79]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[79]####
        }//####[79]####
        taskinfo.setTaskIdArgIndexes(1);//####[79]####
        taskinfo.addDependsOn(right);//####[79]####
        taskinfo.setParameters(left, right);//####[79]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[79]####
        taskinfo.setInstance(this);//####[79]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(TaskID<Integer> left, TaskID<Integer> right) {//####[79]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[79]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(TaskID<Integer> left, TaskID<Integer> right, TaskInfo taskinfo) {//####[79]####
        // ensure Method variable is set//####[79]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[79]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[79]####
        }//####[79]####
        taskinfo.setTaskIdArgIndexes(0, 1);//####[79]####
        taskinfo.addDependsOn(left);//####[79]####
        taskinfo.addDependsOn(right);//####[79]####
        taskinfo.setParameters(left, right);//####[79]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[79]####
        taskinfo.setInstance(this);//####[79]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(BlockingQueue<Integer> left, TaskID<Integer> right) {//####[79]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[79]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(BlockingQueue<Integer> left, TaskID<Integer> right, TaskInfo taskinfo) {//####[79]####
        // ensure Method variable is set//####[79]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[79]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[79]####
        }//####[79]####
        taskinfo.setQueueArgIndexes(0);//####[79]####
        taskinfo.setIsPipeline(true);//####[79]####
        taskinfo.setTaskIdArgIndexes(1);//####[79]####
        taskinfo.addDependsOn(right);//####[79]####
        taskinfo.setParameters(left, right);//####[79]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[79]####
        taskinfo.setInstance(this);//####[79]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(int left, BlockingQueue<Integer> right) {//####[79]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[79]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(int left, BlockingQueue<Integer> right, TaskInfo taskinfo) {//####[79]####
        // ensure Method variable is set//####[79]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[79]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[79]####
        }//####[79]####
        taskinfo.setQueueArgIndexes(1);//####[79]####
        taskinfo.setIsPipeline(true);//####[79]####
        taskinfo.setParameters(left, right);//####[79]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[79]####
        taskinfo.setInstance(this);//####[79]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(TaskID<Integer> left, BlockingQueue<Integer> right) {//####[79]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[79]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(TaskID<Integer> left, BlockingQueue<Integer> right, TaskInfo taskinfo) {//####[79]####
        // ensure Method variable is set//####[79]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[79]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[79]####
        }//####[79]####
        taskinfo.setQueueArgIndexes(1);//####[79]####
        taskinfo.setIsPipeline(true);//####[79]####
        taskinfo.setTaskIdArgIndexes(0);//####[79]####
        taskinfo.addDependsOn(left);//####[79]####
        taskinfo.setParameters(left, right);//####[79]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[79]####
        taskinfo.setInstance(this);//####[79]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(BlockingQueue<Integer> left, BlockingQueue<Integer> right) {//####[79]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[79]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[79]####
    }//####[79]####
    private TaskID<Void> paraTaskMergeSort(BlockingQueue<Integer> left, BlockingQueue<Integer> right, TaskInfo taskinfo) {//####[79]####
        // ensure Method variable is set//####[79]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[79]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[79]####
        }//####[79]####
        taskinfo.setQueueArgIndexes(0, 1);//####[79]####
        taskinfo.setIsPipeline(true);//####[79]####
        taskinfo.setParameters(left, right);//####[79]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[79]####
        taskinfo.setInstance(this);//####[79]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[79]####
    }//####[79]####
    public void __pt__paraTaskMergeSort(int left, int right) {//####[79]####
        if (left < right) //####[81]####
        {//####[81]####
            int middle = left + (right - left) / 2;//####[82]####
            TaskID id1 = paraTaskMergeSort(left, middle);//####[83]####
            TaskID id2 = paraTaskMergeSort(middle + 1, right);//####[84]####
            TaskIDGroup taskGroup = new TaskIDGroup(2);//####[86]####
            taskGroup.add(id1);//####[87]####
            taskGroup.add(id2);//####[88]####
            try {//####[90]####
                taskGroup.waitTillFinished();//####[91]####
            } catch (Exception e) {//####[92]####
            }//####[92]####
            merge(left, middle, right);//####[94]####
        }//####[95]####
    }//####[97]####
//####[97]####
//####[99]####
    private void merge(int left, int middle, int right) {//####[99]####
        for (int i = left; i <= right; i++) //####[100]####
        {//####[100]####
            temp[i] = array[i];//####[101]####
        }//####[102]####
        int i = left;//####[104]####
        int j = middle + 1;//####[105]####
        int k = left;//####[106]####
        while (i <= middle && j <= right) //####[108]####
        {//####[108]####
            if (temp[i] <= temp[j]) //####[109]####
            {//####[109]####
                array[k] = temp[i];//####[110]####
                i++;//####[111]####
            } else {//####[112]####
                array[k] = temp[j];//####[113]####
                j++;//####[114]####
            }//####[115]####
            k++;//####[116]####
        }//####[117]####
        while (i <= middle) //####[119]####
        {//####[119]####
            array[k] = temp[i];//####[120]####
            k++;//####[121]####
            i++;//####[122]####
        }//####[123]####
    }//####[124]####
}//####[124]####
