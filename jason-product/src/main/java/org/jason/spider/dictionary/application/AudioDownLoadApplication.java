package org.jason.spider.dictionary.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.Application;
import org.jason.annotation.Unfinished;
import org.jason.spider.dictionary.DictionaryConstants;
import org.jason.util.finalclass.StringPair;
import org.jason.util.JasonFileUtil;

@Unfinished(todo = "Load Audio should be taken out as a util")
@Application(name = "Translation Application")
public class AudioDownLoadApplication {

  private static final Logger logger = LogManager.getLogger();

  private static final String DOWNLAOD_VEDIO_FORMAT = "downloadvedio -o %s%s.mp3 '%s'";

  public static void main(String[] args) throws IOException {
    downloadAudio();
  }

  @Unfinished(todo = "Use Multiple Threading to download files")
  private static void downloadAudio() throws IOException {
    List<String> pronounceList = JasonFileUtil.readFile(DictionaryConstants.PRONOUNCE_FILE);

    List<String> existingMP3Files = getExistingMP3Files();

    List<StringPair> pronouceToLoad = pronounceList.parallelStream().map(item -> toPair(item)).filter(item -> !existingMP3Files.contains(item.getLeft())).collect(Collectors.toList());

    pronouceToLoad.parallelStream().limit(8000).forEach(item -> downLoadSingleAudio(item));
  }

  private static StringPair toPair(String pronouceLine) {
    int splitPos = pronouceLine.indexOf("=");

    return StringPair.of(pronouceLine.substring(0, splitPos), pronouceLine.substring(splitPos + 1));
  }

  private static void downLoadSingleAudio(StringPair item) {
    String word = item.getLeft();
    String pronounceUrl = item.getRight();

    ProcessBuilder processBuilder = new ProcessBuilder();

    String downloadVedioCommand = String.format(DOWNLAOD_VEDIO_FORMAT, DictionaryConstants.PRONOUNCE_MP3_DIR, word, pronounceUrl);
    processBuilder.command("sh", "-c", downloadVedioCommand);

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

  private static List<String> getExistingMP3Files() throws IOException {
    Path pronounceDir = Paths.get(DictionaryConstants.PRONOUNCE_MP3_DIR);

    List<String> mp3Files = Files.walk(pronounceDir).filter(Files::isRegularFile).map(item -> item.getFileName().toString()).filter(item -> item.endsWith(".mp3")).map(item -> item.replace(".mp3", ""))
        .collect(Collectors.toList());

    return mp3Files;
  }
}
