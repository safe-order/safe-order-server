package kr.yz.safeorder.domain.shopping_cart.service

import io.viascom.nanoid.NanoId
import kr.yz.safeorder.domain.franchise.repository.FranchiseRepository
import kr.yz.safeorder.domain.product.repository.ProductRepository
import kr.yz.safeorder.domain.shopping_cart.ShoppingCartEntity
import kr.yz.safeorder.domain.shopping_cart.repository.ShoppingCartRepository
import org.springframework.stereotype.Service

@Service
class ShoppingCartService(
    private val shoppingCartRepository: ShoppingCartRepository,
    private val franchiseRepository: FranchiseRepository,
    private val productRepository: ProductRepository
) {
    fun create(franchiseId: String, productId: String) {
        val franchiseData = franchiseRepository.findById(franchiseId).orElseThrow {
            throw TODO("존재하지 않는 가맹점")
        }
        val productEntity = productRepository.findById(productId).orElseThrow {
            throw TODO("존재하지 않는 제품")
        }
        val shoppingCartEntity = ShoppingCartEntity(
            NanoId.generate(16),
            productEntity,
            franchiseData
        )
        shoppingCartRepository.save(shoppingCartEntity)
    }

    fun delete(franchiseId: String, shoppingCartId: String) {
        if(!franchiseRepository.existsById(franchiseId)) throw TODO("존재 하지 않는 가맹점")
        val shoppingCartEntity = shoppingCartRepository.findById(shoppingCartId).orElseThrow {
            throw TODO("존재 하지 않는 장바구니")
        }
        shoppingCartRepository.delete(shoppingCartEntity)
    }
}