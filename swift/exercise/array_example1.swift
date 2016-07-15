var bucketList: [String] = ["Climb Mt. Everest", "Fly hot air ballon to Fiji"]

// in swift 3 deprecate i++ and c-style for syntax
/*
for var i=0; i<bucketList.count; i++ {
  print(bucketList[i])
}
*/

for i in 0..<bucketList.count {
  print(bucketList[i])
}
