package com.teosprint.flashcard.service.interfaces;

import com.teosprint.flashcard.dto.CardHashTagDto;

import java.util.List;

public interface CardHashtagService {

    List<String> getHashTagsContainsName(String name);
}
