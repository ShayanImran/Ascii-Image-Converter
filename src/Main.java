import java.awt.*;
import java.io.File;
import java.io.PrintStream;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;


public class Main{
    // Compulsory to start openCV
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        String ASCIIChars = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";
        double maxPixelValue = 256;

        //Instantiating ImageCodecs Class
        Imgcodecs imageCodecs = new Imgcodecs();

        //Opens Windows file explorer
        Frame frame = new Frame();
        FileDialog fileExplorer = new FileDialog(frame, "Choose an Image", FileDialog.LOAD);
        fileExplorer.setDirectory("C:");
        fileExplorer.setVisible(true);
        String imagePath = fileExplorer.getDirectory() + fileExplorer.getFile();
        if (imagePath == null)
            System.out.println("You cancelled the choice");

        else
            System.out.println("You chose " + imagePath);


        //Loads Image
        Mat image = imageCodecs.imread(imagePath);
        System.out.println("Image Loaded");
        System.out.println("Image size: " + image.rows() + " Pixel rows " + image.cols() + " Pixel columns "  );

        //Gets pixel RGB values
        double[] rgb = image.get(0,0);
        System.out.println("red: " + rgb[0] + " Green: " + rgb[1] + " Blue: " + rgb[2]);

        double[][][] pixelArray = new double[image.rows()][image.cols()][3];

        //Loads  the pixels into array
        for (int i = 0;i<image.rows();i++)
        {
            for (int j = 0; j<image.cols();j++)
            {
                pixelArray[i][j] = image.get(i,j);
            }
        }

        //Testing output of pixelArray
      /*
        for (int i = 0;i<image.rows();i++)
        {
            for (int j = 0; j<image.cols();j++)
            {
                    System.out.println(pixelArray[i][j][0] + "," + pixelArray[i][j][1] + "," + pixelArray[i][j][2]);
                }
            }
    */

        // Turns RGB values into average "brightness" of each pixel in image
        int [][] brightnessMatrix= new int[image.rows()][image.cols()];
        for (int i = 0;i<image.rows();i++)
        {
            for (int j = 0; j<image.cols();j++)
            {
                brightnessMatrix[i][j] = (int) ((pixelArray[i][j][0] + pixelArray[i][j][1] + pixelArray[i][j][2] ) / 3);
            }
        }

     // Iterates through each brightness
/*
        for (int i = 0;i<image.rows();i++)
        {
            for (int j = 0; j<image.cols();j++)
            {
                System.out.println(brightnessMatrix[i][j]);
            }

        }

*/

                char[][] ASCII_MATRIX = new char[image.rows()][image.cols()];
                for (int i = 0;i<image.rows();i++)
                {
                    for (int j = 0; j<image.cols();j++)
                    {
                        ASCII_MATRIX[i][j] = ASCIIChars.charAt((int) ((brightnessMatrix[i][j] / maxPixelValue) * ASCIIChars.length()));
            }
        }


        for (int i = 0; i < image.rows() ; i++)
        {
            for (int j = 0; j <image.cols(); j++)
            {
                System.out.print(ASCII_MATRIX[i][j]);
                System.out.print(ASCII_MATRIX[i][j]);
                System.out.print(ASCII_MATRIX[i][j]);
            }
            System.out.println();
        }

        System.exit(0 );


  }
}

