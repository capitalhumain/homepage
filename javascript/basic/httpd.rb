require 'webrick'

options = {}
options[:BindAddress] = "localhost"
options[:Port] = 4000
options[:DocumentRoot] = "."

server = WEBrick::HTTPServer::new(options)

# trap ctrl-c to shutdown server or exit program
trap(:INT) do
  if server.respond_to?(:shutdown)
    server.shutdown
  else 
    exit
  end
end

server.start

