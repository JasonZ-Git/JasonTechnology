package org.jason.dictionary.rest;

import org.apache.commons.io.IOUtils;
import org.jason.dictionary.helper.GooglePronunciationHelper;
import org.jason.dictionary.helper.PronunciationCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@RestController
@RequestMapping("/get")
public class GetPronunciationRestController {

    @GetMapping(value = "/{word}", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    byte[] getPronunciation(@PathVariable String word) throws IOException, URISyntaxException {

        Objects.requireNonNull(word);
        Path path = null;
        if (PronunciationCache.exists(word)) {
            path = PronunciationCache.getPath(word);
        } else {
            path = GooglePronunciationHelper.downLoadGooglePronunciation(word);
        }

        InputStream in = Files.newInputStream(path);

        return IOUtils.toByteArray(in);
    }

    @GetMapping(value = "/countAll")
    public int count() {
        return PronunciationCache.count();
    }

}
