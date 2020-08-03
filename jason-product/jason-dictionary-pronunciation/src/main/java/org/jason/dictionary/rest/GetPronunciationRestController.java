package org.jason.dictionary.rest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.apache.commons.io.IOUtils;

@RestController
public class GetPronunciationRestController {

    @GetMapping(value = "getPronunciation", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public @ResponseBody
    byte[] getPronunciation(@RequestParam String word) throws IOException, URISyntaxException {

        Objects.requireNonNull(word);

        String filePath = "/pronunciation/" + word + ".mp3";

        Path path = Paths.get(ClassLoader.getSystemResource(filePath).toURI());

        if (Files.notExists(path)) {
            return "No such file".getBytes();
        }

        InputStream in = getClass().getResourceAsStream(filePath);

        return IOUtils.toByteArray(in);
    }
}
