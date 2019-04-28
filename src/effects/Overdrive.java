package effects;

public class Overdrive extends Effect {

	private short maxAmplitude;
	private short minAmplitude;
	private static final short standartMax = 2500;
	private static final short standartMin = -2500;
	private double coef;
	
	public Overdrive() {
		super(); 
		this.coef = 0.7;
	}
	
	public void setInputSampleStream(short[] inputAudioStream) {
		this.inputAudioStream = inputAudioStream;		
	}

	@Override
	public synchronized short[] createEffect() {
		this.setMaxAndMinAmpl();
		for(int i = 0; i < this.inputAudioStream.length; i++) {
		if(this.inputAudioStream[i] > this.maxAmplitude)
				this.inputAudioStream[i] = (this.maxAmplitude);
			else if(this.inputAudioStream[i] < this.minAmplitude)
				this.inputAudioStream[i] = (this.minAmplitude);
		}
		return this.inputAudioStream;
	}
	
	private void  setMaxAndMinAmpl() {
		this.maxAmplitude = (short) (Overdrive.standartMax * this.coef);
		this.minAmplitude = (short) (Overdrive.standartMin * this.coef);
	}
	
	public void setOverdriveCoef(double coef) {
		this.coef = coef;
	}

	@Override
	public synchronized short[] getOutputAudioStream() {
		return this.inputAudioStream;
	}
	
}