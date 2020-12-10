package handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler 
{
	public static void main(String[] args) 
	{
		BufferedReader br = null;
		BufferedWriter bw = null;
		Scanner sc = null;
		boolean blankFlag = false;
		String fileName = "Test.txt";
		try
		{
			File file = new File(fileName);
			
			if (!file.exists())
			{
				file.createNewFile();
				blankFlag = true;
			}
			

			FileWriter fw = new FileWriter(file, true);
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
			
			sc = new Scanner(System.in);
			
			System.out.println("Which operation would you like to perform on " + fileName + "?");
			System.out.println("1:  Read");
			System.out.println("2:  Edit");
			System.out.println("3:  Append");
			
			String command = sc.nextLine();
			
			if (command.equals("1") && blankFlag == false)
			{
				String output = null;
				output = br.readLine();
				while (output != null)
				{
					System.out.println(output);
					output = br.readLine();
				}
			}
			else if (command.equals("2") && blankFlag == false)
			{
				System.out.println("While editing, each line of the file will be displayed.");
				System.out.println("Rewrite each line to make edits.  Type DELETE to remove the line entirely.  Press enter if the line is acceptable as-is.");
				File replacementFile = new File("Replacement.txt");
				FileWriter fw2 = new FileWriter(replacementFile, true);
				BufferedWriter bw2 = new BufferedWriter(fw2);
				
				String output = null;
				String input = null;
				output = br.readLine();
				while (output != null)
				{
					System.out.println(output);
					input = sc.nextLine();
					if (!input.equals("DELETE") && !input.equals(""))
					{
						bw2.write(input + "\n");
					}
					else if (input.equals("DELETE")) {}
					else if (input.equals(""))
					{
						bw2.write(output + "\n");
					}
					output = br.readLine();
				}
				
				bw2.flush();
				bw2.close();
				fw2.close();
				bw.close();
				br.close();
				fw.close();
				fr.close();

				file.delete();
				replacementFile.renameTo(file);
			}
			else if ((command.equals("1") || command.equals("2")) && blankFlag == true)
			{
				System.out.println("This file is empty.  Please use the append command if you want to interact with it.");
			}
			else if (command.equals("3"))
			{
				String output = null;
				System.out.println("Append lines to the file.  When you're done, press enter on a blank line.");
				output = sc.nextLine();
				while (!output.equals(""))
				{
					bw.write(output + "\n");
					output = sc.nextLine();
				}
				bw.flush();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (bw != null)
				{
					bw.close();
				}
				if (br != null)
				{
					br.close();
				}
				if (sc != null)
				{
					sc.close();
				}
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}