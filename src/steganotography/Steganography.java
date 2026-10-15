package steganotography;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Steganography {
    private Character STOP_CHARACTER = '*';
    private int BITS_PER_CHARACTER = 7;
    private int MAX_COLOR = 255;

    public BufferedImage hideMessage(BufferedImage original, String message) {
        int x = 0, y = 0;
        message += STOP_CHARACTER;

        BufferedImage result = new BufferedImage(original.getColorModel(), original.copyData(null), original.getColorModel().isAlphaPremultiplied(), null);

        if (message.length() * 7 > (result.getWidth() * result.getHeight())) {
            System.out.println("Not enough pixels in the image");
            return null;
        }

        for (char c: message.toCharArray()) {
            String bits = Integer.toBinaryString(c);
            while (bits.length() < BITS_PER_CHARACTER) {
                bits = "0" + bits;
            }
            for (char bit: bits.toCharArray()) {
                Color color = new Color(original.getRGB(x, y), true);
                if (bit == '0' && color.getRed() % 2 != 0) {
                    result.setRGB(x, y, new Color(color.getRed() - 1, color.getBlue(), color.getGreen()).getRGB());
                } else if (bit == '1' && color.getRed() % 2 == 0) {
                    result.setRGB(x, y, new Color(color.getRed() + 1, color.getBlue(), color.getGreen()).getRGB());
                }
                if (x + 1 == result.getWidth()) {
                    y += 1;
                    x = 0;
                } else {
                    x += 1;
                }
            }
        }

        return result;
    }

    public String getMessage(BufferedImage img) {
        String message = "";
        String temp = "";
        int x = 0;
        int y = 0;

        char lastCharacter = ' ';
        while (lastCharacter != '*') {
            int red = (img.getRGB(x, y) >> 16) & 0xff;
            temp += red % 2;
            if (temp.length() == 7) {
                lastCharacter = (char)Integer.parseInt(temp, 2);
                message += lastCharacter;
                temp = "";
            }

            if (x + 1 == img.getWidth()) {
                y += 1;
                x = 0;
            } else {
                x += 1;
            }
        }

        return message;
    }

    public static void main(String[] args) {
        String originalImageFileName = "pictures/baker.png";
        String hiddenImageFileName = "pictures/hidden.png"; //do not use lossy jpg format, corrupts message, use png
        String message = "Great job on this problem!  Good luck with the rest of the term. Now to make the message really crazy long so the message will have to go beyond a single line of pixels in the image.  This should be about enough. If the picture is only 800 pixels wide, about 115 characters would do the trick";

        //hide message in image
        System.out.println("Hiding message: " + message);
        BufferedImage image = ImageIOLibrary.loadImage(originalImageFileName);
        Steganography s = new Steganography();
        BufferedImage hiddenMessageImage = s.hideMessage(image, message);
        ImageGUI gui = new ImageGUI("SA-3  Can you tell the difference between images?",image, hiddenMessageImage);

        //save image with hidden message to disk
        ImageIOLibrary.saveImage(hiddenMessageImage, hiddenImageFileName,"png");

        //read image from disk and retrieve message from image
        BufferedImage img = ImageIOLibrary.loadImage(hiddenImageFileName);
        String recoveredMessage = s.getMessage(hiddenMessageImage);
        System.out.println("Recovered message: " + recoveredMessage);

    }
}