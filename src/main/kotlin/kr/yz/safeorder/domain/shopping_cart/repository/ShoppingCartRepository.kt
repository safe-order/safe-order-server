package kr.yz.safeorder.domain.shopping_cart.repository

import kr.yz.safeorder.domain.shopping_cart.ShoppingCartEntity
import org.springframework.data.repository.CrudRepository

interface ShoppingCartRepository: CrudRepository<ShoppingCartEntity, String>