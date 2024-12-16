package com.example.relaxationnetwork.services;

import com.example.relaxationnetwork.entities.Pixel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    @Value("${images.root.path}")
    private String imageStoragePath;

    private final BinaryService binaryService;

    public static int imageWidth;
    public static int imageHeight;
    public static int blackPixels = 0;
    public static int whitePixels = 0;

    private final double RED_RATE = 0.299;//0.2126;
    private final double GREEN_RATE = 0.587;//0.7152;
    private final double BLUE_RATE = 0.114;//0.0722;

    /*-------------------------------Работа с изображение-------------------------------------------------------------*/

    @SneakyThrows
    private BufferedImage readImage(MultipartFile image) {
        BufferedImage bufferedImage = ImageIO.read(image.getInputStream());

        imageWidth = bufferedImage.getWidth();

        imageHeight = bufferedImage.getHeight();

        return bufferedImage;
    }

    public BufferedImage restoreAndSaveImage(List<Integer> values) {
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                Color color = getColorByValue(values.get(y * imageWidth + x));

                image.setRGB(x, y, color.getRGB());
            }
        }

        saveBufferedImageToFile(image, "values");

        return image;
    }

    /*Only for test*/
    public BufferedImage restoreAndSaveImageByPixels(List<Pixel> values) {
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        for (Pixel pixel: values) {
            image.setRGB(pixel.getCoordX(), pixel.getCoordY(), new Color(pixel.getRedValue(), pixel.getGreenValue(), pixel.getBlueValue()).getRGB());
        }

        saveBufferedImageToFile(image, "pixels");

        return image;
    }

    @SneakyThrows
    private void saveBufferedImageToFile(BufferedImage image, String name) {
        File imageFile = new File(imageStoragePath + name + "_" + LocalDateTime.now() + ".png");
        ImageIO.write(image, "png", imageFile);
    }

    /*-------------------------------Работа со значениями-------------------------------------------------------------*/

    public List<Integer> getPixelValues(MultipartFile image) {
        BufferedImage bufferedImage = readImage(image);

        List<Pixel> pixels = getPixels(bufferedImage);

        //restoreAndSaveImageByPixels(pixels);

        List<Integer> values = new ArrayList<>();

        for (Pixel pixel: pixels) {
            boolean isPixelBlack = isPixelBlack(pixel);

            values.add(binaryService.getValue(isPixelBlack));
        }

        return values;
    }

    private Color getColorByValue(int value) {
        if (value == 1) {
            return new Color(0, 0, 0);
        }

        return new Color(255, 255, 255);
    }

    /*-------------------------------Работа с пикселями---------------------------------------------------------------*/

    private List<Pixel> getPixels(BufferedImage image) {
        List<Pixel> pixels = new ArrayList<>();

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                Color color = new Color(image.getRGB(x, y));

                //System.out.print("(" +x+"; " +y+") ");

                pixels.add(Pixel.builder()
                        .coordX(x)
                        .coordY(y)
                        .redValue(color.getRed())
                        .greenValue(color.getGreen())
                        .blueValue(color.getBlue())
                        .build());
            }
        }

        return pixels;
    }

    private boolean isPixelBlack(Pixel pixel) {
        double L = RED_RATE * pixel.getRedValue() + GREEN_RATE * pixel.getGreenValue() +
                BLUE_RATE * pixel.getBlueValue();

        if (L < 128) {
            blackPixels++;

            return true;
        }

        whitePixels++;

        return false;
    }
}
