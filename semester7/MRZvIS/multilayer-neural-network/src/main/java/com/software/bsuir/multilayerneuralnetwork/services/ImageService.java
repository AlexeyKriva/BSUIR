/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичес
Вариант 1: Реализовать модель линейной рециркуляционной сети с постоянным коэффициентом обучения с ненормированными весами
*/
package com.software.bsuir.multilayerneuralnetwork.services;

import com.software.bsuir.multilayerneuralnetwork.entities.ImageVector;
import com.software.bsuir.multilayerneuralnetwork.entities.Pixel;
import com.software.bsuir.multilayerneuralnetwork.entities.ImageRectangle;
import com.software.bsuir.multilayerneuralnetwork.exceptions.FileIsNotImageException;
import com.software.bsuir.multilayerneuralnetwork.exceptions.ImageRectangleException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.software.bsuir.multilayerneuralnetwork.exceptions.ExceptionMessage.FILE_IS_NOT_IMAGE;
import static com.software.bsuir.multilayerneuralnetwork.exceptions.ExceptionMessage.IMAGE_RECTANGLE_MESSAGE;

@Service
public class ImageService {
    private static int numRows;
    private static int numCols;
    public static int imageHeight;
    public static int imageWidth;

    public void initRectangleSize(int userNumRows, int userNumCols) {
        numRows = userNumRows;
        numCols = userNumCols;
    }

    public static final int MAX_VALUE_OF_RGB = 255;
    public final static int NUMBER_OF_COMPONENTS_IN_PIXEL = 3;
    private final static String TOTAL_PATH_TO_IMAGE =
            "/Users/aliaksei/Desktop/BSUIR/semester7/MRZvIS/multilayer-neural-network/images/image";

    //--------------------------------------Operations with image--------------------------------------

    @SneakyThrows
    private BufferedImage readImage(MultipartFile image) {
        BufferedImage bufferedImage = ImageIO.read(image.getInputStream());

        imageWidth = bufferedImage.getWidth();

        imageHeight = bufferedImage.getHeight();

        return bufferedImage;
    }

    @SuppressWarnings("unused")
    public BufferedImage buildImageToFile(List<ImageRectangle> imageRectangles) {
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        for (ImageRectangle imageRectangle: imageRectangles) {
            for (List<Pixel> pixels: imageRectangle.getPixels()) {
                for (Pixel pixel: pixels) {
                    int red = (int) Math.abs((pixel.getRedValue() + 1) * MAX_VALUE_OF_RGB / 2) % MAX_VALUE_OF_RGB;
                    int green = (int) Math.abs((pixel.getGreenValue() + 1) * MAX_VALUE_OF_RGB / 2) % MAX_VALUE_OF_RGB;
                    int blue = (int) Math.abs((pixel.getBlueValue() + 1) * MAX_VALUE_OF_RGB / 2) % MAX_VALUE_OF_RGB;

                    Color color = new Color(red, green, blue);

                    image.setRGB(pixel.getCoordX(), pixel.getCoordY(), color.getRGB());
                }
            }
        }

        saveBufferedImageToFile(image);

        return image;
    }

    @SneakyThrows
    private void saveBufferedImageToFile(BufferedImage image) {
        File imageFile = new File(TOTAL_PATH_TO_IMAGE + "_" + LocalDateTime.now() + ".bmp");
        ImageIO.write(image, "png", imageFile);
    }

    //--------------------------------------This is methods to rectangles--------------------------------------

    @SuppressWarnings("all")
    public List<ImageRectangle> splitImageToRectangles(MultipartFile image) {
        BufferedImage imageReader = readImage(image);

        if (imageReader == null) {
            throw new FileIsNotImageException(FILE_IS_NOT_IMAGE);
        }

        return buildPixelRectangles(imageReader, numRows, numCols);
    }

    public List<ImageRectangle> buildPixelRectangles(BufferedImage imageReader, Integer numRows, Integer numCols) {
        List<ImageRectangle> imageRectangles = new ArrayList<>();

        for (int coordY = 0; coordY < imageReader.getHeight(); coordY += numRows) {
            boolean extremeRectangleIsBuilt = false;

            for (int coordX = 0; coordX < imageReader.getWidth(); coordX += numCols) {
                if (coordX + numCols > imageReader.getWidth()) {
                    coordX = imageReader.getWidth() - numCols;
                    extremeRectangleIsBuilt = true;
                }

                if (coordY + numRows > imageReader.getHeight()) {
                    coordY = imageReader.getHeight() - numRows;
                }

                List<List<Pixel>> pixels = findPixelsForRectangle(imageReader, coordX, coordY);

                imageRectangles.add(
                        ImageRectangle.builder()
                                .leftCoordX(coordX)
                                .leftCoordY(coordY)
                                .numRows(numRows)
                                .numCols(numCols)
                                .pixels(pixels)
                                .build()
                );

                if (extremeRectangleIsBuilt) {
                    break;
                }
            }
        }

        return imageRectangles;
    }

    private List<List<Pixel>> findPixelsForRectangle(BufferedImage imageReader, int coordX, int coordY) {
        List<List<Pixel>> pixels = new ArrayList<>();

        for (int rectCoordY = coordY; rectCoordY < coordY + numRows; rectCoordY++) {
            pixels.add(new ArrayList<>());

            for (int rectCoordX = coordX; rectCoordX < coordX + numCols; rectCoordX++) {
                Color color = new Color(imageReader.getRGB(rectCoordX, rectCoordY));

                pixels.get(pixels.size() - 1).add(
                        Pixel.builder()
                                .redValue(color.getRed())
                                .greenValue(color.getGreen())
                                .blueValue(color.getBlue())
                                .coordX(rectCoordX)
                                .coordY(rectCoordY)
                                .build()
                );
            }
        }

        return pixels;
    }

    public ImageRectangle buildReferenceValues(MultipartFile image) {
        BufferedImage bufferedImage = readImage(image);

        List<ImageRectangle> imageRectangles = buildPixelRectangles(bufferedImage, imageHeight, imageWidth);

        if (imageRectangles.size() == 1) {
            return imageRectangles.get(0);
        }

        throw new ImageRectangleException(IMAGE_RECTANGLE_MESSAGE);
    }

    public List<ImageRectangle> buildPredicted(List<ImageVector> imageVectors) {
        List<ImageRectangle> imageRectangles = new ArrayList<>();

        for (ImageVector imageVector: imageVectors) {
            List<Pixel> pixels = imageVector.getPixelsVector();

            ImageRectangle imageRectangle = ImageRectangle.builder()
                    .numRows(numRows)
                    .numCols(numCols)
                    .pixels(new ArrayList<>())
                    .build();

            List<List<Pixel>> rectanglePixels = new ArrayList<>();
            rectanglePixels.add(new ArrayList<>());

            for (int pixelIndex = 0; pixelIndex < pixels.size(); pixelIndex++) {
                if (pixelIndex == 0) {
                    imageRectangle.setLeftCoordX(pixels.get(pixelIndex).getCoordX());
                    imageRectangle.setLeftCoordY(pixels.get(pixelIndex).getCoordY());
                }

                if (pixelIndex == numCols) {
                    rectanglePixels.add(new ArrayList<>());
                }

                rectanglePixels.get(rectanglePixels.size() - 1).add(pixels.get(pixelIndex));
            }

            imageRectangle.setPixels(rectanglePixels);

            imageRectangles.add(imageRectangle);
        }

        return imageRectangles;
    }

    //--------------------------------------This is methods to vectors--------------------------------------

    public List<ImageVector> splitImageIntoVectors(MultipartFile image) {
        List<ImageVector> imageVectors = new ArrayList<>();

        List<ImageRectangle> imageRectangles = splitImageToRectangles(image);

        for (ImageRectangle imageRectangle : imageRectangles) {
            ImageVector imageVector = buildImageVectorFromImageRectangle(imageRectangle);

            imageVectors.add(imageVector);
        }

        return imageVectors;
    }

    private ImageVector buildImageVectorFromImageRectangle(ImageRectangle imageRectangle) {
        ImageVector colorVector = new ImageVector();

        for (int numRow = 0; numRow < imageRectangle.getNumRows(); numRow++) {
            for (int numCol = 0; numCol < imageRectangle.getNumCols(); numCol++) {
                Pixel currentPixel = imageRectangle.getPixels().get(numRow).get(numCol);

                colorVector.addPixel(
                        Pixel.builder()
                                .coordX(currentPixel.getCoordX())
                                .coordY(currentPixel.getCoordY())
                                .redValue(2 * currentPixel.getRedValue() / MAX_VALUE_OF_RGB - 1)
                                .greenValue(2 * currentPixel.getGreenValue() / MAX_VALUE_OF_RGB - 1)
                                .blueValue(2 * currentPixel.getBlueValue() / MAX_VALUE_OF_RGB - 1)
                                .build()
                );
            }
        }

        return colorVector;
    }
}