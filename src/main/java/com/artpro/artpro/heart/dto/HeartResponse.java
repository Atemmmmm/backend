package com.artpro.artpro.heart.dto;

import lombok.Builder;

@Builder
public record HeartResponse(int likeCount, boolean isHeart, Long heartId) {
}