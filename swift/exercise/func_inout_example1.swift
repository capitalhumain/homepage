var error = "The request failed:"

func appendErrorCode(code: Int, inout toErrorString errorString: String) {
  if code == 400 {
    errorString += " bad request."
  }
}

appendErrorCode(400, toErrorString: &error)

print(error)
