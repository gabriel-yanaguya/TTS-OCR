package ocr.ocr;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.googlecode.leptonica.android.Binarize;
import com.googlecode.tesseract.android.TessBaseAPI;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import jp.co.cyberagent.android.gpuimage.GPUImageSobelThresholdFilter;

/**
 * Created by Chan on 25/02/2017.
 */

public class Utils {


    public Bitmap adaptiveThreshold(Bitmap bitmap){
        Mat imageMat = new Mat();
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), false);
        org.opencv.android.Utils.bitmapToMat(newBitmap, imageMat);

        org.opencv.android.Utils.bitmapToMat(newBitmap, imageMat);
        Imgproc.cvtColor(imageMat, imageMat, Imgproc.COLOR_BGR2GRAY);
        Imgproc.adaptiveThreshold(imageMat, imageMat, 0, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, 11,2);
        org.opencv.android.Utils.matToBitmap(imageMat, newBitmap);

        return newBitmap;
    }

    public Bitmap proccessImageBeforeOCR(Bitmap bitmap, Mat imageMat){

        /*
        * http://stackoverflow.com/questions/17874149/how-to-use-opencv-to-process-image-so-that-the-text-become-sharp-and-clear
        * */
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), false);

        org.opencv.android.Utils.bitmapToMat(newBitmap, imageMat);
        Imgproc.cvtColor(imageMat, imageMat, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(imageMat, imageMat, 0, 255, Imgproc.THRESH_OTSU);

        org.opencv.android.Utils.matToBitmap(imageMat, newBitmap);

        return newBitmap;
    }

    public Mat cleanImage(Mat srcImage) {

        //Imgproc.threshold(srcImage, srcImage, 0, 255, Imgproc.THRESH_OTSU);
        Imgproc.erode(srcImage, srcImage, new Mat());
        Imgproc.dilate(srcImage, srcImage, new Mat(), new Point(0, 0), 9);

        return srcImage;
    }

    public Bitmap copyImage(Bitmap original){

        Bitmap newBitmap = original.copy(original.getConfig(), original.isMutable());
        return newBitmap;
    }

}
