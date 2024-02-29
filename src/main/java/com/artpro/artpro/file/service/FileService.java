package com.artpro.artpro.file.service;

import com.artpro.artpro.file.repository.FileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FileService {
    FileRepository fileRepository;

    public String create(MultipartFile file) {
        return fileRepository.save(file);
    }
}