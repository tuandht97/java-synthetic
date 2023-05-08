package org.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class SteganographyImg {

    String ext;
    int encodedMsgOffset;

    //Method for encoding text into source image
    static boolean encode(BufferedImage input, BufferedImage output, int width, int height, String msg, String outputName) {


        int msgLength = msg.length(); //original message length
        //add overhead "!encoded!" to identify start of encoded message,
        //along with original message length which will instruct when to stop
        //the decoding process, once the encoded message has been extracted
        String message = "!encoded!" + msgLength + "!" + msg;
        msgLength = message.length(); //message length included the overhead

        //Creating an array of integers which will hold the entire message
        //in the form of bit format. A message is made up of characters,
        //each character is of the size of one byte. 1 byte = 8 bits.
        //Each byte here is divided into 4 parts of 2 bits, and stored in
        //the twoBitMessage array. Thus, the size of twoBitMessage is
        //4 times the length of the actual message.
        int[] twoBitMessage = new int[4 * msgLength];

        char currentChar;
        for (int i = 0; i < msgLength; i++) {
            currentChar = message.charAt(i); // extracting character at position i from string
            twoBitMessage[4 * i + 0] = (currentChar >> 6) & 0x3; //storing 1st and 2nd bit from the left
            twoBitMessage[4 * i + 1] = (currentChar >> 4) & 0x3; //storing 3rd and 4th bit from the left
            twoBitMessage[4 * i + 2] = (currentChar >> 2) & 0x3; //storing 5th and 6th bit from the left
            twoBitMessage[4 * i + 3] = (currentChar) & 0x3; //storing 7th and 8th bit from the left
        }

        int pixel, pixOut, count = 0;
        ;
        loop:
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (count < 4 * msgLength) { //ensuring that loop only iterates till the entire message has been encoded
                    pixel = input.getRGB(i, j); //Grab the RGB value from the pixel of the source image at position (i,j)

                    //Bit operator AND is used to convert the two LSB to zero.
                    //Once that is established, bit operator OR is used to copy
                    //the two bit message in place of these two LSB.
                    //Thus, we encode two bits of our message in one pixel.
                    //Effectively, 4 pixels, carrying 8 bits of encoded bits
                    //in total, will carry the information of one character.
                    pixOut = (pixel & 0xFFFFFFFC) | twoBitMessage[count++]; //modified RGB value with encoded data

                    output.setRGB(i, j, pixOut); //Set the modified RGB value at given pixel.

                } else {
                    break loop;
                }
            }

        }

        try {
            ImageIO.write(output, "png", new File(outputName)); //create .PNG file with encoded image data
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }// end of encode()


    static String decode(BufferedImage input, int width, int height) {

        if (!isEncoded(input, width, height)) {
            return null;
        }

        int msgLength = getEncodedLength(input, width, height);

        StringBuffer decodedMsg = new StringBuffer();
        Deque<Integer> listChar = new ArrayDeque<Integer>();

        int pixel, temp, charOut, ignore = 0, count = 0;
        loop:
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (ignore < 36 + 4 * (String.valueOf(msgLength).length() + 1)) {
                    ignore++;
                    continue;
                }

                if (count++ == 4 * msgLength) {
                    break loop;
                }
                pixel = input.getRGB(i, j); //grab RGB value at specified pixel
                temp = pixel & 0x03; //extract 2 LSB from encoded data

                listChar.add(temp); //add the extracted data to a queue for later processing

                if (listChar.size() >= 4) { //once we have 8 bits of data extracted
                    //combine them to create a byte, and store this byte as a character
                    charOut = (listChar.pop() << 6) | (listChar.pop() << 4) | (listChar.pop() << 2) | listChar.pop();
                    decodedMsg.append((char) charOut);
                }
            }

        }

        String outputMsg = new String(decodedMsg); //generate extracted message

        return outputMsg;
    } //end of decode()

    static boolean isEncoded(BufferedImage input, int width, int height) { //Check for "!encoded!" at starting

        StringBuffer decodedMsg = new StringBuffer();
        Deque<Integer> listChar = new ArrayDeque<Integer>();

        int pixel, temp, charOut, count = 0;
        loop:
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++, count++) {

                if (count == 45) { //remain in loop till first 9 characters are extracted
                    break loop;
                }
                pixel = input.getRGB(i, j); //grab RGB value at specified pixel
                temp = pixel & 0x03; //extract 2 LSB from encoded data

                listChar.add(temp); //add the extracted data to a queue for later processing

                if (listChar.size() >= 4) { //once we have 8 bits of data extracted
                    //combine them to create a byte, and store this byte as a character
                    charOut = (listChar.pop() << 6) | (listChar.pop() << 4) | (listChar.pop() << 2) | listChar.pop();
                    decodedMsg.append((char) charOut); //else add character to a StringBuffer
                    count++;
                }
            }
        }

        String check = new String(decodedMsg);
        System.out.println(check + " " + check.length());
        if (check.compareTo("!encoded!") == 0) {
            System.out.println("true");
            return true;
        } else {
            return false;
        }

    } //end of isEncoded() method

    static int getEncodedLength(BufferedImage input, int width, int height) { //method to get actual length of message encoded

        StringBuffer decodedMsg = new StringBuffer();
        Deque<Integer> listChar = new ArrayDeque<Integer>();

        int pixel, temp, charOut, count = 0;
        loop:
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (count < 36) { //ignore the 36 bits or 9 bytes, equal to "!encoded!"
                    count++;
                    continue;
                }

                pixel = input.getRGB(i, j); //grab RGB value at specified pixel
                temp = pixel & 0x03; //extract 2 LSB from encoded data

                listChar.add(temp); //add the extracted data to a queue for later processing

                if (listChar.size() >= 4) { //once we have 8 bits of data extracted
                    //combine them to create a byte, and store this byte as a character
                    charOut = (listChar.pop() << 6) | (listChar.pop() << 4) | (listChar.pop() << 2) | listChar.pop();
                    if ((char) charOut == '!') { //terminate process if character extracted is '!'
                        break loop;
                    } else {
                        decodedMsg.append((char) charOut); //else add character to a StringBuffer
                    }
                }
            }

        }

        String length = new String(decodedMsg);
        System.out.println("length is " + Integer.parseInt(length));

        return Integer.parseInt(length);
    } //end of getEncodedLength() method

    String getExt() {
        return ext;
    }

    int getOffset() {
        return encodedMsgOffset;
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\admin\\Downloads\\signatureTest.png";
        String msgEncode = "app.alert( {cMsg: 'this is an example', nIcon: 3,"
                + " nType: 0, cTitle: 'PDFBox Javascript example’} );";
        String savePath = "C:\\Users\\admin\\Downloads\\encodeSignature.png";
        encodeImageFromPath(path, msgEncode, savePath);
        //decodeImageFromPath("C:\\Users\\admin\\Downloads\\c1e49c41-3f1a-48cd-8600-23c77d10ae67-encoding-text-into-signature.png");
    }

    private static void decodeImageFromPath(String path) {
        try {
            File inputImage = new File(path);
            BufferedImage input = ImageIO.read(inputImage);
            int originalWidth = input.getWidth(null);
            int originalHeight = input.getHeight(null);
            String msg = decode(input, originalWidth, originalHeight);
            System.out.println("CCC  " + msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void encodeImageFromPath(String path, String msg, String savePath) {
        try {
            File inputImage = new File(path);
            BufferedImage input = ImageIO.read(inputImage);
            BufferedImage output = ImageIO.read(inputImage);

            int originalWidth = input.getWidth(null);
            int originalHeight = input.getHeight(null);

            boolean success = encode(input, output, originalWidth, originalHeight, msg, savePath);
            System.out.println(success);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
