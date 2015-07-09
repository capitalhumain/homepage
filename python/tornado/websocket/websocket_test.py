import tornado.httpserver
import tornado.websocket
import tornado.ioloop
import tornado.options
import tornado.web
import json

from tornado.options import define, options
define("port", default=8000, help="http run on the given port", type=int)
define("wsport", default=8888, help="websocket run on the given port", type=int)

class IndexHandler(tornado.web.RequestHandler):
  def get(self):
    print("IndexHandler called")
    self.render("index.html")

class EchoWebSocketHandler(tornado.websocket.WebSocketHandler):
  def open(self):
    print("WebSocket opened")
  
  def on_message(self, message):
    self.write_message(u"You said:" + message)

  def on_close(self):
    print("WebSocket closed")

if __name__ == "__main__":
  tornado.options.parse_command_line()
  # http
  http = tornado.web.Application(handlers=[(r"/index.html", IndexHandler)])
  http_server = tornado.httpserver.HTTPServer( http )
  http_server.listen( options.port )
  print("http server on {0}".format(options.port))
  # ws
  app = tornado.web.Application(handlers=[(r"/websocket", EchoWebSocketHandler)])
  ws_server = tornado.httpserver.HTTPServer( app )
  ws_server.listen( options.wsport )
  print("websocket server on {0}".format(options.wsport))
  tornado.ioloop.IOLoop.instance().start()
