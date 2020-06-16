package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.BackpackEditor;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backpacks")
public class BackpackExporterEndpoint {
    private final BackpackEditor backpackEditor;

    public BackpackExporterEndpoint(BackpackEditor backpackEditor) {
        this.backpackEditor = backpackEditor;
    }

    @GetMapping("/{backpackId}/download")
    ResponseEntity<Backpack> downloadBackpack(@PathVariable Long backpackId) {
        Backpack backpack = backpackEditor.getBackpack(backpackId);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + backpack.getName())
                .body(backpack);
    }

//    public ResponseEntity<InputStreamResource> download(@PathVariable(name = "resourceIdentifier") final String filename) throws Exception
//    {
//        final String resourceName = filename + ".dat";
//        final File iFile = new File("/some/folder", resourceName);
//        final long resourceLength = iFile.length();
//        final long lastModified = iFile.lastModified();
//        final InputStream resource = new FileInputStream(iFile);
//
//        return ResponseEntity.ok()
//                .header("Content-Disposition", "attachment; filename=" + resourceName)
//                .contentLength(resourceLength)
//                .lastModified(lastModified)
//                .contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE)
//                .body(resource);
//    }
}
