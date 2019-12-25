import java.awt.*;
import java.io.PrintStream;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;

public class ImageModifier
{
    //Instantiating ImageCodecs Class
    Imgcodecs imageCodecs = new Imgcodecs();

    // Necessary attributes to keep track of
    Mat image;
    double[][][] pixelArray;
    int [][] brightnessMatrix;
    char[][] ASCII_MATRIX;

    // Loads image
    public void loadImage(String imagePath)
    {
        // Creates Mata object and loads image
        Mat image = imageCodecs.imread(imagePath);
        this.image = image;

        System.out.println("Image Loaded");
        System.out.println("Image size: " + image.rows() + " Pixel rows " + image.cols() + " Pixel columns "  );
    }

    // Gets pixel RGB values
    public void getRGB()
    {
        // Creates array to hold rgb values
         double[] rgb = image.get(0,0);
         pixelArray = new double[image.rows()][image.cols()][3];

        //Loads  the pixels into 3D array
        for (int i = 0;i<image.rows();i++)
            for (int j = 0; j<image.cols();j++)
                pixelArray[i][j] = image.get(i,j);
    }

    // Calculates the average brightness of each pixel from their RGB values
    public void getPixelBrightness()
    {
        brightnessMatrix= new int[image.rows()][image.cols()];
        // Turns RGB values into average "brightness" of each pixel in image

        for (int i = 0;i<image.rows();i++)
            for (int j = 0; j<image.cols();j++)
                brightnessMatrix[i][j] = (int)((pixelArray[i][j][0] + pixelArray[i][j][1] + pixelArray[i][j][2]) / 3);
    }

    // Compares each pixels "brightness" to a string of ASCII values and determines which character will represent the pixel
    public void brightnessConversion(String ASCIIChars, double maxPixelValue)
    {
        ASCII_MATRIX = new char[image.rows()][image.cols()];

        for (int i = 0;i<image.rows();i++)
            for (int j = 0; j<image.cols();j++)
                ASCII_MATRIX[i][j] = ASCIIChars.charAt((int) ((brightnessMatrix[i][j] / maxPixelValue) * ASCIIChars.length()));
    }

    // Prints final output to console
    public void printImage()
    {
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
    }

}
