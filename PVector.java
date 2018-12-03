public class PVector {
	float x;
	float y;
	float mag;
	
	PVector(float x, float y){
		this.x = x;
		this.y = y;
		
		float m = (float) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
		mag = m;
	}
	
	public void add(PVector v) {
		x += v.x;
		y += v.y;
	}
	
	public void sub(PVector v) {
		x = x - v.x;
		y = y - v.y;
	}
	
	public void setMag(float s) {
		if (s == 0) {
			x = 0;
			y = 0;
		} else {
			x = (x/mag) * s;
			y = (y/mag) * s;
		}
		
		mag = (float) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
	}
	
	public void mult(float s) {
		x *= s;
		y *= s;
	}
	
	public void limit(float l) {
		if (mag > l) {
			this.setMag(l);
		}
	}
}

