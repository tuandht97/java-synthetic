package org.example;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MinioDownload {

    private void zipTempFile() {
        // Create a temp file
        File tempFile = null;
        try {
            tempFile = File.createTempFile("MyFile", ".txt");
            System.out.println("Temp file created: " + tempFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write some data to the temp file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("Hello world!");
            writer.newLine();
            writer.write("This is a temp file.");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a zip file
        File zipFile = new File("MyZip.zip");
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile))) {
            // Add an entry to the zip file
            ZipEntry zipEntry = new ZipEntry(tempFile.getName());
            zipOut.putNextEntry(zipEntry);
            // Read the data from the temp file
            try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    // Write the data to the zip output stream
                    zipOut.write(line.getBytes());
                    zipOut.write('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Delete the temp file
        if (tempFile.delete()) {
            System.out.println("Temp file deleted.");
        } else {
            System.out.println("Failed to delete temp file.");
        }
    }

    private static String getFileNameFromPath(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }

    public static void main(String[] args) {
        try {
            // Create a minio client object with the server URL, access key and secret key
            MinioClient minioClient = MinioClient.builder().endpoint("http://sidoc-staging-api.demo2.siten.vn:43890")
                    .credentials("minioadmin", "minioadmin").build();
            // Specify the bucket name
            String bucketName = "de7ae573-ae7a-464d-a3e5-1f9d60fda903";

            // Create an array of file paths to download
            String[] filePaths = {
                    "documents/20230518/0004daef-4e4c-40e3-ac73-0a7353338237_Test2.pdf",
                    "documents/20230518/f71e741e-1023-4f1a-be05-fee7207d1d6d_Test1.pdf"
            };

            // Create a temp folder to store the downloaded files
            Path tempFolder = Files.createTempDirectory("minio-files");
            File tempDir = tempFolder.toFile();

            // Iterate over the array of file paths
            for (String filePath : filePaths) {
                // Download the file from the server using getObject method and save it to the temp folder

                InputStream input = minioClient.getObject(GetObjectArgs
                        .builder()
                        .bucket(bucketName)
                        .object(filePath)
                        .build());

                // Create a File object for the file
                String fileName = getFileNameFromPath(filePath);
                File file = new File(tempDir, fileName);

                // Write the file data to the temp folder using a BufferedInputStream
                BufferedInputStream bis = new BufferedInputStream(input);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int read = 0;
                while ((read = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, read);
                }
                fos.flush();
                fos.close();
                bis.close();
            }

//            // Create a ByteArrayOutputStream to store the zip file
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ZipOutputStream zos = new ZipOutputStream(baos);
            // Create a ByteArrayOutputStream to store the zip file
            File zipFile = new File("D:\\MyZip.zip");
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));

            // Loop through the files in the temp folder and add them to the zip file
            for (File file : tempDir.listFiles()) {
                // Create a ZipEntry for the file
                ZipEntry entry = new ZipEntry(file.getName());

                // Put the entry into the ZipOutputStream
                zos.putNextEntry(entry);

                // Read the file data and write it to the ZipOutputStream
                Files.copy(file.toPath(), zos);

                // Close the entry
                zos.closeEntry();
            }

            // Finish writing the zip file
            zos.finish();
            zos.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("File saved successfully");
    }
}
