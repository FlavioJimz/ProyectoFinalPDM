package com.example.libritos.services.servicesImpl;

import com.example.libritos.models.DTOS.ReviewDTO;
import com.example.libritos.models.DTOS.SaveReviewDTO;
import com.example.libritos.models.entities.Book;
import com.example.libritos.models.entities.Review;
import com.example.libritos.repositories.ReviewRepository;
import com.example.libritos.services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServicesImpl implements ReviewServices {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public void save(SaveReviewDTO info) throws Exception {
        Review review = new Review(info.getUser(), info.getBook(), info.getRating(), info.getComment());

        reviewRepository.save(review);
    }

    @Override
    public List<ReviewDTO> getAllCommentsByBook(Book book) {
        List<Review> reviews = reviewRepository.findAllByBook(book);

        return reviews.stream()
                .map(review -> new ReviewDTO(review.getUser().getUsername(),review.getRating(),review.getComment()))
                .collect(Collectors.toList());

    }
}
