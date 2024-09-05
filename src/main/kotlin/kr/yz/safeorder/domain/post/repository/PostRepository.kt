package kr.yz.safeorder.domain.post.repository

import kr.yz.safeorder.domain.post.PostEntity
import org.springframework.data.repository.CrudRepository

interface PostRepository: CrudRepository<PostEntity, String> {
}