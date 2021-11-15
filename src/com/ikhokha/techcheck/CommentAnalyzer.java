package com.ikhokha.techcheck;

import com.ikhokha.techcheck.factory.DataPool;
import com.ikhokha.techcheck.factory.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommentAnalyzer {
	
	private File file;
	
	public CommentAnalyzer(File file) {
		this.file = file;
	}
	
	public Map<String, Integer> analyze() {
		Map<String, Integer> resultsMap = new HashMap<>();
		//This method prepares the 'data' map in 'DataPool' class with initial data before running conditionals
		DataPool.loadTasksInDataPool();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line = null;
			while ((line = reader.readLine()) != null) {

				//going through each entry in the 'data' map in 'DataPool' class and incrementing count based on metric
				for (Map.Entry<String, Task> data: DataPool.data.entrySet()){
					if (data.getValue().Contains(line)) {
						incOccurrence(data.getKey(), resultsMap);
					}
				}

			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		}

		return resultsMap;
		
	}
	
	/**
	 * This method increments a counter by 1 for a match type on the countMap. Uninitialized keys will be set to 1
	 * @param countMap the map that keeps track of counts
	 * @param key the key for the value to increment
	 */
	private void incOccurrence(String key, Map<String, Integer> countMap) {

		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + 1);

	}

}
