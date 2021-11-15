package com.ikhokha.techcheck;

import com.ikhokha.techcheck.factory.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Main {

	public static void main(String[] args) {

		//this 'ArrayList' is able to store all incoming 'Futures'
		//once all 'Futures' are saved in this list, we can run a 'for loop' to extract data from each 'Future'
		Map<String, Integer> totalResults = new HashMap<>();
		ArrayList<Future<Map<String, Integer>>> futures = new ArrayList<>();

		ExecutorService executorService = Executors.newFixedThreadPool(5);

		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		
		for (File commentFile : commentFiles) {

			Callable<Map<String, Integer>> callable = new Callable<Map<String, Integer>>() {
				@Override
				public Map<String, Integer> call() throws Exception {
					CommentAnalyzer commentAnalyzer = new CommentAnalyzer(commentFile);
					return commentAnalyzer.analyze();
				}
			};

			//'submit' method returns a 'Future' which consists of data obtained from the 'callable' variable above
			//we save each 'Future' in a list
			//once 'Future' has completed, we take the data from the 'Future' and add it an array, and the loop re-runs
			//before adding data into array, we cast it
			//future 'type' must match the Callable return type, in our case it is 'Map<String, Integer>'
			futures.add((Future<Map<String, Integer>>) executorService.submit(callable));
		}

		//shutdown the 'executorService'
		executorService.shutdown();

		//once all 'Future' data packets have been retrieved and executor has been shut down
		//we process the data from the 'Future' list
		for (Future<Map<String, Integer>> data: futures) {
			try {
				//iterate and 'get()' data from each 'Future'
				Map<String, Integer> result = data.get();
				//pass the extracted data to the 'addReportResults' method
				addReportResults(result, totalResults);
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}
	
	/**
	 * This method adds the result counts from a source map to the target map 
	 * @param source the source map
	 * @param target the target map
	 */
	private static void addReportResults(Map<String, Integer> source, Map<String, Integer> target) {

		for (Map.Entry<String, Integer> entry : source.entrySet()) {
			target.merge(entry.getKey(), entry.getValue(), Integer::sum);

		}

	}

}
