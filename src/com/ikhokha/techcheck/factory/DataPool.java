package com.ikhokha.techcheck.factory;

import java.util.HashMap;
import java.util.Map;

public class DataPool {
    public static Map<String, Task> data = new HashMap<>();

    //a simple method used to load data in the 'data' map above.
    public static void loadTasksInDataPool() {
        data.put("SHORTER_THAN_15", new LessThan15());
        data.put("MOVER_MENTIONS", new HasMover());
        data.put("SHAKER_MENTIONS", new HasShaker());
        data.put("QUESTIONS", new HasQuestion());
        data.put("SPAM", new HasSpam());
    }
}
