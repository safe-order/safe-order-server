package kr.yz.safeorder.global.error.custom

interface CustomErrorProperty {
    fun status(): Int
    fun message(): String
}