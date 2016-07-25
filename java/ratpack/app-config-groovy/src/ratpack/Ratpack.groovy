import config.DatabaseConfig

import static groovy.json.JsonOutput.toJson
import static ratpack.groovy.Groovy.ratpack

//import java.nio.file.Paths

ratpack {
  serverConfig {
    json "dbconfig.json"
    json Class.getResource("/config/dbconfig.json")
    // json Paths.get("/etc/dbconfig.json")
    // yaml Paths.get("/etc/dbconfig.yaml")
    require("/database", DatabaseConfig)
  }
  handlers {
    get {
      render toJson([msg: "Hello, World!", status: true])
    }
    get("config") { DatabaseConfig config ->
      render toJson(config)
    }
  }
}
