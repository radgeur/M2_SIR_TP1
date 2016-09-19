import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	
	
	public static void main(String[] args) throws IOException{
		ArrayList<String> usersList = new ArrayList<String>();
		ArrayList<String> themesList = new ArrayList<String>();
		int mut[][];
		File usersFile = new File("Users.txt");
		File themesFile = new File("Themes.txt");
		File mutFile = new File("Matrice_mutex.txt");
		File datasFile = new File("Log-clients-themes.txt");
		Scanner scan = new Scanner(datasFile);
		String currentLine;
		String splitLine[];
		
		//To can write into the files
		FileWriter usersFw = new FileWriter(usersFile);
		BufferedWriter usersOutput = new BufferedWriter(usersFw);
		FileWriter themesFw = new FileWriter(themesFile);
		BufferedWriter themesOutput = new BufferedWriter(themesFw);
		FileWriter mutFw = new FileWriter(mutFile);
		BufferedWriter mutOutput = new BufferedWriter(mutFw);
		
		//read the log and write into the files
		while(scan.hasNext()){
			currentLine = scan.next();
			splitLine = currentLine.split(";");
			if(!usersList.contains(splitLine[1])) {
				usersList.add(splitLine[1]);
				usersOutput.write(splitLine[1]);
				usersOutput.write("\n");
				usersOutput.flush();
			}
			
			if(!themesList.contains(splitLine[2])) {
				themesList.add(splitLine[2]);
				themesOutput.write(splitLine[2]);
				themesOutput.write("\n");
				themesOutput.flush();
			}
		}

		usersOutput.close();
		themesOutput.close();
		scan.close();
		
		scan = new Scanner(datasFile);
		mut = new int[usersList.size()][themesList.size()];
		
		//read the log again to can fill the mutex
		while(scan.hasNext()) {
			currentLine = scan.next();
			splitLine = currentLine.split(";");
			System.out.println();
			mut[usersList.indexOf(splitLine[1])][themesList.indexOf(splitLine[2])] = mut[usersList.indexOf(splitLine[1])][themesList.indexOf(splitLine[2])] + 1;
		}
		
		//write into the mutex file
		for (int i=0;i<usersList.size();i++) {
			for(int j=0; j<themesList.size();j++) {
				mutOutput.write(mut[i][j] + ";");
				mutOutput.flush();
			}
			mutOutput.write("\n");
			mutOutput.flush();
		}
		mutOutput.close();
		scan.close();
	}
	
}
