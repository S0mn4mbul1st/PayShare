package com.payshare.server.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotificationReadDto {

    public Long id;
    public String title;
    public String description;
    public boolean isReviewed;

}
