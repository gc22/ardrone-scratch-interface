import scratch.ScratchInterface;

import com.shigeodayo.ardrone.ARDrone;
import com.shigeodayo.ardrone.ARDroneInterface;


public class ScratchArdroneController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	public ScratchArdroneController(){
	}
	
	private static ScratchInterface getScratch() {
		// TODO Auto-generated method stub
		return new ScratchInterface() {
		};
	}

	private static ARDroneInterface getDrone() {
		return new TesterARDrone();
	}
}
