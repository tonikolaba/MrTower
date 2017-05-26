package al.artofsoul.ndihma;

import org.lwjgl.Sys;

public class Ora {
	
	private static boolean paused = false;
	public static long lastFrame, totalTime;
	public static float d = 0, multiplier = 1;
	
	
	public static long merrKohen(){
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	
	public static float merrDelta(){
		long currentTime = merrKohen();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = merrKohen();
		//System.out.println(delta * 0.01f);
		if (delta * 0.001f > 0.05f)
			return 0.05f;
		return delta * 0.001f;
	}
	
	public static float Delta(){
		if (paused)
			return 0;
		else
			return d * multiplier;
	}
	
	public static float TotalTime (){
		return totalTime;
	}
	
	public static float Multiplier(){
		return multiplier;
	}
	
	public static void update(){
		d = merrDelta();
		totalTime += d; // njesoj: totalTime = totalTime + d;
		
	}
	
	public static void ChangeMultiplier(float change){
		if (multiplier + change < -1 && multiplier + change >  7){
			} else {
				multiplier += change;
		}
		
	}
	
	public static void Pause(){
		if (paused)
			paused = false;
		else
			paused = true;
	}

}
