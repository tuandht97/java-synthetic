package org.example;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.zip.*;

public class DownloadAndZipLinux {

    // A method to download a file from a given URL and save it to a temporary folder
    public static File downloadFile(String fileURL) throws IOException {

        // Create a temporary folder
        Path tempDir = Files.createTempDirectory("download");
        // Get the file name from the URL

        String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
        // Create a file object in the temporary folder

        File file = new File(tempDir.toFile(), fileName);

        // Open a connection to the URL
        URL url = new URL(fileURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Get the input stream from the connection
        InputStream inputStream = connection.getInputStream();

        // Create a buffered reader to read from the input stream
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // Create a buffered writer to write to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        // Read and write line by line until the end of the stream
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }

        // Close the reader, writer and connection
        reader.close();
        writer.close();
        connection.disconnect();

        // Return the file object
        return file;
    }

    // A method to zip a folder and save it to the same location using Linux command line
    public static void zipFolder(File folder) throws IOException {
        // Get the folder name and path
        String folderName = folder.getName();
        String folderPath = folder.getParent();

        // Create a zip file name in the same location as the folder
        String zipFileName = folderName + ".zip";

        // Create a command string to zip the folder using Linux zip command
        String command = "zip -r " + zipFileName + " " + folderName;

        // Create a process builder object to execute the command
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

        // Set the working directory of the process builder to the folder path
        processBuilder.directory(new File(folderPath));

        // Start the process and wait for it to finish
        Process process = processBuilder.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // A method to open a file in a folder using Linux command line
    public static void openFile(File file) throws IOException {
        // Get the file name and path
        String fileName = file.getName();
        String filePath = file.getParent();
        // Create a command string to open the file using Linux xdg-open command
        String command = "xdg-open " + fileName;
        // Create a process builder object to execute the command
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        // Set the working directory of the process builder to the file path
        processBuilder.directory(new File(filePath));
        // Start the process and do not wait for it to finish
        Process process = processBuilder.start();
    }

    // A method to zip a file and save it to the same location using Linux command line
    public static void zipFile(File file) throws IOException {
        // Get the file name and path
        String fileName = file.getName();
        String filePath = file.getParent();
        // Create a zip file name in the same location as the file
        String zipFileName = fileName + ".zip";
        // Create a command string to zip the file using Linux zip command
        String command = "zip " + zipFileName + " " + fileName;
        // Create a process builder object to execute the command
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        // Set the working directory of the process builder to the file path
        processBuilder.directory(new File(filePath));
        // Start the process and wait for it to finish
        Process process = processBuilder.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
