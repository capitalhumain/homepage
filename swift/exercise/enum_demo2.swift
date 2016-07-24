enum ProgrammingLanguage : String {
  case Swift
  case ObjectiveC = "Objective-C"
  case C
  case Cpp = "C++"
  case Java
  case Python
  case Ruby
  case Go = "Go Language"
}

let myFavoriteLanguage = ProgrammingLanguage.Swift

print("My favorite programming language is \(myFavoriteLanguage.rawValue)")
