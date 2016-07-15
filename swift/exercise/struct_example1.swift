struct Player {
  let name: String
  let surname: String
  let age: Int
  let instrument: String
}

let bassPlayer = Player(name: "Paul", surname: "McCartney", age: 27, instrument: "bass")
let guitarPlayer = Player(name: "John", surname: "Lennon", age: 29, instrument: "guitar")

print(bassPlayer.name)
print(guitarPlayer.surname)
