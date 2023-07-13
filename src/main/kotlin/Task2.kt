data class User(
    val id: Long?,
    var firstName: String,
    var age: Int? = null,
    var lastName: String,
    var gender: String? = null,
    val address: List<Address>?
)

val listOfUser: MutableList<User> = mutableListOf()
var id: Long = 0

data class Address(
    val id: Long?, val streetName: String, val city: String
)

data class UserRequest(
    val firstName: String,
    val age: Int? = null,
    val lastName: String,
    val gender: String? = null,
    val addressRequest: List<AddressRequest>?
)

data class AddressRequest(val streetNameRequest: String, val cityRequest: String)

fun AddressRequest.toModel() = Address(id = id, streetName = streetNameRequest, city = cityRequest)

fun UserRequest.toModel() = User(id = ++id,
    firstName = firstName.uppercase(),
    age = age,
    gender = "Male",
    lastName = lastName.uppercase(),
    address = addressRequest?.map { it.toModel() })


//Create Function
fun create(userRequest: UserRequest): User {


    val userRequestModel = userRequest.toModel()
    listOfUser.add(userRequestModel)
    return userRequestModel

}

//Get Function
fun getAll() {
    listOfUser.forEach { println(it) }
}

//Get User Details where first Name is ?
fun get(firstName: String) {
    listOfUser.filter { it.firstName.lowercase().equals(firstName.lowercase()) }.forEach { println(it) }
}

//Update User Details where ID is ?
fun update(userRequest: UserRequest, userId: Long) {
    val userRequestModel = userRequest.toModel()

    listOfUser.forEach {
        if (it.id == userId) {
            it.firstName = userRequestModel.firstName
            it.lastName = userRequestModel.lastName
            it.age = userRequestModel.age
            it.gender = userRequestModel.gender
            println("Updated Sucessfully")
        }
    }
}

fun delete(id: Long) {

    listOfUser.forEach {
        if (it.id == id) {
            val result = listOfUser.remove(it)
            if (result) println("${it.firstName} with ID: ${it.id} Removed Sucessfully")
        }
    }

}

fun main() {
    var Fariz = UserRequest(
        firstName = "Fariz", lastName = "Umar", age = 25, gender = "Male", addressRequest = listOf(
            AddressRequest(streetNameRequest = "north Street", cityRequest = "Tuticorin"),
            AddressRequest(streetNameRequest = "South Street", cityRequest = "Chennai")
        )
    )
    create(Fariz)
    var lokesh = UserRequest(
        firstName = "Lokesh", lastName = "Reddy", age = 24, gender = "Male", addressRequest = listOf(
            AddressRequest(streetNameRequest = "north Street", cityRequest = "Tuticorin"),
            AddressRequest(streetNameRequest = "South Street", cityRequest = "Chennai")
        )
    )
    create(lokesh)
    println("-------CREATE USER--------")
    getAll()
    var updateLokesh = UserRequest(
        firstName = "Lokesh", lastName = "Shetty", age = 22, gender = "Male", addressRequest = listOf(
            AddressRequest(streetNameRequest = "north Street", cityRequest = "Tuticorin"),
            AddressRequest(streetNameRequest = "South Street", cityRequest = "Guindy, Chennai")
        )
    )
    println("-------GET USER--------")
    get("lokesh")
    println("-------UPDATE USER--------")
    update(updateLokesh, 2)
    getAll()
    println("-------DELETE USER--------")
    delete(5)
    getAll()


}
