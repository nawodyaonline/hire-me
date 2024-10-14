package com.incognito.joblisting.repo;

import com.incognito.joblisting.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepo {

    List<Post> findByText(String text);
}
