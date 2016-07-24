enum TextAlignment: Int {
  case Left = 20
  case Right = 30
  case Center = 40
  case Justify = 50
}

let myRawValue = 20

if let myAlighment = TextAlignment(rawValue: myRawValue) {
  print("successfully converted \(myRawValue) into a TextAlignment")
} else {
  print("\(myRawValue) has no corresponding TextAlignment case")
}
