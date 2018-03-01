import java.util.ArrayList;

public class Ride {
	
	private int id;
	private int startX;
	private int startY;
	
	private int endX;
	private int endY;
	
	private int startTime;
	private int endTime;
	
	private int realStartTime;
	
	private int distance;
	
	public Ride(int id, int startX, int startY, int endX, int endY, int startTime, int endTime) {
		this.id = id;
		this.startX = startX;
		this.startY = startY;
		this.endY = endY;
		this.endX = endX;
		this.startTime = startTime;
		this.endTime = endTime;
		int xDistance = endX - startX;
		if (xDistance < 0) {
			xDistance *= -1;
		}
		int yDistance = endY - startY;
		if (yDistance < 0) {
			yDistance *= -1;
		}
		this.distance = xDistance + yDistance;
	}
	public int getId() {
		return this.id;
	}
	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getRealStartTime() {
		return realStartTime;
	}
	public void setRealStartTime(int realStartTime) {
		this.realStartTime = realStartTime;
	}
	
	public int getRealEndTime() {
		return this.getRealStartTime() + this.getDistance();
	}
	@Override
	public String toString() {
		return "Ride [id=" + id + ", startX=" + startX + ", startY=" + startY + ", endX=" + endX + ", endY=" + endY
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", realStartTime=" + realStartTime
				+ ", distance=" + distance + "]";
	}
}
