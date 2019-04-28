package effects;

public class Delay extends Effect {

    public Delay(){
       super();
    }
    public void setInputSampleStream(short[] inputAudioStream) {
        this.inputAudioStream = inputAudioStream;
    }

    @Override
    public synchronized short[] createEffect() {
        short amplitude;
        short DelayAmplitude;
        int checkFlag;
        int delay = 5000;
        int position = 0;

        for(int i = delay ; i < this.inputAudioStream.length; i ++) {
            amplitude = this.inputAudioStream[i];
            DelayAmplitude = this.inputAudioStream[position];
            if (i > (this.inputAudioStream.length/2))
            {
                checkFlag = ( (( DelayAmplitude) - (int)(0.4 * amplitude)));
            }
            else
                checkFlag = ( (( DelayAmplitude) + (int)(0.9 * amplitude)));
            if(checkFlag < Short.MAX_VALUE && checkFlag > Short.MIN_VALUE) {
                DelayAmplitude = (short)checkFlag;
                this.inputAudioStream[position] =  DelayAmplitude;
                position += 1;
            }
        }
        return this.inputAudioStream;
    }

    @Override
    public synchronized short[] getOutputAudioStream() {
        return this.inputAudioStream;
    }

}
