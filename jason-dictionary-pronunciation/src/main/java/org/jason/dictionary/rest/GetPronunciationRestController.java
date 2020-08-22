package org.jason.dictionary.rest;

import org.jason.dictionary.helper.GooglePronunciationHelper;
import org.jason.dictionary.helper.PronunciationCache;
import org.jason.util.dictionary.JasonDictionaryAPI;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.IOUtils;

@RestController
public class GetPronunciationRestController {

    @GetMapping(value = "getPronunciation", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public @ResponseBody
    byte[] getPronunciation(@RequestParam String word) throws IOException, URISyntaxException {

        Objects.requireNonNull(word);
        Path path = null;
        if (PronunciationCache.exists(word)){
            path = PronunciationCache.getPath(word);
        } else {
            path = GooglePronunciationHelper.downLoadGooglePronunciation(word);
        }

        InputStream in = Files.newInputStream(path);

        return IOUtils.toByteArray(in);
    }
}
