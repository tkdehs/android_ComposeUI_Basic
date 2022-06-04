package com.pnx.composebasic.util

data class RandomUser(
    val name : String = "김상돈 입니다 ð",
    val discription: String = "오늘도 빡코딩 하고있는감?",
    val profileImage: String = "https://randomuser.me/api/portraits/women/32.jpg"
)

object DummyDataProvider {

    val userList = List<RandomUser>(200){ RandomUser() }
}