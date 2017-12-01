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

public class Block
{
	private boolean valid;
	private int tag;
	
	public Block()
	{
		this.valid = false;
		this.tag = 0;
	}

	public boolean isValid()
	{
		return valid;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	public int getTag()
	{
		return tag;
	}

	public void setTag(int tag)
	{
		this.tag = tag;
	}
}