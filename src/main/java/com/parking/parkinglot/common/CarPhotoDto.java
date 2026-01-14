package com.parking.parkinglot.common;

public class CarPhotoDto {
    private Long id;
    private String filename;
    private String fileType;
    private byte[] fileData;

    public CarPhotoDto(Long id, String filename, String fileType, byte[] fileData) {
        this.id = id;
        this.filename = filename;
        this.fileType = fileType;
        this.fileData = fileData;
    }

    public Long getId() {
        return id;
    }
    public String getFilename() {
        return filename;
    }
    public String getFileType() {
        return fileType;
    }
    public byte[] getFileContent() {
        return fileData;
    }

}
