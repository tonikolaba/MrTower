package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.BeginSession;

import org.lwjgl.opengl.Display;

import al.artofsoul.ndihma.Ora;
import al.artofsoul.ndihma.StateManger;

public class Boot {
	
	public Boot(){
		//Call static method in Artist class to initialize OpenGL calls
		BeginSession();
		// main game loop
		while (!Display.isCloseRequested()) {
			Ora.update();
			StateManger.update();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	public static void main(String[] args){
		new Boot ();
	}
}
