

func sortEvenOdd(numbers: [Int]) -> (evens: [Int], odds: [Int]) {
  var evens = [Int]()
  var odds = [Int]()

  for number in numbers {
    if number % 2 == 0 {
      evens.append(number)
    } else {
      odds.append(number)
    }
  }
  return (evens, odds)
}

var sources: [Int] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
var evens: [Int]
var odds: [Int]
(evens, odds) = sortEvenOdd(sources)

print(evens)
print(odds)
