package project3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		File inFile = null;
		File outFile = null;
		if ((0 < args.length) && (args.length <= 2)) {
			inFile = new File(args[0]);
			outFile = new File(args[1]);
			// if the output file doesn't exists, then create it
			if (!outFile.exists()) {
				outFile.createNewFile();
			}
		} else {
			System.err.println("Invalid arguments count:" + args.length);
			System.exit(1);
		}

		BufferedReader br = null;
		List<Board> boardList = new ArrayList<Board>();
		List<String> lineList = new ArrayList<String>();
		
		try {
			String currentLine;

			br = new BufferedReader(new FileReader(inFile));
			currentLine = br.readLine();
			lineList.add(currentLine);
			
			// Read from file and convert each line to a board
			while((currentLine = br.readLine()) != null) {
				lineList.add(currentLine);
				Board b = Board.readLineToBoard(currentLine);
				b.printBoard();
				boardList.add(b);
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			br.close();
		}
		
		// Generate our customized features
		// for all our features, the output 1 means player1 has more advantage
		// otherwise, the output is 0
		List<String> featureStrings = Feature.getFeatureResults(boardList);
		
		// Append the features to the end of input file
		// then write to the output file
		FileOutputStream fos = new FileOutputStream(outFile);		 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		for (int k = 0; k < lineList.size(); k++) {
			bw.write(lineList.get(k) + "," + featureStrings.get(k) + "\n");
		}
		
		bw.close();
		
	}
}