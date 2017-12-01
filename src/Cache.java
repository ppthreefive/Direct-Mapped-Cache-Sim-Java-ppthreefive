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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

public class Cache
{
	// Attributes
	private String name;
	private int addressSize, wordSize, blockSize, numLines, hits, misses, requests;
	private Vector<Block> blocks = new Vector<Block>();
	
	// Constructor
	public Cache(String name, int addressSize, int wordSize, int blockSize, int numLines)
	{
		this.name = name;
		this.addressSize = addressSize;
		this.wordSize = wordSize;
		this.blockSize = blockSize;
		this.numLines = numLines;
		
		this.hits = 0;
		this.misses = 0;
		this.requests = 0;
		
		// This will add lines to the Cache depending on how many lines are desired
		for (int i = 0; i < this.numLines; i++)
		{
			this.blocks.addElement(new Block());
		}
	}

	public void print()
	{
		String output = "";
		// Calculates the total size of the Cache memory
		int dmcSize = (this.numLines * ((this.blockSize * 32) + 32));
		long memByte = (long)Math.pow(2, this.addressSize);
		
		output += "********** " + this.name + " Cache Size Report **********";
		output += "\n\t" + ((int)(memByte / 4)) + " words of " + this.wordSize + " bits (" + (memByte/*(this.wordSize / 8) * 1024*/) + " bytes)";
		output += "\n\t" + this.numLines + " lines with 1 word tag and " + this.blockSize + " words data each (" + (dmcSize / 8) + " bytes)";
		output += "\n";
		
		System.out.println(output);
	}

	public void readLocation(int address)
	{
		String output = "";
		int blockNum = (address / (this.blockSize * 4));
		int totalBlocks = (int)((Math.pow(2, this.addressSize - (int)(Math.log(this.blockSize * 4)/Math.log(2)))) - 1);
		int offset = (address % ((int)Math.pow(2, (int)(Math.log(this.blockSize * 4)/Math.log(2)))));
		int offsetBits = (int)(Math.log(this.blockSize * 4)/Math.log(2));
		int index = (blockNum % this.numLines);
		int indexBits = (int)(Math.log(this.numLines)/Math.log(2));
		int tagBits = (32 - (offsetBits + indexBits + 2));
		int tag =  (address / ((int)(Math.pow(2, this.blockSize))));
		
		output += "Read mem:\t" + address + " (" + binary(address, this.addressSize) + ")";
		output += "\n  Block #:\t" + blockNum + " (" + binary(blockNum, Integer.bitCount(totalBlocks)) + ")";
		output += "\n  Offset:\t" + offset + " (" + binary(offset, offsetBits) + ")";
		output += "\n  Index:\t" + index + " (" + binary(index, indexBits) + ")";
		output += "\n  Tag:\t\t" + tag + " (" + binary(tag, tagBits) + ")";
		
		if(this.blocks.elementAt(index).isValid() && this.blocks.elementAt(index).getTag() == tag)
		{
			this.hits++;
			output += "\n  Result: ** Hit **";
		}
		else
		{
			this.misses++;
			this.blocks.elementAt(index).setValid(true);
			this.blocks.elementAt(index).setTag(tag);
			output += "\n  Result: Miss";
		}
		
		this.requests++;
		System.out.println(output);
	}

	public void stats()
	{
		NumberFormat fmt = new DecimalFormat("#0.00");
		String output = "";
		
		double hitRatio = (((double)this.hits / (double)this.requests) * 100);
		double missRatio = (((double)this.misses / (double)this.requests) * 100);
		
		output += "********** " + this.name + " Cache Stats Report **********";
		output += "\n\t" + "Requests: " + this.requests;
		output += "\n\t" + "Hits: " + this.hits + " (" + fmt.format(hitRatio) + "%)";
		output += "\n\t" + "Misses: " + this.misses + " (" + fmt.format(missRatio) + "%)";
		output += "\n";
		
		System.out.println(output);
	}
	
	private String binary(int x, int size)
	{ 
		return String.format("%32s", Integer.toBinaryString(x)).replace(" ", "0").substring(32-size);
	}
}