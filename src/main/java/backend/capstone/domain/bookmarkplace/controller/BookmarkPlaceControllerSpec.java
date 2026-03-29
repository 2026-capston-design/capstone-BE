package backend.capstone.domain.bookmarkplace.controller;

import backend.capstone.auth.dto.UserPrincipal;
import backend.capstone.domain.bookmarkplace.dto.BookmarkPlaceCreateRequest;
import backend.capstone.domain.bookmarkplace.dto.BookmarkPlaceCreateResponse;
import backend.capstone.domain.bookmarkplace.dto.BookmarkPlaceListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "즐겨찾기 장소 API")
public interface BookmarkPlaceControllerSpec {

    @Operation(
        summary = "즐겨찾기 장소 목록 조회 API",
        description = "사용자의 즐겨찾기 장소 목록과 전체 개수를 조회합니다."
    )
    BookmarkPlaceListResponse getBookmarkPlaces(UserPrincipal principal);

    @Operation(
        summary = "즐겨찾기 장소 생성 API",
        description = """
            type 필드의 값은 HOME/COMPANY/SCHOOL/ETC 중 하나를 선택해 주세요.
            """
    )
    BookmarkPlaceCreateResponse createBookmarkPlace(
        UserPrincipal principal,
        BookmarkPlaceCreateRequest request
    );
}
