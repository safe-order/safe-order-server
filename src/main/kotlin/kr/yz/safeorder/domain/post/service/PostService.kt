package kr.yz.safeorder.domain.post.service

import kr.yz.safeorder.domain.post.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {
}