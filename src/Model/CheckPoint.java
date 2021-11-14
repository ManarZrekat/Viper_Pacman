package Model;

public class CheckPoint {
	private int[] coords = new int[4];
	
	public CheckPoint()
	{
		this.coords[0] = 0;
		this.coords[1] = 0;
		this.coords[2] = 0;
		this.coords[3] = 0;
	}
	
	public CheckPoint(int xi, int xs, int yi, int ys)
	{
		this.coords[0] = xi;
		this.coords[1] = xs;
		this.coords[2] = yi;
		this.coords[3] = ys;
	}
	
	public int[] getCheckPointCoordinates()
	{
		return this.coords;
	}

}
