package kr.yz.safeorder.domain.product.repository

import kr.yz.safeorder.domain.product.ProductEntity
import org.springframework.data.repository.CrudRepository

interface ProductRepository: CrudRepository<ProductEntity, String>