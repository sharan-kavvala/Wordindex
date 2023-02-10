import java.io.*;
import java.util.*;

class WordIndex {
	
	    private Map<String, Set<Integer>> index; // to store the word-to-page mapping
	    private Set<String> excludeWords; // to store the excluded words

	    public WordIndex() {
	        index = new HashMap<>();
	        excludeWords = new HashSet<>();
	    }

	    // Reads the input files and stores the information in appropriate data structures
	    public void readFiles() throws IOException {
	        // Read exclude-words.txt
	        BufferedReader reader = new BufferedReader(new FileReader("exclude-words.txt"));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            excludeWords.add(line.trim().toLowerCase());
	        }
	        reader.close();

	        // Read Page1.txt, Page2.txt and Page3.txt
	        for (int i = 1; i <= 3; i++) {
	            reader = new BufferedReader(new FileReader("Page" + i + ".txt"));
	            while ((line = reader.readLine()) != null) {
	                String[] words = line.split("\\s+");
	                for (String word : words) {
	                    word = word.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
	                    if (!excludeWords.contains(word)) {
	                        if (!index.containsKey(word)) {
	                            index.put(word, new HashSet<>());
	                        }
	                        index.get(word).add(i);
	                    }
	                }
	            }
	            reader.close();
	        }
	    }

	    // Writes the contents of the index to a file named "index.txt"
	    public void writeToFile() throws IOException {
	        BufferedWriter writer = new BufferedWriter(new FileWriter("index.txt"));
	        List<String> words = new ArrayList<>(index.keySet());
	        words.sort(String::compareTo);
	        for (String word : words) {
	            Set<Integer> pages = index.get(word);
	            StringBuilder sb = new StringBuilder();
	            sb.append(word).append(" : ");
	            for (int page : pages) {
	                sb.append(page).append(",");
	            }
	            sb.deleteCharAt(sb.length() - 1); // to remove the last comma
	            writer.write(sb.toString());
	            writer.newLine();
	        }
	        writer.close();
	    }
	
	}
