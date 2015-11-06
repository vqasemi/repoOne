package com.boomapp.app.dto;

/**
 * @author mohsen
 * @since 11/6/2015.
 */
public class AutoTransferRequestDto {
    Object context;

    AutoTransferDto autoTransferDto;

    public AutoTransferRequestDto() {
    }

    public AutoTransferRequestDto(AutoTransferDto autoTransferDto) {
        this.autoTransferDto = autoTransferDto;
    }
}
