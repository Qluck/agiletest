package agiletest.service;

import agiletest.entity.Media;

import java.util.List;

public interface MediaService {
    List<Media> getMediaByPage(Integer pageNumber);
}
