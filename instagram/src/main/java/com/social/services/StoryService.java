package com.social.services;

import java.util.List;

import com.social.exception.StoryException;
import com.social.exception.UserException;
import com.social.model.Story;

public interface StoryService {

	public Story createStory(Story story,Integer userId) throws UserException;
	
	public List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException;
	
	
}
