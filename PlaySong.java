package musichub.business;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class PlaySong {
    LogWriter Logging = new LogWriter();
    String title_again;
    public static void playSound(String title) throws Exception {
        Scanner scan= new Scanner(System.in); //System.in is a standard input stream
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("./song/" + title + ".wav"));
        while(audioStream == null){
            System.out.println("This title does not exist, please tap a title again");
            title = scan.nextLine();
            audioStream = AudioSystem.getAudioInputStream(new File("./song/" + title + ".wav"));
        }
        int BUFFER_SIZE = 128000;
        AudioFormat audioFormat = null;
        SourceDataLine sourceLine = null;

        audioFormat = audioStream.getFormat();

        sourceLine = AudioSystem.getSourceDataLine(audioFormat);
        sourceLine.open(audioFormat);
        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead =
                        audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (nBytesRead >= 0) {
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }
}