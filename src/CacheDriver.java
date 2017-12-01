/* Author: Phillip Pham
 * Date: 05/04/2017
 * Course: CSC230 Section: 18043
 * 
 * Program Title: Simulate Cache
 * Program Description: This program will demonstrate how a cache works and how it handles hits and misses.
 * 
 * I also watched this video many times to help explain the whole process and the formulas to calculate
 * many variables used in this program: https://www.youtube.com/watch?v=bTj0vFs8ndI
 * 
 * NOTE: Instead of using an integer to represent the valid bit of a cache block, I used a boolean value instead
 * to further simplify the program.
 */

import java.util.Scanner;

public class CacheDriver
{

	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		
		//Read Cache Information
		System.out.println("Cache Information:");
		System.out.print("   Address size, in bits? ");
		int addressSize = keyboard.nextInt();
		System.out.print("   Word size, in bits? ");
		int wordSize = keyboard.nextInt();
		System.out.print("   Block size, in words? ");
		int blockSize = keyboard.nextInt();
		System.out.print("   Number of Lines? ");
		int numLines = keyboard.nextInt();

		// Read Test Parameters
		System.out.println("\nTest Parameters:");
		System.out.print("   Starting Address? ");
		int startAddr = keyboard.nextInt();
		System.out.print("   Ending Address? ");
		int endAddr = keyboard.nextInt();
		System.out.print("   Increment? ");
		int incAddr = keyboard.nextInt();

		// Create the cache
		Cache c =  new Cache("CSC230", addressSize, wordSize, blockSize, numLines);

		// Run the test
		testCache(c, startAddr, endAddr, incAddr);	
		
		keyboard.close();
		System.exit(0);
	}
	
	private static void testCache(Cache c, int startAddr, int endAddr, int inc)
	{
		System.out.println("\n");
		
		c.print();
		
		for(int address = startAddr; address <= endAddr; address += inc)
		{
			c.readLocation(address);
			System.out.println();
		}
		
		c.stats();
	}
}
