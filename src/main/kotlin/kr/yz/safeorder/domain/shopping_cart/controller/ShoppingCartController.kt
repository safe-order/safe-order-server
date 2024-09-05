package kr.yz.safeorder.domain.shopping_cart.controller

import kr.yz.safeorder.domain.shopping_cart.service.ShoppingCartService
import kr.yz.safeorder.global.security.principle.franchise.CustomFranchiseDetails
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/shopping-cart")
class ShoppingCartController(
    private val shoppingCartService: ShoppingCartService
) {
    @PostMapping("")
    fun createShoppingCart(@AuthenticationPrincipal user: CustomFranchiseDetails, @RequestParam("product-id") productId: String) {
        shoppingCartService.create(user.username, productId)
    }

    @DeleteMapping("")
    fun deleteShoppingCart(@AuthenticationPrincipal user: CustomFranchiseDetails, @RequestParam("shopping-cart-id") productId: String) {
        shoppingCartService.delete(user.username, productId)
    }
}