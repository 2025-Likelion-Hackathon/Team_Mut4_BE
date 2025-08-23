package team.mut4.trip.domain.wishlocationfoodbookmark.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.wishlocationfoodbookmark.application.WishLocationFoodBookMarkService;
import team.mut4.trip.domain.wishlocationfoodbookmark.dto.response.WishLocationFoodBookMarkSaveResponse;

@RequiredArgsConstructor
@RequestMapping("/wish-location-food-bookmarks")
@RestController
public class WishLocationFoodBookMarkController {

    private final WishLocationFoodBookMarkService wishLocationFoodBookMarkService;

    @PostMapping
    public ResponseEntity<WishLocationFoodBookMarkSaveResponse> saveWishLocationFoodBookMark(
            @RequestParam Long wishLocationId,
            @RequestParam Long foodId
    ) {
        WishLocationFoodBookMarkSaveResponse response = wishLocationFoodBookMarkService.saveWishLocationFoodBookMark(wishLocationId, foodId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
