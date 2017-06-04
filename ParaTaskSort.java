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
        sequentialMergeSort(0, arraySize - 1);//####[47]####
    }//####[48]####
//####[50]####
    public void parallelSort() {//####[50]####
        TaskID id = paraTaskMergeSort(0, arraySize - 1);//####[51]####
        try {//####[52]####
            id.waitTillFinished();//####[53]####
        } catch (Exception e) {//####[54]####
        }//####[54]####
    }//####[55]####
//####[57]####
    private void sequentialMergeSort(int left, int right) {//####[57]####
        if (left < right) //####[59]####
        {//####[59]####
            int middle = left + (right - left) / 2;//####[60]####
            sequentialMergeSort(left, middle);//####[61]####
            sequentialMergeSort(middle + 1, right);//####[62]####
            merge(left, middle, right);//####[63]####
        }//####[64]####
    }//####[66]####
//####[68]####
    private static volatile Method __pt__paraTaskMergeSort_int_int_method = null;//####[68]####
    private synchronized static void __pt__paraTaskMergeSort_int_int_ensureMethodVarSet() {//####[68]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[68]####
            try {//####[68]####
                __pt__paraTaskMergeSort_int_int_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__paraTaskMergeSort", new Class[] {//####[68]####
                    int.class, int.class//####[68]####
                });//####[68]####
            } catch (Exception e) {//####[68]####
                e.printStackTrace();//####[68]####
            }//####[68]####
        }//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(int left, int right) {//####[68]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[68]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(int left, int right, TaskInfo taskinfo) {//####[68]####
        // ensure Method variable is set//####[68]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[68]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[68]####
        }//####[68]####
        taskinfo.setParameters(left, right);//####[68]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[68]####
        taskinfo.setInstance(this);//####[68]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(TaskID<Integer> left, int right) {//####[68]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[68]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(TaskID<Integer> left, int right, TaskInfo taskinfo) {//####[68]####
        // ensure Method variable is set//####[68]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[68]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[68]####
        }//####[68]####
        taskinfo.setTaskIdArgIndexes(0);//####[68]####
        taskinfo.addDependsOn(left);//####[68]####
        taskinfo.setParameters(left, right);//####[68]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[68]####
        taskinfo.setInstance(this);//####[68]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(BlockingQueue<Integer> left, int right) {//####[68]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[68]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(BlockingQueue<Integer> left, int right, TaskInfo taskinfo) {//####[68]####
        // ensure Method variable is set//####[68]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[68]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[68]####
        }//####[68]####
        taskinfo.setQueueArgIndexes(0);//####[68]####
        taskinfo.setIsPipeline(true);//####[68]####
        taskinfo.setParameters(left, right);//####[68]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[68]####
        taskinfo.setInstance(this);//####[68]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(int left, TaskID<Integer> right) {//####[68]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[68]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(int left, TaskID<Integer> right, TaskInfo taskinfo) {//####[68]####
        // ensure Method variable is set//####[68]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[68]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[68]####
        }//####[68]####
        taskinfo.setTaskIdArgIndexes(1);//####[68]####
        taskinfo.addDependsOn(right);//####[68]####
        taskinfo.setParameters(left, right);//####[68]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[68]####
        taskinfo.setInstance(this);//####[68]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(TaskID<Integer> left, TaskID<Integer> right) {//####[68]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[68]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(TaskID<Integer> left, TaskID<Integer> right, TaskInfo taskinfo) {//####[68]####
        // ensure Method variable is set//####[68]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[68]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[68]####
        }//####[68]####
        taskinfo.setTaskIdArgIndexes(0, 1);//####[68]####
        taskinfo.addDependsOn(left);//####[68]####
        taskinfo.addDependsOn(right);//####[68]####
        taskinfo.setParameters(left, right);//####[68]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[68]####
        taskinfo.setInstance(this);//####[68]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(BlockingQueue<Integer> left, TaskID<Integer> right) {//####[68]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[68]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(BlockingQueue<Integer> left, TaskID<Integer> right, TaskInfo taskinfo) {//####[68]####
        // ensure Method variable is set//####[68]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[68]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[68]####
        }//####[68]####
        taskinfo.setQueueArgIndexes(0);//####[68]####
        taskinfo.setIsPipeline(true);//####[68]####
        taskinfo.setTaskIdArgIndexes(1);//####[68]####
        taskinfo.addDependsOn(right);//####[68]####
        taskinfo.setParameters(left, right);//####[68]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[68]####
        taskinfo.setInstance(this);//####[68]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(int left, BlockingQueue<Integer> right) {//####[68]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[68]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(int left, BlockingQueue<Integer> right, TaskInfo taskinfo) {//####[68]####
        // ensure Method variable is set//####[68]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[68]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[68]####
        }//####[68]####
        taskinfo.setQueueArgIndexes(1);//####[68]####
        taskinfo.setIsPipeline(true);//####[68]####
        taskinfo.setParameters(left, right);//####[68]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[68]####
        taskinfo.setInstance(this);//####[68]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(TaskID<Integer> left, BlockingQueue<Integer> right) {//####[68]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[68]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(TaskID<Integer> left, BlockingQueue<Integer> right, TaskInfo taskinfo) {//####[68]####
        // ensure Method variable is set//####[68]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[68]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[68]####
        }//####[68]####
        taskinfo.setQueueArgIndexes(1);//####[68]####
        taskinfo.setIsPipeline(true);//####[68]####
        taskinfo.setTaskIdArgIndexes(0);//####[68]####
        taskinfo.addDependsOn(left);//####[68]####
        taskinfo.setParameters(left, right);//####[68]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[68]####
        taskinfo.setInstance(this);//####[68]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(BlockingQueue<Integer> left, BlockingQueue<Integer> right) {//####[68]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[68]####
        return paraTaskMergeSort(left, right, new TaskInfo());//####[68]####
    }//####[68]####
    public TaskID<Void> paraTaskMergeSort(BlockingQueue<Integer> left, BlockingQueue<Integer> right, TaskInfo taskinfo) {//####[68]####
        // ensure Method variable is set//####[68]####
        if (__pt__paraTaskMergeSort_int_int_method == null) {//####[68]####
            __pt__paraTaskMergeSort_int_int_ensureMethodVarSet();//####[68]####
        }//####[68]####
        taskinfo.setQueueArgIndexes(0, 1);//####[68]####
        taskinfo.setIsPipeline(true);//####[68]####
        taskinfo.setParameters(left, right);//####[68]####
        taskinfo.setMethod(__pt__paraTaskMergeSort_int_int_method);//####[68]####
        taskinfo.setInstance(this);//####[68]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[68]####
    }//####[68]####
    public void __pt__paraTaskMergeSort(int left, int right) {//####[68]####
        if (left < right) //####[70]####
        {//####[70]####
            int middle = left + (right - left) / 2;//####[71]####
            TaskID id1 = paraTaskMergeSort(left, middle);//####[72]####
            TaskID id2 = paraTaskMergeSort(middle + 1, right);//####[73]####
            TaskIDGroup taskGroup = new TaskIDGroup(2);//####[75]####
            taskGroup.add(id1);//####[76]####
            taskGroup.add(id2);//####[77]####
            try {//####[79]####
                taskGroup.waitTillFinished();//####[80]####
            } catch (Exception e) {//####[81]####
            }//####[81]####
            merge(left, middle, right);//####[83]####
        }//####[84]####
    }//####[86]####
//####[86]####
//####[88]####
    private void merge(int left, int middle, int right) {//####[88]####
        for (int i = left; i <= right; i++) //####[89]####
        {//####[89]####
            temp[i] = array[i];//####[90]####
        }//####[91]####
        int i = left;//####[93]####
        int j = middle + 1;//####[94]####
        int k = left;//####[95]####
        while (i <= middle && j <= right) //####[97]####
        {//####[97]####
            if (temp[i] <= temp[j]) //####[98]####
            {//####[98]####
                array[k] = temp[i];//####[99]####
                i++;//####[100]####
            } else {//####[101]####
                array[k] = temp[j];//####[102]####
                j++;//####[103]####
            }//####[104]####
            k++;//####[105]####
        }//####[106]####
        while (i <= middle) //####[108]####
        {//####[108]####
            array[k] = temp[i];//####[109]####
            k++;//####[110]####
            i++;//####[111]####
        }//####[112]####
    }//####[113]####
}//####[113]####
