package org.jason.dictionary;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

@RestController
public class PronunciationRestController {

    @GetMapping(value = "getPronunciation")
    public String getPronunciation(@RequestParam String word) {
        return word;
    }

    @GetMapping(value = "getPronunciation2")
    public @ResponseBody String getPronunciation2(@RequestParam String word) {
        return word + " file";
    }

    @GetMapping(value = "getPronunciation3", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public @ResponseBody byte[] getPronunciation3(@RequestParam String word) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/pronunciation/"+word+".mp3");
        return IOUtils.toByteArray(in);
    }
}
