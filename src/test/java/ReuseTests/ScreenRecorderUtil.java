package ReuseTests;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.Frame;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
//org.bytedeco.ffmpeg.global.avcodec: Imports constants related to codecs used in video encoding/decoding.
//org.bytedeco.javacv.FrameRecorder: Imports the FrameRecorder class, which is responsible for recording video frames.
//org.bytedeco.javacv.Java2DFrameConverter: Imports a converter to transform BufferedImage objects into Frame objects for recording.
//org.bytedeco.javacv.Frame: Imports the Frame class, which represents a single video frame.
//javax.swing.*: Imports classes from the Swing library for GUI components, like dialog boxes.
//java.awt.*: Imports classes from the AWT library for graphical components, including screen dimensions and images.
//java.awt.image.BufferedImage: Imports the BufferedImage class, which represents an image with an accessible buffer of image data.

public class ScreenRecorderUtil {
        private FrameRecorder recorder; //handle the video recording
        private Robot robot; //used for screen capturing
        private Dimension screenSize;
        private Rectangle captureArea;//Declares a variable to define the area of the screen to capture

        public ScreenRecorderUtil(String fileName) throws Exception {
            // Initialize screen size and capture area
            // Uses the AWT Toolkit class to get the current screen size
            screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            //Initializes a Rectangle object with the dimensions of the screen, defining the area to capture
            captureArea = new Rectangle(screenSize);

            // Initialize FrameRecorder
            recorder = FrameRecorder.createDefault(fileName, screenSize.width, screenSize.height);
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // Set video codec to H.264
            recorder.setFormat("mp4"); // Set the output format to MP4
            recorder.setFrameRate(30); // Set the frame rate
            // Set video bit rate for higher quality (e.g., 4000kbps)
            recorder.setVideoBitrate(4000000); // 4 Mbps, adjust for your needs

            // Set the pixel format for better quality (YUV for H.264)
            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
            recorder.start();
            // Create a Robot instance for screen capturing
            robot = new Robot();
        }

        public void startRecording() throws Exception {
            while (true) {
                // Capture screen
                BufferedImage screen = robot.createScreenCapture(captureArea);
                // Convert BufferedImage to Frame
                Frame frame = new Java2DFrameConverter().convert(screen);
                // Record the frame
                recorder.record(frame);
            }
        }

        public void stopRecording() throws Exception {
            recorder.stop();
            recorder.release();
        }

    }


