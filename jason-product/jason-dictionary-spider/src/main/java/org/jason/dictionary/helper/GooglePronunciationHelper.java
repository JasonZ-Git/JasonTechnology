package org.jason.dictionary.helper;

import com.google.common.io.Files;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;

public class GooglePronunciationHelper {

    private static final Logger logger = LogManager.getLogger();

    private static final String DOWNLOAD_VIDEO_FORMAT = "downloadvedio -o %s '%s'";

    public static byte[] getPronunciation(String word) {
        TranslationResult translation = GoogleTranslationHelper.getTranslation(word);

        return getPronunciationFromUrl(translation.getWord(), translation.getPronounceURL());
    }

    public static byte[] getPronunciationFromUrl(String word, URL pronounceUrl) {

        ProcessBuilder processBuilder = new ProcessBuilder();

        File tempDir = Files.createTempDir();

        String tempMP3File = tempDir + "/" + word + ".mp3";

        downloadMp3File(tempMP3File, pronounceUrl);

        return readMp3File(tempMP3File);
    }

    private static byte[] readMp3File(String filePath) {

        try {
            InputStream in = new FileInputStream(filePath);
            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            logger.error(e);
            return null;
        }
    }

    private static void downloadMp3File(String tempMP3File, URL pronounceUrl) {
        String downloadVideoCommand = String.format(DOWNLOAD_VIDEO_FORMAT, tempMP3File, pronounceUrl);

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("sh", "-c", downloadVideoCommand);

        try {
            Process process = processBuilder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
        } catch (IOException e) {
            logger.error(e);
        }
    }

}
