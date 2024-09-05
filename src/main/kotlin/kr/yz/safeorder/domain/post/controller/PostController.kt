package kr.yz.safeorder.domain.post.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kr.yz.safeorder.domain.post.service.PostService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "notice", description = "notice 생성 삭제 api")
@RestController
@RequestMapping(value = ["/api/notice"])
class PostController(
    private val postService: PostService
) {
    @Operation(
        summary = "게시글 생성",
        description = "게시글 만들기"
    )
    @ApiResponse(
        responseCode = "201",
        description = "게시글 생성 성공"
    )
    @PostMapping("/create")
    fun createNotice(){

    }

    @Operation(
        summary = "게시글 삭제",
        description = "게시글 삭제 하기"
    )
    @ApiResponse(
        responseCode = "200",
        description = "게시글 삭제 성공"
    )
    @DeleteMapping("/delete")
    fun deleteNotice(){

    }
}