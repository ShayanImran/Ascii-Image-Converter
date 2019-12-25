import java.awt.*;
import java.io.File;
import java.io.PrintStream;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;

public class Main
{
    static String ASCIIChars = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";
    static double maxPixelValue = 256;

    // Compulsory to start openCV
    static
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args)
    {

        //Instantiating ImageModifier Class
        ImageModifier imageModifier = new ImageModifier();

        //Opens Windows file explorer for selection
        Frame frame = new Frame();
        FileDialog fileExplorer = new FileDialog(frame, "Choose an Image", FileDialog.LOAD);
        fileExplorer.setDirectory("C:");
        fileExplorer.setVisible(true);
        String imagePath = fileExplorer.getDirectory() + fileExplorer.getFile();
        
        if (imagePath == null)
            System.out.println("You cancelled the choice");
        else
            System.out.println("You chose " + imagePath);

        imageModifier.loadImage(imagePath);
        imageModifier.getRGB();
        imageModifier.getPixelBrightness();
        imageModifier.brightnessConversion(ASCIIChars, maxPixelValue);
        imageModifier.printImage();

        System.exit(0 );

  }
}

